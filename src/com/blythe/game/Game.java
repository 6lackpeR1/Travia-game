package com.blythe.game;

import java.util.ArrayList;
import java.util.LinkedList;

public class Game {
	ArrayList players = new ArrayList();
	int[] places = new int[6];
	int[] purses = new int[6];
	boolean[] inPenaltyBox = new boolean[6];

	int currentPlayer = 0;
	boolean isGettingOutOfPenaltyBox;
	QuestionsSupplier questionsSupplier;

	public Game() {
		this(new QuestionsSupplierImpl());
	}

	public Game(QuestionsSupplier questionsSupplier) {
		this.questionsSupplier = questionsSupplier;
	}

	public String createRockQuestion(int index) {
		return "Rock Question " + index;
	}

	public boolean isPlayable() {
		return (howManyPlayers() >= 2);
	}

	public boolean add(String playerName) {

		players.add(playerName);
		places[howManyPlayers()] = 0;
		purses[howManyPlayers()] = 0;
		inPenaltyBox[howManyPlayers()] = false;

		System.out.println(playerName + " was added");
		System.out.println("They are player number " + players.size());
		return true;
	}

	public int howManyPlayers() {
		return players.size();
	}

	public void roll(int roll) {
		System.out.println(players.get(currentPlayer)
				+ " is the current player");
		System.out.println("They have rolled a " + roll);

		if (inPenaltyBox[currentPlayer]) {
			if (roll % 2 != 0) {
				isGettingOutOfPenaltyBox = true;

				System.out.println(players.get(currentPlayer)
						+ " is getting out of the penalty box");
				places[currentPlayer] = places[currentPlayer] + roll;
				if (places[currentPlayer] > 11)
					places[currentPlayer] = places[currentPlayer] - 12;

				System.out.println(players.get(currentPlayer)
						+ "'s new location is " + places[currentPlayer]);
				System.out.println("The category is " + currentCategory());
				askQuestion();
			} else {
				System.out.println(players.get(currentPlayer)
						+ " is not getting out of the penalty box");
				isGettingOutOfPenaltyBox = false;
			}

		} else {

			places[currentPlayer] = places[currentPlayer] + roll;
			if (places[currentPlayer] > 11)
				places[currentPlayer] = places[currentPlayer] - 12;

			System.out.println(players.get(currentPlayer)
					+ "'s new location is " + places[currentPlayer]);
			System.out.println("The category is " + currentCategory());
			askQuestion();
		}

	}

	private void askQuestion() {
		String currentCategory = currentCategory();
		askQuestion(currentCategory);
	}

	// visibleForTesting
	void askQuestion(String currentCategory) {
		questionsSupplier.askQuestion(currentCategory);
	}

	private String currentCategory() {
		int place = places[currentPlayer];
		return currentCategory(place);
	}

	// visibleForTesting
	String currentCategory(int place) {
		return questionsSupplier.currentCategory(place);
	}

	public boolean wasCorrectlyAnswered() {
		if (inPenaltyBox[currentPlayer]) {
			if (isGettingOutOfPenaltyBox) {
				System.out.println("Answer was correct!!!!");
				purses[currentPlayer]++;
				System.out.println(players.get(currentPlayer) + " now has "
						+ purses[currentPlayer] + " Gold Coins.");

				boolean winner = didPlayerWin();
				currentPlayer++;
				if (currentPlayer == players.size())
					currentPlayer = 0;

				return winner;
			} else {
				currentPlayer++;
				if (currentPlayer == players.size())
					currentPlayer = 0;
				return true;
			}

		} else {

			System.out.println("Answer was corrent!!!!");
			purses[currentPlayer]++;
			System.out.println(players.get(currentPlayer) + " now has "
					+ purses[currentPlayer] + " Gold Coins.");

			boolean winner = didPlayerWin();
			currentPlayer++;
			if (currentPlayer == players.size())
				currentPlayer = 0;

			return winner;
		}
	}

	public boolean wrongAnswer() {
		System.out.println("Question was incorrectly answered");
		System.out.println(players.get(currentPlayer)
				+ " was sent to the penalty box");
		inPenaltyBox[currentPlayer] = true;

		currentPlayer++;
		if (currentPlayer == players.size())
			currentPlayer = 0;
		return true;
	}

	private boolean didPlayerWin() {
		return !(purses[currentPlayer] == 6);
	}

}
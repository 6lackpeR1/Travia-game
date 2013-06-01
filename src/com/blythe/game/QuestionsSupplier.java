package com.blythe.game;

import java.util.LinkedList;

public class QuestionsSupplier {

	LinkedList popQuestions = new LinkedList();
	LinkedList scienceQuestions = new LinkedList();
	LinkedList sportsQuestions = new LinkedList();
	LinkedList rockQuestions = new LinkedList();

	public QuestionsSupplier() {
		for (int i = 0; i < 50; i++) {
			popQuestions.addLast("Pop Question " + i);
			scienceQuestions.addLast(("Science Question " + i));
			sportsQuestions.addLast(("Sports Question " + i));
			rockQuestions.addLast(createRockQuestion(i));
		}
	}

	public String createRockQuestion(int index) {
		return "Rock Question " + index;
	}

	public void askQuestion(String currentCategory) {
		if (currentCategory == "Pop")
			System.out.println(popQuestions.removeFirst());
		if (currentCategory == "Science")
			System.out.println(scienceQuestions.removeFirst());
		if (currentCategory == "Sports")
			System.out.println(sportsQuestions.removeFirst());
		if (currentCategory == "Rock")
			System.out.println(rockQuestions.removeFirst());
	}

	public String currentCategory(int place) {
		if (place == 0)
			return "Pop";
		if (place == 4)
			return "Pop";
		if (place == 8)
			return "Pop";
		if (place == 1)
			return "Science";
		if (place == 5)
			return "Science";
		if (place == 9)
			return "Science";
		if (place == 2)
			return "Sports";
		if (place == 6)
			return "Sports";
		if (place == 10)
			return "Sports";
		return "Rock";
	}

}

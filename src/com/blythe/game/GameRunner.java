package com.blythe.game;

import java.util.Random;

public class GameRunner {

	private static boolean notAWinner;

	public static void main(String[] args) {
		Game game = new Game();
		game.add("Chet");
		game.add("Pat");
		game.add("Sue");
		Random rand = new Random();

		do {
			game.roll(rand.nextInt(5) + 1);
			if (rand.nextInt(9) == 7) {
				notAWinner = game.wrongAnswer();
			} else {
				notAWinner = game.wasCorrectlyAnswered();
			}
		} while (notAWinner);

	}
}

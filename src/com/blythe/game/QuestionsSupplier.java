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
		// TODO Auto-generated method stub

	}

	public Object currentCategory(int place) {
		// TODO Auto-generated method stub
		return null;
	}

}

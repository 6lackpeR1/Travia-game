package com.blythe.game;

import static org.junit.Assert.*;

import java.util.LinkedList;

import org.junit.Before;
import org.junit.Test;

public class GameSpec {
	private Game game;

	static class QuestionsSupplierMock implements QuestionsSupplier {
		private String currentCategory;
		private int place;

		@Override
		public void askQuestion(String currentCategory) {
			this.currentCategory = currentCategory;
		}

		@Override
		public String currentCategory(int place) {
			this.place = place;
			return null;
		}

		public String getAskCategory() {
			return currentCategory;
		}

		public int getCurrentCategoryWithPlace() {
			return place;
		}

	}

	@Before
	public void before() {
		game = new Game();
	}

	@Test
	public void gameInstantiatedWithSupplierParameter() {
		QuestionsSupplier questionsSupplier = new QuestionsSupplierImpl();
		game = new Game(questionsSupplier);
		assertEquals(game.questionsSupplier, questionsSupplier);

	}

	@Test
	public void gameInstantiatedWithAllQuestion() {
		QuestionsSupplier questionsSupplier = new QuestionsSupplierImpl();
		game = new Game(questionsSupplier);
		assertEquals(game.popQuestions.size(), 50);
		assertEquals(game.scienceQuestions.size(), 50);
		assertEquals(game.sportsQuestions.size(), 50);
		assertEquals(game.rockQuestions.size(), 50);
	}

	@Test
	public void askQuestionShouldCallAskQuestionSupplier() {
		QuestionsSupplierMock questionsSupplierMock = new QuestionsSupplierMock();
		game = new Game(questionsSupplierMock);
		game.askQuestion("Pop");
		assertEquals(questionsSupplierMock.getAskCategory(), "Pop");
	}

	@Test
	public void askQuestionShouldCallCurrentCategorySupplier() {
		QuestionsSupplierMock questionsSupplierMock = new QuestionsSupplierMock();
		int place = 2;
		game = new Game(questionsSupplierMock);
		game.currentCategory(place);
		assertEquals(questionsSupplierMock.getCurrentCategoryWithPlace(), place);
	}

	private void assertAskQuestionRemoveFromCurrentCategory(
			LinkedList questions, String currentCategory) {
		int beforeSize = questions.size();
		Object secondQuest = questions.get(1);

		game.askQuestion(currentCategory);
		assertEquals(questions.size(), beforeSize - 1);
		assertEquals(questions.get(0), secondQuest);
	}

	@Test
	public void currentCategoryShouldReturnPOPSpacificPlace() {
		assertEquals(game.currentCategory(0), "Pop");
		assertEquals(game.currentCategory(4), "Pop");
		assertEquals(game.currentCategory(8), "Pop");
	}

	@Test
	public void currentCategoryShouldReturnScienceSpacificPlace() {
		assertEquals(game.currentCategory(1), "Science");
		assertEquals(game.currentCategory(5), "Science");
		assertEquals(game.currentCategory(9), "Science");
	}

	@Test
	public void currentCategoryShouldReturnSportsSpacificPlace() {
		assertEquals(game.currentCategory(2), "Sports");
		assertEquals(game.currentCategory(6), "Sports");
		assertEquals(game.currentCategory(10), "Sports");
	}

	@Test
	public void currentCategoryShouldReturnRockMoreThen10() {
		assertEquals(game.currentCategory(11), "Rock");
		assertEquals(game.currentCategory(100), "Rock");
		assertEquals(game.currentCategory(1000), "Rock");
	}

	@Test
	public void currentCategoryShouldReturnRockLessThen0() {
		assertEquals(game.currentCategory(-1), "Rock");
		assertEquals(game.currentCategory(-100), "Rock");
		assertEquals(game.currentCategory(-1000), "Rock");
	}
}

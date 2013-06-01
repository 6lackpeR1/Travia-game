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

}

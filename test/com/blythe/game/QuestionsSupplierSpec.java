package com.blythe.game;

import static org.junit.Assert.*;

import java.util.LinkedList;

import org.junit.Before;
import org.junit.Test;

public class QuestionsSupplierSpec {

	private QuestionsSupplierImpl questionsSupplier;

	@Before
	public void before() {
		questionsSupplier = new QuestionsSupplierImpl();
	}

	@Test
	public void askQuestionShouldRemovePopFromPopCategory() {
		LinkedList questions = questionsSupplier.popQuestions;
		String currentCategory = "Pop";

		assertAskQuestionRemoveFromCurrentCategory(questions, currentCategory);
	}

	@Test
	public void askQuestionShouldRemoveScienceFromScienceCategory() {
		LinkedList questions = questionsSupplier.scienceQuestions;
		String currentCategory = "Science";

		assertAskQuestionRemoveFromCurrentCategory(questions, currentCategory);
	}

	@Test
	public void askQuestionShouldRemoveSportsFromSportsCategory() {
		LinkedList questions = questionsSupplier.sportsQuestions;
		String currentCategory = "Sports";

		assertAskQuestionRemoveFromCurrentCategory(questions, currentCategory);
	}

	@Test
	public void askQuestionShouldRemoveRockFromRockCategory() {
		LinkedList questions = questionsSupplier.rockQuestions;
		String currentCategory = "Rock";

		assertAskQuestionRemoveFromCurrentCategory(questions, currentCategory);
	}

	private void assertAskQuestionRemoveFromCurrentCategory(
			LinkedList questions, String currentCategory) {
		int beforeSize = questions.size();
		Object secondQuest = questions.get(1);

		questionsSupplier.askQuestion(currentCategory);
		assertEquals(questions.size(), beforeSize - 1);
		assertEquals(questions.get(0), secondQuest);
	}

	@Test
	public void currentCategoryShouldReturnPOPSpacificPlace() {
		assertEquals(questionsSupplier.currentCategory(0), "Pop");
		assertEquals(questionsSupplier.currentCategory(4), "Pop");
		assertEquals(questionsSupplier.currentCategory(8), "Pop");
	}

	@Test
	public void currentCategoryShouldReturnScienceSpacificPlace() {
		assertEquals(questionsSupplier.currentCategory(1), "Science");
		assertEquals(questionsSupplier.currentCategory(5), "Science");
		assertEquals(questionsSupplier.currentCategory(9), "Science");
	}

	@Test
	public void currentCategoryShouldReturnSportsSpacificPlace() {
		assertEquals(questionsSupplier.currentCategory(2), "Sports");
		assertEquals(questionsSupplier.currentCategory(6), "Sports");
		assertEquals(questionsSupplier.currentCategory(10), "Sports");
	}

	@Test
	public void currentCategoryShouldReturnRockMoreThen10() {
		assertEquals(questionsSupplier.currentCategory(11), "Rock");
		assertEquals(questionsSupplier.currentCategory(100), "Rock");
		assertEquals(questionsSupplier.currentCategory(1000), "Rock");
	}

	@Test
	public void currentCategoryShouldReturnRockLessThen0() {
		assertEquals(questionsSupplier.currentCategory(-1), "Rock");
		assertEquals(questionsSupplier.currentCategory(-100), "Rock");
		assertEquals(questionsSupplier.currentCategory(-1000), "Rock");
	}
}

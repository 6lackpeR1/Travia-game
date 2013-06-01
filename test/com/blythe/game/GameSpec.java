package com.blythe.game;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class GameSpec {
	private Game game;

	@Before
	public void before() {
		game = new Game();
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

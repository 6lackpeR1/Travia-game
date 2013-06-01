package com.blythe.game;

public interface QuestionsSupplier {
	
	public void askQuestion(String currentCategory);
	public String currentCategory(int place);
}

package com.kata.tennis.services;

public interface PlayerServices {

	String getPlayerName();

	int getPlayerScore();

	boolean winAPoint();

	void setScoreGame(int scoreGame);
}

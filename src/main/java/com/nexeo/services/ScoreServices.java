package com.nexeo.services;

public interface ScoreServices {
	int showScore();

	boolean score();

	void init();

	int getScoreSet();

	boolean setScoreSet(int scoreSet);

	boolean setScoreGame(int scoreGame);

	void scoreTie();
}

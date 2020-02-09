package com.nexeo.services;

public interface GameServices extends DualServices {

	boolean playerScore(int index);

	boolean scoreIsDeuce();

	void resetGame();
}

package com.kata.tennis.services;

import java.util.Optional;

import com.kata.tennis.models.Player;

public interface DualServices {
	Player getPlayerOne();

	Player getPlayerTwo();

	Optional<Player> getWinner();

	String getCurrentScore();
}

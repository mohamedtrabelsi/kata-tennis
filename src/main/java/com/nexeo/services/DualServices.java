package com.nexeo.services;

import java.util.Optional;
import com.nexeo.kata.models.Player;

public interface DualServices {
	Player getPlayerOne();

	Player getPlayerTwo();

	Optional<Player> getWinner();

	String getCurrentScore();
}

package com.nexeo.kata.models;

import java.util.Optional;
/**
 * 
 * @author Mohamed TRABELSI
 *
 */
public class Dual {

	private final Player playerOne;
	private final Player playerTwo;
	private boolean winnnerOfDual = false;	
	private Player winner;
	
	public boolean isWinnnerOfDual() {
		return winnnerOfDual;
	}

	public void setWinnnerOfDual(boolean winnnerOfDual) {
		this.winnnerOfDual = winnnerOfDual;
	}

	public Dual(Player playerOne, Player playerTwo) {
		this.playerOne = playerOne;
		this.playerTwo = playerTwo;
	}

	public Player getPlayerOne() {
		return playerOne;
	}

	public Player getPlayerTwo() {
		return playerTwo;
	}

	public Optional<Player> getWinner() {
		if (this.winner == null)
			return Optional.empty();
		return Optional.of(this.winner);
	}

	public void setWinner(Player winner) {
		this.winner = winner;
	}
}

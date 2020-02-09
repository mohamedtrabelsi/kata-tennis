package com.nexeo.kata.models;

import com.nexeo.kata.services.GameServices;

public class Game extends Dual implements GameServices {
	private static final int MAX_SCORE = 40;

	public Game(Player playerOne, Player playerTwo) {
		super(playerOne, playerTwo);
	}

	public static Game initGame(String namePlayerOne, String namePlayerTwo) {
		Player playerOne = new Player(namePlayerOne);
		Player playerTwo = new Player(namePlayerTwo);

		return new Game(playerOne, playerTwo);
	}

	public void resetGame() {
		this.getPlayerOne().setScoreGame(0);
		this.getPlayerTwo().setScoreGame(0);
		this.setWinnnerOfDual(false);
		super.setWinner(null);
	}

	public boolean playerScore(int index) {

		if (isWinnnerOfDual())
			return false;

		if (index == 0) {
			if (this.getPlayerTwo().isHasAdvantage()) {
				this.getPlayerTwo().setHasAdvantage(false);
				return true;
			}

			if (scoreIsDeuce()) {
				this.getPlayerOne().setHasAdvantage(true);
				this.getPlayerTwo().setHasAdvantage(false);
				return true;
			}

			if (this.getPlayerOne().getPlayerScore() == MAX_SCORE || this.getPlayerOne().isHasAdvantage()) {
				setWinner(this.getPlayerOne());
				this.setWinnnerOfDual(true);
				this.getPlayerOne().winSet();
			}

			return this.getPlayerOne().winAPoint();
		} else {

			if (this.getPlayerOne().isHasAdvantage()) {
				this.getPlayerOne().setHasAdvantage(false);
				return true;
			}

			if (scoreIsDeuce()) {
				this.getPlayerTwo().setHasAdvantage(true);
				this.getPlayerOne().setHasAdvantage(false);
				return true;
			}

			if (this.getPlayerTwo().getPlayerScore() == MAX_SCORE || this.getPlayerTwo().isHasAdvantage()) {
				setWinner(this.getPlayerTwo());
				this.setWinnnerOfDual(true);
				this.getPlayerTwo().winSet();
			}
			return this.getPlayerTwo().winAPoint();
		}
	}

	public boolean scoreIsDeuce() {
		return (!this.getPlayerOne().isHasAdvantage() && !this.getPlayerTwo().isHasAdvantage())
				&& (this.getPlayerOne().getPlayerScore() == MAX_SCORE
						&& this.getPlayerTwo().getPlayerScore() == MAX_SCORE);
	}

	@Override
	public String getCurrentScore() {

		if (this.scoreIsDeuce()) {
			return this.getPlayerOne().getPlayerName() + " : DEUCE - DEUCE : " + this.getPlayerTwo().getPlayerName();
		}

		if (super.getPlayerOne().isHasAdvantage()) {
			return this.getPlayerOne().getPlayerName() + " : ADVANTAGE " + " - " + this.getPlayerTwo().getPlayerScore()
					+ " : " + this.getPlayerTwo().getPlayerName();

		}

		if (super.getPlayerTwo().isHasAdvantage()) {
			return this.getPlayerOne().getPlayerName() + " : " + this.getPlayerOne().getPlayerScore()
					+ " - ADVANTAGE : " + this.getPlayerTwo().getPlayerName();

		}

		return this.getPlayerOne().getPlayerName() + " : " + this.getPlayerOne().getPlayerScore() + " - "
				+ this.getPlayerTwo().getPlayerScore() + " : " + this.getPlayerTwo().getPlayerName();
	}

}

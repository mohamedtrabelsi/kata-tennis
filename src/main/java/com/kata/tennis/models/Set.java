package com.kata.tennis.models;

import com.kata.tennis.services.SetServices;

/**
 * 
 * @author Mohamed TRABELSI
 *
 */
public class Set extends Dual implements SetServices {

	private boolean tieBreak;

	public static Set initSet(String namePlayerOne, String namePlayerTwo) {
		Player playerOne = new Player(namePlayerOne);
		Player playerTwo = new Player(namePlayerTwo);

		return new Set(playerOne, playerTwo);
	}

	public Set(Player playerOne, Player playerTwo) {
		super(playerOne, playerTwo);
	}

	@Override
	public String getCurrentScore() {
		return this.getPlayerOne().getPlayerName() + " : " + this.getPlayerOne().getScoreSet() + " - "
				+ this.getPlayerTwo().getScoreSet() + " : " + this.getPlayerTwo().getPlayerName();
	}

	@Override
	public boolean isHaveWinner() {
		return false;
	}

	@Override
	public boolean playerWinSet(int index) {
		if (isHaveWinner())
			return false;
		if (index == 0) {
			if (tieBreak)
				return tieRule(this.getPlayerOne(), this.getPlayerTwo());

			if (this.getPlayerOne().getScoreSet() == 5 && this.getPlayerTwo().getScoreSet() == 5) {
				tieBreak = true;

			} else if (winRuleOne(this.getPlayerOne(), this.getPlayerTwo())) {

				this.getPlayerOne().winSet();
				setWinnnerOfDual(true);
				this.setWinner(this.getPlayerOne());

				return false;
			}

			return this.getPlayerOne().winSet();
		} else {

			if (tieBreak)
				return tieRule(this.getPlayerTwo(), this.getPlayerOne());

			if (this.getPlayerTwo().getScoreSet() == 5 && this.getPlayerOne().getScoreSet() == 5) {
				tieBreak = true;
			} else if (winRuleOne(this.getPlayerTwo(), this.getPlayerOne())) {
				this.getPlayerTwo().winSet();
				setWinnnerOfDual(true);
				this.setWinner(this.getPlayerTwo());
				return false;
			}
			return this.getPlayerTwo().winSet();
		}

	}

	private static boolean winRuleOne(Player playerOne, Player playerTwo) {

		return playerTwo.getScoreSet() <= 4 && playerOne.getScoreSet() == 5;
	}

	private boolean tieRule(Player playerOne, Player playerTwo) {

		if (playerOne.getScoreSet() == playerTwo.getScoreSet() + 1) {
			playerOne.winSet();
			this.setWinner(playerOne);
			this.setWinnnerOfDual(true);
			return false;
		}

		return playerOne.scoreTie();
	}

}

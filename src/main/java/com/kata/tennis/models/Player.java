package com.kata.tennis.models;

import com.kata.tennis.services.PlayerServices;
import com.kata.tennis.services.ScoreServices;

/** 
 * @author Mohamed TRABELSI
 */
public class Player implements PlayerServices {
	private String name;
	private ScoreServices scoreService;
	private boolean hasAdvantage;

	public Player(String name) {
		this.name = name;
		this.scoreService = new Score();
	}

	public String getPlayerName() {
		return this.name;
	}

	public int getPlayerScore() {
		return scoreService.showScore();
	}

	public boolean winAPoint() {
		return scoreService.score();
	}

	@Override
	public void setScoreGame(int scoreGame) {
		this.scoreService.setScoreGame(scoreGame);
	}

	public boolean isHasAdvantage() {
		return hasAdvantage;
	}

	public void setHasAdvantage(boolean hasAdvantage) {
		this.hasAdvantage = hasAdvantage;
	}

	public boolean winSet() {
		return this.scoreService.setScoreSet(this.scoreService.getScoreSet() + 1);
	}

	public int getScoreSet() {
		return this.scoreService.getScoreSet();
	}

	public boolean scoreTie() {
		this.scoreService.scoreTie();
		return true;
	}
}

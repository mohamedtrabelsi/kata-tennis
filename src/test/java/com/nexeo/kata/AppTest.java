package com.nexeo.kata;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;

import com.kata.tennis.models.Game;
import com.kata.tennis.models.Player;
import com.kata.tennis.models.Set;
import com.kata.tennis.services.GameServices;
import com.kata.tennis.services.SetServices;

public class AppTest {

	private GameServices gameService;
	private SetServices setService;
	private Player playerOne;
	private Player playerTwo;

	@Before
	@DisplayName("Initializing services")
	public void init() {
		setService = Set.initSet("PLAYER1", "PLAYER2");
		playerOne = setService.getPlayerOne();
		playerTwo = setService.getPlayerTwo();
		gameService = new Game(playerOne, playerTwo);

	}

	@Test
	@DisplayName("Scenario one of player wins complete scenario")
	public void shouldTestIfOnePlayerWinsTheGame() {
		assertEquals(gameService.getCurrentScore(), "PLAYER1 : 0 - 0 : PLAYER2");
		gameService.playerScore(0);
		gameService.playerScore(0);
		gameService.playerScore(0);
		gameService.playerScore(1);
		gameService.playerScore(1);
		gameService.playerScore(1);
		// DEUCE RULE
		assertTrue(gameService.scoreIsDeuce());
		assertEquals(gameService.getCurrentScore(), "PLAYER1 : DEUCE - DEUCE : PLAYER2");
		// ADVANTAGE EACH PLAYER
		gameService.playerScore(0);
		gameService.playerScore(1);
		assertTrue(gameService.scoreIsDeuce());
		gameService.playerScore(1);
		assertEquals(gameService.getCurrentScore(), "PLAYER1 : 40 - ADVANTAGE : PLAYER2");
		assertFalse(gameService.getWinner().isPresent());
		// ONE OF PLAYER SHOULD WIN THE GAME
		gameService.playerScore(1);
		assertTrue(gameService.getWinner().isPresent());
		assertEquals(gameService.getWinner().get().getPlayerName(), playerTwo.getPlayerName());
	}

	@Test
	@DisplayName("Scenario one of player wins the set")
	public void shouldPlayerWinTheSet() {
		assertEquals(setService.getCurrentScore(), "PLAYER1 : 0 - 0 : PLAYER2");
		setService.playerWinSet(0);
		setService.playerWinSet(0);
		setService.playerWinSet(0);
		assertEquals(setService.getCurrentScore(), "PLAYER1 : 3 - 0 : PLAYER2");
		setService.playerWinSet(0);
		setService.playerWinSet(0);
		setService.playerWinSet(0);
		assertTrue(setService.getWinner().isPresent());
		assertEquals(setService.getWinner().get().getPlayerName(), "PLAYER1");
	}

	@Test
	@DisplayName("If Tie Break then one of player wins")
	public void  shouldPlayerWinTheMatchIfTieBreak() {
		assertEquals(setService.getCurrentScore(), "PLAYER1 : 0 - 0 : PLAYER2");
		
		setService.playerWinSet(0);
		setService.playerWinSet(0);
		setService.playerWinSet(0);
		setService.playerWinSet(1);
		setService.playerWinSet(1);
		setService.playerWinSet(1);
		
		assertEquals(setService.getCurrentScore(), "PLAYER1 : 3 - 3 : PLAYER2"); 
		
		setService.playerWinSet(1);
		setService.playerWinSet(1); 
		setService.playerWinSet(0);
		setService.playerWinSet(0); 
		setService.playerWinSet(1); 
		
		assertFalse(setService.isHaveWinner());
		
		setService.playerWinSet(0); 
		setService.playerWinSet(1); 
		setService.playerWinSet(0); 
		setService.playerWinSet(1); 
		
		assertEquals(setService.getCurrentScore(), "PLAYER1 : 7 - 8 : PLAYER2");
		
		setService.playerWinSet(1); 
		
		assertTrue(setService.getWinner().isPresent());
		assertEquals(setService.getWinner().get().getPlayerName(), "PLAYER2");
	}
}

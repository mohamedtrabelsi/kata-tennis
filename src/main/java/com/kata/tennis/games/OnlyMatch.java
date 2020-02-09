package com.kata.tennis.games;

import java.util.Scanner;

import com.kata.tennis.models.Game;
import com.kata.tennis.models.Player;
import com.kata.tennis.models.Set;
import com.kata.tennis.services.GameServices;
import com.kata.tennis.services.SetServices;

public class OnlyMatch {
	private static SetServices setService;
	private static GameServices gameService;
	private static Player playerOne;
	private static Player playerTwo;

	public static void addPlayers(Scanner scanner) throws Throwable {
		scanner.nextLine();
		System.out.print("  Player 1 Name: ");
		String firstPlayerName = scanner.nextLine();
		System.out.println("Player  " + firstPlayerName + " added successfully. Please Tap enter ");
		scanner.nextLine();
		System.out.println("\n\nPlease second player's info:");
		System.out.print("  Player 2 Name: ");
		String secondPlayerName = scanner.nextLine();
		System.out.println("Player " + secondPlayerName + " added successfully. Please Tap enter ");
		// INITIALIZE PLAYERS
		setService = Set.initSet(firstPlayerName, secondPlayerName);
		System.out.println("Player one: " + setService.getPlayerOne().getPlayerName());
		System.out.println("Player two: " + setService.getPlayerTwo().getPlayerName());
		playerOne = setService.getPlayerOne();
		playerTwo = setService.getPlayerTwo();
		// START GAME
		gameService = new Game(playerOne, playerTwo);
		String goal = "";
		while (!goal.equalsIgnoreCase("x")) {
			goal = displayGame(scanner);
		}
		System.out.println("\nExiting System...\n");

	}

	private static String displayGame(Scanner scanner) throws Throwable {
		System.out.println("------ Game begins ------ ");
		System.out.println(gameService.getCurrentScore());
		System.out.println("Tap 1 player one goal | Tap 2 Player two goal ");
		System.out.print("Option: ");
		String option = scanner.next();
		switch (option) {
		case "1":
			gameService.playerScore(0);
			System.out.println("Player 1 wins 1 point");
			System.out.println("Score :" + gameService.getCurrentScore());
			if (gameService.getWinner().isPresent()) {
				System.out.println("Player 1 is winner");
				setService.playerWinSet(0);

				return "x";
			}
			return option;
		case "2":
			gameService.playerScore(1);
			System.out.println("Player 2 wins 1 point");
			System.out.println("Score :" + gameService.getCurrentScore());
			if (gameService.getWinner().isPresent()) {
				System.out.println("Player 2 is winner");
				return "x";
			}
			return option;
		default:
			System.out.println("Invalid option, please re-enter.");
			return option;
		}
	}
}

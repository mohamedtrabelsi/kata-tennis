package GameManagement;

import java.util.Scanner;

import com.nexeo.kata.models.Game;
import com.nexeo.kata.models.Player;
import com.nexeo.kata.models.Set;
import com.nexeo.services.GameServices;
import com.nexeo.services.SetServices;

public class AllSet {
	private static SetServices setService;
	private static GameServices gameService;
	private static Player playerOne;
	private static Player playerTwo;

	public static void addPlayers(Scanner scanner) throws Throwable {
		scanner.nextLine();
		System.out.println("\n\nPlease 1st player's info:");
		System.out.print("  Player 1 Name: ");
		String firstPlayerName = scanner.nextLine();
		System.out.println("Player " + firstPlayerName + " added successfully. Please Tap enter \n\n");
		scanner.nextLine();
		System.out.println("\n\nPlease second player's info:");
		System.out.print("  Player 2 Name: ");
		String secondPlayerName = scanner.nextLine();
		System.out.println("Player " + secondPlayerName + " added successfully. Please Tap enter \n\n");
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
			if (!setService.getWinner().isPresent()) {
				if (gameService.getWinner().isPresent()) {
					setService.playerWinSet(0);
					System.out.println("Player 1 wins the set");
				}
			} else if (setService.getWinner().isPresent()) {
				 System.out.println("winner of set "+ setService.getWinner().get().getPlayerName());
				 return "x";
			}
			return option;
		case "2":
			gameService.playerScore(1);
			System.out.println("Player 2 wins 1 point");
			System.out.println("Score :" + gameService.getCurrentScore());
			if (!setService.getWinner().isPresent()) {
				if (gameService.getWinner().isPresent()) {
					setService.playerWinSet(1);
					System.out.println("Player 2 wins the set");
				}
			} else if (setService.getWinner().isPresent()) {
				 System.out.println("winner of set "+ setService.getWinner().get().getPlayerName());
				 return "x";
			}
			return option;
		default:
			System.out.println("Invalid option, please re-enter.");
			return option;
		}
	}
}

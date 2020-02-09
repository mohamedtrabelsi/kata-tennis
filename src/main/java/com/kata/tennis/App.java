package com.kata.tennis;

import java.util.Scanner;

import com.kata.tennis.games.AllSet;
import com.kata.tennis.games.OnlyMatch;

public class App {
	public static void main(String[] args) throws Throwable {
		Scanner scanner = new Scanner(System.in);
		System.out.println("Welcome to the Kata's program !\n");
		System.out.println("Tennis Game!\n");
		String lastOption = "";
		while (!lastOption.equalsIgnoreCase("x")) {
			lastOption = displayMenu(scanner);
		}
		System.out.println("\nExiting System...\n");

	}

	private static String displayMenu(Scanner scanner) throws Throwable {
		System.out.println("Please select an option:");
		System.out.println("1.  Play only one match ");
		System.out.println("2.  Play match with SET ");
		System.out.println("X.  Exit System.");
		System.out.print("Option: ");
		String option = scanner.next();
		switch (option) {
		case "1":
			OnlyMatch.addPlayers(scanner);
			return option;
		case "2":
			AllSet.addPlayers(scanner);
			return option;
		default:
			System.out.println("Invalid option, please re-enter.");
			return option;
		}
	}

	
}

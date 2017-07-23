package project;

import java.util.Scanner;

public class CastAway {

	public static void main(String[] args) {
		Scanner kb = new Scanner(System.in);
		String playerName;
		boolean winGame = false;
		int health = 0, difficulty, attack = 0;
		char[][] island = { { '\u2620', '\u2620', '\u2620', '\u2620' }, { '\u2620', '\u2620', '\u2620', '\u2620' } };

		// player assignment and difficulty level

		System.out.print("Enter player name: ");
		playerName = kb.nextLine();

		do {
			System.out.println("\nSelect difficulty:\n(1)Easy\n(2)Hard ");
			difficulty = kb.nextInt();

			if (difficulty == 1) {
				health = 20;
				attack = 3;
			} else if (difficulty == 2) {
				health = 10;
				attack = 2;
			} else {
				System.out.println("You must enter either a 1 or 2 to play!\n");
			}
		} while (!(difficulty == 1 || difficulty == 2));

		// game start

		System.out.println("\nWelcome " + playerName + ", to CastAway survival!");

		driverCA driverCAMain = new driverCA(health, attack);

		while (!driverCAMain.winCheck()) {
			driverCAMain.printIsland(island);
			driverCAMain.move(island);
			driverCAMain.moveResult(island);
			driverCAMain.setCheck();
		}
		driverCAMain.printEnd();
	}
}

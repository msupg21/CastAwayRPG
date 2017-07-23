package project;

import java.util.Scanner;

public class driverCA {
	protected int health;
	protected int attack;
	protected boolean winGame = false;
	Scanner kb = new Scanner(System.in);
	char radio = '\u0020', orr = '\u0020', flare = '\u0020';

	public driverCA(int health, int attack) {
		this.health = health;
		this.attack = attack;
	}

	// print blank island
	public void printIsland(char[][] island) {
		System.out.println("\n   \"Pick location of island to search\"\n");
		System.out.println("\n\t  *----- CASTAWAY -----*\n");
		for (int r = 0; r < island.length; r++) {
			for (int c = 0; c < island[r].length; c++) {
				System.out.print("\t" + island[r][c]);
			}
			System.out.println();
		}
		System.out.println("\t " + '\u26F5' + " " + '\u26F5' + " " + '\u26F5' + " " + '\u26F5' + " " + '\u26F5' + " "
				+ '\u26F5' + " " + '\u26F5' + " " + '\u26F5' + " " + '\u26F5');
		System.out.println();
		System.out.println("Collect the 3 keys to survival:  " + radio + " " + orr + " " + flare);
		// the 3 keys are blank unicode spaces until achieved, then replaced
		// with new unicode char
	}

	// visits specific location on island
	public void move(char[][] island) {
		System.out.println("Row location (1-2):");
		int row = kb.nextInt();
		System.out.println("Column location (1-4):");
		int col = kb.nextInt();

		row = row - 1;
		col = col - 1;

		if ((row == 0 || row == 1) && (col == 0 || col == 1 || col == 2 || col == 3)) {
			if (island[row][col] == '\u2717') {
				System.out.println("\nSpot occupied, try again!\n");
				move(island);
			} else {
				island[row][col] = '\u2713';
			}
		} else {
			System.out.println("You did not entered a postion within range!");
			move(island);
		}
	}

	// scenarios for each location
	public void moveResult(char[][] island) {
		if (island[0][0] == '\u2713') {
			System.out.println("You found health! +1");
			System.out.println("You have increased attack power! +1");
			health += 1;
			attack += 1;
			System.out.println("\n     Health = " + health + "\t    Attack Power = " + attack + "\n");
			System.out.println("\t__________________________");
			island[0][0] = '\u2717';

		}
		if (island[0][1] == '\u2713') {
			int snakeHealth = 4;
			int attackMode1;// attack for snake = - 1 & radio scenario
			System.out.println("You have encounted a snake!  Health: " + '\u19DE' + " " + snakeHealth);
			while (snakeHealth > 0) {
				do {
					System.out.println("(1) Attack with stones?");
					System.out.println("(2) Attack with knife?");
					attackMode1 = kb.nextInt();
					if (attackMode1 == 1) {
						System.out.println("You have stoned the snake to death without being bitten!");
						snakeHealth -= 4;
						System.out.println("Snake Health: " + snakeHealth);
					}
					if (attackMode1 == 2) {
						System.out.println("You have slashed snake, but you have been bitten!" + "- " + "3");
						health -= 3;
						snakeHealth -= attack;
						System.out.println("Snakes health = " + snakeHealth);
						System.out.println("\n     Health = " + health + "\t    Attack Power = " + attack + "\n");
					}
				} while (!(attackMode1 == 1 || attackMode1 == 2));
			}
			if (health > 0) {
				System.out.println("You have defeated the snake!");
				System.out.println("You have collected a radio! " + '\u2706');

				radio = '\u2706';

				System.out.println("\n     Health = " + health + "\t    Attack Power = " + attack + "\n");
				island[0][1] = '\u2717';
			} else { // check if you died at during this scenario, or collected
						// all 3 keys to survival
				winCheck();
			}
		}
		if (island[0][2] == '\u2713') { // -2 health at this scenario
			System.out.println("Rock slide hurts you! Health -2");
			health -= 2;
			System.out.println("\n     Health = " + health + "\t    Attack Power = " + attack + "\n");
			island[0][2] = '\u2717';
			if (health <= 0) { // check if you died at during this scenario
				winCheck();
			}
		}
		if (island[0][3] == '\u2713') { // either +2 attack or + 2 health at
										// this scenario
			int pick;
			System.out.println("You have found two mysterious boxes");
			do {
				System.out.println("Which one would you like to pick: 1 or 2? ");
				pick = kb.nextInt();
				if (pick == 1) {
					System.out.println("Found knife upgrade, attack! +2");
					attack += 2;
				}
				if (pick == 2) {
					System.out.println("Found health +2");
					health += 2;
				}
			} while (!(pick == 1 || pick == 2));

			System.out.println("\n     Health = " + health + "\t    Attack Power = " + attack + "\n");
			island[0][3] = '\u2717';
		}
		if (island[1][0] == '\u2713') { // zombie health 8, attack - 2 orr
			int zombieHealth = 8, zombieAttack = 2;
			int attackMode2;
			System.out.println("You just ran into a zombie!" + " " + '\u2620');
			while (zombieHealth > 0 && health > 0) {
				do {
					System.out.println("(1) Swipe for zombie's head");
					System.out.println("(2) Swipe for zombie's legs");
					attackMode2 = kb.nextInt();
					if (attackMode2 == 1) {
						System.out.println("You took out zombie, however you took a hit you can't recover from!");
						System.out.println("You attack strength has been reduced -1");
						System.out.println("You health has been reduced -1");
						attack -= 1;
						health -= 3;
						zombieHealth = 0;
					}
					if (attackMode2 == 2) {
						System.out.println("Smart going for the legs, zombie is now immobile!");
						zombieHealth = 0;
					}
				} while (!(attackMode2 == 1 || attackMode2 == 2));
				if (health > 0) {
					System.out.println("You defeated the zombie!");
					System.out.println("You have aquired orrs! ");
					orr = '\u020a';
					island[1][0] = '\u2717';
				} else { // checks to see if you collected all 3 keys to
							// survival
					winCheck();
				}
			}

			System.out.println("\n     Health = " + health + "\t    Attack Power = " + attack + "\n");
		}
		if (island[1][1] == '\u2713') { // - 3 health scenario
			System.out.println("Stranded without water for 24 hours.  Health -3");
			health -= 3;
			System.out.println("\n     Health = " + health + "\t    Attack Power = " + attack + "\n");
			island[1][1] = '\u2717';
			if (health <= 0) { // check if you died at during this scenario
				winCheck();
			}
		}
		if (island[1][2] == '\u2713') { // +1 health during this scenario
			System.out.println("You have found a first aid kit! Health +1");
			health += 1;
			System.out.println("\n     Health = " + health + "\t    Attack Power = " + attack + "\n");
			island[1][2] = '\u2717';
		}
		if (island[1][3] == '\u2713') { // bear and flare scenario, bear attack
										// -3
			int bearHealth = 7, bearAttack = 3;
			int attackMode3;
			System.out.println("You have ran into a sleeping bear: " + "ʕ•͡-•ʔ " + "Health " + bearHealth);
			while (bearHealth > 0 && health > 0) {
				do {
					System.out.println("(1) Grab flare and try to out run bear.");
					System.out.println("(2) Attack bear while asleep.");
					System.out.println("(3) Throw bear a fish.");
					attackMode3 = kb.nextInt();
					if (attackMode3 == 1) {
						System.out.println("Bear got a good shot on you -5 health.");
						System.out.println("Bear Health: " + bearHealth);
						health -= 5;
						System.out.println("\n     Health = " + health + "\t    Attack Power = " + attack + "\n");
					}
					if (attackMode3 == 2) {
						System.out.println("You jacked the bear up a bit, bear health" + " -" + attack);
						System.out.println("You were hit by bear though health -" + bearAttack);
						bearHealth -= attack;
						health -= bearAttack;
						System.out.println("Bear Health: " + bearHealth);
						System.out.println("\n     Health = " + health + "\t    Attack Power = " + attack + "\n");
					}
					if (attackMode3 == 3) {
						System.out.println("Bear is busy eating fish, so you grab flare and run.");
						bearHealth = 0;
					}
				} while (!(attackMode3 == 1 || attackMode3 == 2 || attackMode3 == 3));

			}

			if (health > 0) { // checks to see if you have collected all 3 keys
								// to survival
				System.out.println("You defeated the bear!");
				System.out.println("You have aquired a flare!");
				flare = '\u2737';
				System.out.println("\n     Health = " + health + "\t    Attack Power = " + attack + "\n");
				island[1][3] = '\u2717';
			} else { // checks to see if you died
				winCheck();
			}

		}

	}

	public boolean winCheck() {
		return winGame;

	}

	// supposed to check for win or if you health reached below 0
	public void setCheck() {
		if (radio == '\u2706' && orr == '\u020a' && flare == '\u2737') {
			winGame = true;
		}
		if (health <= 0) {
			winGame = true;
		}
	}

	public void printEnd() {
		if (radio == '\u2706' && orr == '\u020a' && flare == '\u2737') {
			System.out.println("You have survived CastAway Island!!");
			System.out.println("GAME OVER!!");
			System.out.println(" " + radio + " " + orr + " " + flare);
		}
		if (health <= 0) {
			System.out.println("You have been killed!!");
			System.out.println("GAME OVER!!");
		}
	}

}

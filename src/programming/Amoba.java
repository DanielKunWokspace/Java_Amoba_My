package programming;

import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class Amoba {

	protected static String player1 = "X";
	protected static String player2 = "O";

	public static void main(String[] args) throws InterruptedException {
		amoba();
	}

	/**
	 * AMOBA METHOD CALL 
	 * 
	 * @throws InterruptedException
	 */
	public static void amoba() throws InterruptedException {
		/** CREATE TABLE */
		
		Scanner in = new Scanner(System.in);
		String [][] table = asktoMapSize(in);
							
		initTable(table);
		drawTable(table);
		
		/** END OF CREATE TABLE SECTION */

		/** PLAY AMOBA */
		while (true) {
			/** SECTION OF PLAYER 1 */
			stepWithPlayer(table, "X", in);

			drawTable(table);

			if (checkWinner(table) != 0) {
				System.out.println(("The winner is Player: ") + (checkWinner(table)));
				break;
			}

			if (checkEmptyPlaces(table, player1)) {
				System.out.println("Out of Steps!");
				break;
			}
			/** END OF SECTION */

			/** SECTION OF PLAYER 2 */
			stepWithPlayer(table, "O", in);

			drawTable(table);

			if (checkWinner(table) != 0) {
				System.out.println(("The winner is Player: ") + (checkWinner(table)));
				break;
			}

			if (checkEmptyPlaces(table, player2)) {
				System.out.println("Out of Steps!");
				break;
			}
			/** END OF SECTION */

			/** END OF PLAY AMOBA SECTION */

		}

		in.close();
	}

	/** ASK THE USER FOR A NUMBER */
	public static int readNumberFromUser(String text, Scanner inputReader) {
		int userNumber = 0;
		boolean incorrectInput = true;
		/** CYCLE FOR A CORRECT NUMBER */
		while (incorrectInput) {

			System.out.print(text);
			userNumber = inputReader.nextInt();

			/** CHECK THE INPUT NUMBER */
			if (userNumber >= 0 && userNumber < 10) {
				incorrectInput = false;
			} else {
				System.out.println("Your input must be minimum 0 and lower than 10");
			}

		}
		return userNumber;
	}

	/** TABLE INITIALISATION */
	public static void initTable(String[][] table) {
		for (int row = 0; row < table.length; row++) {
			for (int column = 0; column < table.length; column++) {
				table[row][column] = " ";
			}
		}
	}

	/** TABLE DRAWING */
	public static void drawTable(String[][] table) {
		System.out.print("   ");
		for (int column = 0; column < table.length; column++) {
			System.out.print(column + "   ");
		}
		System.out.println();

		for (int row = 0; row < table.length; row++) {
			System.out.print(row + "| ");
			for (int column = 0; column < table.length; column++) {
				System.out.print(table[row][column] + " | ");
			}
			System.out.println();
		}
	}

	/** STEP WITH PLAYER */
	public static void stepWithPlayer(String[][] table, String playerSign, Scanner scanner) {
		int selectedRow = 0;
		int selectedColumn = 0;
		boolean incorrectPosition = true;
		/** CYCLE FOR CORRECT INPUT */
		while (incorrectPosition) {
			selectedRow = readNumberFromUser("Player " + playerSign + ", add Row ID: ", scanner);
			selectedColumn = readNumberFromUser("Player " + playerSign + ", add Column ID: ", scanner);

			/** CHECK THE INPUT TO LOCATION */
			if (selectedRow <= table.length - 1 && selectedColumn <= table.length - 1) {
				/** CHECK THE INPUT TO FREE */
				if (table[selectedRow][selectedColumn] == " ") {
					incorrectPosition = false;
					table[selectedRow][selectedColumn] = playerSign;
				} else {
					System.out.println("Choose an another position cause it is already taken...");
				}

			} else {
				System.out.println(("Your number is to high, your act. map size is: ") + (table.length - 1));

			}

		}

	}

	/**
	 * WINNER CHECK
	 * 
	 * @throws InterruptedException
	 */
	public static int checkWinner(String[][] table) throws InterruptedException {
		int playerIndex = 0;

		/** CHECK THE COLUMN */
		for (int row = 0; row < table.length; row++) {
			for (int column = 0; column < table.length - 2; column++) {
				if (table[row][column] == player1 && table[row][column + 1] == player1
						&& table[row][column + 2] == player1 ) {
					playerIndex = 1;
					// winnerEffect(table, player1, row, column);

				}
				if (table[row][column] == player2 && table[row][column + 1] == player2
						&& table[row][column + 2] == player2 ) {
					playerIndex = 2;
					// winnerEffect(table, player2, row, column);

				}
			}
		}

		/** CHECK THE ROW */
		for (int row = 0; row < table.length - 2; row++) {
			for (int column = 0; column < table.length; column++) {
				if (table[row][column] == player1 && table[row + 1][column] == player1
						&& table[row + 2][column] == player1) {
					playerIndex = 1;
				}
				if (table[row][column] == player2 && table[row + 1][column] == player2
						&& table[row + 2][column] == player2 ) {
					playerIndex = 2;
				}
			}
		}

		/** CHECK THE BACKWARD */
		for (int row = 0; row < table.length - 2; row++) {
			for (int column = 0; column < table.length - 2; column++) {
				if (table[row][column] == player1 && table[row + 1][column + 1] == player1
						&& table[row + 2][column + 2] == player1 ) {
					playerIndex = 1;
				}
				if (table[row][column] == player2 && table[row + 1][column + 1] == player2
						&& table[row + 2][column + 2] == player2 ) {
					playerIndex = 2;
				}
			}
		}

		/** CHECK THE FORWARD */
		for (int row = 0; row < table.length - 2; row++) {
			for (int column = 2; column < table.length; column++) {
				if (table[row][column] == player1 && table[row + 1][column - 1] == player1
						&& table[row + 2][column - 2] == player1 ) {
					playerIndex = 1;
				}
				if (table[row][column] == player2 && table[row + 1][column - 1] == player2
						&& table[row + 2][column - 2] == player2 ) {
					playerIndex = 2;
				}
			}
		}

		System.out.println("checkWinner...");

		return playerIndex;
	}

	/** EMPTY PLACE CHECK */
	public static boolean checkEmptyPlaces(String[][] table, String playerSign) {
		boolean outOfSteps = true;
		int emptyPlaces = 0;
		

		for (int row = 0; row < table.length; row++) {
			for (int column = 0; column < table.length; column++) {
				if (table[column][row] == " ") {
					emptyPlaces++;
				}

			}
		}

		if (emptyPlaces >= 1) {

			outOfSteps = false;
		}

		System.out.println(("checkEmptyPlaces...") + (emptyPlaces));

		return outOfSteps;
	}

	/**
	 * METHOD OF WINNER EFFECT
	 * 
	 * @throws InterruptedException
	 */
	public static void winnerEffect(String[][] table, String playerSign, int row, int column)
			throws InterruptedException {
		int winEffectmultipler = 1;
		int winEffected = 0;

		while (winEffected < winEffectmultipler) {
			table[row][column] = " ";
			drawTable(table);
			TimeUnit.MILLISECONDS.sleep(500);
			drawTable(table);
			table[row][column + 1] = " ";
			drawTable(table);
			TimeUnit.MILLISECONDS.sleep(500);
			table[row][column + 2] = " ";
			drawTable(table);
			TimeUnit.MILLISECONDS.sleep(500);
			table[row][column] = playerSign;
			drawTable(table);
			TimeUnit.MILLISECONDS.sleep(500);
			table[row][column + 1] = playerSign;
			drawTable(table);
			TimeUnit.MILLISECONDS.sleep(500);
			table[row][column + 2] = playerSign;
			drawTable(table);
			winEffected++;

		}

	}
	/** ASK THE PLAYER FOR A MAP SIZE */
	private static String[][] asktoMapSize(Scanner in) {
		int tableSize = 0;
		tableSize = readNumberFromUser("Add Table Size: ", in);
		
		if (tableSize < 3) {
			tableSize = 3;
			System.out.println("Map size changed to 3x3 cause your input was to low.");
		}
		
		String[][] table = new String[tableSize][tableSize];
		
		return table;
		
	}

}

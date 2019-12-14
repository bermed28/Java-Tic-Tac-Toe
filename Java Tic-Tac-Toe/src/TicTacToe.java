import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class TicTacToe {

	static ArrayList<Integer> playerPositions = new ArrayList<>();
	static ArrayList<Integer> cpuPositions = new ArrayList<>();
	

	public static void main(String[] args) {
		char[][] gameBoard = {

				{' ', '|', ' ', '|', ' '},
				{'-', '+', '-', '+', '-'}, 
				{' ', '|', ' ', '|', ' '},
				{'-', '+', '-', '+', '-'},
				{' ', '|', ' ', '|', ' '}

		};

		printGameBoard(gameBoard);
		
		while(true) {

			System.out.println("Choose a place to make your move (1-9): ");
			Scanner userInp = new Scanner(System.in);
			try {
				int userPos = userInp.nextInt();
			
				while (playerPositions.contains(userPos) || cpuPositions.contains(userPos)) {
					System.out.println("Position Taken! Choose Another Position");
					userPos = userInp.nextInt();
				}
	
				play(gameBoard, userPos, "player");
				String results = checkWinner();
				
				if (results.length() > 0 ) {
					System.out.println(results);
					break;
				}
				
				Random rand = new Random();
				int cpuPos = rand.nextInt(9) + 1;
	
				while (playerPositions.contains(cpuPos) || cpuPositions.contains(cpuPos)) {
					cpuPos = rand.nextInt(9) + 1;
	
				}
				play(gameBoard, cpuPos, "cpu");
				printGameBoard(gameBoard);
				
				results = checkWinner();
				if (results.length() > 0 ) {
					System.out.println(results);
					break;
				}
			} catch(Exception e) {
				System.out.println("Invalid Input! Please Select a Number 1-9");
				continue;
			}

		}
		
		System.exit(0);

	}

	public static void printGameBoard(char[][] gameBoard) {


		for (char[] col : gameBoard) System.out.println(col);


	}

	public static void play(char[][] gameBoard, int position, String user) {

		char symbol = ' ';
		if (user.equals("player")) {
			symbol = 'X';
			playerPositions.add(position);
		} else if (user.equals("cpu")){
			symbol = 'O';
			cpuPositions.add(position);
		}

		switch (position) {
		case 1:
			gameBoard[0][0] = symbol;
			break;
		case 2:
			gameBoard[0][2] = symbol;
			break;
		case 3:
			gameBoard[0][4] = symbol;
			break;
		case 4:
			gameBoard[2][0] = symbol;

			break;
		case 5:
			gameBoard[2][2] = symbol;

			break;
		case 6:
			gameBoard[2][4] = symbol;

			break;
		case 7:
			gameBoard[4][0] = symbol;

			break;
		case 8:
			gameBoard[4][2] = symbol;

			break;
		case 9:
			gameBoard[4][4] = symbol;
			break;

		default:
			break;
		}

	}

	public static String checkWinner() {
		
		List topRow = Arrays.asList(1,2,3);
		List midRow = Arrays.asList(4,5,6);
		List botRow = Arrays.asList(7,8,9);

		List leftCol = Arrays.asList(1,4,7);
		List midCol = Arrays.asList(2,5,8);
		List rightCol = Arrays.asList(3,6,9);

		List cross1 = Arrays.asList(1,5,9);
		List cross2 = Arrays.asList(7,5,3);

		List<List> winCondition = new ArrayList<List>();
		winCondition.add(topRow);
		winCondition.add(midRow);
		winCondition.add(botRow);
		winCondition.add(leftCol);
		winCondition.add(midCol);
		winCondition.add(rightCol);
		winCondition.add(cross1);
		winCondition.add(cross2);

		for (List list : winCondition) {
			if (playerPositions.containsAll(list)) {
				return "Player 1 wins! Restart Application to play again";

			} else if (cpuPositions.containsAll(list)) {
				return "CPU Wins, try Again! Restart Application to play again";

			
			} else if(playerPositions.size() + cpuPositions.size() == 9) {
				return "It's a tie! Restart Application to play again";
			
			}
		}

		return "";
	}

}

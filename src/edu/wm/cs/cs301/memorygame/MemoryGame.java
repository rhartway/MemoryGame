package edu.wm.cs.cs301.memorygame;

import java.util.Scanner;

public class MemoryGame {
    public enum DifficultyLevel {
        EASY, MEDIUM, HARD
    }

    public MemoryGame() throws ArrayIndexOutOfBoundsException {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Welcome to the Memory Game!");
        System.out.println("Choose a difficulty level:");
        System.out.println("1. Easy");
        System.out.println("2. Medium");
        System.out.println("3. Hard");
        System.out.print("Enter the number of your choice: ");

        int choice = scanner.nextInt();
        DifficultyLevel selectedDifficulty = null;

        switch (choice) {
            case 1:
                selectedDifficulty = DifficultyLevel.EASY;
                break;
            case 2:
                selectedDifficulty = DifficultyLevel.MEDIUM;
                break;
            case 3:
                selectedDifficulty = DifficultyLevel.HARD;
                break;
            default:
                System.out.println("Invalid choice. Exiting.");
                System.exit(0);
        }

    

        // Display the initial game board
        
        boolean playAgain = true;
        Scanner gameScanner = new Scanner(System.in);
        while(playAgain) {
        	boolean gameWon = false;
        	 GameBoard gameBoard = new GameBoard(selectedDifficulty, new EnglishAlphabet(), new JapaneseAlphabet());
        	 int turnCounter = 0;
        	 while (!gameWon) {
	        	
	            System.out.println("Turn #" + (turnCounter + 1));
	
	            gameBoard.displayBoard();
	
	            System.out.print("Enter the row and column of the first card (e.g., 1 2) or type 'quit' to exit: ");
	            String userInput = gameScanner.nextLine();
	
	            if (userInput.equalsIgnoreCase("quit")) {
	                break;
	            }
	
	            try {
	                String[] inputArray = userInput.split(" ");
	                int row1 = Integer.parseInt(inputArray[0]);
	                int col1 = Integer.parseInt(inputArray[1]);
	
	
	                if (!gameBoard.isCardMatched(row1 - 1, col1 - 1) && !gameBoard.tileVisible(row1 - 1, col1 - 1)) {
	                    gameBoard.flipCard(row1 - 1, col1 - 1);
	                } else {
	                    System.out.println("Card already matched or flipped. ");
	                    System.out.println("Press 'return' to continue...");
	                    gameScanner.nextLine();
	                    
	                    continue;
	                }
	
	                gameBoard.displayBoard();
	
	                System.out.print("Enter the row and column of the second card (e.g., 3 4): ");
	                String secondCardInput = gameScanner.nextLine();
	
	                if (secondCardInput.equalsIgnoreCase("quit")) {
	                    break;
	                }
	
	                String[] secondCardArray = secondCardInput.split(" ");
	                int row2 = Integer.parseInt(secondCardArray[0]);
	                int col2 = Integer.parseInt(secondCardArray[1]);
	
	                if (!gameBoard.isCardMatched(row2 - 1, col2 - 1) && !gameBoard.tileVisible(row2 - 1, col2 - 1)) {
	                    gameBoard.flipCard(row2 - 1, col2 - 1);
	                    
	                } else {
	                    System.out.println("Card is already flipped or matched. Try again.");
	                    System.out.println("Press 'return' to continue...");
	                    gameScanner.nextLine();
	                    resetUnmatchedCards(gameBoard);
	                    continue;
	                }
	               
	
	                gameBoard.displayBoard();
	
	
	                if (gameBoard.checkMatch(row1 - 1, col1 - 1, row2 - 1, col2 - 1)) {
	                    System.out.println("Match found!");
	                    
	                } else {
	                    System.out.println("No match");
	                    System.out.println("Press <RETURN> to continue...");
	                    gameScanner.nextLine();
	                    gameBoard.flipCard(row1 - 1, col1 - 1);
	                    gameBoard.flipCard(row2 - 1, col2 - 1);
	                }
	
	
	                gameWon = gameBoard.isGameWon(gameBoard);
	                resetUnmatchedCards(gameBoard);
	                turnCounter++;
	            }catch (Exception e) {
					System.out.println("Invalid Input Format. Restarting Turn");
					resetUnmatchedCards(gameBoard);
				}
	        }
	
	        if (gameWon) {
	            System.out.println("Congratulations! You've matched all of the cards in " + turnCounter + " turns.");
	            System.out.println("Would you like to play again? (Y/N) ");
	            String response = gameScanner.nextLine();
	            if (response.equals("Y")) {
	                playAgain = true;
	            } 
	            if (response.equals("N")) {
	                playAgain = false;
	            }

	        } else {
	            System.out.println("Would you like to play again? (Y/N) ");
	            String response = gameScanner.nextLine();
	            if (response.equals("Y")) {
	                playAgain = true;
	            } 
	            if (response.equals("N")) {
	                playAgain = false;
	            }

	        }
	        
	        
        }
        System.out.println("Thank You for Playing!");
        
    }
 
	        
	        
	        
	    private void resetUnmatchedCards(GameBoard gameBoard) {
	        int rows = gameBoard.getNumRows();
	        int cols = gameBoard.getNumCols();
	        
	        for (int row = 0; row < rows; row++) {
	            for (int col = 0; col < cols; col++) {
	                if (gameBoard.tileVisible(row, col) && !gameBoard.isCardMatched(row, col)) {
	                    gameBoard.flipCard(row, col);
	                }
	            }
	           
	            }
	        
	    }
    
    

    
    public static void main(String[] args) {
        new MemoryGame();
    }
}

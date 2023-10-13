package edu.wm.cs.cs301.memorygame;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

	public class MemoryGame {
	    public enum DifficultyLevel {
	        EASY, MEDIUM, HARD
	    }

    
	private List<Score> easyLeaderboard = new ArrayList<>();
	private List<Score> mediumLeaderboard = new ArrayList<>();
	private List<Score> hardLeaderboard = new ArrayList<>();

    public MemoryGame() throws ArrayIndexOutOfBoundsException {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Welcome to the Memory Game!");
        System.out.print("Enter your name: ");
        String playerName = scanner.nextLine();
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
	
	                System.out.print("Enter the row and column of the second card (e.g., 3 4):");
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
	                    System.out.println("Press <RETURN> to continue...");
	                    gameScanner.nextLine();
	                    resetUnmatchedCards(gameBoard);
	                    continue;
	                }
	               
	
	                gameBoard.displayBoard();
	
	
	                if (gameBoard.checkMatch(row1 - 1, col1 - 1, row2 - 1, col2 - 1)) {
	                    System.out.println("Match found!");
	                    
	                } else {
	                    System.out.println("No match\n");
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
	            addScoreToLeaderboard(playerName, turnCounter, selectedDifficulty);
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
        
        readLeaderboardFromFile();
        System.out.println("Thank You for Playing!");
        printLeaderboard();
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
	    
	    public void addScoreToLeaderboard(String playerName, int turns, DifficultyLevel difficulty) {
	        Score score = new Score(playerName, turns, difficulty);
			switch (difficulty) {
	            case EASY:
	                easyLeaderboard.add(score);
	                easyLeaderboard.sort(Comparator.comparing(Score::getTurns));
	                break;
	            case MEDIUM:
	                mediumLeaderboard.add(score);
	                mediumLeaderboard.sort(Comparator.comparing(Score::getTurns));
	                break;
	            case HARD:
	                hardLeaderboard.add(score);
	                hardLeaderboard.sort(Comparator.comparing(Score::getTurns));
	                break;
	        }

	        
	        if (easyLeaderboard.size() > 10) {
	            easyLeaderboard.subList(10, easyLeaderboard.size()).clear();
	        }
	        if (mediumLeaderboard.size() > 10) {
	            mediumLeaderboard.subList(10, mediumLeaderboard.size()).clear();
	        }
	        if (hardLeaderboard.size() > 10) {
	            hardLeaderboard.subList(10, hardLeaderboard.size()).clear();
	        }

	      
	        writeLeaderboardToFile();
	    }

	    private void writeLeaderboardToFile() {
	    	try (BufferedWriter easyWriter = new BufferedWriter(new FileWriter("resources/easy_leaderboard.txt", true));
	    		     BufferedWriter mediumWriter = new BufferedWriter(new FileWriter("resources/medium_leaderboard.txt", true));
	    		     BufferedWriter hardWriter = new BufferedWriter(new FileWriter("resources/hard_leaderboard.txt", true))) {
	            for (Score score : easyLeaderboard) {
	                easyWriter.write(score.getPlayerName() + ", " + score.getTurns());
	                easyWriter.newLine();
	            }
	            for (Score score : mediumLeaderboard) {
	                mediumWriter.write(score.getPlayerName() + ", " + score.getTurns());
	                mediumWriter.newLine();
	            }
	            for (Score score : hardLeaderboard) {
	                hardWriter.write(score.getPlayerName() + ", " + score.getTurns());
	                hardWriter.newLine();
	            }
	        } catch (IOException e) {
	            System.err.println("Error writing to leaderboard file.");
	        }
	    }
	    
	    private void readLeaderboardFromFile() {
	        try (BufferedReader easyReader = new BufferedReader(new FileReader("resources/easy_leaderboard.txt"));
	             BufferedReader mediumReader = new BufferedReader(new FileReader("resources/medium_leaderboard.txt"));
	             BufferedReader hardReader = new BufferedReader(new FileReader("resources/hard_leaderboard.txt"))) {

	            easyLeaderboard.clear();
	            mediumLeaderboard.clear();
	            hardLeaderboard.clear();

	            String line;
	            while ((line = easyReader.readLine()) != null) {
	                String[] parts = line.split(",");
	                if (parts.length == 2) {
	                    String playerName = parts[0].trim();
	                    int turns = Integer.parseInt(parts[1].trim());
	                    easyLeaderboard.add(new Score(playerName, turns, DifficultyLevel.EASY));
	                }
	            }

	            while ((line = mediumReader.readLine()) != null) {
	                String[] parts = line.split(",");
	                if (parts.length == 2) {
	                    String playerName = parts[0].trim();
	                    int turns = Integer.parseInt(parts[1].trim());
	                    mediumLeaderboard.add(new Score(playerName, turns, DifficultyLevel.MEDIUM));
	                }
	            }

	            while ((line = hardReader.readLine()) != null) {
	                String[] parts = line.split(",");
	                if (parts.length == 2) {
	                    String playerName = parts[0].trim();
	                    int turns = Integer.parseInt(parts[1].trim());
	                    hardLeaderboard.add(new Score(playerName, turns, DifficultyLevel.HARD));
	                }
	            }

	        } catch (IOException e) {
	            System.err.println("Error reading leaderboard files.");
	        }
	    }


	    private void printLeaderboard() {
	        System.out.println("Leaderboard:");
	        System.out.println("Easy Difficulty:");
	        for (int i = 0; i < easyLeaderboard.size(); i++) {
	            Score score = easyLeaderboard.get(i);
	            System.out.println((i + 1) + ". " + score.getPlayerName() + " - Turns: " + score.getTurns());
	        }

	        System.out.println("Medium Difficulty:");
	        for (int i = 0; i < mediumLeaderboard.size(); i++) {
	            Score score = mediumLeaderboard.get(i);
	            System.out.println((i + 1) + ". " + score.getPlayerName() + " - Turns: " + score.getTurns());
	        }

	        System.out.println("Hard Difficulty:");
	        for (int i = 0; i < hardLeaderboard.size(); i++) {
	            Score score = hardLeaderboard.get(i);
	            System.out.println((i + 1) + ". " + score.getPlayerName() + " - Turns: " + score.getTurns());
	        }
	    }

    

    
    public static void main(String[] args) {
        new MemoryGame();
    }
}

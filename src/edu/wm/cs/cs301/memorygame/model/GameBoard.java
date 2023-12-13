package edu.wm.cs.cs301.memorygame.model;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import edu.wm.cs.cs301.memorygame.model.MemoryGame.DifficultyLevel;

public class GameBoard {
	
	private final GamePiece[][] board;

	public GameBoard(MemoryGame.DifficultyLevel difficulty, Alphabet alphabet1, Alphabet alphabet2) {
	    int rows, cols;

	    switch (difficulty) {
	        case EASY:
	            rows = 3;
	            cols = 4;
	            break;
	        case MEDIUM:
	            rows = 4;
	            cols = 7;
	            break;
	        case HARD:
	            rows = 7;
	            cols = 8;
	            break;
	        default:
	            throw new IllegalArgumentException("Invalid difficulty level");
	    }

	    board = new GamePiece[rows][cols];
	    char[] alphabetChars = null;
	    List<Character> symbols = null;

	    if (difficulty == MemoryGame.DifficultyLevel.EASY) {
	        symbols = new ArrayList<>();
	        alphabetChars = alphabet1.toCharArray();
	    }

	    if (difficulty == MemoryGame.DifficultyLevel.MEDIUM) {
	        symbols = new ArrayList<>();
	        alphabetChars = alphabet1.toCharArray();
	    }

	    if (difficulty == MemoryGame.DifficultyLevel.HARD) {
	        symbols = new ArrayList<>();
	        alphabetChars = alphabet2.toCharArray();
	    }
        	
    

       
        List<Character> uniqueCharacters = new ArrayList<>();

        int totalSlots = rows * cols;

       
        if (totalSlots % 2 != 0) {
            throw new IllegalArgumentException("Invalid board size for creating pairs.");
        }

        
        for (char symbol : alphabetChars) {
            if (!uniqueCharacters.contains(symbol) && symbols.size() < totalSlots) {
                uniqueCharacters.add(symbol);
                symbols.add(symbol);
                symbols.add(symbol); // Add twice to ensure pairs
            }
        }
        
        
        if (uniqueCharacters.size() * 2 > totalSlots) {
            throw new IllegalArgumentException("Not enough unique characters to create pairs.");
        }

        
        Collections.shuffle(symbols);
        int symbolIndex = 0;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                char symbol = symbols.get(symbolIndex);
                board[i][j] = new CharacterGamePiece(symbol);
                symbolIndex++;
            }
        }
    


        
        Collections.shuffle(symbols);
        int symbolIndex1 = 0;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                char symbol = symbols.get(symbolIndex1);
                board[i][j] = new CharacterGamePiece(symbol);
                symbolIndex1++;
            }
        }
    }

    public void displayBoard() {
        int numRows = board.length;
        int numCols = board[0].length;

        System.out.print("    ");
        for (int col = 1; col <= numCols; col++) {
            System.out.print("   " + col);
        }
        System.out.println("\n  " + String.join("", Collections.nCopies(numCols, "=====")));

        for (int row = 0; row < numRows; row++) {
            System.out.print(row + 1 + " || ");
            for (int col = 0; col < numCols; col++) {
                GamePiece piece = board[row][col];
                if (piece.isVisible()) {
                    System.out.print(" " + piece.getSymbol() + " |");
                } else {
                    System.out.print(" ? |");
                }
            }
            System.out.println("|\n  " + String.join("", Collections.nCopies(numCols, "=====")));
        }
    }




    public void flipCard(int row, int col) {
        GamePiece piece = board[row][col];
        piece.setVisible(!piece.isVisible()); 
    }
    
    public boolean tileVisible (int row, int col) {
    	GamePiece piece = board[row][col];
    	boolean result = piece.isVisible();
    	return result;
    }

    
    public boolean checkMatch(int row1, int col1, int row2, int col2) {
        GamePiece piece1 = board[row1][col1];
        GamePiece piece2 = board[row2][col2];
        if (piece1.equals(piece2)) {
        	piece1.markAsMatched();
        	piece2.markAsMatched();
        	return true;
        }
        else {
			return false;
		}
    }

    public boolean isCardMatched(int row, int col) {
        GamePiece card = board[row][col];
        if(card.isMatched() == true) {
        	return true;
        }
        else {
        	return false;
        }
    }
		
	
	public int getNumRows() {
        return board.length;
    }

    public int getNumCols() {
        if (board.length > 0) {
            return board[0].length;
        } else {
            return 0; 
        }
    }
  


    public boolean isGameWon(GameBoard gameBoard) {
        int numRows = gameBoard.getNumRows();
        int numCols = gameBoard.getNumCols();

        for (int row = 0; row < numRows; row++) {
            for (int col = 0; col < numCols; col++) {
            	GamePiece tile = board[row][col];
                if(!tile.isMatched()) {
                    return false;
                }
            }
        }

        return true;
    }

}



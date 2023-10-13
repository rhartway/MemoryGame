package edu.wm.cs.cs301.memorygame;

public class Score {
    private String playerName;
    private int turns;
    private edu.wm.cs.cs301.memorygame.MemoryGame.DifficultyLevel difficulty;

    public Score(String playerName, int turns, edu.wm.cs.cs301.memorygame.MemoryGame.DifficultyLevel difficulty2) {
        this.playerName = playerName;
        this.turns = turns;
        this.difficulty = difficulty2;
    }

    public String getPlayerName() {
        return playerName;
    }

    public int getTurns() {
        return turns;
    }
    
    public edu.wm.cs.cs301.memorygame.MemoryGame.DifficultyLevel getDifficulty() {
    	return difficulty;
    }
}

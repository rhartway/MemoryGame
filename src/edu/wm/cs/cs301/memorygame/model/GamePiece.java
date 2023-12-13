package edu.wm.cs.cs301.memorygame.model;

public interface GamePiece {
	public boolean equals(GamePiece other);
	public void setVisible(boolean v);
	public boolean isVisible();
	public Character getSymbol();
	public boolean isMatched();
	public void markAsMatched();
}

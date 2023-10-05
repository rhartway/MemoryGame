package edu.wm.cs.cs301.memorygame;

public class CharacterGamePiece implements GamePiece {
	private final Character symbol;
	private boolean visible;
	
	public CharacterGamePiece(char s) {
		this.symbol = s;
		this.visible = false;
	}

	public Character getSymbol() {
		return symbol;
	}
	
	public void setVisible(boolean v) {
		this.visible = v;
	}
	
	public boolean isVisible() {
		return this.visible;
	}
	
	public boolean equals(GamePiece other) {
		if (other instanceof CharacterGamePiece) {
			CharacterGamePiece otherCharacterGamePiece = (CharacterGamePiece) other;
			return this.symbol.equals(otherCharacterGamePiece.getSymbol());
		}
		return false;
	}
	
}

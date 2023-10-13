package edu.wm.cs.cs301.memorygame;

public class CharacterGamePiece implements GamePiece {
	private final Character symbol;
	private boolean visible;
	private boolean matched; // Added property to track if the card is matched or not

	
	public CharacterGamePiece(char s) {
		this.symbol = s;
		this.visible = false;
		this.matched = false;
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
	
	@Override
    public boolean isMatched() {
        return matched;
    }

    // Method to set the card as matched
    public void markAsMatched() {
        matched = true;
    }
	
	public boolean equals(GamePiece other) {
		if (other instanceof CharacterGamePiece) {
			CharacterGamePiece otherCharacterGamePiece = (CharacterGamePiece) other;
			return this.symbol.equals(otherCharacterGamePiece.getSymbol());
		}
		return false;
	}
	
}

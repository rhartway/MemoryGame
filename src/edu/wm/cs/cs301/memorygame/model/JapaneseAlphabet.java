package edu.wm.cs.cs301.memorygame.model;

public class JapaneseAlphabet implements Alphabet {

	    private char[] characters = {
	    	//all my homies love hiragana
	        'あ', 'い', 'う', 'え', 'お',
	        'か', 'き', 'く', 'け', 'こ',
	        'さ', 'し', 'す', 'せ', 'そ',
	        'た', 'ち', 'つ', 'て', 'と',
	        'な', 'に', 'ぬ', 'ね', 'の',
	        'は', 'ひ', 'ふ', 'へ', 'ほ',
	    };

	    @Override
	    public char[] toCharArray() {
	        return characters;
	    }
	}

	

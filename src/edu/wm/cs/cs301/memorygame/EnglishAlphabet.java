package edu.wm.cs.cs301.memorygame;

public class EnglishAlphabet implements Alphabet {
    @Override
    public char[] toCharArray() {
        return "ABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray();
    }
}

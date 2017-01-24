package main;

/**
 * Created by jlesniak on 12/17/16.
 */
public class Card {
    private Value value;
    private Suit suit;

    public Card(Value value, Suit suit) {
        this.suit = suit;
        this.value = value;
    }

    public Value getValue() {
        return value;
    }

    public Suit getSuit() {
        return suit;
    }

    public int getNumericalValue(boolean ace_high) {
        switch (this.value) {
            case TWO: return 2;
            case THREE: return 3;
            case FOUR: return 4;
            case FIVE: return 5;
            case SIX: return 6;
            case SEVEN: return 7;
            case EIGHT: return 8;
            case NINE: return 9;
            case TEN: return 10;
            case JACK: return 10;
            case QUEEN: return 10;
            case KING: return 10;
            case ACE: return (ace_high) ? 11 : 1;
            default: return 0;
        }
    }
}

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
            case TEN:
            case JACK:
            case QUEEN:
            case KING: return 10;
            case ACE: return (ace_high) ? 11 : 1;
            default: return 0;
        }
    }

    private String getValString() {
        switch (this.value) {
            case TWO: return "2";
            case THREE: return "3";
            case FOUR: return "4";
            case FIVE: return "5";
            case SIX: return "6";
            case SEVEN: return "7";
            case EIGHT: return "8";
            case NINE: return "9";
            case TEN: return "10";
            case JACK: return "J";
            case QUEEN: return "Q";
            case KING: return "K";
            case ACE: return "A";
            default: return "";
        }
    }

    private String getSuitString() {
        switch (this.suit) {
            case HEARTS: return "H";
            case DIAMONDS: return "D";
            case SPADES: return "S";
            case CLUBS: return "C";
            default: return "";
        }
    }

    private String getCardEdge(int len) {
        String ret_str = "";
        for (; len > 0; --len) ret_str += "-";
        return ret_str;
    }

    public String toString(boolean face_up) {
        String edge_str, card_str, new_line = "\n";

        if (face_up) {
            card_str = "|" + this.getValString() + " of " + this.getSuitString() + "|";
        } else {
            card_str = "########";
        }

        edge_str = getCardEdge(card_str.length());
        return edge_str + new_line + card_str + new_line + edge_str + new_line;
    }
}

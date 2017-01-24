package test;
import main.Card;
import main.Deck;
import main.Hand;
import main.Value;
import main.Suit;
import java.util.ArrayList;

public class Test {
    private static int i = 2;
    public static int swag() {
        return i++;
    }

    public static void main(String[] args) {
        Card c = new Card(Value.EIGHT, Suit.CLUBS);
        Card c1 = new Card(Value.ACE, Suit.CLUBS);
        System.out.println(c.getValue());
        System.out.println(c.getNumericalValue(false));
        System.out.println(c1.getNumericalValue(false));
        System.out.println(c1.getNumericalValue(true));
    }
}

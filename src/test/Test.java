package test;

/**
 * Created by jlesniak on 12/17/16.
 */
import main.Card;
import main.Deck;
import main.Hand;
import main.Value;
import main.Suit;
import java.util.ArrayList;
import java.util.Random;

public class Test {
    private static int i = 2;
    public static int swag() {
        return i++;
    }

    public static void main(String[] args) {
        /*Card c = new Card(Value.QUEEN, Suit.CLUBS);
        Card c1 = new Card(Value.ACE, Suit.CLUBS);
        System.out.println(c.getValue());
        System.out.println(c.getNumericalValue(false));
        System.out.println(c1.getNumericalValue(false));
        System.out.println(c1.getNumericalValue(true));*/
        Random r = new Random();
        r.setSeed(r.nextInt());
        for (int i = 0; i < 20; ++i) {
            System.out.println(r.nextInt(4));
        }
    }
}

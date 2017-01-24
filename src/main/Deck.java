/**
 * Created by jlesniak on 12/17/16.
 */
package main;
import java.util.ArrayList;

public class Deck {
    private ArrayList<Card> deck;
    private int cur_card;

    public Deck(int num_decks) {
        deck = new ArrayList<>();
        for (Suit s : Suit.values()) {
            for (Value v : Value.values()) {
                deck.add(new Card(v, s));
            }
        }

        for (int i = 0; i < num_decks - 1; i++) {
            deck.addAll(deck);
        }

        cur_card = 0;
    }

    public Card drawCard() {
        if (cur_card == deck.size()) {
            cur_card = 0;
        }
        return deck.get(cur_card++);
    }
}

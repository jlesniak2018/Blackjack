package main;

/**
 * Created by jlesniak on 12/17/16.
 */
import java.util.ArrayList;
import java.util.Random;

public class Deck {
    private ArrayList<Card> deck;
    private int cur_card;
    private static Random rand = new Random();

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
        if (cur_card >= deck.size()) {
            this.shuffle();
        }
        return deck.get(cur_card++);
    }

    public int getCardsLeft() {
        return deck.size() - cur_card;
    }

    public int getNumCards() {
        return deck.size();
    }

    public void shuffle() {
        ArrayList<Card> new_deck = new ArrayList<>();

        while (deck.size() != 0) {
            int rand_int = rand.nextInt(deck.size()-1);
            new_deck.add(deck.remove(rand_int));
        }

        deck = new_deck;
        cur_card = 0;
    }
}

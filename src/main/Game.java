package main;

/**
 * Created by jlesniak on 1/24/17.
 */
import java.util.ArrayList;

public class Game {
    private Dealer dealer;
    private NonDealer nd;
    //private ArrayList<NonDealers> nds;
    private Deck deck;

    public Game(Dealer dealer, NonDealer nd, Deck deck) {
        this.dealer = dealer;
        this.nd = nd;
        this.deck = deck;
    }
}

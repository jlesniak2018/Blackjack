package main;

/**
 * Created by jlesniak on 1/24/17.
 */
import java.util.ArrayList;
import java.util.Scanner;

public class Game {
    private Dealer dealer;
    private NonDealer nd;
    //private ArrayList<NonDealers> nds;
    private Deck deck;
    private static Scanner s = new Scanner(System.in);

    public Game(Dealer dealer, NonDealer nd, Deck deck) {
        this.dealer = dealer;
        this.nd = nd;
        this.deck = deck;
    }

    public void start() {
        this.playRound();
    }

    public void playRound() {
        this.dealRound();
        this.makeBet();
    }

    private void makeBet() {
        boolean legal_bet = false;
        while (!legal_bet) {
            System.out.print("Place your bet (current funds: $" + nd.getFunds() + "): ");
            // Throw IllegalBetError
            int bet = s.nextInt();
            legal_bet = nd.placeBet(bet);
        }
    }

    private void dealRound() {
        //TODO: check if deck needs to be reshuffled

        nd.setHand(new Hand(deck.drawCard(), deck.drawCard()));
        dealer.setHand(new Hand(deck.drawCard(), deck.drawCard()));
    }
}

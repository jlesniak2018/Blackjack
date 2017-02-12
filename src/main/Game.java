package main;

/**
 * Created by jlesniak on 1/24/17.
 */
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Game {
    private Dealer dealer;
    private NonDealer nd;
    private int nd_bet;
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
        boolean deal_next = true;
        while (nd.getFunds() > 0 && deal_next) {
            this.dealRound();
            this.makeBet(nd);
            if (!this.checkNaturals()) {
                this.playHand(nd);
                this.playDealer();
            }
            this.evaluateHands();

            System.out.println("Play again? [y/n]: ");
            String dec = s.next().trim().toLowerCase();
            if (dec.equals("y")) {
                deal_next = false;
            }
            s.nextLine();
        }
    }

    private void dealRound() {
        if (deck.getCardsLeft() < 4) {
            deck.shuffle(new ArrayList<>());
        }

        nd.setHand(new Hand(deck.drawCard(), deck.drawCard()));
        dealer.setHand(new Hand(deck.drawCard(), deck.drawCard()));
    }

    private void makeBet(NonDealer p) {
        boolean legal_bet = false;
        while (!legal_bet) {
            System.out.print("Place your bet (current funds: $" + p.getFunds() + "): ");

            if (s.hasNextInt()) {
                nd_bet = s.nextInt();
                legal_bet = p.placeBet(nd_bet);
            }

            s.nextLine();
        }
    }

    private boolean checkNaturals() {
        return (dealer.getHand().isNatural() || nd.getHand().isNatural());
    }

    private void playHand(NonDealer p) {
        boolean still_playing = true;
        while (still_playing) {
            System.out.print("Enter 'h' to hit or 's' to stay: ");
            String dec = s.next().trim();

            if (dec.equals("h")) {
                if (deck.getCardsLeft() == 0) {
                    List<Card> cards_played = dealer.getHand().getCards();
                    cards_played.addAll(nd.getHand().getCards());
                    deck.shuffle(cards_played);
                }
                p.getHand().addHitCard(deck.drawCard());

                if (p.getHand().hasBusted()) {
                    still_playing = false;
                }
            } else if (dec.equals("s")) {
                still_playing = false;
            }

            s.nextLine();
        }
    }

    private void playDealer() {
        while (PlayingStrategy.hitDealer(dealer.getHand())) {
            dealer.getHand().addHitCard(deck.drawCard());
        }
    }

    private void evaluateHands() {
        if (!nd.getHand().hasBusted()) {
            if (dealer.getHand().getTotal() == nd.getHand().getTotal()) {
                nd.addFunds(nd_bet);
            } else if (nd.getHand().isNatural()) {
                nd.addFunds((int) (2.5 * nd_bet));
            } else if (dealer.getHand().hasBusted() || nd.getHand().getTotal() > dealer.getHand().getTotal()) {
                nd.addFunds(2 * nd_bet);
            }
        }
    }
}

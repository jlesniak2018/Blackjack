package main;

/**
 * Created by jlesniak on 1/24/17.
 */
import java.awt.*;
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
        deck.shuffle(new ArrayList<>());
        this.playRound();
    }

    public void playRound() {
        boolean deal_next = true;
        while (nd.getFunds() > 0 && deal_next) {
            this.makeBet(nd);
            this.dealRound();
            if (!this.checkNaturals()) {
                this.playHand(nd);
                if (!nd.getHand().hasBusted()) {
                    this.playDealer();
                }
            }
            this.evaluateHands();

            if (nd.getFunds() <= 0) {
                deal_next = false;
            } else {
                System.out.print("Play again? [y/n]: ");
                String dec = s.next().trim().toLowerCase();
                if (dec.equals("n")) {
                    deal_next = false;
                }
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

    private void printHands(boolean face_up) {
        System.out.println("Dealer's Hand:");
        System.out.println(dealer.getHand().toString(face_up));
        System.out.println(nd.getName() + "'s Hand:");
        System.out.println(nd.getHand().toString(true));
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
            printHands(false);
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
        // 0 = player lost; 1 = player wins; 2 = player ties
        int player_outcome = 0;

        if (!nd.getHand().hasBusted()) {
            if (dealer.getHand().getTotal() == nd.getHand().getTotal()) {
                nd.addFunds(nd_bet);
                player_outcome = 2;
            } else if (nd.getHand().isNatural()) {
                nd.addFunds((int) (2.5 * nd_bet));
                player_outcome = 1;
            } else if (dealer.getHand().hasBusted() || nd.getHand().getTotal() > dealer.getHand().getTotal()) {
                nd.addFunds(2 * nd_bet);
                player_outcome = 1;
            }
        }

        printHands(true);

        if (player_outcome == 0) {
            System.out.println("You lost " + nd_bet + " dollars.");
        } else if (player_outcome == 1) {
            int printed_bet = (nd.getHand().isNatural()) ? (int)(nd_bet*1.5) : nd_bet;
            System.out.println("You win " + printed_bet + " dollars.");
        } else {
            System.out.println("You tied.");
        }

        System.out.println();
    }
}

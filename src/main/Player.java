package main;

/**
 * Created by jlesniak on 1/23/17.
 */
//import java.util.ArrayList;

public abstract class Player {
    private Hand hand;
    private String name;
    private boolean is_dealer;

    public Player(String name, boolean is_dealer) {
        this(null, name, is_dealer);
    }

    public Player(Hand hand, String name, boolean is_dealer) {
        this.hand = hand;
        this.name = name;
        this.is_dealer = is_dealer;
    }

    /*public void addHand(Hand hand) {
        hands.add(hand);
    }*/

    /*public boolean removeHand(Hand hand) {
        return hands.remove(hand);
    }*/

    public void setHand(Hand h) {
        hand = h;
    }

    public Hand getHand() {
        return hand;
    }

    public String getName() {
        return name;
    }
}

package main;

/**
 * Created by jlesniak on 1/23/17.
 */
import java.util.ArrayList;

public class Player {
    private Hand hand;
    private String name;

    public Player(String name) {
        this(null, name);
    }

    public Player(Hand hand, String name) {
        this.hand = hand;
        this.name = name;
    }

    /*public void addHand(Hand hand) {
        hands.add(hand);
    }*/

    /*public boolean removeHand(Hand hand) {
        return hands.remove(hand);
    }*/

    public Hand getHand() {
        return hand;
    }

    public String getName() {
        return name;
    }
}

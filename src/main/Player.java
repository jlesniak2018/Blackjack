package main;

/**
 * Created by jlesniak on 1/23/17.
 */
import java.util.ArrayList;

public class Player {
    private ArrayList<Hand> hands;
    private String name;

    public Player(String name) {
        this(new ArrayList<Hand>(), name);
    }

    public Player(ArrayList<Hand> hands, String name) {
        this.hands = hands;
        this.name = name;
    }

    public void addHand(Hand hand) {
        hands.append(hand);
    }

    public Hand removeHand(Hand hand) {
        return hands.remove(hand);
    }

    public ArrayList<Hand> getHands() {
        return new ArrayList<Hand>(hands);
    }

    public String getName() {
        return name;
    }
}

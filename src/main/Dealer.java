package main;

/**
 * Created by jlesniak on 2/3/17.
 */
public class Dealer extends Player {
    public Dealer(String name, boolean is_dealer) {
        this(null, name, is_dealer);
    }

    public Dealer(Hand hand, String name, boolean is_dealer) {
        super(hand, name, is_dealer);
    }
}

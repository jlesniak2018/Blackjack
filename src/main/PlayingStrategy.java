package main;

/**
 * Created by jlesniak on 2/9/17.
 */
public class PlayingStrategy {
    public static boolean hitDealer(Hand dealer) {
        return (dealer.getTotal() <= 15 || (dealer.getTotal() <= 16 && dealer.isSoft()));
    }
}

/**
 * Created by jlesniak on 1/20/17.
 */
package main;
public class Hand {
    private Card hole_card;
    private Card down_card;

    public Hand() {
        hole_card = null;
        down_card = null;
    }

    public void setHoleCard(Card c) {
        hole_card = c;
    }

    public void setDownCard(Card c) {
        down_card = c;
    }

    public Card getHoleCard() {
        return hole_card;
    }

    public Card getDownCard() {
        return down_card;
    }

    public int getTotal(boolean hole_ace_high, boolean down_ace_high) {
        if (hole_card == null || down_card == null) {
            return 0;
        }
        return hole_card.getNumericalValue(hole_ace_high) + down_card.getNumericalValue(down_ace_high);
    }
}

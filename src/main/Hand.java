package main;

import java.util.ArrayList;

/**
 * Created by jlesniak on 1/20/17.
 */
public class Hand {
    private Card hole_card;
    private Card down_card;
    private ArrayList<Card> hit_cards;

    public Hand() {
        this(null, null);
    }

    public Hand(Card hole_card, Card down_card) {
        this.hole_card = hole_card;
        this.down_card = down_card;
        this.hit_cards = new ArrayList<>();
    }

    public void setHoleCard(Card c) {
        hole_card = c;
    }

    public void setDownCard(Card c) {
        down_card = c;
    }

    public void addHitCard(Card c) {
        if (c.getValue() == Value.ACE) {
            hit_cards.add(c);
        } else {
            hit_cards.add(0, c);
        }
    }

    public Card getHoleCard() {
        return hole_card;
    }

    public Card getDownCard() {
        return down_card;
    }

    public ArrayList<Card> getHitCards() {
        return hit_cards;
    }

    private int getNonAceTotal() {
        int total = 0;

        if (hole_card.getValue() != Value.ACE) total += hole_card.getNumericalValue(false);
        if (down_card.getValue() != Value.ACE) total += down_card.getNumericalValue(false);

        for (Card c: hit_cards) {
            if (c.getValue() != Value.ACE) total += c.getNumericalValue(false);
        }

        return total;
    }

    private int evalAce(Card c, int cur_total) {
        boolean ace_high = (cur_total + 11 > 21) ? false : true;
        return c.getNumericalValue(ace_high);
    }

    private int getAceTotal(int cur_total) {
        if (hole_card.getValue() != Value.ACE) cur_total += evalAce(hole_card, cur_total);
        if (down_card.getValue() != Value.ACE) cur_total += evalAce(down_card, cur_total);

        for (Card c: hit_cards) {
            if (c.getValue() != Value.ACE) cur_total += evalAce(c, cur_total);
        }

        return cur_total;
    }

    public int getTotal() {
        if (hole_card == null || down_card == null) {
            return 0;
        }

        return getAceTotal(getNonAceTotal());
    }
}

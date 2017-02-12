package main;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jlesniak on 1/20/17.
 */
public class Hand {
    private Card down_card;
    private Card hole_card;
    private List<Card> hit_cards;
    private boolean is_natural;

    public Hand(Card down_card, Card hole_card) {
        this.down_card = down_card;
        this.hole_card = hole_card;
        this.hit_cards = new ArrayList<>();
        this.is_natural = this.getTotal() == 21;
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

    public List<Card> getHitCards() {
        return new ArrayList<>(hit_cards);
    }

    public List<Card> getCards() {
        List<Card> ret_arr = this.getHitCards();
        ret_arr.add(0, hole_card);
        ret_arr.add(0, down_card);
        return ret_arr;
    }

    public boolean isSoft() {
        for (Card c : hit_cards) {
            if (c.getValue() == Value.ACE) return true;
        }
        return (down_card.getValue() == Value.ACE) || (hole_card.getValue() == Value.ACE);
    }

    public boolean isNatural() {
        return is_natural;
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
        boolean ace_high = (cur_total + 11 <= 21);
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

    public boolean hasBusted() {
        return this.getTotal() > 21;
    }

    public String toString(boolean is_down) {
        String ret_str = hole_card.toString(false);
        ret_str += down_card.toString(is_down);

        for (Card c: hit_cards) {
            ret_str += c.toString(false);
        }

        return ret_str;
    }
}

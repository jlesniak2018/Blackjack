package main;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jlesniak on 1/20/17.
 */
public class Hand {
    private Card down_card;
    private Card up_card;
    private List<Card> hit_cards;
    private boolean is_natural;

    public Hand(Card down_card, Card up_card) {
        this.down_card = down_card;
        this.up_card = up_card;
        this.hit_cards = new ArrayList<>();
        this.is_natural = this.getTotal() == 21;
    }

    /*public void setUpCard(Card c) {
        up_card = c;
    }

    public void setDownCard(Card c) {
        down_card = c;
    }*/

    public void addHitCard(Card c) {
        hit_cards.add(c);
    }

    public Card getUpCard() {
        return up_card;
    }

    public Card getDownCard() {
        return down_card;
    }

    public List<Card> getHitCards() {
        return new ArrayList<>(hit_cards);
    }

    public List<Card> getCards() {
        List<Card> ret_arr = this.getHitCards();
        ret_arr.add(0, up_card);
        ret_arr.add(0, down_card);
        return ret_arr;
    }

    public boolean isSoft() {
        int total = getNonAceTotal();
        int num_aces = 0;

        for (Card c : hit_cards) {
            if (c.getValue() == Value.ACE) ++num_aces;
        }

        num_aces += (down_card.getValue() == Value.ACE) ? 1 : 0;
        num_aces += (up_card.getValue() == Value.ACE) ? 1 : 0;

        if (num_aces == 0) return false;

        int min_soft_total = 0;
        for (int i = 1; i <= num_aces; ++i) {
            min_soft_total += (i == 1) ? 11 : 1;
        }

        return (21-total >= min_soft_total);
    }

    public boolean isNatural() {
        return is_natural;
    }

    private int getNonAceTotal() {
        int total = 0;

        if (up_card.getValue() != Value.ACE) total += up_card.getNumericalValue(false);
        if (down_card.getValue() != Value.ACE) total += down_card.getNumericalValue(false);

        for (Card c: hit_cards) {
            if (c.getValue() != Value.ACE) total += c.getNumericalValue(false);
        }

        return total;
    }

    private int evalAce(Card c, int cur_total, int extra_aces) {
        boolean ace_high = (cur_total + 11 + extra_aces <= 21);
        return c.getNumericalValue(ace_high);
    }

    private int getAceTotal(int cur_total) {
        int num_aces = 0;

        if (up_card.getValue() == Value.ACE) ++num_aces;
        if (down_card.getValue() == Value.ACE) ++num_aces;

        for (Card c: hit_cards) {
            if (c.getValue() == Value.ACE) ++num_aces;
        }

        if (up_card.getValue() == Value.ACE) {
            cur_total += (num_aces > 1) ? evalAce(up_card, cur_total, --num_aces) : evalAce(up_card, cur_total, 0);
        }

        if (down_card.getValue() == Value.ACE) {
            cur_total += (num_aces > 1) ? evalAce(down_card, cur_total, --num_aces) : evalAce(down_card, cur_total, 0);
        }

        for (Card c: hit_cards) {
            if (c.getValue() == Value.ACE) {
                cur_total += (num_aces > 1) ? evalAce(c, cur_total, --num_aces) : evalAce(c, cur_total, 0);
            }
        }

        return cur_total;
    }

    public int getTotal() {
        if (up_card == null || down_card == null) {
            return 0;
        }

        return getAceTotal(getNonAceTotal());
    }

    public boolean hasBusted() {
        return getTotal() > 21;
    }

    public String toString(boolean face_up) {
        String ret_str = down_card.toString(face_up);
        ret_str += up_card.toString(true);

        for (Card c: hit_cards) {
            ret_str += c.toString(true);
        }

        return ret_str;
    }
}

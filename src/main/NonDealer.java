package main;

/**
 * Created by jlesniak on 2/3/17.
 */
public class NonDealer extends Player {
    private int funds;

    public NonDealer(String name, int funds, boolean is_dealer) {
        this(null, name, funds, is_dealer);
    }

    public NonDealer(Hand hand, String name, int funds, boolean is_dealer) {
        super(hand, name, is_dealer);
        this.funds = funds;
    }

    public int getFunds() {
        return funds;
    }

    public void setFunds(int amount) {
        funds = amount;
    }

    public void addFunds(int amount) {
        funds += amount;
    }

    public boolean placeBet(int bet) {
        if (bet > funds) {
            System.out.println("Not enough funds.");
            return false;
        }
        funds -= bet;
        return true;
    }
}

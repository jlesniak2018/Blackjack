package main;

/**
 * Created by jlesniak on 2/3/17.
 */
import java.util.Scanner;

public class Main {
    private static Game game;

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);

        String name = "";
        while (name.equals("")) {
            // Create IllegalNameError
            System.out.print("Please enter your name: ");
            name = s.next();
        }

        int funds = 0;
        while (funds <= 0) {
            // Create IllegalFundsError
            System.out.print("Please enter how much money you'd like to start with: ");
            // Catch runtime error here
            funds = s.nextInt();
        }

        int num_decks = 0;
        while (num_decks <= 0) {
            // Create IllegalDecksError
            System.out.print("Please enter how many decks you'd like to play with: ");
            // Catch runtime error here
            num_decks = s.nextInt();
        }

        game = new Game(new Dealer("Dealer", true), new NonDealer(name, funds, false), new Deck(num_decks));
    }
}

package main;

/**
 * Created by jlesniak on 2/3/17.
 */
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    private static Game game;

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);

        String name = "";
        while (name.equals("")) {
            System.out.print("Please enter your name: ");
            name = s.nextLine().trim();
        }

        int funds = 0;
        while (funds <= 0) {
            System.out.print("Please enter how much money you'd like to start with: ");
            if (s.hasNextInt()) {
                funds = s.nextInt();
            }
            s.nextLine();
        }

        int num_decks = 0;
        while (num_decks <= 0) {
            System.out.print("Please enter how many decks you'd like to play with: ");
            if (s.hasNextInt()) {
                num_decks = s.nextInt();
            }
            s.nextLine();
        }

        game = new Game(new Dealer("Dealer", true), new NonDealer(name, funds, false), new Deck(num_decks));
        game.start();
    }
}

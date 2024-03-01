import java.util.ArrayList;
import java.util.Scanner;

public class Blackjack {
    Scanner kb;
    private Deck deck;
    private ArrayList<Card> player;
    private ArrayList<Card> dealer;

    public Blackjack() {
        deck = new Deck();
        player = new ArrayList<>();
        dealer = new ArrayList<>();
        kb = new Scanner(System.in);

    }
    public static void main(String[] args) {
        Blackjack game = new Blackjack();
        game.run();
    }

    private void run() {
        // dealCards()

        deck.shuffle();
        dealInitialCards();
        playerTurn();
        dealerTurn();
        determineWinner();

        //player turn
        System.out.println();
        System.out.println("hit or stay?");
        String response = kb.nextLine();

        int count = 52;
        while (response.equals("hit") && count > 0) {
            player.add(deck.getCard());
            System.out.println(player.getLast());
            count--;
        }

    }

    private void dealInitialCards() {
        player.add(deck.getCard());
        dealer.add(deck.getCard());
        player.add(deck.getCard());
        dealer.add(deck.getCard());

        System.out.println("Dealer's hand: " + dealer.get(0) + " [?]");
        System.out.println("Player's hand: " + player.get(0) + " " + player.get(1));
    }

    private int calculateHandValue(ArrayList<Card> hand) {
        int value = 0;
        int aceCount = 0;

        for (Card card : hand) {
            int cardValue = card.getValue();
            if (cardValue == 1) { // Ace
                aceCount++;
                value += 11; // Assume Ace is worth 11 initially
            } else if (cardValue > 10) { // Face cards
                value += 10;
            } else {
                value += cardValue;
            }
        }

        // Adjust Ace values if necessary
        while (aceCount > 0 && value > 21) {
            value -= 10; // Change Ace value from 11 to 1
            aceCount--;
        }

        return value;
    }

    private void playerTurn() {
        String response;
        do {
            System.out.println("Hit or stay?");
            response = kb.nextLine().toLowerCase();
            if (response.equals("hit")) {
                player.add(deck.getCard());
                System.out.println("Player's hand: " + player);
            }
        } while (response.equals("hit") && calculateHandValue(player) <= 21);
    }

    private void dealerTurn() {
        // Reveal the dealer's facedown card
        System.out.println("Dealer's hand: " + dealer);

        // Dealer hits until their hand value is 17 or higher
        while (calculateHandValue(dealer) < 17) {
            dealer.add(deck.getCard());
            System.out.println("Dealer hits: " + dealer.get(dealer.size() - 1));
        }

        // Print the final dealer's hand
        System.out.println("Dealer's final hand: " + dealer);
    }

    private void determineWinner() {
        int playerHandValue = calculateHandValue(player);
        int dealerHandValue = calculateHandValue(dealer);

        System.out.println("Player's hand value: " + playerHandValue);
        System.out.println("Dealer's hand value: " + dealerHandValue);

        if (playerHandValue > 21) {
            System.out.println("Player busts! Dealer wins.");
        } else if (dealerHandValue > 21 || playerHandValue > dealerHandValue) {
            System.out.println("Player wins!");
        } else if (playerHandValue < dealerHandValue) {
            System.out.println("Dealer wins!");
        } else {
            System.out.println("It's a tie!");
        }
    }

}

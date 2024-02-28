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
        player.add(deck.getCard());
        dealer.add(deck.getCard());
        player.add(deck.getCard());
        dealer.add(deck.getCard());

        System.out.println("dealer hand: \t" + dealer.get(0) + " [?]");
        System.out.println("player hand: \t" + player.get(0) + " " + player.get(1));

        //player turn
        System.out.println();
        System.out.println("hit or stay?");
        String response = kb.nextLine();

        int count = 52;
        if (response.equals("hit") && count > 0) {
            player.add(deck.getCard());
            System.out.println(player.getLast());
            count--;
        }
        else if (response.equals("stay") && count > 0) {
            System.out.println("dealer hand: \t" + dealer.get(0) + dealer.get(1));
            System.out.println("player hand: \t" + player.size());
        }











        //while(response.equals("hit")){
        //do {} while (logic);

//        for (int i = 0; i < player.size(); i++) {
//            player.get(i);
//        }
        // playerTurn()
        // dealerTurn()
        // calcHandValue()

    }

    public void calcHandValue() {

    }

}

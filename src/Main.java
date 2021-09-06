import java.util.ArrayList;


public class Main {
    public static void main(String[] args){
        Player p = new Player();
        p.getMainDeck().printDeck();
        p.getSideDeck().printDeck();
        p.playCard(2).printCard();
        p.getSideDeck().printDeck();
    }
}

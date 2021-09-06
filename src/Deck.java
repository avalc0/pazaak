import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

/**
 * Represents a deck of {@code Card} objects.
 */
public class Deck {
    protected ArrayList<Card> deck;
    private int size;
    private static Random rand = new Random();

    /**
     * Default constructor. Creates a 10-card random deck.
     */
    public Deck() {
        size = 10;
        deck = randomDeck();
    }

    /**
     * Creates a deck of a set size
     * @param size the number of cards in the deck
     */
    public Deck(int size) {
        this.size = size;
        deck = new ArrayList<>();
    }

    /**
     * Creates a Deck object using a list of Card objects
     * @param deck the list of cards to create the deck from
     */
    public Deck(ArrayList<Card> deck) {
        this.deck = deck;
        this.size = deck.size();
    }

    /**
     * @return a randomized deck consisting of {@code size} number of random Card objects
     */
    private ArrayList<Card> randomDeck() {
        ArrayList<Card> randDeck = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            randDeck.add(new Card());
        }
        return randDeck;
    }


    /**
     * Adds a {@param card} to the deck
     * @throws IndexOutOfBoundsException if deck is full
     */
    public void addCard(Card card) throws IndexOutOfBoundsException {
        if (deck.size() + 1 > size) throw new IndexOutOfBoundsException();
        deck.add(card);
    }

    /**
     * Removes the last card from the deck
     * @throws IndexOutOfBoundsException if deck is empty
     */
    public void removeCard() throws IndexOutOfBoundsException {
        removeCard(deck.size()-1);
    }

    /**
     * Removes the card at index {@param i} from the deck
     * @throws IndexOutOfBoundsException if there is no card at this index
     */
    public void removeCard(int i) throws IndexOutOfBoundsException {
        if (deck.size() < i) throw new IndexOutOfBoundsException();
        deck.remove(i);
    }

    /**
     * Creates a 4-card side deck from the main deck. does *not* remove cards from main deck
     * @return the four-card side deck
     * @throws IndexOutOfBoundsException if main deck is too small to make side deck
     */
    public Deck makeSideDeck() throws IndexOutOfBoundsException {
        if(deck.size() < 4) throw new IndexOutOfBoundsException();
        Collections.shuffle(deck);
        ArrayList<Card> subDeck = new ArrayList<>(deck.subList(0, 4));
        return new Deck(subDeck);
    }

    /**
     * @return size
     */
    public int getSize(){ return size; }

    /**
     * @return the card at index {@param i}
     */
    public Card getCard(int i) {return deck.get(i); }

    /**
     * prints all cards in the deck
     * */
    public void printDeck() {
        for (int i = 0; i < deck.size() - 1; i++) {
            deck.get(i).printCard();
            System.out.print(", ");
        }
        deck.get(deck.size()-1).printCard();
        System.out.println();
    }
}

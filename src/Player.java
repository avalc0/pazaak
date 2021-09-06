/**
 * Represents a player of the game. A player has a 10-card main deck and a 4-card side deck selected from the main deck
 */
public class Player {
    public Deck mainDeck;
    public Deck sideDeck;

    /**
     * Creates a player with a random main deck and selected side deck
     * @throws IndexOutOfBoundsException for deck-creation errors
     */
    public Player() throws IndexOutOfBoundsException {
        mainDeck = new Deck();
        sideDeck = mainDeck.makeSideDeck();
    }

    /**
     * re-selects a side deck from the main deck
     * @throws IndexOutOfBoundsException if side deck creation fails
     */
    public void drawNewSideDeck() throws IndexOutOfBoundsException {
        sideDeck = mainDeck.makeSideDeck();
    }

    /** @return the main deck */
    public Deck getMainDeck() {return mainDeck;}

    /** @return the side deck */
    public Deck getSideDeck() {return sideDeck;}

    /**
     * plays a card from the side deck, removing it from that deck
     * @param i the index of the card to be played
     * @return the card
     * @throws IndexOutOfBoundsException if card cannot be retrieved from that index
     */
    public Card playCard(int i) throws IndexOutOfBoundsException {
        Card toPlay = sideDeck.getCard(i);
        sideDeck.removeCard(i);
        return toPlay;
    }


}

/**
 * Represents a player of the game. A player has a 10-card main deck and a 4-card side deck selected from the main deck
 */
public class Player {
    private Deck mainDeck;
    private Deck sideDeck;
    private int score = 0;
    private boolean standing = false; // is the player taking the STAND action
    private boolean playedCard = false; // has the player played a card this round


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

    /**
     * plays a card from the side deck, removing it from that deck and incrementing the score.
     * @param i the index of the card to be played
     * @return the card
     * @throws IndexOutOfBoundsException if card cannot be retrieved from that index
     */
    public Card playCard(int i) throws IndexOutOfBoundsException {
        playedCard = true;
        Card toPlay = sideDeck.getCard(i);
        sideDeck.removeCard(i);
        if(toPlay.getMod()) score += toPlay.getValue();
        else score -= toPlay.getValue();
        return toPlay;
    }

    /**
     * "draws" a card, adding a random value (1-10) to the score.
     */
    public void draw() {
        int valDrawn = (int) Math.floor(Math.random()*(10) + 1);
        System.out.println("drew: " + valDrawn);
        score += valDrawn;
    }

    /**
     * takes the STAND action
     */
    public void stand() {
        standing = true;
    }

    /**
     * Resets the player's stats to the initial values
     */
    public void resetPlayer() {
        standing = false;
        score = 0;
        playedCard = false;
    }

    /**
     * sets the player to having not played a card this round.
     */
    public void resetPlayedCard() {playedCard = false;}

    /** @return the main deck */
    public Deck getMainDeck() {return mainDeck;}

    /** @return the side deck */
    public Deck getSideDeck() {return sideDeck;}

    /** @return the current total */
    public int getScore() {return score;}

    /** @return true if the player is taking the STAND action */
    public boolean isStanding() {return standing;}

    /** @return true if the player has played a card this round */
    public boolean isPlayedCard() {return playedCard;}

}

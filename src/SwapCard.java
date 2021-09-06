/**
 * Represents a card that can swap its modifier
 */
public class SwapCard extends Card {

    /**
     * Constructs a SwapCard
     * @param value the numerical value of the card
     * @param mod boolean variable distinguishing if card is positive (true) or negative (false)
     */
    public SwapCard(int value, boolean mod) throws IndexOutOfBoundsException {
        super(value, mod);
    }

    /**
     * Swaps between + and -
     */
    public void swapMod() {
        super.setMod(!super.getMod());
    }
}

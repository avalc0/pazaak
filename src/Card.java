import java.sql.SQLOutput;
import java.util.Random;

/**
 * Represents a Card, with a value and a +/- modifier
 */
public class Card {
    private int value;
    private boolean mod; // true is +, false is -
    private Random rand = new Random();

    // bounds of the card values
    private static int maxValue = 6;
    private static int minValue = 1;
    private static int range = maxValue - minValue + 1;


    /**
     * Default constructor for a card, with a random value and modifier
     */
    public Card() {
        value = (int)(Math.random() * range) + minValue;
        mod = rand.nextBoolean();
    }

    /**
     * Constructs a card with {@param value} and {@param mod}, where true is + and false is -
     * @throws IndexOutOfBoundsException if card value is outside of bounds
     */
    public Card(int value, boolean mod) throws IndexOutOfBoundsException {
        if (minValue > value || maxValue < value) throw new IndexOutOfBoundsException();
        this.value = value;
        this.mod = mod;
    }

    /**
     * @return the card's value
     */
    public int getValue() {
        return value;
    }

    /**
     * @return the card's modifier, where true is + and false is -
     */
    public boolean getMod() {
        return mod;
    }

    /**
     * @param mod sets the card modifier
     */
    public void setMod(boolean mod) {
        this.mod = mod;
    }

    /** prints the card */
    public void printCard() {
        if (!mod) System.out.print("-");
        System.out.print(value);
    }
}

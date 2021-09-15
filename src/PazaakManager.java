import java.util.Scanner;

/**
 * Manages a game of Pazaak
 */
public class PazaakManager {

    public Player player0 = new Player();
    public Player player1 = new Player();
    private int playerIdx = 0;

    public PazaakManager() {}

    /**
     * Plays a round of Pazaak in the command line, with two players. See in-line comments
     * TODO randomize starting player
     * TODO keep track of round victories, and account for winner of prior round starts this round
     * TODO keep track of round wins (to play a game).
     */
    public void playPazaakRound() {
        System.out.println("Type HELP for commands");
        newHand();
        boolean endRound = false; // should the round be ended
        do {
            System.out.println();
            // if both players are standing, ends the round
            if(player0.isStanding() && player1.isStanding()) {
                System.out.println("ROUND ENDED");
                break;
            }

            Player thisPlayer = getNextPlayer();
            System.out.println("Player " + playerIdx + "'s turn:");
            Scanner scnr = new Scanner(System.in);
            if(player0.isStanding() && player1.isStanding()) endRound = true;
            // if this player isn't standing (ie should draw a card)
            if(!thisPlayer.isStanding()) {
                thisPlayer.draw();
                // prints total score and current hand
                System.out.println("current total: " + thisPlayer.getScore());
                System.out.print("current hand: ");
                thisPlayer.getSideDeck().printDeck();

                loop:
                // while commands can be entered
                while (true) {
                    // if this player has exactly 20 score, they automatically do the STAND action.
                    if(thisPlayer.getScore() == 20) {
                        thisPlayer.stand();
                        System.out.println("Drew cards to get score to 20. Player STANDS.");
                        break;
                    }

                    if(player0.isStanding() && player1.isStanding()) break;
                    String t = scnr.nextLine().toUpperCase();
                    switch (t) {
                        // player takes the PLAY action
                        case "PLAY 1":
                        case "PLAY 2":
                        case "PLAY 3":
                        case "PLAY 4":
                            int i = Integer.parseInt(t.substring(t.length() - 1));
                            // if the player has already taken the PLAY action this turn, prevents them from playing
                            // a card
                            if(thisPlayer.isPlayedCard()) {
                                System.out.println("ERROR: cannot play more than one card per turn. STAND or END");
                                break;
                            }
                            // if index specified is larger than number of cards in side deck
                            if(thisPlayer.getSideDeck().getSize() < 1) {
                                System.out.println("ERROR: this index is too large");
                                break;
                            }
                            // plays the card at specified index
                            System.out.println("Played card " + thisPlayer.getSideDeck().getCard(i-1).getValueStr());
                            thisPlayer.playCard(i-1);
                            // if playing the card brings the score to 20, player takes STAND action. Commands stop.
                            if (thisPlayer.getScore() == 20) {
                                System.out.println("Player " + playerIdx + " score is 20. Player STANDS (score " + thisPlayer.getScore() + ")");
                                thisPlayer.stand();
                                break loop;
                            // if playing the card brings the score over 20, player automatically loses. Round ends.
                            } else if (thisPlayer.getScore() > 20) {
                                System.out.println("Player " + playerIdx + " busts.");
                                break loop;
                            }
                            System.out.println("current total: " + thisPlayer.getScore());
                            break;
                        // player takes the END TURN action, which moves to the next player
                        case "END TURN":
                        case "END":
                            System.out.println("Player " + playerIdx + " has ENDED their turn. Moving to next player." +
                                    "..");
                            break loop;
                        // player takes the STAND action, which locks their score and moves to the next player.
                        case "STAND":
                            System.out.println("Player " + playerIdx + " is STANDING (score " + thisPlayer.getScore() + ")");
                            thisPlayer.stand();
                            break loop;
                        // prints the current player's hand
                        case "HAND":
                            System.out.print("current hand: ");
                            thisPlayer.getSideDeck().printDeck();
                            System.out.println("Select new command...");
                            break;
                        // prints the current player's score
                        case "SCORE":
                            System.out.println("current score: " + thisPlayer.getScore());
                            System.out.println("Select new command...");
                            break;
                        // prints both player's scores
                        case "SCORES":
                            showScores();
                            System.out.println("Select new command...");
                            break;
                        // help menu
                        case "HELP":
                            System.out.println("END TURN, END: Ends the turn. Player will draw at the " +
                                    "beginning of their next turn.\nSTAND: locks the player's score for the round" +
                                            ".\nPLAY n: Play the nth card in the player's hand.\nHAND: shows the " +
                                    "current player's hand.\nSCORE: shows the current player's score\nSCORES: shows " +
                                    "both player's scores.\nHELP: shows this menu.");
                            System.out.println("Select new command...");

                            break;

                        // invalid command.
                        default:
                            System.out.println("invalid command");
                            break;
                    }
                }
            }
            // if the player is STANDING, skips their turn
            else {
                System.out.println("Player " + playerIdx +  " is STANDING (score " + thisPlayer.getScore() + ")" +
                        ". going" +
                        " to next player.");
            }
            // if a player's score is above 20 at the end of the round, end the round (the opposite player will win)
            if(player0.getScore() > 20 || player1.getScore() > 20) {
                endRound = true;
            }

            thisPlayer.resetPlayedCard();

        }
        while(!endRound);
        showScores();
        determineWinner();
    }


    /**
     * Toggles the current player.
     * @return the opposite player
     */
    private Player getNextPlayer() {
        if (playerIdx == 0) playerIdx = 1;
        else playerIdx = 0;

        if (playerIdx == 0) return player0;
        else return player1;
    }

    /**
     * determines and prints the winner based on current player scores
     */
    private void determineWinner() {

        if (player0.getScore() > 20) {
            System.out.println("Player 1 wins (Player 0 busted)");
        } else if (player1.getScore() > 20) {
            System.out.println("Player 0 wins (Player 1 busted)");
        } else if (player0.getScore() == player1.getScore()) {
            System.out.println("TIE");
        } else if (player0.getScore() > player1.getScore()) {
            System.out.println("Player 0 wins");
        } else {
            System.out.println("Player 0 wins");
        }
    }

    /**
     * Clears the STAND state and HasPlayed state of both players to start a new round (does NOT reset side decks/hands)
     */
    private void newHand() {
        player0.resetPlayer();
        player1.resetPlayer();
    }

    /**
     * prints both player's scores
     */
    private void showScores() {
        System.out.println("Player 0 score: " + player0.getScore());
        System.out.println("Player 1 score: " + player1.getScore());
    }

}

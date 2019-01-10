
import java.util.List;
import java.util.ArrayList;

/**
 * The ElevensBoard class represents the board in a game of Elevens.
 */
public class ThirteensBoard extends Board {

    /**
     * The size (number of cards) on the board.
     */
    private static final int BOARD_SIZE = 10;

    /**
     * The ranks of the cards for this game to be sent to the deck.
     */
    private static final String[] RANKS =
            {"ace", "2", "3", "4", "5", "6", "7", "8", "9", "10", "jack", "queen", "king"};

    /**
     * The suits of the cards for this game to be sent to the deck.
     */
    private static final String[] SUITS =
            {"spades", "hearts", "diamonds", "clubs"};

    /**
     * The values of the cards for this game to be sent to the deck.
     */
    private static final int[] POINT_VALUES =
            {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 0};

    /**
     * Flag used to control debugging print statements.
     */
    private static final boolean I_AM_DEBUGGING = false;


    /**
     * Creates a new <code>ElevensBoard</code> instance.
     */
    public ThirteensBoard() {
        super(BOARD_SIZE, RANKS, SUITS, POINT_VALUES);
    }

    /**
     * Determines if the selected cards form a valid group for removal.
     * In Elevens, the legal groups are (1) a pair of non-face cards
     * whose values add to 11, and (2) a group of three cards consisting of
     * a jack, a queen, and a king in some order.
     *
     * @param selectedCards the list of the indices of the selected cards.
     * @return true if the selected cards form a valid group for removal;
     * false otherwise.
     */
    /* *** TO BE IMPLEMENTED IN ACTIVITY 9 *** */
    @Override
    public boolean isLegal(List<Integer> selectedCards) {
        if (selectedCards.size() < 1 || selectedCards.size() > 2) {
            return false;
        } else {
            if (selectedCards.size() == 1) {
                return containsK(selectedCards);
            } else {
                int a = selectedCards.get(0);
                int b = selectedCards.get(1);
                if (this.cardAt(a).pointValue() + this.cardAt(b).pointValue() == 13) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Determine if there are any legal plays left on the board.
     * In Elevens, there is a legal play if the board contains
     * (1) a pair of non-face cards whose values add to 13, or (2) a king card.
     *
     * @return true if there is a legal play left on the board;
     * false otherwise.
     */
    @Override
    public boolean anotherPlayIsPossible() {
        /* *** TO BE IMPLEMENTED IN ACTIVITY 9 *** */
        List<Integer> temp = new ArrayList<Integer>();
        for (int i = 0; i < BOARD_SIZE; i++) {
            temp.add(i);
            if(containsK(temp)){
                return true;
            }
            temp.clear();
            for (int j = i; j < BOARD_SIZE; j++) {
                temp.add(i);
                temp.add(j);
                if (containsPairSum13(temp)) {
                    return true;
                }
                temp.clear();
            }
        }
        return false;
    }

    /**
     * Check for an 13-pair in the selected cards.
     *
     * @param selectedCards selects a subset of this board.  It is list
     *                      of indexes into this board that are searched
     *                      to find an 13-pair.
     * @return true if the board entries in selectedCards
     * contain an 13-pair; false otherwise.
     */
    private boolean containsPairSum13(List<Integer> selectedCards) {
        /* *** TO BE IMPLEMENTED IN ACTIVITY 9 *** */
        if (selectedCards.size() == 2) {
            if (this.cardAt(selectedCards.get(0)).pointValue() + this.cardAt(selectedCards.get(1)).pointValue() == 13) {
                return true;
            }
        }
        return false;

    }

    /**
     * Check for a K in the selected cards.
     *
     * @param selectedCards selects a subset of this board.  It is list
     *                      of indexes into this board that are searched
     *                      to find a K card.
     * @return true if the board entries in selectedCards
     * include a king; false otherwise.
     */
    private boolean containsK(List<Integer> selectedCards) {
        for (int i = 0; i < selectedCards.size(); i++) {
            if (this.cardAt(i).rank().equalsIgnoreCase("king")) {
                return true;
            }
        }
        return false;

    }
}
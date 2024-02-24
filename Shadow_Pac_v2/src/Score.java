import bagel.*;

/**
 * Class that stores the game score and allows display of it like a message.
 */
public class Score extends Message {
    private static final int SCORE_BOTTOM_LEFT_X = 25;
    private static final int SCORE_BOTTOM_LEFT_Y = 25;
    private static final int SCORE_FONT_SIZE = 20;
    private static final String SCORE_STRING = "SCORE ";
    private int scoreValue = 0;

    /**
     * Creates score with value initialized to 0.
     */
    public Score() {
        super(SCORE_STRING + 0, SCORE_FONT_SIZE, SCORE_BOTTOM_LEFT_X, SCORE_BOTTOM_LEFT_Y);
    }

    /**
     * This method increment the score according to the class of the eaten object.
     * @param eatenGameEntityClass This is the class of the eaten object.
     */
    public void increment(Class eatenGameEntityClass) {
        if (eatenGameEntityClass == Dot.class) {
            scoreValue += Dot.DOT_POINT;
        } else if (eatenGameEntityClass == Cherry.class) {
            scoreValue += Cherry.CHERRY_POINT;
        } else if (eatenGameEntityClass == Ghost.class) {
            scoreValue += Ghost.GHOST_FRENZY_POINT;
        }
        setContent(SCORE_STRING + scoreValue);
    }

    /**
     * This method gives the current score value.
     * @return int Returns the score value.
     */
    public int getScoreValue() {
        return scoreValue;
    }


}

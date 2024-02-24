import bagel.Input;

/**
 * Abstract class that represents the behaviour of each game level.
 */
public abstract class Level {

    protected static final String WALL_STRING = "Wall";
    protected static final String DOT_STRING = "Dot";
    protected static final String GHOST_STRING = "Ghost";
    private boolean isPlayerLegalMove = true;
    private int updateCount = 0;
    private boolean levelStarted = false;
    private boolean levelCompleted = false;
    private boolean lost = false;

    /**
     * This method is ued to set player's move's legality.
     * @param isPlayerLegalMove This is the boolean value for player's move legality.
     */
    protected void setIsPlayerLegalMove(boolean isPlayerLegalMove) {
        this.isPlayerLegalMove = isPlayerLegalMove;
    }

    /**
     * This method tells whether currently the player has a legal move.
     * @return boolean This returns PlayerLegalMove.
     */
    protected boolean getIsPlayerLegalMove() {
        return isPlayerLegalMove;
    }

    /**
     * This method gives the current frame's update count.
     * @return int This returns updateCount, the current level update count.
     */
    protected int getUpdateCount() {
        return updateCount;
    }

    /**
     * Thhis method increments the frame update count by 1.
     */
    protected void incrementUpdateCount() {
        updateCount += 1;
    }

    /**
     * This method resets the frame update count to 0.
     */
    protected void resetUpdateCount() {
        updateCount = 0;
    }

    /**
     * This method updates the game frame displayed according to the level logic.
     * @param input This is the input key pressed.
     */
    protected abstract void update(Input input);

    /**
     * Method used to read file,
     * find the positions of the game entities and create an array list of these objects
     */
    protected abstract void readCSV();

    /**
     * This method gives the starting state of the level.
     * @return boolean This returns whether the level has begun.
     */
    protected boolean isLevelStarted() {
        return levelStarted;
    }

    /**
     * This method sets the starting state of the level.
     * @param levelStarted This is the new level starting state.
     */
    protected void setLevelStarted(boolean levelStarted) {
        this.levelStarted = levelStarted;
    }

    /**
     * This method gives the finishing state of the level.
     * @return boolean This returns whether the level has been completed.
     */
    protected boolean isLevelCompleted() {
        return levelCompleted;
    }

    /**
     * This method sets the level is completed.
     */
    protected void setLevelCompleted() {
        this.levelCompleted = true;
    }

    /**
     * This method gives whether the game is lost in this level.
     * @return boolean This returns whether the game is lost in this level.
     */
    protected boolean isLost() {
        return lost;
    }

    /**
     * This method sets the game is lost in the level.
     */
    protected void setLost() {
        this.lost = true;
    }
}

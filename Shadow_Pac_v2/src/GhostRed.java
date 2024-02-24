import bagel.Image;
import bagel.util.Side;

/**
 * Class that represents eatable, frenziable red ghost.
 */
public class GhostRed extends Ghost {
    private static final Image GHOST_RED_IMAGE = new Image("res/ghostRed.png");
    private static final int GHOST_RED_SPEED = 1;
    private static final double GHOST_RED_FRENZY_SPEED = 0.5;

    /**
     * Create red ghost with its top left located at (topLeftX, topLeftY), initially moving to the right.
     * @param topLeftX This is the red ghost's x-coordinate.
     * @param topLeftY This is the red ghost's y-coordinate.
     */
    public GhostRed(double topLeftX, double topLeftY) {
        super(topLeftX, topLeftY);
        setImage(GHOST_RED_IMAGE);
        setGhostSpeed(GHOST_RED_SPEED);
        setMoveDirection(Side.RIGHT);
    }

    /**
     * This method resets the red ghost into its normal mode and original position.
     */
    public void reset() {
        super.reset();
        setImage(GHOST_RED_IMAGE);
        setGhostSpeed(GHOST_RED_SPEED);
    }

    /**
     * This method sets the red ghost into frenzy mode, in terms of value, image and speed.
     */
    public void setFrenzy() {
        super.setFrenzy();
        setGhostSpeed(GHOST_RED_FRENZY_SPEED);
    }

}

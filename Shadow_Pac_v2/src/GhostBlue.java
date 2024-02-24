import bagel.Image;
import bagel.util.Side;

/**
 * Class that represents eatable, frenziable blue ghost.
 */
public class GhostBlue extends Ghost {
    private static final Image GHOST_BLUE_IMAGE = new Image("res/ghostBlue.png");
    private static final int GHOST_BLUE_SPEED = 2;
    private static final double GHOST_BLUE_FRENZY_SPEED = 1.5;

    /**
     * Create blue ghost with its top left located at (topLeftX, topLeftY), initially moving down.
     * @param topLeftX This is the blue ghost's x-coordinate.
     * @param topLeftY This is the blue ghost's y-coordinate.
     */
    public GhostBlue(double topLeftX, double topLeftY) {
        super(topLeftX, topLeftY);
        setImage(GHOST_BLUE_IMAGE);
        setGhostSpeed(GHOST_BLUE_SPEED);
        setMoveDirection(Side.BOTTOM);
    }

    /**
     * This method sets the blue ghost into frenzy mode, in terms of value, image and speed.
     */
    public void setFrenzy() {
        super.setFrenzy();
        setGhostSpeed(GHOST_BLUE_FRENZY_SPEED);
    }

    /**
     * This method resets the blue ghost into its normal mode and original position.
     */
    public void reset() {
        super.reset();
        setImage(GHOST_BLUE_IMAGE);
        setGhostSpeed(GHOST_BLUE_SPEED);
    }

}

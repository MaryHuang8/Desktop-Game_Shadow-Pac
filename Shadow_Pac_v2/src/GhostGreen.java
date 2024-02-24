import bagel.Image;
import bagel.util.Side;

import java.util.Random;

/**
 * Class that represents eatable, frenziable green ghost.
 */
public class GhostGreen extends Ghost {
    private static final Image GHOST_GREEN_IMAGE = new Image("res/ghostGreen.png");
    private static final int GHOST_GREEN_SPEED = 4;
    private static final double GHOST_GREEN_FRENZY_SPEED = 3.5;
    private static final Side[] MOVE_DIRECTIONS = new Side[] {Side.LEFT, Side.RIGHT, Side.TOP, Side.BOTTOM};

    /**
     * Create green ghost with its top left located at (topLeftX, topLeftY), initially moving in a random direction.
     * @param topLeftX This is the green ghost's x-coordinate.
     * @param topLeftY This is the green ghost's y-coordinate.
     */
    public GhostGreen(double topLeftX, double topLeftY) {
        super(topLeftX, topLeftY);
        setImage(GHOST_GREEN_IMAGE);
        setGhostSpeed(GHOST_GREEN_SPEED);
        setMoveDirection(MOVE_DIRECTIONS[new Random().nextInt(MOVE_DIRECTIONS.length)]);
    }

    /**
     * This method resets the green ghost into its normal mode and original position.
     */
    public void reset() {
        super.reset();
        setImage(GHOST_GREEN_IMAGE);
        setGhostSpeed(GHOST_GREEN_SPEED);
    }

    /**
     * This method sets the green ghost into frenzy mode, in terms of value, image and speed.
     */
    public void setFrenzy() {
        super.setFrenzy();
        setGhostSpeed(GHOST_GREEN_FRENZY_SPEED);
    }

}

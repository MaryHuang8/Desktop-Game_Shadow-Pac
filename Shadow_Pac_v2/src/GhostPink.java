import bagel.Image;
import bagel.util.Side;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

/**
 * Class that represents eatable, frenziable pink ghost.
 */
public class GhostPink extends Ghost {
    private static final Image GHOST_PINK_IMAGE = new Image("res/ghostPink.png");
    private static final int GHOST_PINK_SPEED = 3;
    private static final double GHOST_PINK_FRENZY_SPEED = 2.5;
    private static final ArrayList<Side> MOVE_DIRECTIONS = new ArrayList<Side>(
            Arrays.asList(Side.LEFT, Side.RIGHT, Side.TOP, Side.BOTTOM));
    private ArrayList<Side> possibleMoveDirections = (ArrayList<Side>) MOVE_DIRECTIONS.clone();
    private Random randomMoveDirectionPicker = new Random();

    /**
     * Create pink ghost with its top left located at (topLeftX, topLeftY), initially moving in a random direction.
     * @param topLeftX This is the pink ghost's x-coordinate.
     * @param topLeftY This is the pink ghost's y-coordinate.
     */
    public GhostPink(double topLeftX, double topLeftY) {
        super(topLeftX, topLeftY);
        setImage(GHOST_PINK_IMAGE);
        setGhostSpeed(GHOST_PINK_SPEED);
        setMoveDirection(MOVE_DIRECTIONS.get(new Random().nextInt(MOVE_DIRECTIONS.size())));
    }

    /**
     * This method resets the pink ghost into its normal mode and original position.
     */
    public void reset() {
        super.reset();
        setImage(GHOST_PINK_IMAGE);
        setGhostSpeed(GHOST_PINK_SPEED);
    }

    /**
     * This method sets the pink ghost into frenzy mode, in terms of value, image and speed.
     */
    public void setFrenzy() {
        super.setFrenzy();
        setGhostSpeed(GHOST_PINK_FRENZY_SPEED);
    }

    /**
     * This method randomly sets the pink ghost's new move direction.
     */
    public void setNewMoveDirection() {
        possibleMoveDirections.remove(getMoveDirection());
        Side newMoveDirection = possibleMoveDirections.get(randomMoveDirectionPicker.nextInt(possibleMoveDirections.size()));
        super.setMoveDirection(newMoveDirection);
    }

    /**
     * This method moves the pink ghost to the new position after a step.
     * Continues to check for a legal move until the new move direction won't lead the pink ghost to collide with wall.
     * @param lv1Walls This is the array list of walls.
     */
    public void move(ArrayList<Wall> lv1Walls) {
        possibleMoveDirections = (ArrayList<Side>) MOVE_DIRECTIONS.clone();
        while (!isGhostLegalMove(lv1Walls)) {
            setNewMoveDirection();
        }
        if (getMoveDirection() == Side.RIGHT) {
            setTopLeftX(getTopLeftX() + getGhostSpeed());
        } else if (getMoveDirection() == Side.LEFT) {
            setTopLeftX(getTopLeftX() - getGhostSpeed());
        } else if (getMoveDirection() == Side.BOTTOM) {
            setTopLeftY(getTopLeftY() + getGhostSpeed());
        } else if (getMoveDirection() == Side.TOP) {
            setTopLeftY(getTopLeftY() - getGhostSpeed());
        }
    }

}

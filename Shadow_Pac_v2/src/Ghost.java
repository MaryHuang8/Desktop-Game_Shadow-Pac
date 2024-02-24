import bagel.Image;
import bagel.util.Point;
import bagel.util.Side;
import bagel.util.Rectangle;

import java.util.ArrayList;


/**
 * Class that represents all types of game entity ghosts, including those eatable once entering frenzy mode.
 */
public class Ghost extends GameEntity implements Eatable, Frenziable {
    private static final Image GHOST_LEVEL_0_IMAGE = new Image("res/ghostRed.png");
    private static final Image GHOST_FRENZY_IMAGE = new Image("res/ghostFrenzy.png");
    protected static final int GHOST_FRENZY_POINT = 30;
    private double startGhostX;
    private double startGhostY;
    private int value = 0;
    private double ghostSpeed = 0;
    private Side moveDirection = Side.NONE;

    /**
     * Create ghost with its top left located at (topLeftX, topLeftY), and records its starting position.
     * @param topLeftX This is the ghost's x-coordinate.
     * @param topLeftY This is the ghost's y-coordinate.
     */
    public Ghost(double topLeftX, double topLeftY) {
        super(topLeftX, topLeftY);
        startGhostX = topLeftX;
        startGhostY = topLeftY;
        setImage(GHOST_LEVEL_0_IMAGE);
    }

    /**
     * This method eats the ghost.
     * Ghost becomes invisible once it's eaten, and its value reset to 0.
     */
    public void eat() {
        value = 0;
        changeVisibility();
    }

    /**
     * This method gives the current value of the ghost.
     * @return int Returns the current score value of the ghost.
     */
    public int getValue() {
        return value;
    }

    /**
     * This method sets the common properties of ghosts when entering frenzy mode.
     * Respective frenzy speeds are implemented accordingly in each sub ghost class.
     */
    public void setFrenzy() {
        this.value = GHOST_FRENZY_POINT;
        setImage(GHOST_FRENZY_IMAGE);
    }

    /**
     * This method sets the speed of the ghost.
     * @param ghostSpeed This is the new speed of the ghost.
     */
    public void setGhostSpeed(double ghostSpeed) {
        this.ghostSpeed = ghostSpeed;
    }

    /**
     * This method returns the current speed of the ghost.
     * @return double Returns the current ghost speed.
     */
    public double getGhostSpeed() {
        return ghostSpeed;
    }

    /**
     * This method checks if the ghost will collide with a wall if continuing in the current move direction.
     * @param lv1Walls This is the array list of walls.
     * @return boolean Returns true if the ghost can legally move, false if the ghost will collide with wall.
     */
    public boolean isGhostLegalMove(ArrayList<Wall> lv1Walls) {

        Point nextGhostCenterPoint;
        double centerX = getTopLeftX() + getImage().getWidth()/2;
        double centerY = getTopLeftY() + getImage().getHeight()/2;
        if (moveDirection == Side.LEFT) {
            nextGhostCenterPoint = new Point(centerX - getGhostSpeed(), centerY);
        } else if (moveDirection == Side.RIGHT) {
            nextGhostCenterPoint = new Point(centerX + getGhostSpeed(), centerY);
        } else if (moveDirection == Side.BOTTOM) {
            nextGhostCenterPoint = new Point(centerX, centerY + getGhostSpeed());
        } else if (moveDirection == Side.TOP) {
            nextGhostCenterPoint = new Point(centerX, centerY - getGhostSpeed());
        } else {
            return true;
        }
        Rectangle theoreticalGhost = getImage().getBoundingBoxAt(nextGhostCenterPoint);
        Rectangle wallRectangle;

        for (Wall wall : lv1Walls) {
            wallRectangle = wall.getImage().getBoundingBoxAt(wall.getCenterPoint());
            if (wallRectangle.intersects(theoreticalGhost)) {
                return false;
            }
        }
        return true;
    }

    /**
     * This method sets the new move direction.
     * For all ghosts but the pink ghost, the change in move direction logic is to reverse the current direction.
     */
    public void setNewMoveDirection() {
        if (moveDirection == Side.LEFT) {
            this.moveDirection = Side.RIGHT;
        } else if (moveDirection == Side.RIGHT) {
            this.moveDirection = Side.LEFT;
        } else if (moveDirection == Side.TOP) {
            this.moveDirection = Side.BOTTOM;
        } else if (moveDirection == Side.BOTTOM) {
            this.moveDirection = Side.TOP;
        }

    }

    /**
     * This method moves the ghost to the new position after a step.
     * For all ghosts but the pink ghost, possibility of illegal move is checked once only for the current direction.
     * @param lv1Walls This is the array list of walls.
     */
    public void move(ArrayList<Wall> lv1Walls) {
        if (!isGhostLegalMove(lv1Walls)) {
            setNewMoveDirection();
        }
        if (moveDirection == Side.RIGHT) {
            setTopLeftX(getTopLeftX() + ghostSpeed);
        } else if (moveDirection == Side.LEFT) {
            setTopLeftX(getTopLeftX() - ghostSpeed);
        } else if (moveDirection == Side.BOTTOM) {
            setTopLeftY(getTopLeftY() + ghostSpeed);
        } else if (moveDirection == Side.TOP) {
            setTopLeftY(getTopLeftY() - ghostSpeed);
        }
    }

    /**
     * This method gives the ghost's current move direction.
     * @return Side Returns the ghost's current move direction.
     */
    protected Side getMoveDirection() {
        return moveDirection;
    }

    /**
     * This method sets the ghost's move direction.
     * @param moveDirection This is the new move direction of the ghost.
     */
    protected void setMoveDirection(Side moveDirection) {
        this.moveDirection = moveDirection;
    }

    /**
     * This method resets the ghost to its original position.
     */
    public void resetPosition() {
        setTopLeftX(startGhostX);
        setTopLeftY(startGhostY);
    }

    /**
     * This method resets the ghost to its original position and becomes visible again if it was eaten.
     * Respective normal speeds and ghost images are implemented accordingly in each sub ghost class.
     */
    public void reset() {
        if (!getVisible()) {
            changeVisibility();
        }
        resetPosition();
        value = 0;
    }
}

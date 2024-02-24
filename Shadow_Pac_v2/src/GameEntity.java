import bagel.*;
import bagel.util.Point;

/**
 * Abstract class that represents all game entities in the game.
 */
public abstract class GameEntity {
    private double topLeftX;
    private double topLeftY;

    private boolean isVisible = true;

    private Image image;

    /**
     * Create game entity with its top left located at (topLeftX, topLeftY).
     * @param topLeftX This is the game entity's x-coordinate.
     * @param topLeftY This is the game entity's y-coordinate.
     */
    public GameEntity(double topLeftX, double topLeftY) {
        this.topLeftX = topLeftX;
        this.topLeftY = topLeftY;
    }


    /**
     * This method draws the game entity on the game window if it is not eaten.
     */
    public void draw() {
        if (isVisible) {
            image.drawFromTopLeft(topLeftX, topLeftY);
        }
    }

    /**
     * This method gives the image of the game entity.
     * @return Image Returns the image of the game entity.
     */
    public Image getImage() {
        return image;
    }

    /**
     * This method sets the image of the game entity.
     * @param image This is the image of the game entity.
     */
    protected void setImage(Image image) {
        this.image = image;
    }

    /**
     * This method calculates the center point of the game entity's image.
     * @return Point Returns the center point of the game entity's image
     */
    public Point getCenterPoint() {
        double centerX = topLeftX + image.getWidth()/2;
        double centerY = topLeftY + image.getHeight()/2;
        Point centerPoint = new Point(centerX, centerY);
        return centerPoint;
    }

    /**
     * This method gives the x-coordinate of the top left of the game entity.
     * @return double Returns the x-coordinate of the top left of the game entity.
     */
    public double getTopLeftX() {
        return topLeftX;
    }

    /**
     * This method gives the y-coordinate of the top left of the game entity.
     * @return double Returns the y-coordinate of the top left of the game entity.
     */
    public double getTopLeftY() {
        return topLeftY;
    }

    /**
     * This method sets the x-coordinate of the top left of the game entity.
     * @param topLeftX This is the new x-coordinate of the top left of the game entity.
     */
    public void setTopLeftX(double topLeftX) {
        this.topLeftX = topLeftX;
    }

    /**
     * This method sets the y-coordinate of the top left of the game entity.
     * @param topLeftY This is the new y-coordinate of the top left of the game entity.
     */
    public void setTopLeftY(double topLeftY) {
        this.topLeftY = topLeftY;
    }

    /**
     * This method switches the visibility of the game entity.
     */
    public void changeVisibility() {
        isVisible = !isVisible;
    }

    /**
     * This method gives the current state of visibility of the game entity.
     * @return boolean Returns the current state of visibility of the game entity.
     */
    public boolean getVisible() {
        return isVisible;
    }
}

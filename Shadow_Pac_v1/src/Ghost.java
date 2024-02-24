import bagel.Image;
import bagel.util.Point;

public class Ghost extends GameEntity {
    private static final Image IMAGE = new Image("res/ghostRed.png");

    public Ghost(double topRightX, double topRightY) {
        super(topRightX, topRightY);
    }
    public Image getImage() {
        return IMAGE;
    }

    // Draws the ghost image at its x & y coordinates
    public void draw() {
        IMAGE.drawFromTopLeft(super.getTopLeftX(), super.getTopLeftY());
    }

    // Return the center point of the ghost's image
    public Point getCenterPoint() {
        double centerX = super.getTopLeftX() + IMAGE.getWidth()/2;
        double centerY = super.getTopLeftY() + IMAGE.getHeight()/2;
        Point centerPoint = new Point(centerX, centerY);
        return centerPoint;
    }

}

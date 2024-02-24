import bagel.Image;
import bagel.util.Point;

public class Wall extends GameEntity {
    private final static Image IMAGE = new Image("res/wall.png");

    public Wall(double topRightX, double topRightY) {
        super(topRightX, topRightY);
    }
    public Image getImage() {
        return IMAGE;
    }

    // Draws the wall image at its x & y coordinates
    public void draw() {
        IMAGE.drawFromTopLeft(super.getTopLeftX(), super.getTopLeftY());
    }

    // Return the center point of the wall's image
    public Point getCenterPoint() {
        double centerX = super.getTopLeftX() + IMAGE.getWidth()/2;
        double centerY = super.getTopLeftY() + IMAGE.getHeight()/2;
        return new Point(centerX, centerY);
    }
}

import bagel.Image;
import bagel.util.Point;

public class Dot extends GameEntity {
    private static final Image IMAGE = new Image("res/dot.png");
    private boolean gone = false;
    private int value = 10;

    public Dot(double topRightX, double topRightY) {
        super(topRightX, topRightY);
    }
    public Image getImage() {
        return IMAGE;
    }

    // Draws the dot image at its x & y coordinates
    public void draw() {
        if (!gone) {
            IMAGE.drawFromTopLeft(super.getTopLeftX(), super.getTopLeftY());
        }
    }

    // Return the center point of the dot's image
    public Point getCenterPoint() {
        double centerX = super.getTopLeftX() + IMAGE.getWidth()/2;
        double centerY = super.getTopLeftY() + IMAGE.getHeight()/2;
        Point centerPoint = new Point(centerX, centerY);
        return centerPoint;
    }
    public void setGone() {
        value = 0;
        gone = true;
    }
    public boolean getGone() {
        return gone;
    }

    public int getValue() {
        return value;
    }
}

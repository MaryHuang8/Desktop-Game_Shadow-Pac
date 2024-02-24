import bagel.*;
import bagel.util.Point;
public abstract class GameEntity implements Drawable {
    private double topLeftX;
    private double topLeftY;
    private boolean gone = false;

    public GameEntity(double topLeftX, double topLeftY) {
        this.topLeftX = topLeftX;
        this.topLeftY = topLeftY;

    }


    public abstract void draw();


    public abstract Image getImage();

    public abstract Point getCenterPoint();

    public void setGone() {
        gone = true;
    }

    public double getTopLeftX() {
        return topLeftX;
    }

    public double getTopLeftY() {
        return topLeftY;
    }

    public void setTopLeftX(double topRightX) {
        this.topLeftX = topRightX;
    }

    public void setTopLeftY(double topRightY) {
        this.topLeftY = topRightY;
    }
}

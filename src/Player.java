import bagel.DrawOptions;
import bagel.Image;
import bagel.Input;
import bagel.Keys;
import bagel.util.Point;

import static java.lang.Math.PI;

public class Player extends GameEntity {
    private static final int MAX_LIVES = 3;
    private static final double PLAYER_STEP_SIZE = 3;
    private static final Image PAC = new Image("res/pac.png");
    private static final Image PAC_OPEN = new Image("res/pacOpen.png");
    private Image image = PAC;
    private int numLives = MAX_LIVES;

    public Player(double topRightX, double topRightY) {
        super(topRightX, topRightY);
    }

    public Image getImage() {
        return image;
    }

    public void changeMouth(boolean pacIsOpen) {
        if (pacIsOpen) {
            image = PAC_OPEN;
        } else {
            image = PAC;
        }
    }


    private double rotation = 0;

    public void move(Input input) {


        if (input.isDown(Keys.LEFT)) {
            super.setTopLeftX(super.getTopLeftX() - PLAYER_STEP_SIZE);
        }
        if (input.isDown(Keys.RIGHT)) {
            super.setTopLeftX(super.getTopLeftX() + PLAYER_STEP_SIZE);
        }
        if (input.isDown(Keys.UP)) {
            super.setTopLeftY(super.getTopLeftY() - PLAYER_STEP_SIZE);
        }
        if (input.isDown(Keys.DOWN)) {
            super.setTopLeftY(super.getTopLeftY() + PLAYER_STEP_SIZE);
        }

    }
    public void rotate(Input input) {
        if (input.isDown(Keys.LEFT)) {
            rotation = PI;
        }
        if (input.isDown(Keys.RIGHT)) {
            rotation = 0;
        }
        if (input.isDown(Keys.UP)) {
            rotation = 3*PI/2;
        }
        if (input.isDown(Keys.DOWN)) {
            rotation = PI/2;
        }
    }

    // Draws the player image at its x & y coordinates with the corresponding rotation
    @Override
    public void draw() {

        DrawOptions drawOption = new DrawOptions();
        image.drawFromTopLeft(super.getTopLeftX(), super.getTopLeftY(), drawOption.setRotation(rotation));
    }

    public void drawStart() {
        rotation = 0;
        image.drawFromTopLeft(super.getTopLeftX(), super.getTopLeftY());
    }

    // Return the center point of the player's image
    public Point getCenterPoint() {
        double centerX = super.getTopLeftX() + image.getWidth()/2;
        double centerY = super.getTopLeftY() + image.getHeight()/2;
        Point centerPoint = new Point(centerX, centerY);
        return centerPoint;
    }

    // decrement number of lives left by 1, return true if all lives are lost.
    public boolean loseALife() {
        numLives -= 1;
        if (numLives == 0) {
            return true;
        }
        return false;
    }

    public int getNumLives() {
        return numLives;
    }


}

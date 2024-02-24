import bagel.DrawOptions;
import bagel.Image;
import bagel.Input;
import bagel.Keys;

import static java.lang.Math.PI;

/**
 * Player of the game - a Pac-Man that moves and rotates according to key input.
 */
public class Player extends GameEntity implements Frenziable {
    protected static final int MAX_LIVES = 3;
    private static final int PLAYER_NORMAL_SPEED = 3;
    private static final int PLAYER_FRENZY_SPEED = 4;
    private static final Image PAC = new Image("res/pac.png");
    private static final Image PAC_OPEN = new Image("res/pacOpen.png");
    public static final int MOUTH_MOVE_INTERVAL = 15;
    private boolean pacIsOpen = false;
    private int numLives = MAX_LIVES;
    private double playerSpeed = PLAYER_NORMAL_SPEED;
    private double startPlayerX;
    private double startPlayerY;
    private double rotation = 0;

    /**
     * Create player with its top left located at (topLeftX, topLeftY), and records its starting position.
     * @param topLeftX This is the player's x-coordinate.
     * @param topLeftY This is the player's y-coordinate.
     */
    public Player(double topLeftX, double topLeftY) {
        super(topLeftX, topLeftY);
        startPlayerX = topLeftX;
        startPlayerY = topLeftY;
        setImage(PAC);
    }

    /**
     * This method opens or closes the mouth of the Pac-Man.
     */
    public void changeMouth() {
        pacIsOpen = !pacIsOpen;
        if (pacIsOpen) {
            setImage(PAC_OPEN);
        } else {
            setImage(PAC);
        }
    }

    /**
     * This method moves the player according to the key input.
     * @param input This is the key input.
     */
    public void move(Input input) {

        if (input.isDown(Keys.LEFT)) {
            setTopLeftX(getTopLeftX() - playerSpeed);
        }
        if (input.isDown(Keys.RIGHT)) {
            setTopLeftX(getTopLeftX() + playerSpeed);
        }
        if (input.isDown(Keys.UP)) {
            setTopLeftY(getTopLeftY() - playerSpeed);
        }
        if (input.isDown(Keys.DOWN)) {
            setTopLeftY(getTopLeftY() + playerSpeed);
        }

    }

    /**
     * This method rotates the player according to the key input.
     * @param input This is the key input.
     */
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

    /**
     * This method draws the player image at its current position with its current rotation.
     */
    @Override
    public void draw() {
        DrawOptions drawOption = new DrawOptions();
        getImage().drawFromTopLeft(super.getTopLeftX(), super.getTopLeftY(), drawOption.setRotation(rotation));
    }

    /**
     * This method resets the player to its initial position and face right.
     */
    public void resetPosition() {
        rotation = 0;
        setTopLeftX(startPlayerX);
        setTopLeftY(startPlayerY);
    }

    /**
     * This method reduces the player's number of lives by 1.
     * @return boolean Returns true if the player lost all its lives, false if the player is still alive.
     */
    public boolean loseALife() {
        numLives -= 1;
        if (numLives == 0) {
            return true;
        }
        return false;
    }

    /**
     * This method gives the current number of lives that the player has.
     * @return int Returns current number of lives that the player has.
     */
    public int getNumLives() {
        return numLives;
    }

    /**
     * This method sets the player into frenzy mode.
     */
    public void setFrenzy() {
        playerSpeed = PLAYER_FRENZY_SPEED;
    }

    /**
     * This method resets the player into normal mode.
     */
    public void resetSpeed() {
        playerSpeed = PLAYER_NORMAL_SPEED;
    }


}

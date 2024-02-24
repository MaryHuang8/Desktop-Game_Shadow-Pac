import bagel.Image;

/**
 * Class that represents game entity pellet to be eaten to enter frenzy mode.
 */
public class Pellet extends GameEntity implements Eatable {
    private static final Image PELLET_IMAGE = new Image("res/pellet.png");

    /**
     * Create pellet with its top left located at (topLeftX, topLeftY).
     * @param topLeftX This is the pellet's x-coordinate.
     * @param topLeftY This is the pellet's y-coordinate.
     */
    public Pellet(double topLeftX, double topLeftY) {
        super(topLeftX, topLeftY);
        setImage(PELLET_IMAGE);
    }

    /**
     * Eat the pellet so it becomes invisible.
     */
    @Override
    public void eat() {
        changeVisibility();
    }
}

import bagel.Image;

/**
 * Class that represents eatable game entity dot.
 */
public class Dot extends GameEntity implements Eatable {
    private static final Image DOT_IMAGE = new Image("res/dot.png");
    protected static final int DOT_POINT = 10;

    /**
     * Create dot with its top left located at (topLeftX, topLeftY).
     * @param topLeftX This is the dot's x-coordinate.
     * @param topLeftY This is the dot's y-coordinate.
     */
    public Dot(double topLeftX, double topLeftY) {
        super(topLeftX, topLeftY);
        setImage(DOT_IMAGE);
    }

    /**
     * Eat the dot so it becomes invisible.
     */
    public void eat() {
        changeVisibility();
    }
}

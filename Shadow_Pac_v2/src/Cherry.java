import bagel.Image;

/**
 * Class that represents eatable game entity cherry.
 */
public class Cherry extends GameEntity implements Eatable {
    private static final Image CHERRY_IMAGE = new Image("res/cherry.png");
    protected static final int CHERRY_POINT = 20;

    /**
     * Create cherry with its top left located at (topLeftX, topLeftY).
     * @param topLeftX This is the cherry's x-coordinate.
     * @param topLeftY This is the cherry's y-coordinate.
     */
    public Cherry(double topLeftX, double topLeftY) {
        super(topLeftX, topLeftY);
        setImage(CHERRY_IMAGE);
    }

    /**
     * Eat the cherry so it becomes invisible.
     */
    public void eat() {
        changeVisibility();
    }
}

import bagel.Image;

/**
 * Class that represents game entity wall.
 */
public class Wall extends GameEntity {
    private final static Image WALL_IMAGE = new Image("res/wall.png");

    /**
     * Create wall with its top left located at (topLeftX, topLeftY).
     * @param topLeftX This is the wall's x-coordinate.
     * @param topLeftY This is the wall's y-coordinate.
     */
    public Wall(double topLeftX, double topLeftY) {
        super(topLeftX, topLeftY);
        setImage(WALL_IMAGE);
    }


}

import bagel.Image;

/**
 * Hearts that allows display according to player's number of lives.
 */
public class Heart {
    private static final int FIRST_HEART_TOP_LEFT_X = 900;
    private static final int FIRST_HEART_TOP_LEFT_Y = 10;
    private static final int TOP_LEFT_X_INCREMENT = 30;
    private static final Image HEART_IMAGE = new Image("res/heart.png");


    /**
     * Create heart at expected position.
     */
    public Heart() {
    }

    /**
     * This method draws the current number of hearts at their corresponding positions on game window.
     * @param numPlayerLives This is the number of lives the player currently has.
     */
    public void draw(int numPlayerLives) {
        int curTopLeftX =  FIRST_HEART_TOP_LEFT_X;
        for (int i=0; i<numPlayerLives; i++) {
            HEART_IMAGE.drawFromTopLeft(curTopLeftX, FIRST_HEART_TOP_LEFT_Y);
            curTopLeftX += TOP_LEFT_X_INCREMENT;
        }
    }
}

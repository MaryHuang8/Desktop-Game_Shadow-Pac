import bagel.Image;
public class Heart implements Drawable {
    private static final int FIRST_HEART_TOP_LEFT_X = 900;
    private static final int FIRST_HEART_TOP_LEFT_Y = 10;
    private static final int TOP_LEFT_X_INCREMENT = 30;
    private static final Image IMAGE = new Image("res/heart.png");
    private int numHearts;

    public Heart(Player player) {
        numHearts = player.getNumLives();
    }

    // Draws the current number of heart images at their corresponding positions on game window
    public void draw() {
        int curTopLeftX =  FIRST_HEART_TOP_LEFT_X;
        for (int i=0; i<numHearts; i++) {
            IMAGE.drawFromTopLeft(curTopLeftX, FIRST_HEART_TOP_LEFT_Y);
            curTopLeftX += TOP_LEFT_X_INCREMENT;
        }
    }
}

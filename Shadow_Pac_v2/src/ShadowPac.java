import bagel.*;

/**
 * Skeleton Code for SWEN20003 Project 2B, Semester 1, 2023
 *
 * Shadow Pac
 * @author Luoman (Mary) Huang
 */
public class ShadowPac extends AbstractGame  {
    private final static int WINDOW_WIDTH = 1024;
    private final static int WINDOW_HEIGHT = 768;
    private final static String GAME_TITLE = "SHADOW PAC";
    private final Image BACKGROUND_IMAGE = new Image("res/background0.png");

    /**
     * Create Shadow Pac object - opens the game window titled "SHADOW PAC".
     */
    public ShadowPac(){
        super(WINDOW_WIDTH, WINDOW_HEIGHT, GAME_TITLE);
    }
    protected static final int TITLE_BOTTOM_LEFT_X = 260;
    protected static final int TITLE_BOTTOM_LEFT_Y = 250;
    private static final String TITLE_STRING = "SHADOW PAC";
    private final Message TITLE_MESSAGE = new Message(TITLE_STRING, TITLE_BOTTOM_LEFT_X, TITLE_BOTTOM_LEFT_Y);
    private final Message WIN_MESSAGE = new Message("WELL DONE!");
    private final Message LOSE_MESSAGE = new Message("GAME OVER!");
    protected final Message LEVEL_COMPLETE_MESSAGE = new Message("LEVEL COMPLETE!");
    private static final int LV0_COMPLETE_DURATION = 300;

    private Level0 level0 = new Level0();
    private Level1 level1 = new Level1();

    /**
     * Method used to read file,
     * find the positions of the player, the ghosts, the walls and the dots and create objects
     */
    protected void readCSV() {
        level0.readCSV();
        level1.readCSV();
    }

    /**
     * The entry point for the program.
     */
    public static void main(String[] args) {
        ShadowPac game = new ShadowPac();
        game.readCSV();
        game.run();
    }

    /**
     * Performs a state update.
     * Allows the game to exit when the escape key is pressed.
     */
    @Override
    protected void update(Input input) {

        if (input.wasPressed(Keys.ESCAPE)) {
            Window.close();
        }
        BACKGROUND_IMAGE.draw(Window.getWidth() / 2.0, Window.getHeight() / 2.0);

        // if player loses all three lives in a level, display lose message
        if (level0.isLost() || level1.isLost()) {
            LOSE_MESSAGE.print();

        // if both levels are completed, display win message
        } else if (level0.isLevelCompleted() && level1.isLevelCompleted()) {
            WIN_MESSAGE.print();

        // Display the starting title and instruction message at the start of the game
        } else if (!level0.isLevelStarted()) {
            TITLE_MESSAGE.print();
            Level0.LEVEL_0_INSTRUCTION_MESSAGE_1.print();
            Level0.LEVEL_0_INSTRUCTION_MESSAGE_2.print();

            if (input.isDown(Keys.SPACE)) {
                level0.setLevelStarted(true);
            }

        // entering level 0 mechanisms
        } else if (level0.isLevelStarted() && !level0.isLevelCompleted()) {
            level0.update(input);
            level0.incrementUpdateCount();

        // Display level transition messages
        } else if (level0.isLevelCompleted() && !level1.isLevelStarted()) {

            if (level0.getUpdateCount() < LV0_COMPLETE_DURATION) {
                LEVEL_COMPLETE_MESSAGE.print();

            } else {
                Level1.LEVEL_1_INSTRUCTION_MESSAGE_1.print();
                Level1.LEVEL_1_INSTRUCTION_MESSAGE_2.print();
                Level1.LEVEL_1_INSTRUCTION_MESSAGE_3.print();
                // Reset player and score for level 1
                if (input.isDown(Keys.SPACE)) {
                    level1.setLevelStarted(true);
                }
            }
            level0.incrementUpdateCount();

        // entering level 1 mechanisms
        } else if (level1.isLevelStarted() && !level1.isLevelCompleted()) {
                level1.update(input);
                level1.incrementUpdateCount();
        }

    }

}

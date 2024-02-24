import bagel.Font;
import bagel.Input;
import bagel.util.Point;
import bagel.util.Rectangle;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Class that represents the behaviour of level 0 game and movements of game entities within level 0.
 */
public class Level0 extends Level {
    /* Level 0 instruction messages info */
    private static final int LEVEL_0_INSTRUCTION_INCREMENT_X = 60;
    private static final int LEVEL_0_INSTRUCTION_INCREMENT_Y = 190;
    protected static final String INSTRUCTION_1_CONTENT = "PRESS SPACE TO START";
    protected static final String INSTRUCTION_2_CONTENT = "USE ARROW KEYS TO MOVE";
    private final static int LEVEL_0_INSTRUCTION_FONT_SIZE = 24;
    private final static Font LEVEL_0_INSTRUCTION_FONT = new Font(
            "res/FSO8BITR.TTF",LEVEL_0_INSTRUCTION_FONT_SIZE);
    protected final static Message LEVEL_0_INSTRUCTION_MESSAGE_1 = new Message(
            INSTRUCTION_1_CONTENT, LEVEL_0_INSTRUCTION_FONT, LEVEL_0_INSTRUCTION_FONT_SIZE,
            ShadowPac.TITLE_BOTTOM_LEFT_X + LEVEL_0_INSTRUCTION_INCREMENT_X,
            ShadowPac.TITLE_BOTTOM_LEFT_Y + LEVEL_0_INSTRUCTION_INCREMENT_Y);
    protected final static Message LEVEL_0_INSTRUCTION_MESSAGE_2 = new Message(
            INSTRUCTION_2_CONTENT, LEVEL_0_INSTRUCTION_FONT, LEVEL_0_INSTRUCTION_FONT_SIZE,
            LEVEL_0_INSTRUCTION_MESSAGE_1.getBottomLeftY() + 2 * LEVEL_0_INSTRUCTION_FONT_SIZE);

    private final static String WORLD_FILE_0 = "res/level0.csv";
    private int numDots = 0;
    private int winScore = numDots * Dot.DOT_POINT;
    private ArrayList<GameEntity> lv0GameEntities = new ArrayList<GameEntity>();
    private Player player;
    private Heart heart = new Heart();
    private Score score = new Score();


    /**
     * Creates level0 with expected level 0 starting logic.
     */
    public Level0() {

    }

    /**
     * Method used to read level 0 game objects file,
     * find the positions of the player, the ghosts, the walls and the dots and create objects
     */
    protected void readCSV() {

        // Read the level 0 world file
        try (BufferedReader br = new BufferedReader(new FileReader(WORLD_FILE_0))) {

            // the 1st line definitely contains player information
            // store the initial position of the player read from the 1st line of the world file, and initiate player
            String playerLine = br.readLine();
            String playerCells[] = playerLine.split(",");
            double startPlayerX0 = Double.parseDouble(playerCells[1]);
            double startPlayerY0 = Double.parseDouble(playerCells[2]);
            player = new Player(startPlayerX0, startPlayerY0);

            String text;
            // store all stationary game entities wall, dot, ghost into a lv0GameEntities array
            while ((text = br.readLine()) != null) {
                String cells[] = text.split(",");
                double curX = Double.parseDouble(cells[1]);
                double curY = Double.parseDouble(cells[2]);

                if (cells[0].equals(Level.GHOST_STRING)) {
                    lv0GameEntities.add(new Ghost(curX, curY));
                } else if (cells[0].equals(Level.WALL_STRING)) {
                    lv0GameEntities.add(new Wall(curX, curY));
                } else if (cells[0].equals(Level.DOT_STRING)) {
                    lv0GameEntities.add(new Dot(curX, curY));
                    numDots += 1;
                }
            }
            winScore = numDots * Dot.DOT_POINT;

        } catch (FileNotFoundException e) {
            e.printStackTrace();
            System.out.println("File is not found");
        } catch (ArrayIndexOutOfBoundsException e) {
            e.printStackTrace();
            System.out.println("Caught an index exception");
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Caught an IO exception");
        }
    }

    /**
     * This method updates the game frame displayed according to the level 0 logic.
     * @param input This is the input key pressed.
     */
    protected void update(Input input) {
        // update the hearts and score displayed
        heart.draw(player.getNumLives());
        score.print();

        Point playerCenterPoint = player.getCenterPoint();
        Rectangle playerRectangle = player.getImage().getBoundingBoxAt(playerCenterPoint);
        // a theoretical player to test if a move will be legal i.e. if the player will move into a wall
        Player theoreticalPlayer = new Player(player.getTopLeftX(), player.getTopLeftY());
        theoreticalPlayer.move(input);
        Point theorPlayerCenterPoint = theoreticalPlayer.getCenterPoint();
        Rectangle theorPlayerRectangle = theoreticalPlayer.getImage().getBoundingBoxAt(theorPlayerCenterPoint);
        for (GameEntity gameEntity : lv0GameEntities) {
            Point gameEntityCenterPoint = gameEntity.getCenterPoint();
            Rectangle gameEntityRectangle = gameEntity.getImage().getBoundingBoxAt(gameEntityCenterPoint);

            // Player collides with a stationary game entity
            if (playerRectangle.intersects(gameEntityRectangle)) {

                if (gameEntity.getClass() == Ghost.class) {
                    // player loses a life if collides with a ghost. If all lives are lost game is lost.
                    if (player.loseALife()) {
                        setLost();
                        setLevelCompleted();
                    }
                    player.resetPosition();
                }
                if (gameEntity.getClass() == Dot.class && (gameEntity.getVisible())) {
                    // The dot is eaten
                    ((Dot) gameEntity).eat();
                    score.increment(Dot.class);
                    if (score.getScoreValue() == winScore) {
                        setLevelCompleted();
                        resetUpdateCount();
                    }

                }

            }

            // if Pac-Man moves according to the input key, Pac-Man will intersect with a wall - an illegal move
            if (theorPlayerRectangle.intersects(gameEntityRectangle) && (gameEntity.getClass() == Wall.class)) {
                setIsPlayerLegalMove(false);
            }

            gameEntity.draw();
        }

        // Draw Pacman with a moving mouth every 15 frames
        if (getUpdateCount() % Player.MOUTH_MOVE_INTERVAL == 0) {
            player.changeMouth();
        }

        // only modify the position of the player if the input move is legal
        if (getIsPlayerLegalMove()) {
            player.move(input);
        } else {
            setIsPlayerLegalMove(true);
        }
        // player will rotate according to the move direction regardless of whether it can move or not
        player.rotate(input);

        // Display the player's new position in the game window
        player.draw();
    }

}

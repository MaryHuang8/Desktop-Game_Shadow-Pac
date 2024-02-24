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
 * Class that represents the behaviour of level 1 game and movements of game entities within level 1.
 */
public class Level1 extends Level {
    /* Level 1 instruction messages info */
    public static final String INSTRUCTION_3_CONTENT = "EAT THE PELLET TO ATTACK";
    private static final int LEVEL_1_FIRST_BOTTOM_LEFT_X = 200;
    private static final int LEVEL_1_FIRST_BOTTOM_LEFT_Y = 350;
    private final static int LEVEL_1_INSTRUCTION_FONT_SIZE = 40;
    private final static Font LEVEL_1_INSTRUCTION_FONT= new Font(
            "res/FSO8BITR.TTF", LEVEL_1_INSTRUCTION_FONT_SIZE);
    private final static String WORLD_FILE_1 = "res/level1.csv";
    protected final static Message LEVEL_1_INSTRUCTION_MESSAGE_1 = new Message(
            Level0.INSTRUCTION_1_CONTENT, LEVEL_1_INSTRUCTION_FONT, LEVEL_1_INSTRUCTION_FONT_SIZE,
            LEVEL_1_FIRST_BOTTOM_LEFT_X, LEVEL_1_FIRST_BOTTOM_LEFT_Y);
    protected final static Message LEVEL_1_INSTRUCTION_MESSAGE_2 = new Message(
            Level0.INSTRUCTION_2_CONTENT, LEVEL_1_INSTRUCTION_FONT, LEVEL_1_INSTRUCTION_FONT_SIZE,
            LEVEL_1_INSTRUCTION_MESSAGE_1.getBottomLeftY() + 2 * LEVEL_1_INSTRUCTION_FONT_SIZE);
    protected final static Message LEVEL_1_INSTRUCTION_MESSAGE_3 = new Message(
            INSTRUCTION_3_CONTENT, LEVEL_1_INSTRUCTION_FONT, LEVEL_1_INSTRUCTION_FONT_SIZE,
            LEVEL_1_INSTRUCTION_MESSAGE_2.getBottomLeftY() + 2 * LEVEL_1_INSTRUCTION_FONT_SIZE);

    private static int LEVEL_1_WIN_SCORE = 800;
    private static final String PELLET_STRING = "Pellet";
    private static final String CHERRY_STRING = "Cherry";
    private static final String GHOST_RED_STRING = "GhostRed";
    private static final String GHOST_BLUE_STRING = "GhostBlue";
    private static final String GHOST_GREEN_STRING = "GhostGreen";
    private static final String GHOST_PINK_STRING = "GhostPink";
    private boolean isFrenzy = false;
    private static final int FRENZY_DURATION = 1000;
    private int initialFrenzyFrame;
    private ArrayList<GameEntity> lv1GameEntities = new ArrayList<GameEntity>();
    private  ArrayList<Wall> lv1Walls = new ArrayList<>();
    private ArrayList<Integer> indexOfLv1Ghosts = new ArrayList<>();
    private Player player;
    private Heart heart = new Heart();
    private Score score = new Score();

    /**
     * Creates level1 with expected level 1 starting logic.
     */
    public Level1() {

    }

    /**
     * Method used to read level 1 game objects file,
     * find the positions of the player, the colorful ghosts, the walls, the dots, the cherries and the pellet
     * and stores them in an array list of game entities.
     */
    protected void readCSV() {

        // Read the level 1 world file
        try (BufferedReader br = new BufferedReader(new FileReader(WORLD_FILE_1))) {
            // store the initial position of the player for level 1
            String playerLine = br.readLine();
            String playerCells[] = playerLine.split(",");
            double startPlayerX1 = Double.parseDouble(playerCells[1]);
            double startPlayerY1 = Double.parseDouble(playerCells[2]);
            player = new Player(startPlayerX1,startPlayerY1);

            String text;
            int curIndex = 0;
            // store all stationary game entities wall, dot, ghost into a lv0GameEntities array
            while ((text = br.readLine())!=null) {
                String cells[] = text.split(",");
                double curX = Double.parseDouble(cells[1]);
                double curY = Double.parseDouble(cells[2]);

                if (cells[0].contains(Level.GHOST_STRING)) {
                    if (cells[0].equals(GHOST_RED_STRING)) {
                        lv1GameEntities.add(new GhostRed(curX, curY));
                    } else if (cells[0].equals(GHOST_BLUE_STRING)) {
                        lv1GameEntities.add(new GhostBlue(curX, curY));
                    } else if (cells[0].equals(GHOST_GREEN_STRING)) {
                        lv1GameEntities.add(new GhostGreen(curX, curY));
                    } else if (cells[0].equals(GHOST_PINK_STRING)) {
                        lv1GameEntities.add(new GhostPink(curX, curY));
                    }
                    indexOfLv1Ghosts.add(curIndex);
                } else if (cells[0].equals(Level.DOT_STRING)) {
                    lv1GameEntities.add(new Dot(curX, curY));
                } else if (cells[0].equals(Level.WALL_STRING)) {
                    lv1GameEntities.add(new Wall(curX, curY));
                    lv1Walls.add(new Wall(curX, curY));
                } else if (cells[0].equals(PELLET_STRING)) {
                    lv1GameEntities.add(new Pellet(curX, curY));
                } else if (cells[0].equals(CHERRY_STRING)) {
                    lv1GameEntities.add(new Cherry(curX, curY));
                }
                curIndex += 1;
            }

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
     * This method updates the game frame displayed according to the level 1 logic.
     * @param input This is the input key pressed.
     */
    protected void update(Input input) {
        // update the hearts and score displayed
        heart.draw(player.getNumLives());
        score.print();

        // stop frenzy mode after frenzy duration
        if (isFrenzy && (getUpdateCount() > initialFrenzyFrame + FRENZY_DURATION)) {
            isFrenzy = false;
            for (Integer i : indexOfLv1Ghosts) {
                ((Ghost)lv1GameEntities.get(i)).reset();
            }
            player.resetSpeed();
        }

        Point playerCenterPoint = player.getCenterPoint();
        Rectangle playerRectangle = player.getImage().getBoundingBoxAt(playerCenterPoint);
        // a theoretical player to test if a move will be legal i.e. if the player will move into a wall
        Player theoreticalPlayer = new Player(player.getTopLeftX(), player.getTopLeftY());
        theoreticalPlayer.move(input);
        Point theorPlayerCenterPoint = theoreticalPlayer.getCenterPoint();
        Rectangle theorPlayerRectangle = theoreticalPlayer.getImage().getBoundingBoxAt(theorPlayerCenterPoint);
        for (GameEntity gameEntity : lv1GameEntities) {
            Point gameEntityCenterPoint = gameEntity.getCenterPoint();
            Rectangle gameEntityRectangle = gameEntity.getImage().getBoundingBoxAt(gameEntityCenterPoint);

            // Player collides with a stationary game entity
            if (playerRectangle.intersects(gameEntityRectangle)) {

                // enters Frenzy Mode once player eats the pellet
                if (gameEntity.getClass() == Pellet.class && gameEntity.getVisible()) {
                    ((Pellet) gameEntity).eat();
                    isFrenzy = true;
                    initialFrenzyFrame = getUpdateCount();
                    for (Integer i : indexOfLv1Ghosts) {
                        ((Ghost)lv1GameEntities.get(i)).setFrenzy();
                    }
                    player.setFrenzy();
                }

                // score increments accordingly as player eats cherry
                if (gameEntity.getClass() == Cherry.class && gameEntity.getVisible()) {
                    ((Cherry) gameEntity).eat();
                    score.increment(Cherry.class);
                }

                if (Ghost.class.isAssignableFrom(gameEntity.getClass())) {

                    // player loses a life if collides with a non-eaten ghost
                    if (!isFrenzy && gameEntity.getVisible()) {
                        // player loses a life if collides with a ghost. If all lives are lost game is lost.
                        if (player.loseALife()) {
                            setLost();
                            setLevelCompleted();
                        }
                        ((Ghost) gameEntity).resetPosition();
                        player.resetPosition();
                        // score increments accordingly as player eats ghost in Frenzy Mode
                    } else if (((Ghost)gameEntity).getValue() > 0) {
                        ((Ghost) gameEntity).eat();
                        score.increment(Ghost.class);
                    }
                }

                // score increments accordingly if player collides with a non-eaten dot
                if (gameEntity.getClass() == Dot.class && gameEntity.getVisible()) {
                    // The dot is eaten
                    ((Dot) gameEntity).eat();
                    score.increment(Dot.class);

                }

                // game won if current score is equal to or larger than win score
                if (score.getScoreValue() >= LEVEL_1_WIN_SCORE) {
                    setLevelCompleted();
                }

            }

            // if Pac-Man moves according to the input key, Pac-Man will intersect with a wall - an illegal move
            if (theorPlayerRectangle.intersects(gameEntityRectangle) && (gameEntity.getClass() == Wall.class)) {
                setIsPlayerLegalMove(false);
            }

            // move all ghosts according to their behaviour pattern provided, and draw them after everything else
            if (Ghost.class.isAssignableFrom(gameEntity.getClass())) {
                ((Ghost) gameEntity).move(lv1Walls);
            } else {
                gameEntity.draw();
            }
        }

        // make sure the ghosts appear in front of dots
        for (Integer i : indexOfLv1Ghosts) {
            lv1GameEntities.get(i).draw();
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

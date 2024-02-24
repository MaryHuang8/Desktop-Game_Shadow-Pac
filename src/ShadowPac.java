import bagel.*;
import bagel.util.Point;
import java.io.FileReader;
import java.io.BufferedReader;
import bagel.util.Rectangle;

/**
 * Skeleton Code for SWEN20003 Project 1, Semester 1, 2023
 *
 * Please enter your name below
 * @author Luoman Huang
 */
public class ShadowPac extends AbstractGame  {
    private final static int WINDOW_WIDTH = 1024;
    private final static int WINDOW_HEIGHT = 768;
    private final static String GAME_TITLE = "SHADOW PAC";
    private final Image BACKGROUND_IMAGE = new Image("res/background0.png");
    /*
    private final Image ghostImage = new Image("res/ghostRed.png");
    private final Image wallImage = new Image("res/wall.png");
    private final Image dotImage = new Image("res/dot.png");
    */


    public ShadowPac(){
        super(WINDOW_WIDTH, WINDOW_HEIGHT, GAME_TITLE);
    }


    private final Message TITLE_MESSAGE = new Title();

    private final Message INSTRUCTION_MESSAGE_1 = new Instruction("PRESS SPACE TO START");
    private final Message INSTRUCTION_MESSAGE_2 = new Instruction("USE ARROW KEYS TO MOVE");

    private final Score SCORE_MESSAGE = new Score();
    private final Message WIN_MESSAGE = new Message("WELL DONE!");
    private final Message LOSE_MESSAGE = new Message("GAME OVER!");
    private static final int MOUTH_MOVE_INTERVAL = 15;
    private static final int STATIONARY_GAME_ENTITIES_SIZE = 270;


    /**
     * Method used to read file and create objects (you can change
     * this method as you wish).
     */

    private final GameEntity gameEntities[] = new GameEntity[STATIONARY_GAME_ENTITIES_SIZE];
    private int curNumGameEntities = 0;
    private int numDots = 0;
    private Player player;
    private double startPlayerX;
    private double startPlayerY;
    private int winningScore = numDots * 10;
    // find the positions of the player, the ghosts, the walls and the dots
    private void readCSV() {

        try (BufferedReader br = new BufferedReader(new FileReader("res/level0.csv"))) {
            // store the initial position of the player read from the 1st line of the world file, and initiate player
            String playerLine = br.readLine();
            String playerCells[] = playerLine.split(",");
            startPlayerX = Double.parseDouble(playerCells[1]);
            startPlayerY = Double.parseDouble(playerCells[2]);
            player = new Player(startPlayerX, startPlayerY);

            String text;
            // store all stationary game entities wall, dot, ghost into a gameEntities array
            while ((text = br.readLine())!=null) {
                String cells[] = text.split(",");
                double curX = Double.parseDouble(cells[1]);
                double curY = Double.parseDouble(cells[2]);

                if (cells[0].equals("Ghost")) {
                    gameEntities[curNumGameEntities] = new Ghost(curX, curY);
                } else if (cells[0].equals("Wall")) {
                    gameEntities[curNumGameEntities] = new Wall(curX, curY);
                } else if (cells[0].equals("Dot")) {
                    gameEntities[curNumGameEntities] = new Dot(curX, curY);
                    numDots += 1;
                }
                curNumGameEntities += 1;
            }
            winningScore = numDots * 10;

        } catch (Exception e) {
            e.printStackTrace();
        }

    }



    /**
     * The entry point for the program.
     */
    public static void main(String[] args) {
        ShadowPac game = new ShadowPac();
        game.readCSV();
        game.run();
    }

    private boolean gameStarted = false;
    private boolean pacOpen = true;
    private int updateCount = 0;
    private boolean gameLost = false;
    private boolean gameWon = false;
    private boolean legalMove = true;
    /**
     * Performs a state update.
     * Allows the game to exit when the escape key is pressed.
     */
    @Override
    protected void update(Input input) {

        if (input.wasPressed(Keys.ESCAPE)){
            Window.close();
        }
        BACKGROUND_IMAGE.draw(Window.getWidth()/2.0, Window.getHeight()/2.0);


        // Display the starting title and instruction message.
        if (!gameStarted) {

            TITLE_MESSAGE.print();
            INSTRUCTION_MESSAGE_1.print();
            INSTRUCTION_MESSAGE_2.print();


            if (input.isDown(Keys.SPACE)) {
                    gameStarted = true;
            }
        // if all three lives are lost, display lose message
        } else if (gameLost) {
            LOSE_MESSAGE.print();
        // if all dots are eaten, display win message
        } else if (gameWon) {
            WIN_MESSAGE.print();
        // in game
        } else {
            // update the hearts and score displayed
            Heart heart = new Heart(player);
            heart.draw();
            SCORE_MESSAGE.print();
            Score scoreValue = new Score(SCORE_MESSAGE.getScore());
            scoreValue.print();

            Point playerCenterPoint = player.getCenterPoint();
            Rectangle playerRectangle = player.getImage().getBoundingBoxAt(playerCenterPoint);
            // a theoretical player to test if a move will be legal i.e. if the player will move into a wall
            Player theoreticalPlayer = new Player(player.getTopLeftX(), player.getTopLeftY());
            theoreticalPlayer.move(input);
            Point theorPlayerCenterPoint = theoreticalPlayer.getCenterPoint();
            Rectangle theorPlayerRectangle = theoreticalPlayer.getImage().getBoundingBoxAt(theorPlayerCenterPoint);
            for (GameEntity gameEntity : gameEntities) {
                Point gameEntityCenterPoint = gameEntity.getCenterPoint();
                Rectangle gameEntityRectangle = gameEntity.getImage().getBoundingBoxAt(gameEntityCenterPoint);

                // Player collides with a stationary game entity
                if (playerRectangle.intersects(gameEntityRectangle)) {

                    if (gameEntity.getClass() == Ghost.class) {
                        // player loses a life if collides with a ghost
                        gameLost = player.loseALife();
                        if (gameLost) {
                            break;
                        }
                        player.setTopLeftX(startPlayerX);
                        player.setTopLeftY(startPlayerY);
                        player.drawStart();
                    }
                    if (gameEntity.getClass() == Dot.class && ((Dot) gameEntity).getValue()>0) {
                        // The dot is eaten
                        gameEntity.setGone();
                        SCORE_MESSAGE.incrementScore();
                        if (SCORE_MESSAGE.getScore() == winningScore) {
                            gameWon = true;
                        }
                    }

                }

                // if Pac-Man moves according to the input key, Pac-Man will intersect with a wall - an illegal move
                if (theorPlayerRectangle.intersects(gameEntityRectangle) && (gameEntity.getClass() == Wall.class)) {
                    legalMove = false;
                }

                gameEntity.draw();
            }

            // Draw Pacman with a moving mouth every 15 frames
            if (updateCount % MOUTH_MOVE_INTERVAL == 0) {
                pacOpen = !pacOpen;
                player.changeMouth(pacOpen);
                // reset the update count to avoid integer overflow
                updateCount = 0;
            }

            // only modify the position of the player if the input move is legal
            if (legalMove) {
                player.move(input);
            } else {
                legalMove = true;
            }
            // player will rotate according to the move direction regardless of whether it can move or not
            player.rotate(input);

            // Display the player's new position in the game window
            player.draw();

            updateCount += 1;
        }

    }
}

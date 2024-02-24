import bagel.*;
public class Score extends Message implements Printable {
    private static final int SCORE_INCREMENT = 10;
    private static final int SCORE_BOTTOM_LEFT_X = 25;
    private static final int SCORE_BOTTOM_LEFT_Y = 25;
    private static final int SCORE_FONT_SIZE = 20;
    private static final String SCORE_STRING = "SCORE  ";
    private static final Font SCORE_FONT = new Font("res/FSO8BITR.TTF", SCORE_FONT_SIZE);
    private int score = 0;
    public Score() {
        super(SCORE_STRING, SCORE_FONT_SIZE, SCORE_BOTTOM_LEFT_X, SCORE_BOTTOM_LEFT_Y);
    }
    public Score(int score) {
           super(String.valueOf(score), SCORE_FONT_SIZE, SCORE_BOTTOM_LEFT_X + SCORE_FONT.getWidth(SCORE_STRING), SCORE_BOTTOM_LEFT_Y);
    }

    public void incrementScore() {
        score += SCORE_INCREMENT;
    }

    public void print() {
        super.print();
    }


    public int getScore() {
        return score;
    }
}

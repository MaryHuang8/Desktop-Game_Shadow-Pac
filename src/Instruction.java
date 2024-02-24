import bagel.Font;
import bagel.Window;

public class Instruction extends Message implements Printable {
    private static final int FIRST_INSTRUCTION_INCREMENT_X = 60;
    private static final int FIRST_INSTRUCTION_INCREMENT_Y = 190;
    private static final String INSTRUCTION1_STRING = "PRESS SPACE TO START";
    private static final String INSTRUCTION2_STRING = "USE ARROW KEYS TO MOVE";
    private final static int INSTRUCTION_FONT_SIZE = 24;
    private final static Font INSTRUCTION_FONT = new Font("res/FSO8BITR.TTF",INSTRUCTION_FONT_SIZE);
    public Instruction(String string) {
        super(string, INSTRUCTION_FONT, INSTRUCTION_FONT_SIZE);
        if (string.equals(INSTRUCTION1_STRING)) {
            setBottomLeftX(Title.TITLE_BOTTOM_LEFT_X + FIRST_INSTRUCTION_INCREMENT_X);
            setBottomLeftY(Title.TITLE_BOTTOM_LEFT_Y + FIRST_INSTRUCTION_INCREMENT_Y);
        } else if (string.equals(INSTRUCTION2_STRING)) {
            setBottomLeftY(Title.TITLE_BOTTOM_LEFT_Y + FIRST_INSTRUCTION_INCREMENT_Y + 2*INSTRUCTION_FONT_SIZE);
        }
    }
}

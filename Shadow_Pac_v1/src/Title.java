import bagel.Font;
import bagel.Window;

public class Title extends Message implements Printable {
    public static final int TITLE_BOTTOM_LEFT_X = 260;
    public static final int TITLE_BOTTOM_LEFT_Y = 250;


    private final static int DEFAULT_FONT_SIZE = 64;
    private final static Font DEFAULT_FONT = new Font("res/FSO8BITR.TTF", DEFAULT_FONT_SIZE);

    private static final String TITLE_STRING = "SHADOW PAC";
    public Title() {
        super(TITLE_STRING,DEFAULT_FONT,DEFAULT_FONT_SIZE, TITLE_BOTTOM_LEFT_X, TITLE_BOTTOM_LEFT_Y);
    }

}

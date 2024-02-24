import bagel.*;

public class Message implements Printable {
    private static final int DEFAULT_FONT_SIZE = 64;
    private static final Font DEFAULT_FONT = new Font("res/FSO8BITR.TTF", DEFAULT_FONT_SIZE);

    private Font font;
    private double bottomLeftX;
    private double bottomLeftY;
    private String string;
    private int fontSize;

    // Message rendered with default font and size at the center of the game window
    public Message(String string) {
        font = DEFAULT_FONT;
        fontSize = DEFAULT_FONT_SIZE;
        bottomLeftX = (Window.getWidth() - font.getWidth(string))/2;
        bottomLeftY = (Window.getHeight() + fontSize)/2;
        this.string = string;
    }
    // Message rendered with the specified font and size at the center of the game window
    public Message(String string, Font font, int fontSize) {
        this.font = font;
        this.fontSize = fontSize;
        bottomLeftX = (Window.getWidth() - font.getWidth(string))/2;
        bottomLeftY = (Window.getHeight() + fontSize)/2;
        this.string = string;
    }

    // Message rendered with default font and specified size, at the specified x and y coordinates
    public Message(String string, int fontSize, double bottomLeftX, double bottomLeftY) {
        font = new Font("res/FSO8BITR.TTF", fontSize);
        this.fontSize = fontSize;
        this.bottomLeftX = bottomLeftX;
        this.bottomLeftY = bottomLeftY;
        this.string = string;
    }

    // Message rendered with specified font and size at specified x and y coordinates
    public Message(String string, Font font, int fontSize, double bottomLeftX, double bottomLeftY) {
        this.font = font;
        this.fontSize = fontSize;
        this.bottomLeftX = bottomLeftX;
        this.bottomLeftY = bottomLeftY;
        this.string = string;
    }


    public void setBottomLeftX(double bottomLeftX) {
        this.bottomLeftX = bottomLeftX;
    }

    public void setBottomLeftY(double bottomLeftY) {
        this.bottomLeftY = bottomLeftY;
    }


    public void print() {
        font.drawString(string, bottomLeftX, bottomLeftY);
    }


}

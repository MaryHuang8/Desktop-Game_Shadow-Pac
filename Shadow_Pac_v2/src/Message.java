import bagel.*;

/**
 * Class that represents messages to be displayed in game window.
 */
public class Message {
    private static final int DEFAULT_FONT_SIZE = 64;
    private static final Font DEFAULT_FONT = new Font("res/FSO8BITR.TTF", DEFAULT_FONT_SIZE);
    private Font font = DEFAULT_FONT;
    private double bottomLeftX;
    private double bottomLeftY;
    private String content;
    private int fontSize = DEFAULT_FONT_SIZE;

    /**
     * Creates message rendered with default font and size at the center of the game window.
     * @param content This is the content of the message.
     */
    public Message(String content) {
        bottomLeftX = (Window.getWidth() - font.getWidth(content))/2;
        bottomLeftY = (Window.getHeight() + fontSize)/2;
        this.content = content;
    }

    /**
     * Creates message rendered with default font and size, at the specified x and y coordinates.
     * @param content This is the content of the message.
     * @param bottomLeftX This is the x-coordinate for the bottom left of the message.
     * @param bottomLeftY This is the y-coordinate for the bottom left of the message.
     */
    public Message(String content, double bottomLeftX, double bottomLeftY) {
        this.bottomLeftX = bottomLeftX;
        this.bottomLeftY = bottomLeftY;
        this.content = content;
    }

    /**
     * Creates message rendered with the specified font and size at the center of the game window.
     * @param content This is the content of the message.
     * @param font This is the font of the message.
     * @param fontSize This is the font size of the message.
     */
    public Message(String content, Font font, int fontSize) {
        this.font = font;
        this.fontSize = fontSize;
        bottomLeftX = (Window.getWidth() - font.getWidth(content))/2;
        bottomLeftY = (Window.getHeight() + fontSize)/2;
        this.content = content;
    }

    /**
     * Creates message rendered with default font and specified size, at the specified x and y coordinates.
     * @param content This is the content of the message.
     * @param fontSize This is the font size of the message.
     * @param bottomLeftX This is the x-coordinate for the bottom left of the message.
     * @param bottomLeftY This is the y-coordinate for the bottom left of the message.
     */
    public Message(String content, int fontSize, double bottomLeftX, double bottomLeftY) {
        font = new Font("res/FSO8BITR.TTF", fontSize);
        this.fontSize = fontSize;
        this.bottomLeftX = bottomLeftX;
        this.bottomLeftY = bottomLeftY;
        this.content = content;
    }

    /**
     * Creates message rendered with the specified font and size at the horizontal center of the game window.
     * @param content This is the content of the message.
     * @param font This is the font of the message.
     * @param fontSize This is the font size of the message.
     * @param bottomLeftY This is the y-coordinate for the bottom left of the message.
     */
    public Message(String content, Font font, int fontSize, double bottomLeftY) {
        this.font = font;
        this.fontSize = fontSize;
        bottomLeftX = (Window.getWidth() - font.getWidth(content))/2;
        this.bottomLeftY = bottomLeftY;
        this.content = content;
    }

    /**
     * Creates message rendered with specified font and size at specified x and y coordinates.
     * @param content This is the content of the message.
     * @param font This is the font of the message.
     * @param fontSize This is the font size of the message.
     * @param bottomLeftX This is the x-coordinate for the bottom left of the message.
     * @param bottomLeftY This is the y-coordinate for the bottom left of the message.
     */
    public Message(String content, Font font, int fontSize, double bottomLeftX, double bottomLeftY) {
        this.font = font;
        this.fontSize = fontSize;
        this.bottomLeftX = bottomLeftX;
        this.bottomLeftY = bottomLeftY;
        this.content = content;
    }

    /**
     * This method gives the y-coordinate of the bottom left of the message.
     * @return double Returns the bottom left y-coordinate of the message.
     */
    public double getBottomLeftY() {
        return bottomLeftY;
    }

    /**
     * This method prints the message in the game window rendered.
     */
    public void print() {
        font.drawString(content, bottomLeftX, bottomLeftY);
    }

    /**
     * This method sets the content of the message.
     * @param content This is the new content of the message.
     */
    protected void setContent(String content) {
        this.content = content;
    }
}

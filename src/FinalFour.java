import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * The type Final four.
 */
public class FinalFour implements LevelInformation {
    private int numberOfBalls = 3;
    private List<Velocity> ballsVelocity = new ArrayList<>();
    private int paddleSpeed = 7;
    private int paddleWidth = 150;
    private String levelName = "Green 3";
    private List<Block> blocks = new ArrayList<>();
    private BackgroundFour background = new BackgroundFour(this.levelName);

    /**
     * @return the number of the balls
     */
    public int numberOfBalls() {
        return this.numberOfBalls;
    }
    /**
     * @return The initial velocity of each ball
     */
    public List<Velocity> initialBallVelocities() {
        ballsVelocity.add(new Velocity(3, -5));
        ballsVelocity.add(new Velocity(0, 6));
        ballsVelocity.add(new Velocity(-3, -5));

        return ballsVelocity;
    }
    /**
     * @return the paddle speed
     */
    public int paddleSpeed() {
        return this.paddleSpeed;
    }
    /**
     * @return the paddle width
     */
    public int paddleWidth() {
        return this.paddleWidth;

    }
    /**
     * @return  the level name that will be displayed at the top of the screen.
     */
    public String levelName() {
        return this.levelName;
    }
    /**
     * @return Returns a sprite with the background of the level
     */
    public Sprite getBackground() {
        return this.background;
    }
    /**
     * @return The Blocks that make up this level
     */
    public List<Block> blocks() {
        //FIRST ROW
        for (int i = 0, j = 0; i < 13; i++, j += 57) {
            Rectangle rect = new Rectangle(new Point(30 + j, 130), 57, 25);
            Block block = new Block(rect, Color.GRAY);
            blocks.add(block);
        }
        //SECOND ROW
        for (int i = 0, j = 0; i < 13; i++, j += 57) {
            Rectangle rect = new Rectangle(new Point(30 + j, 156), 57, 25);
            Block block = new Block(rect, Color.RED);
            blocks.add(block);
        }
        //THIRD ROW
        for (int i = 0, j = 0; i < 13; i++, j += 57) {
            Rectangle rect = new Rectangle(new Point(30 + j, 181), 57, 25);
            Block block = new Block(rect, Color.YELLOW);
            blocks.add(block);
        }
        //FORTH ROW
        for (int i = 0, j = 0; i < 13; i++, j += 57) {
            Rectangle rect = new Rectangle(new Point(30 + j, 206), 57, 25);
            Block block = new Block(rect, Color.cyan);
            blocks.add(block);
        }
        //FIFTH ROW
        for (int i = 0, j = 0; i < 13; i++, j += 57) {
            Rectangle rect = new Rectangle(new Point(30 + j, 231), 57, 25);
            Block block = new Block(rect, Color.WHITE);
            blocks.add(block);
        }
        //SIXTH ROW
        for (int i = 0, j = 0; i < 13; i++, j += 57) {
            Rectangle rect = new Rectangle(new Point(30 + j, 256), 57, 25);
            Block block = new Block(rect, Color.pink);
            blocks.add(block);
        }
        //SEVENTH ROW
        for (int i = 0, j = 0; i < 13; i++, j += 57) {
            Rectangle rect = new Rectangle(new Point(30 + j, 281), 57, 25);
            Block block = new Block(rect, Color.orange);
            blocks.add(block);
        }

            return this.blocks;
    }
    /**
     * @return Number of levels that should be removed before the level is considered to be "cleared".
     */
    public int numberOfBlocksToRemove() {
        return this.blocks.size();
    }
}

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * The type Direct hit.
 */
public class DirectHit implements LevelInformation {
    private int numberOfBalls = 1;
    private List<Velocity> ballsVelocity = new ArrayList<>();
    private int paddleSpeed = 8;
    private int paddleWidth = 150;
    private String levelName = "Direct Hit";
    private List<Block> blocks = new ArrayList<>();
    private Rectangle mark = new Rectangle(new Point(375, 250), 50, 40);
    private Block block = new Block(mark, Color.pink);
    private Sprite background = new BackgroundOne(this.levelName);

    /**
     * @return the number of balls
     */
    public int numberOfBalls() {
        return this.numberOfBalls;
    }
    /**
     * @return The initial velocity of each ball
     */
    public List<Velocity> initialBallVelocities() {
        ballsVelocity.add(new Velocity(0, -8));
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
            blocks.add(block);
            return this.blocks;
    }
    /**
     * @return Number of levels that should be removed before the level is considered to be "cleared".
     */
    public int numberOfBlocksToRemove() {
            return this.blocks.size();
    }
}

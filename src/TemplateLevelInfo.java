import java.util.ArrayList;
import java.util.List;

/**
 * The type Template level info.
 */
public class TemplateLevelInfo implements LevelInformation {
    private int numberOfBalls;
    private List<Velocity> ballsVelocity = new ArrayList<>();
    private int paddleSpeed;
    private int paddleWidth;
    private String levelName;
    private List<Block> blocks = new ArrayList<>();
    private BackgroundTemplate background;

    /**
     * Instantiates a new Template level info.
     *
     * @param numberOfBalls the number of balls
     * @param velocities    the velocities
     * @param paddleSpeed   the paddle speed
     * @param paddleWidth   the paddle width
     * @param levelName     the level name
     * @param blocks        the blocks
     * @param background    the background
     */
    public TemplateLevelInfo(int numberOfBalls, List<Velocity> velocities, int paddleSpeed, int paddleWidth,
                             String levelName, List<Block> blocks, BackgroundTemplate background) {
        this.numberOfBalls = numberOfBalls;
        this.ballsVelocity = velocities;
        this.paddleSpeed = paddleSpeed;
        this.paddleWidth = paddleWidth;
        this.levelName = levelName;
        this.blocks = blocks;
        this.background = background;
    }

    @Override
    public int numberOfBalls() {
        return this.numberOfBalls;
    }

    @Override
    public List<Velocity> initialBallVelocities() {
        return this.ballsVelocity;
    }

    @Override
    public int paddleSpeed() {
        return this.paddleSpeed;
    }

    @Override
    public int paddleWidth() {
        return this.paddleWidth;
    }

    @Override
    public String levelName() {
        return this.levelName;
    }

    @Override
    public Sprite getBackground() {
        return this.background;
    }

    @Override
    public List<Block> blocks() {
        return this.blocks;
    }

    @Override
    public int numberOfBlocksToRemove() {
        return this.blocks.size();
    }
}

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * The type Green 3.
 */
public class Green3 implements LevelInformation {
    private int numberOfBalls = 2;
    private List<Velocity> ballsVelocity = new ArrayList<>();
    private int paddleSpeed = 9;
    private int paddleWidth = 150;
    private String levelName = "Green 3";
    private List<Block> blocks = new ArrayList<>();
    private BackgroundThree background = new BackgroundThree(this.levelName);

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
        ballsVelocity.add(new Velocity(5, -5));
        ballsVelocity.add(new Velocity(-5, -5));

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
        for (int i = 0, j = 0; i < 9; i++, j += 60) {
                Rectangle rect = new Rectangle(new Point(229 + j, 170), 60, 30);
                Block block = new Block(rect, new Color(20, 70, 80));
                blocks.add(block);
        }
        //SECOND ROW
        for (int i = 0, j = 0; i < 8; i++, j += 60) {
            Rectangle rect = new Rectangle(new Point(289 + j, 200), 60, 30);
            Block block = new Block(rect, new Color(33, 74, 80));
            blocks.add(block);
        }
        //THIRD ROW
        for (int i = 0, j = 0; i < 7; i++, j += 60) {
            Rectangle rect = new Rectangle(new Point(349 + j, 230), 60, 30);
            Block block = new Block(rect, new Color(28, 80, 73));
            blocks.add(block);
        }
        //FORTH ROW
        for (int i = 0, j = 0; i < 6; i++, j += 60) {
            Rectangle rect = new Rectangle(new Point(409 + j, 260), 60, 30);
            Block block = new Block(rect, new Color(51, 75, 80));
            blocks.add(block);
        }
        //FIFTH ROW
        for (int i = 0, j = 0; i < 5; i++, j += 60) {
            Rectangle rect = new Rectangle(new Point(469 + j, 290), 60, 30);
            Block block = new Block(rect, new Color(68, 80, 77));
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
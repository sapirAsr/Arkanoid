import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * The type Wide easy.
 */
public class WideEasy implements LevelInformation {
    private int numberOfBalls = 10;
    private List<Velocity> ballsVelocity = new ArrayList<>();
    private int paddleSpeed = 3;
    private int paddleWidth = 600;
    private String levelName = "Wide Easy";
    private List<Block> blocks = new ArrayList<>();
    private BackgroundTwo backgroundTwo = new BackgroundTwo(this.levelName);

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
        Velocity v = new Velocity(5, -5);
        double speed = Math.sqrt((Math.pow(v.getDx(), 2) + (Math.pow(v.getDy(), 2))));
        for (int i = 0, j = 355; i < 5; i++, j -= 10) {
            v = v.fromAngleAndSpeed(j, speed);
            double dx = -v.getDx();
            double dy = -v.getDy();
            ballsVelocity.add(new Velocity(dx, dy));

        }
        for (int i = 5, j = 5; i < 10; i++, j += 10) {
           v = v.fromAngleAndSpeed(j, speed);
            double dx = v.getDx();
            double dy = -v.getDy();
           ballsVelocity.add(new Velocity(dx, dy));

       }

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
        return this.backgroundTwo;
    }
    /**
     * @return The Blocks that make up this level
     */
    public List<Block> blocks() {
        for (int i = 0, j = 0; i < 15; i++, j += 49) {
            Rectangle mark = new Rectangle(new Point(31 + j, 250), 51, 40);
            Block block = new Block(mark, Color.black);
            if (i < 2) {
                block = new Block(mark, Color.pink);
            } else if (i >= 2 && i < 4) {
                block = new Block(mark, new Color(64, 39, 65));
            } else if (i >= 4 && i < 6) {
                block = new Block(mark, new Color(65, 9, 63));
            } else if (i >= 6 && i < 9) {
                block = new Block(mark, new Color(65, 0, 41));
            } else if (i >= 9 && i < 11) {
                block = new Block(mark, new Color(161, 105, 178));
            } else if (i >= 11 && i < 13) {
                block = new Block(mark, new Color(132, 61, 178));
            } else if (i >= 13 && i < 15) {
                block = new Block(mark, new Color(109, 74, 90));
            }
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


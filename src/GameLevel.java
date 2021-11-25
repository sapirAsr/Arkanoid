import biuoop.DrawSurface;
import biuoop.KeyboardSensor;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * .
 * asrassa
 */

public class GameLevel implements Animation {
    private SpriteCollection sprites;
    private GameEnvironment environment;
    private KeyboardSensor keyboard;
    private Counter blockCounter = new Counter(0);
    private Counter ballsCounter = new Counter(0);
    private Counter score;
    private Counter lives;
    private Paddle paddle;
    private Rectangle pad = new Rectangle(new Point(280, 570), 200, 25);
    private AnimationRunner runner;
    private boolean running;
    private LevelInformation levelInformation;
    private List<Ball> balls = new ArrayList<>();


    /**
     * .
     * builder
     *
     * @param levelInformation levelInformation
     */
    public GameLevel(LevelInformation levelInformation) {
        this.levelInformation = levelInformation;
    }

    /**
     * .
     * builder
     *
     * @param levelInformation levelInformation
     * @param ar               animation runner
     * @param ks               KeyboardSensor
     * @param lives            Counter
     * @param score            Counter
     */
    public GameLevel(LevelInformation levelInformation, AnimationRunner ar, KeyboardSensor ks, Counter lives,
                     Counter score) {
        this.levelInformation = levelInformation;
        this.runner = ar;
        this.keyboard = ks;
        this.lives = lives;
        this.score = score;
    }

    /**
     * .
     * geter of the KeyboardSensor
     *
     * @return KeyboardSensor k
     */
    public KeyboardSensor getK() {
        return this.keyboard;
    }

    /**
     * .
     * adder of collidable
     *
     * @param c the collidable
     */
    public void addCollidable(Collidable c) {
        this.environment.addCollidable(c);
    }

    /**
     * .
     * adder of sprite
     *
     * @param s the spriut
     */
    public void addSprite(Sprite s) {
        this.sprites.addSprite(s);
    }

    /**
     * .
     *
     * @return the amount of lives
     */
    public int getLives() {
        return this.lives.getValue();
    }

    /**
     * .
     *
     * @return the amount of blocks
     */
    public int getBlocks() {
        return this.blockCounter.getValue();
    }

    /**
     * . Initialize a new game: create the Blocks and Ball (and Paddle)
     * and add them to the game.
     */
    public void initialize() {
        this.sprites = new SpriteCollection();
        this.addSprite(this.levelInformation.getBackground());
        this.environment = new GameEnvironment();
        BlockRemover blockRemover = new BlockRemover(this, this.blockCounter);
        BallRemover ballRemover = new BallRemover(this, this.ballsCounter);
        ScoreTrackingListener scoreTrackingListener = new ScoreTrackingListener(this.score);
        ScoreIndicator scoreIndicator = new ScoreIndicator(this.score);
        LivesIndicator livesIndicator = new LivesIndicator(this.lives);


        //the border rectangles
        //top rectangle
        Rectangle top = new Rectangle(new Point(0, 30), 800, 15);
        top.setColorOfRec(Color.gray);
        Block topBlock = new Block(top);
        topBlock.setHitPoints(0);
        topBlock.addToGame(this);
        // bottom rectangle
        Rectangle bottom = new Rectangle(new Point(0, 600), 800, 30);
        bottom.setColorOfRec(Color.gray);
        Block bottomBlock = new Block(bottom);
        bottomBlock.setHitPoints(0);
        bottomBlock.addToGame(this);
        bottomBlock.addHitListener(ballRemover);
        // left rectangle
        Rectangle left = new Rectangle(new Point(0, 30), 15, 572);
        left.setColorOfRec(Color.gray);
        Block leftBlock = new Block(left);
        leftBlock.setHitPoints(0);
        leftBlock.addToGame(this);
        // right rectangle
        Rectangle right = new Rectangle(new Point(785, 30), 15, 570);
        right.setColorOfRec(Color.gray);
        Block rightBlock = new Block(right);
        rightBlock.setHitPoints(0);
        rightBlock.addToGame(this);

        this.addSprite(scoreIndicator);
        this.addSprite(livesIndicator);

        List<Block> blocks = new ArrayList<>(this.levelInformation.blocks());
        int size = blocks.size();
        for (int i = 0; i < size; i++) {
            Block block = this.levelInformation.blocks().get(i);
            block.addToGame(this);
            this.blockCounter.increase(1);
            block.addHitListener(blockRemover);
            block.addHitListener(scoreTrackingListener);
            for (int j = 0; j < balls.size(); j++) {
                blockRemover.hitEvent(block, balls.get(j));
            }
        }

        //the paddle
        double startP = 400 - ((this.levelInformation.paddleWidth()) / 2);

        pad = new Rectangle(new Point(startP, 575), this.levelInformation.paddleWidth(), 25);
        pad.setColorOfRec(Color.ORANGE);
        this.paddle = new Paddle(pad, this.keyboard, this.levelInformation.paddleSpeed());
        this.paddle.setGameEN(this.environment);
        this.paddle.addToGame(this);

    }

    /**
     * .
     * Run one round of the game -- start the animation loop.
     */
    public void playOneTurn() {
        this.createBallsOnTopOfPaddle();
        this.running = true;
        // use our runner to run the current animation -- which is one turn of
        // the game.
        this.runner.run(new CountdownAnimation(2, 3, this.sprites)); // countdown before turn starts.
        // use our runner to run the current animation -- which is one turn of
        // the game.
        this.runner.run(this);

    }

    /**
     * .
     * Run the game
     */
    public void run() {
        this.playOneTurn();
    }

    /**
     * .
     * Remove collidable.
     *
     * @param c the Collidable
     */
    public void removeCollidable(Collidable c) {
        this.environment.removeCollidable(c);
    }

    /**
     * Remove sprite.
     *
     * @param s the Sprite
     */
    public void removeSprite(Sprite s) {
        this.sprites.removeSprite(s);

    }

    /**
     * @param d the surface to draw on
     */
    public void doOneFrame(DrawSurface d) {
        this.sprites.drawAllOn(d);
        if ((this.blockCounter.getValue() == 0) || (this.lives.getValue() == 0)) {
            if (this.blockCounter.getValue() == 0) {
                this.score.increase(100);
                this.running = false;
            } else {
                this.running = false;
            }
        }
        if ((this.ballsCounter.getValue() == 0) && (this.lives.getValue() != 0)) {
            for (int i = 0; i < this.levelInformation.numberOfBalls(); i++) {
                Ball ballA = new Ball(400, 565, 7, Color.red, environment);
                ballA.setVelocity(this.levelInformation.initialBallVelocities().get(i));
                ballA.addToGame(this);
                ballsCounter.increase(1);
                balls.add(ballA);
            }


            //the paddle
            this.removeSprite(this.paddle);
            this.removeCollidable(this.paddle);
            double startP = 400 - ((this.levelInformation.paddleWidth()) / 2);
            pad = new Rectangle(new Point(startP, 575), this.levelInformation.paddleWidth(), 25);
            pad.setColorOfRec(Color.ORANGE);
            this.paddle = new Paddle(pad, this.keyboard, this.levelInformation.paddleSpeed());
            this.paddle.setGameEN(this.environment);
            this.paddle.addToGame(this);
            this.lives.decrease(1);
            if ((this.lives.getValue() > 0)) {
                // countdown before turn starts.
                this.runner.run(new CountdownAnimation(2, 3, this.sprites));
            }
        }
        this.sprites.notifyAllTimePassed();

        if (this.keyboard.isPressed("p")) {
            this.runner.run(new KeyPressStoppableAnimation(this.keyboard, KeyboardSensor.SPACE_KEY,
                    new PauseScreen(this.keyboard)));
        }

    }

    /**
     * .
     * create Balls On Top Of Paddle
     */

    public void createBallsOnTopOfPaddle() {
        for (int i = 0; i < this.levelInformation.numberOfBalls(); i++) {
            Ball ballA = new Ball(400, 565, 7, Color.red, environment);
            ballA.setVelocity(this.levelInformation.initialBallVelocities().get(i));
            ballA.addToGame(this);
            ballsCounter.increase(1);
            balls.add(ballA);
        }

    }

    /**
     * @return if the loop should stop
     */
    public boolean shouldStop() {
        return !this.running;
    }
}

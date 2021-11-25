/**
 * The type Show hi scores task.
 */
public class ShowHiScoresTask implements Task<Void> {
    private AnimationRunner runner;
    private KeyPressStoppableAnimation highScoresAnimation;

    /**
     * Instantiates a new Show hi scores task.
     *
     * @param runner              the runner
     * @param highScoresAnimation the high scores animation
     */
    public ShowHiScoresTask(AnimationRunner runner, KeyPressStoppableAnimation highScoresAnimation) {
        this.runner = runner;
        this.highScoresAnimation = highScoresAnimation;
    }

    /**
     * @return runnig the high scores animation
     */
    public Void run() {
        highScoresAnimation.restartKey();
        this.runner.run(this.highScoresAnimation);
        return null;
    }
}

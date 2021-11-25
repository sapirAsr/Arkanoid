/**.
 * asrassa
 */

public class BallRemover implements HitListener {
    private GameLevel game;
    private Counter removedBalls;

    /**
     * Instantiates a new Ball remover.
     *
     * @param game  the game
     * @param removedBalls the removed balls
     */
    public BallRemover(GameLevel game, Counter removedBalls) {
        this.game = game;
        this.removedBalls = removedBalls;
    }
    /**.
     *  This method is called whenever the beingHit object is hit.
     * @param deathRegion the block
     * @param beingHit the Ball that's doing the hitting.
     */
    public void hitEvent(Block deathRegion, Ball beingHit) {
        beingHit.removeFromGame(this.game);
        this.removedBalls.decrease(1);
    }

}

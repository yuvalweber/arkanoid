// Yuval Weber 207455437
package listeners;

import sprites.Ball;
import coliisions.Block;

/**
 * listeners.BallColorChanger class. This class is in charge of changing the color of the ball when it hits a block.
 */
public class ScoreTrackingListener implements HitListener {
    private final Counter currentScore;
    /**
     * Constructor for the listeners.ScoreTrackingListener class.
     *
     * @param scoreCounter The counter that keeps track of the score.
     */
    public ScoreTrackingListener(Counter scoreCounter) {
        this.currentScore = scoreCounter;
    }
    @Override
    public void hitEvent(Block beingHit, Ball hitter) {
        this.currentScore.increase(5);
    }
}

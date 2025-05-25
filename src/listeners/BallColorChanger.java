// Yuval Weber 207455437
package listeners;

import sprites.Ball;
import coliisions.Block;

/**
 * listeners.BallColorChanger class. This class
 * is in charge of changing the color of the ball
 * when it hits a block.
 */
public class BallColorChanger implements HitListener {

    /**
     * This method is called whenever the beingHit object is hit.
     * The hitter parameter is the Geometry.Ball that's doing the hitting.
     *
     * @param beingHit The block that was hit.
     * @param hitter The ball that hit the block.
     */
    @Override
    public void hitEvent(Block beingHit, Ball hitter) {
        // change the color of the ball to a random color.
        hitter.setColor(beingHit.getCollisionRectangle().getColor());
    }
}

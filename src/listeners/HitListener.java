// Yuval Weber 207455437
package listeners;

import sprites.Ball;
import coliisions.Block;

/**
 * The listeners.HitListener interface is used to notify objects that a hit event has occurred.
 */
public interface HitListener {
    // This method is called whenever the beingHit object is hit.
// The hitter parameter is the Geometry.Ball that's doing the hitting.
    /**
     * Method to notify objects that a hit event has occurred.
     * @param beingHit the block that was hit
     * @param hitter the ball that hit the block
     */
    void hitEvent(Block beingHit, Ball hitter);
}

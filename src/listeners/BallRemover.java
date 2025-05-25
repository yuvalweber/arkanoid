// Yuval Weber 207455437
package listeners;

import gameManagement.Game;
import sprites.Ball;
import coliisions.Block;

/**
 * listeners.BallRemover class. This class is in charge of removing balls from the game, as well as keeping count
 * of the number of balls that are currently in the game.
 */
public class BallRemover implements HitListener {
    private final Game game;
    private final Counter remainingBalls;
    /**
     * Constructor.
     * @param game The game.
     * @param remainingBlocks The remaining blocks.
     */
    public BallRemover(Game game, Counter remainingBlocks) {
        this.game = game;
        this.remainingBalls = remainingBlocks;
    }
    // Blocks that are hit should be removed
// from the game. Remember to remove this listener from the block
// that is being removed from the game.
    @Override
    public void hitEvent(Block beingHit, Ball hitter) {
        hitter.removeFromGame(this.game);
        this.remainingBalls.decrease(1);
    }
}

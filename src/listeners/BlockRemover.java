// Yuval Weber 207455437
package listeners;
// a listeners.BlockRemover is in charge of removing blocks from the game, as well as keeping count
// of the number of blocks that remain.

import gameManagement.Game;
import sprites.Ball;
import coliisions.Block;

/**
 * listeners.BlockRemover class to remove blocks from the game.
 */
public class BlockRemover implements HitListener {
    private final Game game;
    private final Counter remainingBlocks;
    /**
     * Constructor for the listeners.BlockRemover class.
     *
     * @param game The game to remove the block from.
     * @param remainingBlocks The counter of the remaining blocks.
     */
    public BlockRemover(Game game, Counter remainingBlocks) {
        this.game = game;
        this.remainingBlocks = remainingBlocks;
    }
    // Blocks that are hit should be removed
// from the game. Remember to remove this listener from the block
// that is being removed from the game.
    @Override
    public void hitEvent(Block beingHit, Ball hitter) {
        beingHit.removeHitListener(this);
        beingHit.removeFromGame(this.game);
        this.remainingBlocks.decrease(1);
    }
}

// Yuval Weber 207455437
package sprites;
import biuoop.DrawSurface;
import listeners.Counter;
import gameManagement.Game;

/**
 * The Geometry.ScoreIndicator class represents the score indicator in the game.
 * The score indicator is a sprite that can be drawn on the screen.
 */
public class ScoreIndicator implements Sprite {
    private final Counter score;

    /**
     * Constructor for the Geometry.ScoreIndicator class.
     *
     * @param score The score counter.
     */
    public ScoreIndicator(Counter score) {
        this.score = score;
    }

    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(java.awt.Color.BLACK);
        d.drawText(350, 15, "Score: " + this.score.getValue(), 15);
    }

    @Override
    public void timePassed() {
    }

    /**
     * Add the Geometry.ScoreIndicator to the game.
     *
     * @param g The game to add the Geometry.ScoreIndicator to.
     */
    public void addToGame(Game g) {
        g.addSprite(this);
    }
}

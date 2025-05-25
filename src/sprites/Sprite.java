// 207455437 Yuval Weber
package sprites;
import biuoop.DrawSurface;
/**
 * The Geometry.Sprite interface represents a game object that can be drawn to the screen and updated.
 */
public interface Sprite {
    // draw the sprite to the screen
    /**
     * Draw the sprite to the screen.
     * @param d the DrawSurface to draw on
     */
    void drawOn(DrawSurface d);
    // notify the sprite that time has passed
    /**
     * Notify the sprite that time has passed.
     */
    void timePassed();
}

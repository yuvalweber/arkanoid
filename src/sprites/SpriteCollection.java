// 207455437 Yuval Weber
package sprites;
import biuoop.DrawSurface;
import java.util.ArrayList;
/**
 * Geometry.SpriteCollection class.
 * The Geometry.SpriteCollection will hold a collection of sprites.
 * It will have the following methods:
 * addSprite(Geometry.Sprite s) - add the given sprite to the collection.
 * notifyAllTimePassed() - call timePassed() on all sprites.
 * drawAllOn(d) - call drawOn(d) on all sprites.
 */
public class SpriteCollection {
    private final ArrayList<Sprite> sprites;

    /**
     * Constructor.
     * @param sprites - list of sprites.
     */
    public SpriteCollection(ArrayList<Sprite> sprites) {
        this.sprites = sprites;
    }

    /**
     * Constructor.
     */
    public SpriteCollection() {
        this.sprites = new ArrayList<>();
    }
    /**
     * Add the given sprite to the collection.
     * @param s - sprite.
     */
    public void addSprite(Sprite s) {
        this.sprites.add(s);
    }

    /**
     * Get the list of sprites.
     * @return list of sprites.
     */
    public ArrayList<Sprite> getSprites() {
        return this.sprites;
    }
    // call timePassed() on all sprites.
    /**
     * Call timePassed() on all sprites.
     */
    public void notifyAllTimePassed() {
        ArrayList<Sprite> spritesCopy = new ArrayList<>(this.sprites);
        for (Sprite s : spritesCopy) {
            s.timePassed();
        }
    }
    // call drawOn(d) on all sprites.
    /**
     * Call drawOn(d) on all sprites.
     * @param d - DrawSurface.
     */
    public void drawAllOn(DrawSurface d) {
        ArrayList<Sprite> spritesCopy = new ArrayList<>(this.sprites);
        for (Sprite s : spritesCopy) {
            s.drawOn(d);
        }
    }
}
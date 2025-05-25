// 207455437 Yuval Weber
package coliisions;
import biuoop.DrawSurface;
import gameManagement.Game;
import geometry.Line;
import geometry.Point;
import geometry.Rectangle;
import geometry.Velocity;
import listeners.HitListener;
import listeners.HitNotifier;
import sprites.Ball;
import sprites.Sprite;

import java.awt.Color;
import java.util.List;
import java.util.ArrayList;
/**
 * The Geometry.Block class represents a block in the game.
 * The block is a rectangle that has a color and a rectangle.
 * The block can be drawn on the screen and can be collided with.
 */
public class Block implements Collidable, Sprite, HitNotifier {
    private List<HitListener> hitListeners;
    private final Rectangle rectangle;
    private final Color boundColor = Color.BLACK;
    /**
     * Constructor for the Geometry.Block class.
     *
     * @param rectangle The rectangle that represents the block.
     */
    public Block(Rectangle rectangle) {
        this.rectangle = rectangle;
    }

    /**
     * Constructor for the Geometry.Block class.
     *
     * @param x The x coordinate of the upper left point of the block.
     * @param y The y coordinate of the upper left point of the block.
     * @param width The width of the block.
     * @param height The height of the block.
     */
    public Block(int x, int y, int width, int height) {
        this.rectangle = new Rectangle(x, y, width + x, height + y, Color.BLACK);
    }

    /**
     * Constructor for the Geometry.Block class.
     *
     * @param x The x coordinate of the upper left point of the block.
     * @param y The y coordinate of the upper left point of the block.
     * @param width The width of the block.
     * @param height The height of the block.
     * @param color The color of the block.
     */
    public Block(int x, int y, int width, int height, Color color) {
        this.rectangle = new Rectangle(x, y, width + x, height + y, color);
    }
    @Override
    public void addHitListener(HitListener hl) {
        if (this.hitListeners == null) {
            this.hitListeners = new ArrayList<>();
        }
        this.hitListeners.add(hl);
    }
    @Override
    public void removeHitListener(HitListener hl) {
        if (this.hitListeners == null) {
            return;
        }
        this.hitListeners.remove(hl);
    }
    @Override
    public Rectangle getCollisionRectangle() {
        return this.rectangle;
    }
    @Override
    public Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity) {
        for (Line line : this.rectangle.getLinesArr()) {
            if (line.isPointInRange(collisionPoint)) {
                if (!ballColorMatch(hitter)) {
                    this.notifyHit(hitter);
                }
                if (line.isLineParallelToYAxis()) {
                    return new Velocity(-currentVelocity.getDx(), currentVelocity.getDy());
                } else {
                    return new Velocity(currentVelocity.getDx(), -currentVelocity.getDy());
                }
            }
        }
        return currentVelocity;
    }
    @Override
    public void drawOn(DrawSurface surface) {
        surface.setColor(this.rectangle.getColor());
        surface.fillRectangle((int) this.rectangle.getUpperLeft().getX(),
                (int) this.rectangle.getUpperLeft().getY(),
                (int) this.rectangle.getWidth(),
                (int) this.rectangle.getHeight());
        surface.setColor(this.boundColor);
        surface.drawRectangle((int) this.rectangle.getUpperLeft().getX(),
                (int) this.rectangle.getUpperLeft().getY(),
                (int) this.rectangle.getWidth(),
                (int) this.rectangle.getHeight());
    }


    /**
     * Notify all the hit listeners that a hit event occurred.
     * @param hitter The ball that hit the block.
     */
    private void notifyHit(Ball hitter) {
        // Make a copy of the hitListeners before iterating over them.
        if (this.hitListeners == null) {
            return;
        }
        List<HitListener> listeners = new ArrayList<HitListener>(this.hitListeners);
        // Notify all listeners about a hit event:
        for (HitListener hl : listeners) {
            hl.hitEvent(this, hitter);
        }
    }

    /**
     * Remove the block from the game.
     * @param game The game to remove the block from.
     */
    public void removeFromGame(Game game) {
        game.removeCollidable(this);
        game.removeSprite(this);
    }

    /**
     * Check if the ball's color matches the block's color.
     * @param ball The ball to check the color with.
     * @return True if the colors match, false otherwise.
     */
    public boolean ballColorMatch(Ball ball) {
        return this.rectangle.getColor().equals(ball.getColor());
    }
    @Override
    public void timePassed() {
        // do nothing
    }

    /**
     * Add the block to the game.
     *
     * @param game The game to add the block to.
     */
    public void addToGame(Game game) {
        game.addCollidable(this);
        game.addSprite(this);
    }
}

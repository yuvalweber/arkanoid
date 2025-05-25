// 207455437 Yuval Weber
package coliisions;
import biuoop.DrawSurface;
import biuoop.KeyboardSensor;
import biuoop.GUI;
import gameManagement.Game;
import geometry.Line;
import geometry.Point;
import geometry.Rectangle;
import geometry.Velocity;
import sprites.Ball;
import sprites.Sprite;

/**
 * The Geometry.Paddle class represents a paddle that can move left and right.
 * The paddle is a block that can be drawn on a DrawSurface.
 * It also implements the Geometry.Sprite and Geometry.Collidable interfaces.
 */
public class Paddle implements Sprite, Collidable {
    private final KeyboardSensor keyboard;
    private final GUI gui;
    private final Block block;
    private final double moveAmount;
    private static final double THRESHOLD = 0.0001;

    /**
     * Constructs a paddle with the specified block, GUI, and color.
     *
     * @param block the block representing the paddle
     * @param gui the GUI of the game
     */
    public Paddle(Block block, GUI gui) {
        this.block = block;
        this.gui = gui;
        this.keyboard = this.gui.getKeyboardSensor();
        this.moveAmount = this.getCollisionRectangle().getWidth() / 10;
    }

    /**
     * Moves the paddle to the left.
     */
    public void moveLeft() {
        double boundSize = 20;
        double rightBound = this.gui.getDrawSurface().getWidth() - boundSize;
        double nextMove = this.getCollisionRectangle().getUpperLeft().getX() - this.moveAmount;
        if (nextMove < 0) {
            nextMove = rightBound - this.getCollisionRectangle().getWidth() - THRESHOLD;
        }
        this.getCollisionRectangle().setUpperLeft(
                new Point(nextMove, this.getCollisionRectangle().getUpperLeft().getY())
        );
        this.getCollisionRectangle().populateLinesArr();
    }

    /**
     * Moves the paddle to the right.
     */
    public void moveRight() {
        double boundSize = 20;
        double rightBound = this.gui.getDrawSurface().getWidth() - boundSize;
        double nextMove = this.getCollisionRectangle().getUpperLeft().getX() + this.moveAmount;
        if (nextMove + this.getCollisionRectangle().getWidth() > rightBound) {
            nextMove = boundSize + THRESHOLD;
        }
        this.getCollisionRectangle().setUpperLeft(
                new Point(nextMove, this.getCollisionRectangle().getUpperLeft().getY())
        );
        this.getCollisionRectangle().populateLinesArr();
    }
    // Geometry.Sprite
    @Override
    public void timePassed() {
        if (this.keyboard.isPressed(KeyboardSensor.RIGHT_KEY)) {
            this.moveRight();
        }
        if (this.keyboard.isPressed(KeyboardSensor.LEFT_KEY)) {
            this.moveLeft();
        }
    }
    @Override
    public void drawOn(DrawSurface d) {
        this.block.drawOn(d);
    }
    // Geometry.Collidable
    @Override
    public Rectangle getCollisionRectangle() {
        return this.block.getCollisionRectangle();
    }


    @Override
    public Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity) {
        for (Line line : this.getCollisionRectangle().getLinesArr()) {
            if (line.isPointInRange(collisionPoint)) {
                int region = this.getCollisionRectangle().getCollisionRegion(collisionPoint);
                double currentSpeed = Velocity.getSpeedFromVelocity(currentVelocity);
                switch (region) {
                    case 1:
                        currentVelocity = Velocity.fromAngleAndSpeed(300, currentSpeed);
                        break;
                    case 2:
                        currentVelocity = Velocity.fromAngleAndSpeed(330, currentSpeed);
                        break;
                    case 3:
                        currentVelocity = new Velocity(currentVelocity.getDx(), -currentVelocity.getDy());
                        break;
                    case 4:
                        currentVelocity = Velocity.fromAngleAndSpeed(30, currentSpeed);
                        break;
                    case 5:
                        currentVelocity = Velocity.fromAngleAndSpeed(60, currentSpeed);
                        break;
                    default:
                        break;
                }
            }
        }
        return currentVelocity;
    }
    // Add this paddle to the game.
    /**
     * Adds this paddle to the specified game.
     *
     * @param g the game to add this paddle to
     */
    public void addToGame(Game g) {
        g.addSprite(this);
        g.addCollidable(this);
    }
}
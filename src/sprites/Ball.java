// 207455437 Yuval Weber
package sprites;
import java.awt.Color;
import biuoop.DrawSurface;
import coliisions.Collidable;
import coliisions.CollisionInfo;
import gameManagement.Game;
import gameManagement.GameEnvironment;
import geometry.Line;
import geometry.Point;
import geometry.Rectangle;
import geometry.Velocity;

/**
 * The Geometry.Ball class represents a ball with a center point, radius, color, velocity.
 * The Geometry.Ball also have info regarding his collision and collisionObjects.
 * It can be drawn on a DrawSurface, and its movement can be managed.
 */
public class Ball implements Sprite {
    private Point center;
    private final int radius;
    private Color color;
    private Velocity vt;
    private final GameEnvironment gameEnvironment;
    private static final double THRESHOLD = 0.0001;


    /**
     * Constructor for the Geometry.Ball class.
     *
     * @param center The center point of the ball.
     * @param r The radius of the ball.
     * @param color The color of the ball.
     * @param gameEnvironment The game environment of the ball.
     */
    public Ball(Point center, int r, Color color, GameEnvironment gameEnvironment) {
        this.center = center;
        this.radius = r;
        this.color = color;
        this.gameEnvironment = gameEnvironment;
        this.vt = new Velocity(0, 0);
    }

    /**
     * Constructor for the Geometry.Ball class.
     *
     * @param x The x-coordinate of the center point of the ball.
     * @param y The y-coordinate of the center point of the ball.
     * @param r The radius of the ball.
     * @param color The color of the ball
     * @param gameEnvironment The game environment of the ball.
     */
    public Ball(int x, int y, int r, Color color, GameEnvironment gameEnvironment) {
        this(new Point(x, y), r, color, gameEnvironment);
    }

    /**
     * Gets the x-coordinate of the ball's center.
     *
     * @return The x-coordinate of the center point.
     */
    public int getX() {
        return (int) this.center.getX();
    }

    /**
     * Gets the y-coordinate of the ball's center.
     *
     * @return The y-coordinate of the center point.
     */
    public int getY() {
        return (int) this.center.getY();
    }

    /**
     * Gets the size (radius) of the ball.
     *
     * @return The radius of the ball.
     */
    public int getSize() {
        return this.radius;
    }

    /**
     * Gets the color of the ball.
     *
     * @return The color of the ball.
     */
    public Color getColor() {
        return this.color;
    }

    /**
     * Draws the ball on the given DrawSurface.
     *
     * @param surface The DrawSurface to draw on.
     */
    public void drawOn(DrawSurface surface) {
        surface.setColor(this.getColor());
        surface.fillCircle(getX(), getY(), this.getSize());
    }

    /**
     * Sets the velocity of the ball.
     *
     * @param v The velocity to set.
     */
    public void setVelocity(Velocity v) {
        this.vt = v;
    }

    /**
     * Sets the velocity of the ball.
     *
     * @param dx The change in x direction.
     * @param dy The change in y direction.
     */
    public void setVelocity(double dx, double dy) {
        this.vt = new Velocity(dx, dy);
    }

    /**
     * Gets the velocity of the ball.
     *
     * @return The current velocity of the ball.
     */
    public Velocity getVelocity() {
        return this.vt;
    }

    /**
     * Gets the next location of the ball based on its current velocity.
     * @param location the location of the point to check
     * @return The next location point of the ball.
     */
    public Point getBallNextLocation(Point location) {
        Point newLoc = new Point(location.getX(), location.getY());
        if (this.getVelocity().getDx() > 0) {
            newLoc.setX(location.getX() + this.getVelocity().getDx() + this.getSize());
        }
        if (this.getVelocity().getDx() < 0) {
            newLoc.setX(location.getX() + this.getVelocity().getDx() - this.getSize());
        }
        if (this.getVelocity().getDy() > 0) {
            newLoc.setY(location.getY() + this.getVelocity().getDy() + this.getSize());
        }
        if (this.getVelocity().getDy() < 0) {
            newLoc.setY(location.getY() + this.getVelocity().getDy() - this.getSize());
        }
        return newLoc;
    }

    /**
     * Applies the velocity to the ball and changes its location based on collisions with objects.
     */
    private void applyVelocityAndChangeLocation() {
        Point newCenter = this.getVelocity().applyToPoint(this.center);
        Point nextLocation = this.getBallNextLocation(this.center);
        // specify line as the movement of the ball from the center
        Line ballMove = new Line(this.center.getX(), this.center.getY(), nextLocation.getX(), nextLocation.getY());
        CollisionInfo collisionInfo = this.gameEnvironment.getClosestCollision(ballMove);
        if (collisionInfo != null) {
            Point collisionPoint = collisionInfo.collisionPoint();
            // if from the right
            if (collisionPoint.getX() >= this.center.getX()) {
                newCenter.setX(collisionPoint.getX() - THRESHOLD);
                // from the left
            } else if (this.center.getX() >= collisionPoint.getX()) {
                newCenter.setX(collisionPoint.getX() + THRESHOLD);
            }
            // from above
            if (collisionPoint.getY() <= this.center.getY()) {
                newCenter.setY(collisionPoint.getY() + THRESHOLD);
            } else if (this.center.getY() <= collisionPoint.getY()) {
                newCenter.setY(collisionPoint.getY() - THRESHOLD);
            }
            this.setVelocity(collisionInfo.collisionObject().hit(this, collisionPoint, this.getVelocity()));
        }
        this.center = newCenter;
    }


    /**
     * Moves the ball outside if it is inside a collidable object.
     * @return true if the ball was inside a collidable object, false otherwise.
     */
    private boolean moveBallOutsideIfInside() {
        boolean isInside = false;
        for (Collidable c: this.gameEnvironment.getCollidables()) {
            Rectangle rect = c.getCollisionRectangle();
            double currentSpeed = Velocity.getSpeedFromVelocity(this.getVelocity());
            Velocity temp;
            if (rect.isInside(this.center)) {
                isInside = true;
                int region = rect.getCollisionRegion(this.center);
                switch (region) {
                    case 1:
                        temp = Velocity.fromAngleAndSpeed(300, currentSpeed);
                        this.center = temp.applyToPoint(this.center);
                        break;
                    case 2:
                        temp = Velocity.fromAngleAndSpeed(330, currentSpeed);
                        this.center = temp.applyToPoint(this.center);
                        break;
                    case 3:
                        temp = new Velocity(this.getVelocity().getDx(), -this.getVelocity().getDy());
                        this.center = temp.applyToPoint(this.center);
                        break;
                    case 4:
                        temp = Velocity.fromAngleAndSpeed(30, currentSpeed);
                        this.center = temp.applyToPoint(this.center);
                        break;
                    case 5:
                        temp = Velocity.fromAngleAndSpeed(60, currentSpeed);
                        this.center = temp.applyToPoint(this.center);
                        break;
                    default:
                        break;
                }
            }
        }
        return isInside;
    }

    /**
     * Moves the ball one-step considering collisions with objects.
     */
    public void moveOneStep() {
        boolean isInside = this.moveBallOutsideIfInside();
        while (isInside) {
            isInside = this.moveBallOutsideIfInside();
        }
        this.applyVelocityAndChangeLocation();
    }

    @Override
    public void timePassed() {
        this.moveOneStep();
    }

    /**
     * Adds the ball to the game.
     *
     * @param g The game to add the ball to.
     */
    public void addToGame(Game g) {
        g.addSprite(this);
    }

    /**
     * Sets the color of the ball.
     *
     * @param c The color to set.
     */
    public void setColor(Color c) {
        this.color = c;
    }

    /**
     * Removes the ball from the game.
     *
     * @param game The game to remove the ball from.
     */
    public void removeFromGame(Game game) {
        game.removeSprite(this);
    }
}

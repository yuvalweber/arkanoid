// 207455437 Yuval Weber
package coliisions;

import geometry.Point;
import geometry.Rectangle;
import geometry.Velocity;
import sprites.Ball;

/**
 * The Geometry.Collidable interface will be used by things that can be collided with.
 * It will have a method that returns the "collision shape" of the object and
 * a method that will notify the object that we collided with it at a given
 * point with a given velocity.
 */
public interface Collidable {
    // Return the "collision shape" of the object.
    /**
     * The method getCollisionRectangle returns the "collision shape" of the object.
     * @return the "collision shape" of the object.
     */
    Rectangle getCollisionRectangle();
    // Notify the object that we collided with it at collisionPoint with
    // a given velocity.
    // The return is the new velocity expected after the hit (based on
    // the force the object inflicted on us).
    /**
     * The method hit notifies the object that we collided with it at collisionPoint with a given velocity.
     * @param hitter the ball that hit the object.
     * @param collisionPoint the point of the collision.
     * @param currentVelocity the current velocity of the object.
     * @return the new velocity expected after the hit.
     */
    Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity);
}

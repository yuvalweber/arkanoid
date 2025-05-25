// 207455437 Yuval Weber
package coliisions;

import geometry.Point;

/**
 * coliisions.CollisionInfo class.
 * This class holds the information about the collision point and the collidable object.
 */
public class CollisionInfo {

    private final Point collisionPoint;
    private final Collidable collisionObject;
    /**
     * Constructor.
     * @param collisionPoint - the point at which the collision occurs.
     * @param collisionObject - the collidable object involved in the collision.
     */
    public CollisionInfo(Point collisionPoint, Collidable collisionObject) {
        this.collisionPoint = collisionPoint;
        this.collisionObject = collisionObject;
    }
    // the point at which the collision occurs.
    /**
     * @return the point at which the collision occurs.
     */
    public Point collisionPoint() {
        return this.collisionPoint;
    }
    // the collidable object involved in the collision.
    /**
     * @return the collidable object involved in the collision.
     */
    public Collidable collisionObject() {
        return this.collisionObject;
    }
}

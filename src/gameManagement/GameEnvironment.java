// 207455437 Yuval Weber
package gameManagement;
import coliisions.Collidable;
import coliisions.CollisionInfo;
import geometry.Line;
import geometry.Point;
import geometry.Rectangle;

import java.util.List;
/**
 * The gameManagement.GameEnvironment class holds a collection of collidables.
 * It has a method that checks if a given trajectory intersects with any of the collidables.
 * If it does, it returns the closest collision point and the collidable object that was hit.
 */
public class GameEnvironment {
    private final List<Collidable> collidables;

    /**
     * Constructor.
     * @param collidables - a list of collidables.
     */
    public GameEnvironment(List<Collidable> collidables) {
        this.collidables = collidables;
    }

    /**
     * Constructor.
     */
    public GameEnvironment() {
        this.collidables = new java.util.ArrayList<Collidable>();
    }

    // add the given collidable to the environment.
    /**
     * Add a collidable to the environment.
     * @param c - the collidable to add.
     */
    public void addCollidable(Collidable c) {
        collidables.add(c);
    }

    /**
     * Get a collidables list.
     * @return the collidables list.
     */
    public List<Collidable> getCollidables() {
        return this.collidables;
    }
    // Assume an object moving from line.start() to line.end().
    // If this object will not collide with any of the collidables
    // in this collection, return null. Else, return the information
    // about the closest collision that is going to occur.

    /**
     * Get the closest collision point of a trajectory with the collidables.
     * @param trajectory - the trajectory to check for collisions.
     * @return the closest collision point and the collidable object that was hit.
     */
    public CollisionInfo getClosestCollision(Line trajectory) {
        Point closestCollisionPoint = null;
        Collidable closestCollidable = null;
        double minDistance = Double.MAX_VALUE;
        for (Collidable c : collidables) {
            Rectangle rect = c.getCollisionRectangle();
            Point currentCollisionPoint = trajectory.closestIntersectionToStartOfLine(rect);
            if (currentCollisionPoint != null) {
                double distance = trajectory.start().distance(currentCollisionPoint);
                if (distance < minDistance) {
                    minDistance = distance;
                    closestCollidable = c;
                    closestCollisionPoint = currentCollisionPoint;
                }
            }
        }
        if (closestCollisionPoint == null) {
            return null;
        }
        return new CollisionInfo(closestCollisionPoint, closestCollidable);
    }
}
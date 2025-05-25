// 207455437 Yuval Weber
package geometry;
/**
 * This class represents a two-dimensional point in a Cartesian coordinate system.
 * It provides methods to calculate distance to another point, check for equality,
 * and access or modify the x and y coordinates.
 */
public class Point {

    private double x;
    private double y;

    // constructor
    /**
     * Constructs a Geometry.Point object with the specified x and y coordinates.
     *
     * @param x - The x-coordinate of the point.
     * @param y - The y-coordinate of the point.
     */
    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    // distance -- return the distance of this point to the other point
    /**
     * Calculates and returns the Euclidean distance between this point and another point.
     *
     * @param other - The other Geometry.Point object to calculate the distance to.
     * @return The distance between this point and the other point.
     */
    public double distance(Point other) {
        if (other == null) {
            return Double.MAX_VALUE;
        }
        // Use the distance formula
        return Math.sqrt(Math.pow(this.x - other.x, 2) + Math.pow(this.y - other.y, 2));
    }

    // equals -- return true is the points are equal, false otherwise
    /**
     * Compares this Geometry.Point object with another Geometry.Point object for equality.
     * Two points are considered equal if their x and y coordinates are within a small tolerance (epsilon).
     *
     * @param other - The other Geometry.Point object to compare with.
     * @return True if the points are equal, False otherwise.
     */
    public boolean equals(Point other) {
        final double epsilon = 0.000001d; // Tolerance for floating-point comparison
        return (other != null) && (Math.abs(other.x - this.x) < epsilon) && (Math.abs(other.y - this.y) < epsilon);
    }

    // Return the x and y values of this point
    /**
     * Returns the x-coordinate of this point.
     *
     * @return The x-coordinate of this point.
     */
    public double getX() {
        return this.x;
    }

    /**
     * Returns the y-coordinate of this point.
     *
     * @return The y-coordinate of this point.
     */
    public double getY() {
        return this.y;
    }

    /**
     * Sets the x-coordinate of this point.
     *
     * @param x - The new x-coordinate value.
     */
    public void setX(double x) {
        this.x = x;
    }

    /**
     * Sets the y-coordinate of this point.
     *
     * @param y - The new y-coordinate value.
     */
    public void setY(double y) {
        this.y = y;
    }
}
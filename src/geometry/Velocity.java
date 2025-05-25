// 207455437 Yuval Weber
package geometry;

/**
 * Geometry.Velocity specifies the change in position on the `x` and the `y` axes.
 */
public class Velocity {
    private double dx; // Geometry.Velocity component along the x-axis
    private double dy; // Geometry.Velocity component along the y-axis

    /**
     * Constructs a new Geometry.Velocity object with the specified components.
     * @param dx the change in position along the x-axis
     * @param dy the change in position along the y-axis
     */
    public Velocity(double dx, double dy) {
        this.dx = dx;
        this.dy = dy;
    }

    /**
     * Gets the velocity component along the x-axis.
     * @return the velocity component along the x-axis
     */
    public double getDx() {
        return this.dx;
    }

    /**
     * Gets the velocity component along the y-axis.
     * @return the velocity component along the y-axis
     */
    public double getDy() {
        return this.dy;
    }

    /**
     * Sets the velocity component along the x-axis.
     * @param dx the new velocity component along the x-axis
     */
    public void setDx(double dx) {
        this.dx = dx;
    }

    /**
     * Sets the velocity component along the y-axis.
     * @param dy the new velocity component along the y-axis
     */
    public void setDy(double dy) {
        this.dy = dy;
    }

    /**
     * Takes a point with position (x,y) and returns a new point with position (x+dx, y+dy).
     * @param p the point to which the velocity will be applied
     * @return a new point with the applied velocity
     */
    public Point applyToPoint(Point p) {
        return new Point(p.getX() + dx, p.getY() + dy);
    }

    /**
     * Converts a speed and angle to velocity components along the x and y axes.
     * @param angle the angle in degrees
     * @param speed the speed or magnitude of the velocity
     * @return a new Geometry.Velocity object with components derived from the angle and speed
     */
    public static Velocity fromAngleAndSpeed(double angle, double speed) {
        double radian = Math.toRadians(angle) - Math.PI / 2;
        double dx = Math.cos(radian) * speed;
        double dy = Math.sin(radian) * speed;
        return new Velocity(dx, dy);
    }

    /**
     * Gets the speed of the velocity.
     * @param v the velocity
     * @return the speed of the velocity
     */
    public static double getSpeedFromVelocity(Velocity v) {
        return Math.sqrt(Math.pow(v.getDx(), 2) + Math.pow(v.getDy(), 2));
    }
}

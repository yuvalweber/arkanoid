// 207455437 Yuval Weber
package geometry;
import biuoop.DrawSurface;
import java.awt.Color;

/**
 * The Geometry.Rectangle class represents a rectangle defined by its start point, width, height, and color.
 * It also contains an array of Geometry.Line objects representing its edges.
 */
public class Rectangle {
    private final Point upperLeft;
    private final double width;
    private final double height;
    private Color color;
    private Line[] lines;

    /**
     * Constructs a rectangle with the specified start point, width, height, and color.
     *
     * @param upperLeft the starting point of the rectangle
     * @param width the width of the rectangle
     * @param height the height of the rectangle
     * @param color the color of the rectangle
     */
    public Rectangle(Point upperLeft, double width, double height, Color color) {
        this.upperLeft = upperLeft;
        this.width  = width;
        this.height = height;
        this.color = color;
        this.populateLinesArr();
    }

    /**
     * Constructs a rectangle with the specified start point, width, height.
     *
     * @param upperLeft the starting point of the rectangle
     * @param width the width of the rectangle
     * @param height the height of the rectangle
     */
    public Rectangle(Point upperLeft, double width, double height) {
        this.upperLeft = upperLeft;
        this.width  = width;
        this.height = height;
        this.color = Color.BLACK;
        this.populateLinesArr();
    }


    /**
     * Constructs a rectangle using coordinates of two opposite corners.
     * The color is set to black by default.
     *
     * @param x1 the x-coordinate of the first corner
     * @param y1 the y-coordinate of the first corner
     * @param x2 the x-coordinate of the opposite corner
     * @param y2 the y-coordinate of the opposite corner
     */
    public Rectangle(double x1, double y1, double x2, double y2) {
        this.upperLeft = new Point((int) x1, (int) y1);
        this.width = x2 - x1;
        this.height = y2 - y1;
        this.populateLinesArr();
        this.color = Color.black;
    }

    /**
     * Constructs a rectangle with the specified width and height.
     * The start point is set to (0, 0) and the color is set to black by default.
     *
     * @param width the width of the rectangle
     * @param height the height of the rectangle
     */
    public Rectangle(double width, double height) {
        this.upperLeft = new Point(0, 0);
        this.width  = width;
        this.height = height;
        this.color = Color.BLACK;
        this.populateLinesArr();
    }

    /**
     * Constructs a rectangle using coordinates of two opposite corners and a color.
     *
     * @param x1 the x-coordinate of the first corner
     * @param y1 the y-coordinate of the first corner
     * @param x2 the x-coordinate of the opposite corner
     * @param y2 the y-coordinate of the opposite corner
     * @param color the color of the rectangle
     */
    public Rectangle(double x1, double y1, double x2, double y2, Color color) {
        this.upperLeft = new Point((int) x1, (int) y1);
        this.width = x2 - x1;
        this.height = y2 - y1;
        this.populateLinesArr();
        this.color = color;
    }

    /**
     * Returns the width of the rectangle.
     *
     * @return the width of the rectangle
     */
    public double getWidth() {
        return this.width;
    }

    /**
     * Returns the height of the rectangle.
     *
     * @return the height of the rectangle
     */
    public double getHeight() {
        return this.height;
    }

    /**
     * Returns the start point of the rectangle.
     *
     * @return the start point of the rectangle
     */
    public Point getUpperLeft() {
        return this.upperLeft;
    }

    /**
     * Sets the start point of the rectangle.
     *
     * @param upperLeft the new start point of the rectangle
     */
    public void setUpperLeft(Point upperLeft) {
        this.upperLeft.setX(upperLeft.getX());
        this.upperLeft.setY(upperLeft.getY());
    }
    /**
     * Returns the color of the rectangle.
     *
     * @return the color of the rectangle
     */
    public Color getColor() {
        return this.color;
    }

    /**
     * Sets the color of the rectangle.
     *
     * @param color the new color of the rectangle
     */
    public void setColor(Color color) {
        this.color = color;
    }
    /**
     * Draws the rectangle on the given drawing surface.
     *
     * @param surface the drawing surface to draw the rectangle on
     */

    public void drawOn(DrawSurface surface) {
        surface.setColor(this.color);
        surface.fillRectangle((int) this.getUpperLeft().getX(),  (int) this.getUpperLeft().getY(),
                (int) this.getWidth(),  (int) this.getHeight());
    }

    /**
     * Populates the lines array with Geometry.Line objects representing the edges of the rectangle.
     */
    public void populateLinesArr() {
        lines = new Line[4];
        lines[0] = new Line(
                this.getUpperLeft().getX(), this.getUpperLeft().getY(),
                this.getUpperLeft().getX(), this.getUpperLeft().getY() + this.getHeight()
        );
        lines[1] = new Line(
                this.getUpperLeft().getX(), this.getUpperLeft().getY() + this.getHeight(),
                this.getUpperLeft().getX() + this.getWidth(), this.getUpperLeft().getY() + this.getHeight()
        );
        lines[2] = new Line(
                this.getUpperLeft().getX() + this.getWidth(), this.getUpperLeft().getY() + this.getHeight(),
                this.getUpperLeft().getX() + this.getWidth(), this.getUpperLeft().getY()
        );
        lines[3] = new Line(
                this.getUpperLeft().getX(), this.getUpperLeft().getY(),
                this.getUpperLeft().getX() + this.getWidth(), this.getUpperLeft().getY()
        );
    }


    /**
     * Returns a list of intersection points with a given line.
     *
     * @param line the line to check for intersections
     * @return a list of intersection points
     */
    public java.util.List<Point> intersectionPoints(Line line) {
        java.util.List<Point> intersectionPoints = new java.util.ArrayList<>();
        for (Line l : this.lines) {
            Point intersection = l.intersectionWith(line);
            if (intersection != null) {
                intersectionPoints.add(intersection);
            }
        }
        return intersectionPoints;
    }
    /**
     * Checks if a point is inside the rectangle.
     *
     * @param point the point to check
     * @return true if the point is inside the rectangle, false otherwise
     */
    public boolean isInside(Point point) {
        return this.getUpperLeft().getX() < point.getX()
                && this.getUpperLeft().getY() < point.getY()
                && this.getUpperLeft().getX() + this.getWidth() > point.getX()
                && this.getUpperLeft().getY() + this.getHeight() > point.getY();
    }


    /**
     * Returns the region of the paddle that was hit by the ball.
     *
     * @param collisionPoint the point of collision
     * @return the region of the paddle that was hit
     */
    public int getCollisionRegion(Point collisionPoint) {
        double width = this.getWidth();
        int regionAmount = 5;
        double proportion = (collisionPoint.getX() - this.getUpperLeft().getX()) / width;
        if (proportion == 0) {
            proportion = 0.1;
        }
        return  (int) Math.ceil(proportion * regionAmount);
    }
    /**
     * Returns the array of Geometry.Line objects representing the edges of the rectangle.
     *
     * @return an array of Geometry.Line objects
     */
    public Line[] getLinesArr() {
        return this.lines;
    }
}

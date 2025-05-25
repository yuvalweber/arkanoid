// 207455437 Yuval Weber
package geometry;
/**
 * Represents a line segment between two points in 2D space.
 */
public class Line {
    private final Point start;
    private final Point end;
    private Point intersectionPoint;
    private static final double EPSILON = 0.000001d;

    /**
     * Constructs a line segment between two given points.
     *
     * @param start The starting point of the line.
     * @param end   The ending point of the line.
     */
    public Line(Point start, Point end) {
        if (start == null || end == null) {
           System.out.println("Start or end point are null!");
           System.exit(1);
        }
        this.start = start;
        this.end = end;
        this.intersectionPoint = null;
    }

    /**
     * Constructs a line segment between two given coordinates.
     *
     * @param x1 The x-coordinate of the starting point.
     * @param y1 The y-coordinate of the starting point.
     * @param x2 The x-coordinate of the ending point.
     * @param y2 The y-coordinate of the ending point.
     */
    public Line(double x1, double y1, double x2, double y2) {
        this(new Point(x1, y1), new Point(x2, y2));
    }

    /**
     * Returns the length of the line segment.
     *
     * @return The length of the line segment.
     */
    public double length() {
        return this.start().distance(this.end);
    }

    /**
     * Returns the middle point of the line segment.
     *
     * @return The middle point of the line segment.
     */
    public Point middle() {
        return new Point((this.end().getX() + this.start().getX()) / 2,
                (this.end().getY() + this.start().getY()) / 2);
    }

    /**
     * Returns the starting point of the line segment.
     *
     * @return The starting point of the line segment.
     */
    public Point start() {
        return this.start;
    }

    /**
     * Returns the ending point of the line segment.
     *
     * @return The ending point of the line segment.
     */
    public Point end() {
        return this.end;
    }


    /**
     * Checks if the x-coordinate of a point is within the segment of the line.
     *
     * @param pt The point to check.
     * @return true if the x-coordinate is within the segment, false otherwise.
     */
    public boolean isPointXInSegment(Point pt) {
        double maxX = Math.max(this.start.getX(), this.end.getX());
        double minX = Math.min(this.start.getX(), this.end.getX());
        return (pt.getX() > minX || Math.abs(pt.getX() - minX) < EPSILON)
                && (pt.getX() < maxX || Math.abs(pt.getX() - maxX) < EPSILON);
    }

    /**
     * Checks if the y-coordinate of a point is within the segment of the line.
     *
     * @param pt The point to check.
     * @return true if the y-coordinate is within the segment, false otherwise.
     */
    public boolean isPointYInSegment(Point pt) {
        double maxY = Math.max(this.start.getY(), this.end.getY());
        double minY = Math.min(this.start.getY(), this.end.getY());
        return (pt.getY() > minY || Math.abs(pt.getY() - minY) < EPSILON)
                && (pt.getY() < maxY || Math.abs(pt.getY() - maxY) < EPSILON);
    }

    /**
     * Checks if a point lies on the line segment.
     *
     * @param pt The point to check.
     * @return true if the point lies on the line segment, false otherwise.
     */
    public boolean isPointInRange(Point pt) {
        return this.isPointXInSegment(pt) && this.isPointYInSegment(pt);
    }

    /**
     * Finds the y-coordinate of a given x-coordinate or vice versa, based on the line equation.
     *
     * @param value The x or y coordinate value.
     * @param type  The type of coordinate to find (x or y).
     * @return The y-coordinate if type is "x", or the x-coordinate if type is "y".
     */
    public double putPointInLineEquation(double value, String type) {
        double m = (this.end.getY() - this.start.getY()) / (this.end.getX() - this.start.getX());
        double n = this.end.getY() - m * (this.end.getX());
        if (type.equals("x")) {
            return (m * value + n);
        }
        return (value - n) / m;
    }


    /**
     * Checks if a point satisfies the equation of the line.
     *
     * @param pt The point to check.
     * @return true if the point satisfies the line equation, false otherwise.
     */
    public boolean pointMeetLineEquation(Point pt) {
       // if both x equal check if y is in segment
        if (Math.abs(this.start.getX() - this.end.getX()) < EPSILON) {
            return this.isPointYInSegment(pt) && this.isPointXInSegment(pt);
        // if both y equal check if x is in segment
        } else if (Math.abs(this.start.getY() - this.end.getY()) < EPSILON) {
            return this.isPointXInSegment(pt) && this.isPointYInSegment(pt);
        } else {
            return (Math.abs(pt.getY() - this.putPointInLineEquation(pt.getX(), "x")) < EPSILON)
                    && this.isPointInRange(pt);
        }
    }

    /**
     * Calculates the determinant of two points.
     *
     * @param p1 The first point.
     * @param p2 The second point.
     * @return The determinant of the two points.
     */
    public double calcDeterminant(Point p1, Point p2) {
        return (p1.getX() * p2.getY()) - (p1.getY() * p2.getX());
    }

    /**
     * Checks if the line segment is actually a point (both start and end points are the same).
     *
     * @return true if the line segment is a point, false otherwise.
     */
    public boolean isLineAPoint() {
        return (Math.abs(this.end.getX() - this.start.getX()) < EPSILON)
                && (Math.abs(this.end.getY() - this.start.getY()) < EPSILON);
    }

    /**
     * Checks if the line segment is parallel to the y-axis.
     *
     * @return true if the line segment is parallel to the y-axis, false otherwise.
     */
    public boolean isLineParallelToYAxis() {
        return Math.abs(this.end.getX() - this.start.getX()) < EPSILON
                && Math.abs(this.end.getY() - this.start.getY()) > EPSILON;
    }

    /**
     * Checks if the line segment is parallel to the x-axis.
     *
     * @return true if the line segment is parallel to the x-axis, false otherwise.
     */
    public boolean isLineParallelToXAxis() {
        return Math.abs(this.end.getY() - this.start.getY()) < EPSILON
                && Math.abs(this.end.getX() - this.start.getX()) > EPSILON;
    }

    /**
     * Handles the special case where one or both lines are points.
     *
     * <p>This method determines if the current line and another line (represented by `other`)
     * intersect at a point when either or both lines are considered as points.
     *
     * @param other The other line to check for intersection with the current line.
     * @return {@code true} if the point situations are handled successfully (i.e.,
     *         the lines intersect at a point or are identical as points);
     *         {@code false} if they do not intersect as points.
     */
    public boolean handlePointSituation(Line other) {
        if (this.isLineAPoint() && other.isLineAPoint()) {
            return this.equals(other);
        } else if (other.isLineAPoint() && this.pointMeetLineEquation(other.start)) {
            this.intersectionPoint = new Point(other.start.getX(), other.start.getY());

        } else if (this.isLineAPoint() && other.pointMeetLineEquation(this.start)) {
            this.intersectionPoint = new Point(this.start.getX(), this.start.getY());
        } else {
            return false;
        }
        return true;
    }

    /**
     * Handles the special case where one or both lines are parallel to the Y-axis.
     *
     * <p>This method determines if the current line and another line (represented by `other`)
     * intersect when either or both lines are parallel to the Y-axis.
     *
     * @param other The other line to check for intersection with the current line.
     * @return {@code true} if the lines intersect when considering their parallelism to the Y-axis;
     *         {@code false} otherwise.
     */
    public boolean handleParallelToXSituation(Line other) {
        if (other.isLineParallelToYAxis() && this.isLineParallelToYAxis()) {
            if (Math.abs(this.start.getX() - other.start.getX()) < EPSILON) {
                // get upperLine
                double maxThisY = Math.max(this.start.getY(), this.end.getY());
                double maxOtherY = Math.max(other.start.getY(), other.end.getY());
                double minOtherY = Math.min(other.start.getY(), other.end.getY());
                double minThisY = Math.min(this.start.getY(), this.end.getY());
                // no overlap in y coordinates
                if (maxThisY < minOtherY || maxOtherY < minThisY) {
                    return false;
                    // overlap in y coordinates
                } else {
                    if (Math.abs(maxThisY - minOtherY) < EPSILON) {
                        this.intersectionPoint = new Point(this.start.getX(), maxThisY);
                    } else if (Math.abs(minThisY - maxOtherY) < EPSILON) {
                        this.intersectionPoint = new Point(this.start.getX(), minThisY);
                    }
                    return true;
                }
            } else {
                return false;
            }
        } else if (other.isLineParallelToYAxis() && !(this.isLineParallelToYAxis())
                && this.isPointXInSegment(other.start)) {
            double intersectY = this.putPointInLineEquation(other.start.getX(), "x");
            Point interPoint = new Point(other.start.getX(), intersectY);
            if (other.isPointYInSegment(interPoint) && this.isPointYInSegment(interPoint)) {
                this.intersectionPoint = interPoint;
                return true;
            } else {
                return false;
            }
        } else if (this.isLineParallelToYAxis() && !(other.isLineParallelToYAxis())
                && other.isPointXInSegment(this.start)) {
            double intersectY = other.putPointInLineEquation(this.start.getX(), "x");
            Point interPoint = new Point(this.start.getX(), intersectY);
            if (this.isPointYInSegment(interPoint) && other.isPointYInSegment(interPoint)) {
                this.intersectionPoint = interPoint;
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    /**
     * Handles the special case where one or both lines are parallel to the X-axis.
     *
     * <p>This method determines if the current line and another line (represented by `other`)
     * intersect when either or both lines are parallel to the X-axis.
     *
     * @param other The other line to check for intersection with the current line.
     * @return {@code true} if the lines intersect when considering their parallelism to the X-axis;
     *         {@code false} otherwise.
     */
    public boolean handleParallelToYSituation(Line other) {
        if (other.isLineParallelToXAxis() && this.isLineParallelToXAxis()) {
            if (Math.abs(this.start.getY() - other.start.getY()) < EPSILON) {
                // get upperLine
                double maxThisX = Math.max(this.start.getX(), this.end.getX());
                double maxOtherX = Math.max(other.start.getX(), other.end.getX());
                double minOtherX = Math.min(other.start.getX(), other.end.getX());
                double minThisX = Math.min(this.start.getX(), this.end.getX());
                // no overlap in x coordinates
                if (maxThisX < minOtherX || maxOtherX < minThisX) {
                    return false;
                    // overlap in X coordinates
                } else {
                    if (Math.abs(maxThisX - minOtherX) < EPSILON) {
                        this.intersectionPoint = new Point(maxThisX, this.start.getY());

                    } else if (Math.abs(minThisX - maxOtherX) < EPSILON) {
                        this.intersectionPoint = new Point(minThisX, this.start.getY());
                    }
                    return true;
                }
            } else {
                return false;
            }
        } else if (other.isLineParallelToXAxis() && !(this.isLineParallelToXAxis())
                && this.isPointYInSegment(other.start)) {
            double intersectX = this.putPointInLineEquation(other.start.getY(), "y");
            Point interPoint = new Point(intersectX, other.start.getY());
            if (other.isPointXInSegment(interPoint) && this.isPointXInSegment(interPoint)) {
                this.intersectionPoint = interPoint;
                return true;
            } else {
                return false;
            }
        } else if (this.isLineParallelToXAxis() && !(other.isLineParallelToXAxis())
                && other.isPointYInSegment(this.start())) {
            double intersectX = other.putPointInLineEquation(this.start().getY(), "y");
            Point interPoint = new Point(intersectX, this.start().getY());
            if (this.isPointXInSegment(interPoint) && other.isPointXInSegment(interPoint)) {
                this.intersectionPoint = interPoint;
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    /**
     * Checks if this line segment intersects with another line segment.
     *
     * @param other The other line segment.
     * @return true if the line segments intersect, false otherwise.
     */
    public boolean isIntersecting(Line other) {
        if (other == null) {
            return false;
        }
        Point xdiff = new Point(this.start.getX() - this.end.getX(), other.start.getX() - other.end.getX());
        Point ydiff = new Point(this.start.getY() - this.end.getY(), other.start.getY() - other.end.getY());

        double denom = this.calcDeterminant(xdiff, ydiff);
        // parallel, same point or infinite points
        if (denom < EPSILON) {
            // if at least one of the lines is a point
            if (other.isLineAPoint() || this.isLineAPoint()) {
                return this.handlePointSituation(other);
            }
            // line parallel to yAxis
            if (other.isLineParallelToYAxis() || this.isLineParallelToYAxis()) {
                return this.handleParallelToXSituation(other);
            }

            // line parallel to xAxis
            if (other.isLineParallelToXAxis() || this.isLineParallelToXAxis()) {
                return this.handleParallelToYSituation(other);
            }

            // both lines have the same slope
            double otherSlope = (other.end().getY() - other.start().getY())
                    / (other.end().getX() - other.start().getX());
            double thisLineSlope = (this.end().getY() - this.start().getY())
                    / (this.end().getX() - this.start().getX());
            if (Math.abs(otherSlope - thisLineSlope) < EPSILON) {
                double otherLineY = other.end().getY() - otherSlope * (other.end().getX());
                double thisLineY = this.end().getY() - thisLineSlope * (this.end().getX());
                // if both have same y of line
                if (Math.abs(otherLineY - thisLineY) < EPSILON) {
                    // get upperLine
                    double maxThisY = Math.max(this.start().getY(), this.end().getY());
                    double maxOtherY = Math.max(other.start().getY(), other.end().getY());
                    double minOtherY = Math.min(other.start().getY(), other.end().getY());
                    double minThisY = Math.min(this.start().getY(), this.end().getY());
                    // if it's not in the range
                    if (maxThisY < minOtherY || maxOtherY < minThisY) {
                        return false;
                        // overlap in y coordinates
                    } else {
                        if (Math.abs(maxThisY - minOtherY) < EPSILON) {
                            this.intersectionPoint = new Point(this.start().getX(), maxThisY);

                        } else if (Math.abs(minThisY - maxOtherY) < EPSILON) {
                            this.intersectionPoint = new Point(this.start().getX(), minThisY);
                        }
                        return true;
                    }
                } else {
                    return false;
                }
            }
        }

        // if lines are not parallel, calculate point using determinant.
        Point d = new Point(this.calcDeterminant(this.start(), this.end()),
                this.calcDeterminant(other.start(), other.end()));
        double intersectX = this.calcDeterminant(d, xdiff) / denom;
        double intersectY = this.calcDeterminant(d, ydiff) / denom;
        Point intersectionPoint = new Point(intersectX, intersectY);
        if (this.isPointInRange(intersectionPoint) && other.isPointInRange(intersectionPoint)) {
           this.intersectionPoint = intersectionPoint;
           return true;
        }
        return false;
    }

    /**
     * Checks if this line segment intersects with two other line segments.
     *
     * @param other1 The first other line segment.
     * @param other2 The second other line segment.
     * @return true if this line segment intersects with both other line segments, false otherwise.
     */
    public boolean isIntersecting(Line other1, Line other2) {
        return this.isIntersecting(other1) && this.isIntersecting(other2);
    }

    /**
     * Returns the intersection point if the line segments intersect.
     *
     * @param other The other line segment.
     * @return The intersection point if the line segments intersect, otherwise null.
     */
    public Point intersectionWith(Line other) {
        if (other != null && this.isIntersecting(other) && this.intersectionPoint != null) {
            // we do this to always return to null in order to not cause undefined behaviors
            Point copyOfInterPoint = new Point(this.intersectionPoint.getX(),
                                               this.intersectionPoint.getY());
            this.intersectionPoint = null;
            return copyOfInterPoint;
        }
        return null;
    }

    /**
     * Returns the closest intersection point to the start of the line segment.
     *
     * @param rect The rectangle to check for intersection with the line segment.
     * @return The closest intersection point to the start of the line segment, or null if no intersection.
     */
    public Point closestIntersectionToStartOfLine(Rectangle rect) {
        Line[] lines = rect.getLinesArr();
        Point closestIntersection = null;
        // start is based on lower x
        double minDistance = Double.MAX_VALUE;
        for (Line line : lines) {
            Point intersection = this.intersectionWith(line);
            if (intersection != null) {
                double distance = this.start.distance(intersection);
                if (distance < minDistance) {
                    minDistance = distance;
                    closestIntersection = intersection;
                }
            }
        }
        return closestIntersection;
    }

    /**
     * Checks if two line segments are equal.
     *
     * @param other The other line segment.
     * @return true if the line segments are equal, false otherwise.
     */
    public boolean equals(Line other) {
        return (other != null)
                && ((this.start().equals(other.start()) && this.end().equals(other.end()))
                || (this.start().equals(other.end()) && this.end().equals(other.start())));
    }
}
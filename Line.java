/**
 * @author Roie Amsalem
 * @version 1.0
 * @since 2024-02-01
 */
// Roie Amsalem 322535436
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Iterator;

/**
 * The Line class represents a line segment between two points in a two-dimensional space.
 * It provides methods for calculating various properties of the line, checking intersections,
  and drawing on a surface.
 */
public class Line {

    final Point start;
    final Point end;

    /**
     * Represents positive infinity slope in case of a vertical line.
     */
    public static final double INFINITE_SLOPE = 2147483647;

    /**
     * Represents a constant value indicating the end of a line segment.
     */
    public static final double END = -2147483647;

    /**
     * Constructs a new Line object with the specified start and end points.
     *
     * @param start the starting point of the line segment
     * @param end   the ending point of the line segment
     */
    public Line(Point start, Point end) {
        this.start = start;
        this.end = end;
    }

    /**
     * Calculates the length of the line segment.
     *
     * @return the length of the line segment
     */
    public double length() {
        return this.start.distance(this.end);
    }

    /**
     * Calculates the middle point of the line segment.
     *
     * @return the middle point of the line segment
     */
    public Point middle() {
        double x = (this.start.getX() + this.end.getX()) / 2;
        double y = (this.start.getY() + this.end.getY()) / 2;
        return new Point(x, y);
    }

    /**
     * Gets the starting point of the line segment.
     *
     * @return the starting point of the line segment
     */
    public Point start() {
        return this.start;
    }

    /**
     * Gets the ending point of the line segment.
     *
     * @return the ending point of the line segment
     */
    public Point end() {
        return this.end;
    }

    /**
     * Calculates the slope of the line segment.
     *
     * @return the slope of the line segment
     */
    public double findSlope() {
        if (this.start.getX() - this.end.getX() != 0) {
            double slope = (this.start.getY() - this.end.getY()) / (this.start.getX() - this.end.getX());
            return slope;
        } else {
            return INFINITE_SLOPE;
        }
    }

    /**
     * Checks if the line segment has an infinite slope (vertical line).
     *
     * @param myLine    the current line segment
     * @param otherLine the other line segment
     * @return true if either line segment has an infinite slope, false otherwise
     */
    public boolean infiniteSlope(Line myLine, Line otherLine) {
        double mySlope = myLine.findSlope();
        double otherSlope = otherLine.findSlope();
        if (mySlope == INFINITE_SLOPE) {
            double xInterception = myLine.start.getX();
            double bLine = -otherSlope * otherLine.start.getX() + otherLine.start.getY();
            double yInterception = otherSlope * xInterception + bLine;
            return true;
        } else if (otherSlope == INFINITE_SLOPE) {
            double xInterception = otherLine.start.getX();
            double bLine = -mySlope * myLine.start.getX() + myLine.start.getY();
            double yInterception = mySlope * xInterception + bLine;
            return true;
        } else {
            return false;
        }
    }

    /**
     * Calculates the y-coordinate of the intersection point for two lines with infinite slopes (vertical lines).
     *
     * @param myLine    the current line segment
     * @param otherLine the other line segment
     * @return the y-coordinate of the intersection point
     */
    public Point infiniteSlopeIntersection(Line myLine, Line otherLine) {
        double mySlope = myLine.findSlope();
        double otherSlope = otherLine.findSlope();
        if (mySlope == INFINITE_SLOPE) {
            double xInterception = myLine.start.getX();
            double bLine = -otherSlope * otherLine.start.getX() + otherLine.start.getY();
            double yInterception = otherSlope * xInterception + bLine;
            return new Point(xInterception, yInterception);
        } else if (otherSlope == INFINITE_SLOPE) {
            double xInterception = otherLine.start.getX();
            double bLine = -mySlope * myLine.start.getX() + myLine.start.getY();
            double yInterception = mySlope * xInterception + bLine;
            return new Point(xInterception, yInterception);
        }
        return new Point(END, END);
    }

    /**
     * Checks if the current line segment intersects with another line segment.
     *
     * @param other the other line segment
     * @return true if the line segments intersect, false otherwise
     */
    public boolean isIntersecting(Line other) {
        double slope = this.findSlope();
        double otherSlope = other.findSlope();
        if (slope == otherSlope && this.start.getX() == other.start.getX()) {
            return true;
        } else if (slope == otherSlope) {
            return false;
        }

        if (slope == INFINITE_SLOPE && otherSlope == INFINITE_SLOPE) {
            if (this.start.getX() != other.start.getX()) {
                return false;
            } else {
                return true;
            }
        }

        if (slope == INFINITE_SLOPE || otherSlope == INFINITE_SLOPE) {
            return infiniteSlope(this, other);
        }

        // Calculate general intersection
        double yLine = this.start.getY() - slope * this.start.getX();
        double otherYLine = other.start.getY() - otherSlope * other.start.getX();

        // Calculate general intersection
        double xInterception = (yLine - otherYLine) / (otherSlope - slope);
        return true;
    }

    /**
     * Checks if the current line segment intersects with two other line segments.
     *
     * @param other1 the first other line segment
     * @param other2 the second other line segment
     * @return true if all line segments intersect, false otherwise
     */
    public boolean isIntersecting(Line other1, Line other2) {
        return this.isIntersecting(other1) && this.isIntersecting(other2);
    }

    /**
     * Calculates the intersection point of the current line segment with another line segment.
     *
     * @param other the other line segment
     * @return the intersection point
     */
    public Point intersectionWith(Line other) {
        double slope = this.findSlope();
        double otherSlope = other.findSlope();

        // Calculate general intersection
        double yLine = this.start.getY() - slope * this.start.getX();
        double otherYLine = other.start.getY() - otherSlope * other.start.getX();

        if (slope == INFINITE_SLOPE || otherSlope == INFINITE_SLOPE) {
            return infiniteSlopeIntersection(this, other);
        }

        // Calculate general intersection
        double xInterception = (yLine - otherYLine) / (otherSlope - slope);
        double yInterception = yLine + slope * xInterception;
        Point intersection = new Point(xInterception, yInterception);
        return intersection;
    }

    /**
     * Finds the closest intersection point to the start of the line segment with a rectangle.
     *
     * @param rect the rectangle with which intersection is checked
     * @param ball the ball to which the intersection is checked
     * @return the closest intersection point to the start of the line segment, or null if no intersection
     */
    public Point closestIntersectionToStartOfLine(Rectangle rect, Ball ball) {
        Line[] linesArray = rect.linesOfRect();
        List<Point> points = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            if (this.isIntersecting(linesArray[i])) {
                Point temp = this.intersectionWith(linesArray[i]);
                // Check if the point is on the line itself.
                if (temp.getX() <= Math.max(linesArray[i].start.getX(), linesArray[i].end.getX())
                        && temp.getX() >= Math.min(linesArray[i].start.getX(), linesArray[i].end.getX())
                        && temp.getY() <= Math.max(linesArray[i].start.getY(), linesArray[i].end.getY())
                        && temp.getY() >= Math.min(linesArray[i].start.getY(), linesArray[i].end.getY())) {
                    points.add(this.intersectionWith(linesArray[i]));
                }
            }
        }
        if (points.size() == 1) {
            return points.get(0);
        }
        points = checkBallDirection(points, ball);
        if (points.size() == 0) {
            return null;
        } else {
            Point[] pointsArray = new Point[points.size()];
            for (int i = 0; i < points.size(); i++) {
                pointsArray[i] = points.get(i);
            }
            for (int i = 0; i < points.size(); i++) {
                for (int j = 0; j < points.size(); j++) {
                    if (pointsArray[i].equals(pointsArray[j])) {
                        continue;
                    }
                    if (this.start.distance(points.get(i)) - this.start.distance(points.get(j)) > 0) {
                        pointsArray[i] = pointsArray[j];
                        continue;
                    } else {
                        pointsArray[j] = pointsArray[i];
                        continue;
                    }
                }
            }
            return pointsArray[0];
        }
    }

    /**
     * Checks the direction of the ball and removes intersection points that are not relevant.
     *
     * @param points the list of intersection points to be checked
     * @param ball   the ball for which the direction is checked
     * @return the list of relevant intersection points based on the ball's direction
     */
    public List<Point> checkBallDirection(List<Point> points, Ball ball) {
        Iterator<Point> iterator = points.iterator();

        while (iterator.hasNext()) {
            Point point = iterator.next();


            //Checks which quarter on the x,y axis we are on and limits possibilites accordingly for collisions.
            if (ball.velocity.dx > 0 && ball.velocity.dy > 0) {
                if (point.getX() < ball.getX() || point.getY() < ball.getY()) {
                    iterator.remove();
                }
            } else if (ball.velocity.dx > 0 && ball.velocity.dy < 0) {
                if (point.getX() < ball.getX() || point.getY() > ball.getY()) {
                    iterator.remove();
                }
            } else if (ball.velocity.dx < 0 && ball.velocity.dy > 0) {
                if (point.getX() > ball.getX() || point.getY() < ball.getY()) {
                    iterator.remove();
                }
            } else if (ball.velocity.dx < 0 && ball.velocity.dy < 0) {
                if (point.getX() > ball.getX() || point.getY() > ball.getY()) {
                    iterator.remove();
                }
            }
        }

        return points;
    }

    /**
     * Checks if the current line segment is equal to another line segment.
     *
     * @param other the other line segment
     * @return true if the line segments are equal, false otherwise
     */
    public boolean equals(Line other) {
        if (this.findSlope() == other.findSlope()) {
            if (this.start.equals(other.start) && this.end.equals(other.end)) {
                return true;
            }
            return false;
        } else {
            return false;
        }
    }

    /**
     * Draws the line segment on a DrawSurface with a specified color.
     *
     * @param surface the DrawSurface on which the line segment will be drawn
     */
    public void drawOn(biuoop.DrawSurface surface) {
        surface.setColor(Color.black);
        surface.drawLine((int) this.start.getX(), (int) this.start.getY(),
                (int) this.end.getX(), (int) this.end.getY());
    }
}

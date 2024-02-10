/**
 * @author Roie Amsalem
 * @version 1.0
 * @since 2024-02-01
 */
// Roie Amsalem 322535436

/**
 * Represents a point in a two-dimensional space.
 */
public class Point {

    private double x;
    private double y;

    /**
     * Constructs a Point with specified x and y coordinates.
     *
     * @param x the x-coordinate of the point
     * @param y the y-coordinate of the point
     */
    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Calculates the distance between this point and another point.
     *
     * @param other the other point
     * @return the distance between the two points
     */
    public double distance(Point other) {
        return Math.sqrt((this.x - other.x) * (this.x - other.x)
                + (this.y - other.y) * (this.y - other.y));
    }

    /**
     * Checks if this point is equal to another point.
     *
     * @param other the other point
     * @return true if the points are equal, false otherwise
     */
    public boolean equals(Point other) {
        return (this.x == other.x && this.y == other.y);
    }

    /**
     * Gets the x-coordinate of this point.
     *
     * @return the x-coordinate
     */
    public double getX() {
        return this.x;
    }

    /**
     * Gets the y-coordinate of this point.
     *
     * @return the y-coordinate
     */
    public double getY() {
        return this.y;
    }

    public void updateX(int speed) {
        this.x += speed;
    }
}

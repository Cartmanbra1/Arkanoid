/**
 * @author Roie Amsalem < royiamsalem@gmail.com >
 * @version 1.0
 * @since 2024-02-01
 */
// Roie Amsalem 322535436

/**
 * Represents a velocity in a two-dimensional space.
 */
public class Velocity {

    double dx;
    double dy;

    /**
     * Constructs a Velocity with specified dx and dy values.
     *
     * @param dx the change in x-coordinate per time unit
     * @param dy the change in y-coordinate per time unit
     */
    public Velocity(double dx, double dy) {
        this.dx = dx;
        this.dy = dy;
    }

    /**
     * Applies the velocity to a given point and returns a new point.
     *
     * @param p the point to which the velocity is applied
     * @return a new point after applying the velocity
     */
    public Point applyToPoint(Point p) {
        return new Point(p.getX() + dx, p.getY() + dy);
    }

    /**
     * Gets the change in x-coordinate per time unit.
     *
     * @return the dx value of the velocity
     */
    public double getX() {
        return this.dx;
    }

    /**
     * Gets the change in y-coordinate per time unit.
     *
     * @return the dy value of the velocity
     */
    public double getY() {
        return this.dy;
    }

    /**
     * Creates a velocity from a given angle and speed.
     *
     * @param angle the angle of the velocity vector
     * @param speed the speed of the velocity vector
     * @return a new Velocity object based on the angle and speed
     */
    public static Velocity fromAngleAndSpeed(double angle, double speed) {
        double dx = Math.sin(Math.toRadians(angle)) * speed;
        double dy = (-1) * Math.cos(Math.toRadians(angle)) * speed;
        return new Velocity(dx, dy);
    }
}

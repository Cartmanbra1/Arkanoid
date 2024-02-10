/**
 * The Ball class represents a ball in a two-dimensional space. The ball has a center, radius, color, and velocity.
 *
 * @author Roie Amsalem <royiamsalem@gmail.com>
 * @version 1.0
 * @since 2024-01-19
 */

import biuoop.DrawSurface;
import java.util.Random;

public class Ball implements Sprite {

    private Point center;
    private int radius;
    private java.awt.Color color;
    public Velocity velocity;
    private GameEnvironment gameEnvironment;

    /**
     * Constructs a new Ball object with the specified center, radius, and color.
     *
     * @param center           the center of the ball
     * @param radius           the radius of the ball
     * @param color            the color of the ball
     * @param gameEnvironment  the game environment in which the ball exists
     */
    public Ball(Point center, int radius, java.awt.Color color, GameEnvironment gameEnvironment) {
        this.center = center;
        this.radius = radius;
        this.color = color;
        this.gameEnvironment = gameEnvironment;
    }

    /**
     * Constructs a new Ball object with the specified coordinates, radius, and color.
     *
     * @param x                the x-coordinate of the center of the ball
     * @param y                the y-coordinate of the center of the ball
     * @param radius           the radius of the ball
     * @param color            the color of the ball
     * @param gameEnvironment  the game environment in which the ball exists
     */
    public Ball(double x, double y, int radius, java.awt.Color color, GameEnvironment gameEnvironment) {
        Point other = new Point(x, y);
        this.center = other;
        this.radius = radius;
        this.color = color;
        this.gameEnvironment = gameEnvironment;
    }

    /**
     * Gets the x-coordinate of the center of the ball.
     *
     * @return the x-coordinate of the center
     */
    public int getX() {
        return (int) this.center.getX();
    }

    /**
     * Gets the y-coordinate of the center of the ball.
     *
     * @return the y-coordinate of the center
     */
    public int getY() {
        return (int) this.center.getY();
    }

    /**
     * Gets the size (radius) of the ball.
     *
     * @return the size of the ball
     */
    public int getSize() {
        return (int) this.radius;
    }

    /**
     * Changes the size of the ball to a random value between 0 and 49.
     */
    public void changeSize() {
        Random random = new Random();
        this.radius = random.nextInt(50);
    }

    /**
     * Gets the color of the ball.
     *
     * @return the color of the ball
     */
    public java.awt.Color getColor() {
        return this.color;
    }

    /**
     * Draws the ball on the given DrawSurface.
     *
     * @param surface the DrawSurface on which to draw the ball
     */
    public void drawOn(DrawSurface surface) {
        surface.setColor(this.getColor());
        surface.fillCircle(this.getX(), this.getY(), this.getSize());
    }

    /**
     * Sets the velocity of the ball to the specified Velocity object.
     *
     * @param v the new velocity of the ball
     */
    public void setVelocity(Velocity v) {
        this.velocity = v;
    }

    /**
     * Sets the velocity of the ball to the specified dx and dy values.
     *
     * @param dx the change in x-coordinate per step
     * @param dy the change in y-coordinate per step
     */
    public void setVelocity(double dx, double dy) {
        Velocity newVelocity = new Velocity(dx, dy);
        this.velocity = newVelocity;
    }

    /**
     * Gets the current velocity of the ball.
     *
     * @return the velocity of the ball
     */
    public Velocity getVelocity() {
        return this.velocity;
    }

    /**
     * Moves the ball one step, adjusting its position based on its velocity and handling collisions with frame boundaries.
     *
     * @param frame the frame representing the boundaries
     */
    public void moveOneStep(Rectangle frame) {
        double nextX = this.center.getX() + this.velocity.dx;
        double nextY = this.center.getY() + this.velocity.dy;

        // Adjust the position if it would go out of bounds
        if (nextX - radius < frame.getTopX()) {
            nextX = frame.getTopX() + radius;
            setVelocity(-velocity.dx, velocity.dy); // Reverse x velocity
        } else if (nextX + radius > frame.getBottomX()) {
            nextX = frame.getBottomX() - radius;
            setVelocity(-velocity.dx, velocity.dy); // Reverse x velocity
        }

        if (nextY - radius < frame.getTopY()) {
            nextY = frame.getTopY() + radius;
            setVelocity(velocity.dx, -velocity.dy); // Reverse y velocity
        } else if (nextY + radius > frame.getBottomY()) {
            nextY = frame.getBottomY() - radius;
            setVelocity(velocity.dx, -velocity.dy); // Reverse y velocity
        }

        // Update the center based on the adjusted position
        this.center = new Point(nextX, nextY);
    }

    /**
     * Moves the ball one step in the opposite direction if it is inside the specified frame.
     *
     * @param frame the frame representing the boundaries
     */
    public void moveOneStepOpposite(Rectangle frame) {
        if (isInBox(frame)) {
            if (touchesLeftWall(frame)) {
                setVelocity((-1) * velocity.dx, velocity.dy); // Reverse x velocity
            } else if (touchesRightWall(frame)) {
                setVelocity((-1) * velocity.dx, velocity.dy); // Reverse x velocity
            }

            if (touchesTop(frame)) {
                setVelocity(velocity.dx, -velocity.dy); // Reverse y velocity
            } else if (touchesBottom(frame)) {
                setVelocity(velocity.dx, -velocity.dy);
            }
        }

        // Update the center based on the adjusted position
        this.center = velocity.applyToPoint(this.center);
    }

    /**
     * Checks if the ball is touching the top boundary of the frame.
     *
     * @param frame the frame representing the boundaries
     * @return true if touching the top boundary, false otherwise
     */
    public boolean touchesTop(Rectangle frame) {
        double nextY = this.center.getY() + this.velocity.dy;
        double alternateY = this.center.getY() - this.velocity.dy;

        if (nextY + radius > frame.getTopY() && alternateY - radius < frame.getTopY()) {
            return true;
        }
        return false;
    }

    /**
     * Checks if the ball is touching the bottom boundary of the frame.
     *
     * @param frame the frame representing the boundaries
     * @return true if touching the bottom boundary, false otherwise
     */
    public boolean touchesBottom(Rectangle frame) {
        double nextY = this.center.getY() + this.velocity.dy;
        double alternateY = this.center.getY() - this.velocity.dy;

        if (nextY - radius < frame.getBottomY() && alternateY + radius > frame.getBottomY()) {
            return true;
        }
        return false;
    }

    /**
     * Checks if the ball is inside the specified frame.
     *
     * @param frame the frame representing the boundaries
     * @return true if inside the frame, false otherwise
     */
    public boolean isInBox(Rectangle frame) {
        if (((this.center.getX() + this.velocity.getX() - this.radius) < frame.getBottomX())
                && ((this.center.getX() + this.velocity.getX() + this.radius) > frame.getTopX())
                && ((this.center.getY() + this.velocity.getY() - this.radius) < frame.getBottomY())
                && ((this.center.getY() + this.velocity.getY() + this.radius) > frame.getTopY())) {
            return true;
        }
        return false;
    }

    /**
     * Checks if the ball is touching the left boundary of the frame.
     *
     * @param frame the frame representing the boundaries
     * @return true if touching the left boundary, false otherwise
     */
    public boolean touchesLeftWall(Rectangle frame) {
        double nextX = this.center.getX() + this.velocity.dx;
        double alternateX = this.center.getX() - this.velocity.dx;

        if (nextX + radius > frame.getTopX() && alternateX - radius < frame.getTopX()) {
            return true;
        }
        return false;
    }

    /**
     * Checks if the ball is touching the right boundary of the frame.
     *
     * @param frame the frame representing the boundaries
     * @return true if touching the right boundary, false otherwise
     */
    public boolean touchesRightWall(Rectangle frame) {
        double nextX = this.center.getX() + this.velocity.dx;
        double alternateX = this.center.getX() - this.velocity.dx;

        if (nextX - radius < frame.getBottomX() && alternateX + radius > frame.getBottomX()) {
            return true;
        }
        return false;
    }

    /**
     * Moves the ball one step, handling collisions with objects in the game environment.
     */
    public void moveOneStep() {
        Line trajectory = calculateTrajectory();
        // If there is a point of collision close
        if (gameEnvironment.getClosestCollision(trajectory, this) != null) {
            // Get the collision object
            CollisionInfo collidable = gameEnvironment.getClosestCollision(trajectory, this);
            // only commit a collision when the ball is close
            double distance = this.center.distance(collidable.collisionPoint());
            if (Math.abs(distance) < radius) {
                // go to the object and use hit to calculate velocity.
                this.velocity = collidable.collisionObject().hit(collidable.collisionPoint(), this.velocity);
            }
        }
        this.center = velocity.applyToPoint(this.center);
    }

    /**
     * Calculates the trajectory of the ball.
     *
     * @return the trajectory of the ball as a Line object
     */
    public Line calculateTrajectory() {
        Point start = new Point(this.getX(), this.getY());
        Point end = velocity.applyToPoint(start);
        return new Line(start, end);
    }

    /**
     * Updates the state of the ball based on the passage of time.
     */
    public void timePassed() {
        moveOneStep();
    }

    /**
     * Adds the ball to the specified game by adding it to the sprites collection.
     *
     * @param game the game to which the ball will be added
     */
    public void addToGame(Game game) {
        game.addSprite(this);
    }

}

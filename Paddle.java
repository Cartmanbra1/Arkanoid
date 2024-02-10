/**
 * @author Roie Amsalem
 * @version 1.0
 * @since 2024-02-01
 */
// Roie Amsalem 322535436
import biuoop.DrawSurface;
import biuoop.KeyboardSensor;

import java.awt.*;

/**
 * The Paddle class represents the player-controlled paddle in a game. It implements both the Sprite and Collidable
 * interfaces to participate in the game dynamics.
 */
public class Paddle implements Sprite, Collidable {

    private biuoop.KeyboardSensor keyboard;
    private Rectangle rect;
    private int speed;

    private final int WIDTH = 800;
    private final int HEIGHT = 600;
    // Makes the circular motion of the paddle smoother.
    private final int PADDLE_MOVEMENT = 20;

    /**
     * Creates a Paddle with a specified keyboard sensor and rectangle.
     *
     * @param gui   the graphical user interface associated with the game
     * @param rect  the rectangle representing the paddle's position and size
     */
    public Paddle(biuoop.GUI gui, Rectangle rect) {
        this.keyboard = gui.getKeyboardSensor();
        this.rect = rect;
        this.speed = 5;
    }

    /**
     * Moves the paddle to the left based on the defined speed.
     */
    public void moveLeft() {
        this.rect.updateX(-this.speed);
    }

    /**
     * Moves the paddle to the right based on the defined speed.
     */
    public void moveRight() {
        this.rect.updateX(this.speed);
    }

    /**
     * Implements the timePassed method for the Sprite interface.
     * Checks for left and right key presses and updates the paddle's position accordingly.
     * Handles wrapping around the screen if the paddle moves beyond the screen borders.
     */
    public void timePassed() {
        if (keyboard.isPressed(KeyboardSensor.LEFT_KEY)) {
            moveLeft();
        }
        if (keyboard.isPressed(KeyboardSensor.RIGHT_KEY)) {
            moveRight();
        }
        if (this.rect.getTopX() > WIDTH) {
            this.rect.updateX(-WIDTH - PADDLE_MOVEMENT);
        } else if (this.rect.getBottomX() < 0) {
            this.rect.updateX(WIDTH + PADDLE_MOVEMENT);
        }
    }

    /**
     * Draws the paddle on the specified DrawSurface.
     *
     * @param d  the DrawSurface on which the paddle will be drawn
     */
    public void drawOn(DrawSurface d) {
        d.setColor(Color.black);
        this.getCollisionRectangle().drawOn(d);
    }

    /**
     * Retrieves the collision rectangle of the paddle.
     *
     * @return the collision rectangle representing the paddle's position and size
     */
    public Rectangle getCollisionRectangle() {
        return this.rect;
    }

    /**
     * Handles the paddle's collision response when hit by a ball.
     *
     * @param collisionPoint      the point where the collision occurred
     * @param currentVelocity     the current velocity of the colliding object
     * @return                    the new velocity after the collision
     */
    public Velocity hit(Point collisionPoint, Velocity currentVelocity) {
        Point p1 = new Point(this.rect.getTopX(), this.rect.getTopY());
        Point p2 = new Point(this.rect.getBottomX(), this.rect.getTopY());
        Line topOfPaddle = new Line(p1, p2);
        Line[] linesOfTop = splitPaddle(topOfPaddle);
        for (int i = 0; i < linesOfTop.length; i++) {
            // All y values will be the same so this stays as rect to simplify.
            if (Threshold.areEqual(this.rect.getTopY(), collisionPoint.getY())
                    && collisionPoint.getX() < linesOfTop[i].end.getX()
                    && collisionPoint.getX() > linesOfTop[i].start.getX()) {
                return Velocity.fromAngleAndSpeed(300 + i * 30, 5);
            }
        }
        return currentVelocity;
    }

    /**
     * Splits the given line into multiple lines representing the top of the paddle.
     *
     * @param line  the original line to be split
     * @return      an array of lines representing the top of the paddle
     */
    public Line[] splitPaddle(Line line) {
        Line[] linesOfTop = new Line[5];

        Point[] points = new Point[6];
        for (int i = 0; i < points.length; i++) {
            points[i] = new Point(line.start.getX() + 20 * i, line.start.getX() + 20 * (i + 1));
        }

        for (int i = 0; i < linesOfTop.length; i++) {
            linesOfTop[i] = new Line(points[i], points[i + 1]);
        }

        return linesOfTop;
    }

    /**
     * Adds the paddle to the specified Game, making it both a collidable and a sprite.
     *
     * @param game  the Game to which the paddle will be added
     */
    public void addToGame(Game game) {
        game.addCollidable(this);
        game.addSprite(this);
    }
}

import java.awt.*;

/**
 * The Block class represents a block in the game, implementing both the Collidable and Sprite interfaces.
 * It contains methods for handling collisions, drawing on the surface, and updating its state over time.
 */
public class Block implements Collidable, Sprite {

    private Rectangle rect;

    /**
     * Creates a Block with a specified rectangle.
     *
     * @param rect  the rectangle representing the block's position and size
     */
    public Block(Rectangle rect) {
        this.rect = rect;
    }

    /**
     * Retrieves the collision rectangle of the block.
     *
     * @return the collision rectangle representing the block's position and size
     */
    public Rectangle getCollisionRectangle() {
        return this.rect;
    }

    /**
     * Draws the block on the specified DrawSurface.
     *
     * @param surface  the DrawSurface on which the block will be drawn
     */
    public void drawOn(biuoop.DrawSurface surface) {
        surface.setColor(Color.cyan);
        this.getCollisionRectangle().drawOn(surface);
        Line[] linesArray = this.rect.linesOfRect();
        for (int i = 0; i < linesArray.length; i++) {
            linesArray[i].drawOn(surface);
        }
    }

    /**
     * Handles the block's collision response when hit by a ball.
     *
     * @param collisionPoint      the point where the collision occurred
     * @param currentVelocity     the current velocity of the colliding object
     * @return                    the new velocity after the collision
     */
    public Velocity hit(Point collisionPoint, Velocity currentVelocity) {
        /*
         Check if the x value of collision point is the same as the bottom x of the block up to epsilon
         Also checks if we are in the range of y to commit a collision.
          same process for all ifs with differing sides of a block
         */
        if (Threshold.areEqual(this.rect.getBottomX(), collisionPoint.getX())
                && collisionPoint.getY() < this.rect.getBottomY()
                && collisionPoint.getY() > this.rect.getTopY()) {
            return new Velocity(-currentVelocity.dx, currentVelocity.dy);
        }
        if (Threshold.areEqual(this.rect.getTopX(), collisionPoint.getX())
                && collisionPoint.getY() < this.rect.getBottomY()
                && collisionPoint.getY() > this.rect.getTopY()) {
            return new Velocity(-currentVelocity.dx, currentVelocity.dy);
        }
        if (Threshold.areEqual(this.rect.getBottomY(), collisionPoint.getY())
                && collisionPoint.getX() < this.rect.getBottomX()
                && collisionPoint.getX() > this.rect.getTopX()) {
            return new Velocity(currentVelocity.dx, -currentVelocity.dy);
        }
        if (Threshold.areEqual(this.rect.getTopY(), collisionPoint.getY())
                && collisionPoint.getX() < this.rect.getBottomX()
                && collisionPoint.getX() > this.rect.getTopX()) {
            return new Velocity(currentVelocity.dx, -currentVelocity.dy);
        }
        return currentVelocity;
    }

    /**
     * Implements the timePassed method for the Sprite interface.
     * This block doesn't change over time, so the method does nothing.
     */
    public void timePassed() {
        return;
    }

    /**
     * Adds the block to the specified Game, making it both a collidable and a sprite.
     *
     * @param game  the Game to which the block will be added
     */
    public void addToGame(Game game) {
        game.addCollidable(this);
        game.addSprite(this);
    }

    /**
     * Adds the block to the specified Game as a collidable.
     *
     * @param game   the Game to which the block will be added
     * @param frame  a boolean indicating whether the block is part of the game frame
     */
    public void addToGame(Game game, Boolean frame) {
        game.addCollidable(this);
    }
}

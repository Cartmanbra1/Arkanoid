/**
 * The Collidable interface represents objects that can participate in collisions in the game.
 * It defines methods for retrieving the collision rectangle and handling collision responses.
 */
public interface Collidable {

    /**
     * Returns the "collision shape" of the object.
     *
     * @return the collision rectangle representing the object's position and size
     */
    Rectangle getCollisionRectangle();

    /**
     * Notifies the object that a collision occurred at the specified collisionPoint with a given velocity.
     * The return is the new velocity expected after the hit, based on the force the object inflicted on the colliding object.
     *
     * @param collisionPoint      the point where the collision occurred
     * @param currentVelocity     the current velocity of the colliding object
     * @return                    the new velocity after the collision
     */
    Velocity hit(Point collisionPoint, Velocity currentVelocity);
}

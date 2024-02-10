/**
 * The CollisionInfo class represents information about a collision between two objects.
 */
public class CollisionInfo {

    private Point collisionPoint;
    private Collidable collisionObject;

    /**
     * Constructs a CollisionInfo object with the specified collision point and collidable object.
     *
     * @param point  the point at which the collision occurs
     * @param object the collidable object involved in the collision
     */
    public CollisionInfo(Point point, Collidable object) {
        this.collisionPoint = point;
        this.collisionObject = object;
    }

    /**
     * Retrieves the point at which the collision occurs.
     *
     * @return the collision point
     */
    public Point collisionPoint() {
        return this.collisionPoint;
    }

    /**
     * Retrieves the collidable object involved in the collision.
     *
     * @return the collidable object
     */
    public Collidable collisionObject() {
        return this.collisionObject;
    }
}

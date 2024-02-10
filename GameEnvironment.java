/**
 * @author Roie Amsalem < royiamsalem@gmail.com >
 * @version 1.0
 * @since 2024-02-01
 */
// Roie Amsalem 322535436

import java.util.ArrayList;
import java.util.List;

/**
 * The GameEnvironment class represents the environment of the game, containing a list of collidable objects.
 * It provides methods for adding collidable objects and finding the
 * closest collision point for a given trajectory and ball.
 */
public class GameEnvironment {

    private List<Collidable> objectsList = new ArrayList<>();

    /**
     * Adds a collidable object to the game environment.
     *
     * @param c  the collidable object to be added
     */
    public void addCollidable(Collidable c) {
        objectsList.add(c);
    }

    /**
     * Finds the closest collision point between a trajectory and a ball within the game environment.
     *
     * @param trajectory  the trajectory line of the ball
     * @param ball        the ball for which the collision is being checked
     * @return            a CollisionInfo object containing the closest collision point and
     * the collidable object involved,
     *                    or null if no collision is found
     */
    public CollisionInfo getClosestCollision(Line trajectory, Ball ball) {
        List<Point> collisions = new ArrayList<>();

        // Make a list of points
        for (Collidable collidable : objectsList) {
            // Check if there is an intersection
            if (trajectory.closestIntersectionToStartOfLine(collidable.getCollisionRectangle(), ball) != null) {
                collisions.add(trajectory.closestIntersectionToStartOfLine(collidable.getCollisionRectangle(), ball));
            }
        }

        // If its empty (it shouldnt be generally) return null.
        if (collisions.size() == 0) {
            return null;
        }

        Point closestPoint = collisions.get(0);

        // Find the closest point of all points
        for (int i = 1; i < collisions.size(); i++) {
            if (Math.abs(closestPoint.distance(trajectory.start))
                    > Math.abs(collisions.get(i).distance(trajectory.start))) {
                List<Point> points = new ArrayList<>();
                points.add(collisions.get(i));
                points = trajectory.checkBallDirection(points, ball);
                if (points.size() != 0) {
                    closestPoint = collisions.get(i);
                }
            }
        }
        // Go over all collidables and find the closest intersection to return with the point as CollisionInfo.
        for (Collidable collidable : objectsList) {
            if (trajectory.closestIntersectionToStartOfLine(collidable.getCollisionRectangle(), ball) != null) {
                if (trajectory.closestIntersectionToStartOfLine(collidable.getCollisionRectangle(),
                        ball).equals(closestPoint)) {
                    return new CollisionInfo(closestPoint, collidable);
                }
            }
        }

        return null;
    }
}

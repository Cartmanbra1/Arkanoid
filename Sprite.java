/**
 * @author Roie Amsalem
 * @version 1.0
 * @since 2024-02-01
 */
// Roie Amsalem 322535436

import biuoop.DrawSurface;

/**
 * The Sprite interface represents objects that can be drawn on a DrawSurface and can be notified of the passage of time.
 */
public interface Sprite {

    /**
     * Draws the sprite on the given DrawSurface.
     *
     * @param d the DrawSurface on which to draw the sprite
     */
    void drawOn(DrawSurface d);

    /**
     * Notifies the sprite that time has passed.
     */
    void timePassed();
}

/**
 * @author Roie Amsalem
 * @version 1.0
 * @since 2024-02-01
 */
// Roie Amsalem 322535436

import biuoop.DrawSurface;

import java.util.ArrayList;
import java.util.List;

/**
 * The SpriteCollection class represents a collection of sprites in a game.
 * It provides methods for adding sprites, updating their state, and drawing them on a DrawSurface.
 */
public class SpriteCollection {

    private List<Sprite> spriteList = new ArrayList<>();

    /**
     * Adds a sprite to the collection.
     *
     * @param s the sprite to be added
     */
    public void addSprite(Sprite s) {
        spriteList.add(s);
    }

    /**
     * Calls the timePassed() method on all sprites in the collection, updating their state.
     */
    public void notifyAllTimePassed() {
        for (Sprite sprite : spriteList) {
            sprite.timePassed();
        }
    }

    /**
     * Calls the drawOn(d) method on all sprites in the collection, drawing them on the specified DrawSurface.
     *
     * @param d the DrawSurface on which sprites will be drawn
     */
    public void drawAllOn(DrawSurface d) {
        for (Sprite sprite : spriteList) {
            sprite.drawOn(d);
        }
    }
}

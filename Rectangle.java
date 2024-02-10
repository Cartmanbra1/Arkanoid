/**
 * @author Roie Amsalem
 * @version 1.0
 * @since 2024-02-01
 */
// Roie Amsalem 322535436

import biuoop.DrawSurface;
import java.util.List;
import java.awt.*;

/**
 * The Rectangle class represents a rectangle in a two-dimensional
 * space with specified top-left and bottom-right points, and a color.
 */
public class Rectangle {

    private Point topLeft;
    private Point bottomRight;
    private Color color;

    /**
     * Constructs a new Rectangle object with the specified top-left and bottom-right points, and color.
     *
     * @param topLeft     the top-left point of the frame
     * @param bottomRight the bottom-right point of the frame
     * @param color       the color of the frame
     */
    public Rectangle(Point topLeft, Point bottomRight, Color color) {
        this.topLeft = topLeft;
        this.bottomRight = bottomRight;
        this.color = color;
    }

    /**
     * Constructs a new Rectangle object with the specified upper-left point, width, height, and default color.
     *
     * @param upperLeft the upper-left point of the frame
     * @param width     the width of the frame
     * @param height    the height of the frame
     */
    public Rectangle(Point upperLeft, double width, double height) {
        this.topLeft = upperLeft;
        this.bottomRight = new Point(width, height);
        this.color = Color.black;
    }

    /**
     * Gets the x-coordinate of the top-left point of the frame.
     *
     * @return the x-coordinate of the top-left point
     */
    public int getTopX() {
        return (int) this.topLeft.getX();
    }

    /**
     * Gets the y-coordinate of the top-left point of the frame.
     *
     * @return the y-coordinate of the top-left point
     */
    public int getTopY() {
        return (int) this.topLeft.getY();
    }

    /**
     * Gets the x-coordinate of the bottom-right point of the frame.
     *
     * @return the x-coordinate of the bottom-right point
     */
    public int getBottomX() {
        return (int) this.bottomRight.getX();
    }

    /**
     * Gets the y-coordinate of the bottom-right point of the frame.
     *
     * @return the y-coordinate of the bottom-right point
     */
    public int getBottomY() {
        return (int) this.bottomRight.getY();
    }

    /**
     * Gets the upper-left point of the frame.
     *
     * @return the upper-left point
     */
    public Point getUpperLeft() {
        return topLeft;
    }

    /**
     * Gets the color of the frame.
     *
     * @return the color of the frame
     */
    public Color getColor() {
        return this.color;
    }

    /**
     * Changes the color of the frame.
     *
     * @param color the new color
     */
    public void changeColor(Color color) {
        this.color = color;
    }

    /**
     * Returns a list of intersection points between the rectangle and a given line.
     *
     * @param line the line to check for intersection
     * @return a list of intersection points
     */
    public List<Point> intersectionPoints(Line line) {
        Line[] linesArray = linesOfRect();
        List<Point> points = null;
        for (int i = 0; i < 4; i++) {
            if (line.isIntersecting(linesArray[i])) {
                points.add(line.intersectionWith(linesArray[i]));
            }
        }
        return points;
    }

    /**
     * Returns an array of the four lines forming the rectangle.
     *
     * @return an array of lines
     */
    public Line[] linesOfRect() {
        Line[] linesArray = new Line[4];
        linesArray[0] = new Line(this.getUpperLeft(), new Point(this.getBottomX(), this.getTopY()));
        linesArray[1] = new Line(new Point(this.getBottomX(), this.getTopY()),
                new Point(this.getBottomX(), this.getBottomY()));
        linesArray[2] = new Line(new Point(this.getBottomX(), this.getBottomY()),
                new Point(this.getTopX(), this.getBottomY()));
        linesArray[3] = new Line(new Point(this.getTopX(), this.getBottomY()), this.getUpperLeft());
        return linesArray;
    }

    /**
     * Checks if a given line is completely or partially within the rectangle.
     *
     * @param line the line to check for containment
     * @return true if the line is within the rectangle, false otherwise
     */
    public boolean isInRect(Line line) {
        if ((line.start.getX() > this.getTopX() && line.start.getX() < this.getBottomX()
                && line.start.getY() > this.getTopY() && line.start.getY() < this.getBottomY())
                || (line.end.getX() > this.getTopX() && line.end.getX() < this.getBottomX()
                && line.end.getY() > this.getTopY() && line.end.getY() < this.getBottomY())) {
            return true;
        }
        return false;
    }

    /**
     * Draws the frame on the given DrawSurface.
     *
     * @param surface the DrawSurface on which to draw the frame
     */
    public void drawOn(DrawSurface surface) {
        surface.setColor(this.getColor());
        int width = this.getBottomX() - this.getTopX();
        int height = this.getBottomY() - this.getTopY();
        surface.fillRectangle(this.getTopX(), this.getTopY(), width, height);
    }

    /**
     * Updates the x-coordinates of the top-left and bottom-right points of the frame.
     *
     * @param speed the speed by which to update the x-coordinates
     */
    public void updateX(int speed) {
        this.topLeft.updateX(speed);
        this.bottomRight.updateX(speed);
    }
}

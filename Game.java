import java.awt.*;
import java.util.Random;
import biuoop.DrawSurface;
import biuoop.Sleeper;

/**
 * The Game class represents the main class for the Arkanoid game.
 */
public class Game {

    private SpriteCollection sprites;
    private GameEnvironment environment;
    private biuoop.GUI gui;

    /**
     * Adds a collidable object to the game environment.
     *
     * @param c the collidable object to be added
     */
    public void addCollidable(Collidable c) {
        environment.addCollidable(c);
    }

    /**
     * Adds a sprite object to the game.
     *
     * @param s the sprite object to be added
     */
    public void addSprite(Sprite s) {
        sprites.addSprite(s);
    }

    /**
     * Initializes a new game, creating blocks, ball, paddle, and adding them to the game.
     */
    public void initialize() {
        this.gui = new biuoop.GUI("Arkanoid", 800, 600);  // Initialize the game GUI.
        Random random = new Random();
        biuoop.Sleeper sleeper = new biuoop.Sleeper();
        this.sprites = new SpriteCollection();  // Initialize the sprite collection.
        this.environment = new GameEnvironment();  // Initialize the game environment.
        Rectangle screen = new Rectangle(new Point(0, 0), 800, 600);
        Block frame = new Block(screen);  // Create a block representing the game frame.

        int x1 = 100, y1 = 0, x2 = 150, y2 = 20;
        int range = 700;

        // Create blocks and add them to the game.
        for (int i = 0; i < 6; i++) {
            Color color = new Color(random.nextInt(255), random.nextInt(255), random.nextInt(255));
            while (x2 <= range - 50 * i) {
                Point p1 = new Point(x1, y1);
                Point p2 = new Point(x2, y2);
                Rectangle rect = new Rectangle(p1, p2, color);
                Block block = new Block(rect);
                block.addToGame(this);
                x1 += 50;
                x2 += 50;
            }
            x1 = 100 + 50 * (i + 1);
            x2 = 150 + 50 * (i + 1);
            y1 += 20;
            y2 += 20;
        }

        // Create balls and paddle and add them to the game.
        Ball ball1 = new Ball(400, 300, 15, Color.pink, environment);
        Ball ball2 = new Ball(400, 350, 15, Color.pink, environment);
        ball1.setVelocity(1, 4);
        ball2.setVelocity(-1, -4);
        Point p1 = new Point(360, 500);
        Point p2 = new Point(440, 530);
        Rectangle p = new Rectangle(p1, p2, Color.lightGray);
        Paddle paddle = new Paddle(gui, p);
        ball1.addToGame(this);
        ball2.addToGame(this);
        frame.addToGame(this, true);
        paddle.addToGame(this);
    }

    /**
     * Runs the game animation loop.
     */
    public void run() {
        Sleeper sleeper = new Sleeper();
        int framesPerSecond = 60;
        int millisecondsPerFrame = 1000 / framesPerSecond;
        Rectangle backGround = new Rectangle(new Point(0, 0), 800, 600);
        backGround.changeColor(Color.blue);

        while (true) {
            long startTime = System.currentTimeMillis();  // Start timing

            DrawSurface d = gui.getDrawSurface();
            backGround.drawOn(d);
            this.sprites.drawAllOn(d);
            gui.show(d);
            this.sprites.notifyAllTimePassed();

            // Timing
            long usedTime = System.currentTimeMillis() - startTime;
            long milliSecondLeftToSleep = millisecondsPerFrame - usedTime;
            if (milliSecondLeftToSleep > 0) {
                sleeper.sleepFor(milliSecondLeftToSleep);
            }
        }
    }
}

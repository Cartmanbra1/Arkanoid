/**
 * @author Roie Amsalem
 * @version 1.0
 * @since 2024-02-01
 */
// Roie Amsalem 322535436
/**
 * The Main class contains the main method to launch the Arkanoid game.
 */
public class Main {

    /**
     * The main method initializes and runs the Arkanoid game.
     *
     * @param args command line arguments (unused)
     */
    public static void main(String[] args) {
        Game game = new Game();
        game.initialize();
        game.run();
    }
}

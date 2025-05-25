// 207455437 Yuval Weber
import gameManagement.Game;

/**
 * Ass5Game class to run the game.
 */
public class Ass5Game {
    /**
     * Main method to run the game.
     * @param args command line arguments
     */
    public static void main(String[] args) {
        Game game = new Game();
        game.initialize();
        game.run();
    }
}
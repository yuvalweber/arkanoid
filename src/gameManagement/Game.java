// 207455437 Yuval Weber
package gameManagement;
import coliisions.Block;
import coliisions.Collidable;
import coliisions.Paddle;
import geometry.Point;
import geometry.Rectangle;
import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.Sleeper;
import listeners.BallColorChanger;
import listeners.BallRemover;
import listeners.BlockRemover;
import listeners.ScoreTrackingListener;
import listeners.Counter;
import sprites.Ball;
import sprites.ScoreIndicator;
import sprites.Sprite;
import sprites.SpriteCollection;

import java.awt.Color;

/**
 * The gameManagement.Game class is in charge of the game's logic.
 * It holds the sprites and the collidables, and is in charge of the animation.
 */
public class Game {
    private final SpriteCollection sprites;
    private final GameEnvironment environment;
    private Block[] bounds;
    private GUI gui;
    private final Counter remainingBlocks;
    private final Counter remainingBalls;
    private final Counter score;

    /**
     * Constructor.
     */
    public Game() {
        this.sprites = new SpriteCollection();
        this.environment = new GameEnvironment();
        this.addBounds();
        this.remainingBlocks = new Counter();
        this.remainingBalls = new Counter();
        this.score = new Counter();
    }
    /**
     * Add the given collidable to the game.
     * @param c the collidable to add.
     */
    public void addCollidable(Collidable c) {
        this.environment.addCollidable(c);
    }

    /**
     * Add the given sprite to the game.
     * @param s the sprite to add.
     */
    public void addSprite(Sprite s) {
        this.sprites.addSprite(s);
    }

    /**
     * addBounds method to add the bounds of the game.
     */
    private void addBounds() {
        Block top = new Block(new Rectangle(new Point(0, 0), 800, 20, Color.GRAY));
        Block left = new Block(new Rectangle(new Point(0, 0), 20, 600, Color.GRAY));
        Block right = new Block(new Rectangle(new Point(780, 0), 20, 600, Color.GRAY));
        Block bottom = new Block(new Rectangle(new Point(0, 580), 800, 20, Color.GRAY));
        this.bounds = new Block[] {top, left, right, bottom};
    }


    /**
     * Remove the given collidable from the game.
     * @param c the collidable to remove.
     */
    public void removeCollidable(Collidable c) {
        this.environment.getCollidables().remove(c);
    }

    /**
     * Remove the given sprite from the game.
     * @param s the sprite to remove.
     */
    public void removeSprite(Sprite s) {
        this.sprites.getSprites().remove(s);
    }
    // Initialize a new game: create the Blocks and Geometry.Ball (and Geometry.Paddle)
    // and add them to the game.
    /**
     * Initialize a new game: create the Blocks and Geometry.Ball (and Geometry.Paddle)
     * and add them to the game.
     */
    public void initialize() {
        this.gui = new GUI("Arkanoid", 800, 600);
        Paddle paddle = new Paddle(new Block(new Rectangle(new Point(400, 560), 100, 20, Color.ORANGE)),
                this.gui);
        paddle.addToGame(this);
        for (int i = 0; i < 3; i++) {
            Ball ball = new Ball(new Point(400 - i * 10, 500 - i * 10), 6, Color.BLACK, this.environment);
            ball.setVelocity(3 + i, 5 + i);
            ball.addToGame(this);
            this.remainingBalls.increase(1);
        }
        for (Block bound : this.bounds) {
            bound.addToGame(this);
        }
        BallRemover ballRemover = new BallRemover(this, this.remainingBalls);
        // the last one is down
        // this is the death region
        this.bounds[this.bounds.length - 1].addHitListener(ballRemover);
        Color specialGray = Color.decode("#405D72");
        Color[] colors = {specialGray, Color.RED, Color.YELLOW, Color.BLUE, Color.PINK, Color.GREEN};
        BlockRemover blockRemover = new BlockRemover(this, this.remainingBlocks);
        BallColorChanger ballColorChanger = new BallColorChanger();
        ScoreTrackingListener scoreTrackingListener = new ScoreTrackingListener(this.score);
        for (int i = 0; i < 6; i++) {
            int amountToPrint = 12 - i;
            for (int j = 0; j < amountToPrint; j++) {
                Block block = new Block(new Rectangle(new Point(730 - j * 50, 100 + i * 20), 50, 20, colors[i]));
                block.addToGame(this);
                block.addHitListener(blockRemover);
                block.addHitListener(ballColorChanger);
                block.addHitListener(scoreTrackingListener);
                this.remainingBlocks.increase(1);
            }
        }
        ScoreIndicator scoreIndicator = new ScoreIndicator(this.score);
        scoreIndicator.addToGame(this);
    }
// Run the game -- start the animation loop.
    /**
     * Run the game -- start the animation loop.
     */
    public void run() {
        Sleeper sleeper = new Sleeper();
        int framesPerSecond = 60;
        int millisecondsPerFrame = 1000 / framesPerSecond;
        while (this.remainingBlocks.getValue() > 0 && this.remainingBalls.getValue() > 0) {
            long startTime = System.currentTimeMillis(); // timing
            DrawSurface d = this.gui.getDrawSurface();
            this.sprites.drawAllOn(d);
            this.gui.show(d);
            this.sprites.notifyAllTimePassed();
            // timing
            long usedTime = System.currentTimeMillis() - startTime;
            long milliSecondLeftToSleep = millisecondsPerFrame - usedTime;
            if (milliSecondLeftToSleep > 0) {
                sleeper.sleepFor(milliSecondLeftToSleep);
            }
        }
        if (this.remainingBlocks.getValue() == 0) {
            this.score.increase(100);
        }
        gui.close();
    }
}

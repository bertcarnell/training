import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GraphicsEnvironment;
import java.awt.Point;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;

/**
 * The top-level Frame class class for the Game.
 * 
 * @author DJ Rao
 */
@SuppressWarnings("serial")
public class GameFrameStep extends JFrame {
    /** Fixed width for the game. */
    private static final int WINDOW_WIDTH = 700;
    /** Fixed height for the game. */
    private static final int WINDOW_HEIGHT = 500;

    /**
     * Just a label added to the north of the game panel to display score.
     * 
     * @see score
     */
    private JLabel scoreLabel;

    /**
     * The remaining number of calls to the gameStep method before a new enemy
     * is automatically added to the game.
     */
    private int enemyGenerationCounter;

    /**
     * The number of missile objects that have been created.
     */
    private int shotsFired = 0;

    /**
     * A button that allows the user to fire a missile.
     */
    private JButton fireButton;

    /**
     * The game panel that logically encapsulates all the enemies, missiles, and
     * the turret.
     */
    private GamePanel panel;

    /**
     * Default constructor to control the game.
     */
    public GameFrameStep() {
        // Setup the initial JFrame elements
        setTitle("Ball Destruction!");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        setResizable(false);
        setLayout(new BorderLayout());

        // Setup rest of the GUI
        Container  contentPane = getContentPane();
        fireButton = new JButton("FIRE");
        scoreLabel = new JLabel("0");

        panel = new GamePanel(WINDOW_WIDTH - 50, WINDOW_HEIGHT - 50);
        panel.setPreferredSize(new Dimension(WINDOW_WIDTH - 50, WINDOW_HEIGHT - 50)); 
        
        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.add(new JLabel("Score: "), BorderLayout.LINE_START);
        topPanel.add(scoreLabel, BorderLayout.CENTER);
        
        contentPane.add(panel, BorderLayout.CENTER);
        contentPane.add(fireButton, BorderLayout.SOUTH);
        contentPane.add(topPanel, BorderLayout.NORTH);
    }

    // - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - 
    // For basic operation, you don't need to modify below this line. 
    // - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - 

    /**
     * This method must be called to start operations of the game. This method
     * essentially just creates a simple timer that calls the gameStep method
     * every 30 milliseconds.
     */
    public void start() {
        // Center the frame on the screen and show it.
        centerFrame(this);
        setVisible(true);
        // Create a game-step timer to step through the game.
        //Timer gameStep = new Timer(30, e -> gameStep());
        //gameStep.start();
        
        gameStep();
        gameStep();
        gameStep();
    }

    /**
     * Method to perform one step in the animation of the game. This method
     * essentially performs the following tasks:
     * 
     * <ol>
     * <li>Performs operations associated with detecting collisions between
     * enemy(s) and missile(s) by calling GamePanel.detectCollision()</li>
     * <li>Updates score in the display label</li>
     * <li>Moves all the enemies and missiles and repaints the panel to update
     * the display.</li>
     * <li>If the number of shots fired is greater-than 10, it displays a
     * suitable message and stops the game by calling System.exit(0)</li>
     * </ol>
     */
    private void gameStep() {
        panel.detectCollision();
        int score = panel.getTotalScore();
        scoreLabel.setText(Integer.toString(score));
        panel.move();
        panel.repaint();
        if (shotsFired > 10) {
            if (score >= 800) {
                JOptionPane.showMessageDialog(null, "You Win!",
                        "Game Finished Message",
                        JOptionPane.INFORMATION_MESSAGE);
                System.exit(0);
            } else {
                JOptionPane.showMessageDialog(null, "You Lose!",
                        "Game Finished Message",
                        JOptionPane.INFORMATION_MESSAGE);
                System.exit(0);
            }
        }
        if (enemyGenerationCounter == 0) {
            panel.addEnemy();
            setEnemyGenerationCounter();
        }
        enemyGenerationCounter--;
    }

    /**
     * Method centers the frame in the middle of the screen. This is really
     * just a nicety and does not affect the operation of the game in any
     * meaningful way.
     * 
     * @param frame to center with respect to the users screen dimensions.
     */
    private void centerFrame(JFrame frame) {
        int width = frame.getWidth();
        int height = frame.getHeight();
        GraphicsEnvironment ge = GraphicsEnvironment
                .getLocalGraphicsEnvironment();
        Point center = ge.getCenterPoint();
        // Figure out where the frame needs to be positions to center it.
        int xposition = center.x - width / 2;
        int yposition = center.y - height / 2;
        // Center the frame.
        frame.setBounds(xposition, yposition, width, height);
        frame.validate();
    }

    /**
     * Randomly assign a value to determine how soon a new Enemy should be
     * created.
     */
    private void setEnemyGenerationCounter() {
        // Set the number of times the gameStep method should be called
        // before the next enemy is added to the game.
        enemyGenerationCounter = (int) (Math.random() * 300);
    }

    /**
     * The main method to execute the game.
     * 
     * @param args Command-line arguments if any. This program does not use this
     *             argument.
     */
    public static void main(String[] args) {
        GameFrameStep main = new GameFrameStep();
        main.start();
    }
}

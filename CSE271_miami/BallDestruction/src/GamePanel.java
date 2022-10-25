import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.ArrayList;

import javax.swing.JPanel;

/**
 * This class contains the paintable objects such as the enemies, turret, and
 * missile. It also keeps track of the
 * 
 * @author DJ Rao
 */
@SuppressWarnings("serial")
public class GamePanel extends JPanel {
    /**
     * The list of enemies in the game. Objects are added in the addEnemy
     * method and removed in the detectCollison method.
     */
    private ArrayList<Enemy> enemyList;
    
    /**
     * The list of missiles in the game. Objects are added in the addMissile
     * method and removed in the detectCollison method.
     */
    private ArrayList<Missile> missileList;

    /**
     * The current score in the game. This value is updated in the 
     * detectCollision method.
     */
    private int totalScore = 0;
    
    private Turret turret;
    
    private boolean isNextEnemyBig = true;
    
    public GamePanel() {
    	this(0, 0);
    }
    
    public GamePanel(int frameWidth, int frameHeight) {
    	enemyList = new ArrayList<Enemy>();
    	missileList = new ArrayList<Missile>();
    	turret = new Turret(frameWidth / 2, frameHeight);
    	
    	// need for balls to be above the turret
    	enemyList.add(new BigEnemy(frameWidth, frameHeight - 2*turret.getTurretHeight()));
    	enemyList.add(new SmallEnemy(frameWidth, frameHeight - 2*turret.getTurretHeight()));
    }
    
    public void paintComponent(Graphics g) {
    	super.paintComponent(g);
    	for (int i = 0; i < enemyList.size(); i++) {
    		enemyList.get(i).paintComponents(g);
    	}
    	
    	for (int i = 0; i < missileList.size(); i++) {
    		missileList.get(i).paintComponent(g);
    	}
    	
    	turret.paintComponent(g);
    }
    
    public void move() {
    	for (int i = 0; i < enemyList.size(); i++) {
    		enemyList.get(i).move(getWidth(), getHeight());
    	}
    	
    	for (int i = 0; i < missileList.size(); i++) {
    		missileList.get(i).move(getWidth(), getHeight(), missileList, i);
    	}
    }
    
    public void addMissile() {
    	missileList.add(new Missile(turret.getTurretTop().x - Missile.INITIAL_WIDTH / 2, turret.getTurretTop().y));
    }
    
    public void addEnemy() {
    	if (isNextEnemyBig) {
    		enemyList.add(new BigEnemy(getWidth(), getHeight()));
    		isNextEnemyBig = false;
    	} else {
    		enemyList.add(new SmallEnemy(getWidth(), getHeight()));
    		isNextEnemyBig = true;
    	}
    }

    public int getTotalScore() {
    	return totalScore;
    }
    
    public void moveTurretRight() {
    	turret.moveRight();
    }
    
    public void moveTurretLeft() {
    	turret.moveLeft();
    }
    
    /**
     * Method detects the collision of the missile and all the enemies. This is
     * done by drawing invisible rectangles around the enemies and missiles, if
     * they intersect, then they collide.
     */
    public void detectCollision() {
        // Uses bounds for enemies and missiles to detect intersection.
        for (int i = 0; i < enemyList.size(); i++) {
            Rectangle enemyRec = enemyList.get(i).getBounds();
            for (int j = 0; j < missileList.size(); j++) {
                Rectangle missileRec = missileList.get(j).getBounds();
                if (missileRec.intersects(enemyRec)) {
                    // Missile has hit an enemy!
                    enemyList.get(i).processCollision(enemyList, i);
                    missileList.remove(j);
                    if (enemyList.get(i) instanceof BigEnemy) {
                        totalScore += 100;
                    } else {
                        totalScore += 150;
                    }
                }
            }
        }
    }
}

import java.awt.Color;
import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

public class SmallEnemy extends Enemy {
	private static final long serialVersionUID = 3L;
	private static final int INITIAL_HEIGHT = 30;
	private static final int INITIAL_WIDTH = 30;
	private static final double INITIAL_SPEED = 6.0;
	private static final int DECREASE_WHEN_HIT = 10;
	private static final double SPEED_INCREASE = 0.05;
	
	public SmallEnemy(int panelWidth, int panelHeight) {
		super(ThreadLocalRandom.current().nextInt(1, Math.max(panelWidth - INITIAL_WIDTH, 2)), 
				ThreadLocalRandom.current().nextInt(1, Math.max(panelHeight - INITIAL_HEIGHT, 2)), 
				INITIAL_HEIGHT, INITIAL_WIDTH, INITIAL_SPEED);
		this.setColor();
	}
	
	@Override
	public void setColor() {
		int randRed = ThreadLocalRandom.current().nextInt(0, 256);
		int randGreen = ThreadLocalRandom.current().nextInt(0, 256);
		int randBlue = ThreadLocalRandom.current().nextInt(0, 256);
		
		setEnemyColor(new Color(randRed, randGreen, randBlue));
	}
	
	@Override
	public void move(int frameWidth, int frameHeight) {
		int newX = getX() + (int) getEnemySpeed();
		if (newX > frameWidth - getWidth() | newX < 0) {
			setEnemySpeed(-1.0 * getEnemySpeed());
		}
		// setLocation(x, y) calls Component.move() which is overloaded.  Use setBounds instead
		setBounds(getX() + (int) getEnemySpeed(), getY(), getWidth(), getHeight());
		setEnemySpeed(Math.signum(getEnemySpeed()) * (Math.abs(getEnemySpeed()) + SPEED_INCREASE));
	}
	
	@Override
	public void processCollision(ArrayList<Enemy> list, int smallEnemy) {
		int newHeight = getHeight() - DECREASE_WHEN_HIT;
		int newWidth = getWidth() - DECREASE_WHEN_HIT;
		
		if (newHeight <= 0 | newWidth <= 0) {
			setBounds(0, 0, 0, 0);
			list.remove(smallEnemy);
		} else {
			setSize(newWidth, newHeight);
		}
	}
}

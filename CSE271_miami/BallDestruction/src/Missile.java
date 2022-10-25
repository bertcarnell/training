import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

import javax.swing.JComponent;

public class Missile extends JComponent {
	private static final long serialVersionUID = 4L;
	private static final int INITIAL_HEIGHT = 15;
	public static final int INITIAL_WIDTH = 15;
	private static final int INITIAL_SPEED = 5;

	private int missileSpeed;
	private Color missileColor;
	
	public Missile(int x, int y) {
		setBounds(x, y, INITIAL_WIDTH, INITIAL_HEIGHT);
		missileSpeed = INITIAL_SPEED;
		setMissileColor();
	}
	
	public void setMissileColor() {
		int randRed = ThreadLocalRandom.current().nextInt(0, 256);
		int randGreen = ThreadLocalRandom.current().nextInt(0, 256);
		int randBlue = ThreadLocalRandom.current().nextInt(0, 256);
		
		missileColor = new Color(randRed, randGreen, randBlue);
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.setColor(missileColor);
		g.fillOval(getX(), getY(), getWidth(), getHeight());
	}
	
	public void move(int panelWidth, int panelHeight, ArrayList<Missile> list, int missile) {
		int newY = getY() - missileSpeed;
		if (newY > panelHeight - getHeight() | newY < 0) {
			list.remove(missile);
			return;
		}
		setLocation(getX(), newY);
	}
	
	public int getMissileSpeed() {
		return missileSpeed;
	}
}

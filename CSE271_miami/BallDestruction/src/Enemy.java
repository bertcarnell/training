import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

import javax.swing.JComponent;

public abstract class Enemy extends JComponent {
	private static final long serialVersionUID = 1L;
	private double enemySpeed;
	private Color enemyColor;

	public Enemy(int x, int y, int height, int width, double enemySpeed) {
		this.enemySpeed = enemySpeed;
		setBounds(x, y, width, height);
	}
	
	public abstract void processCollision(ArrayList<Enemy> list, int enemy);
	
	public abstract void setColor();
	
	// This overrides java.awt.Component.move
	public abstract void move(int frameWidth, int frameHeight);
	
	public void paintComponents(Graphics g) {
		super.paintComponents(g);
		g.setColor(getEnemyColor());
		g.fillOval(getX(), getY(), getWidth(), getHeight());
	}
	
	public double getEnemySpeed() {
		return enemySpeed;
	}
	
	public void setEnemySpeed(double enemySpeed) {
		this.enemySpeed = enemySpeed;
	}
	
	public Color getEnemyColor() {
		return enemyColor;
	}
	
	public void setEnemyColor(Color enemyColor) {
		this.enemyColor = enemyColor;
	}
}

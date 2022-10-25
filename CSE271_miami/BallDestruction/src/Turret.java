import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

import javax.swing.JComponent;

public class Turret extends JComponent {
	private static final long serialVersionUID = 6L;
	private static final int baseWidth = 50;
	private static final int baseHeight = 10;
	private static final int turretWidth = 2;
	private static final int turretHeight = 50;
	private Rectangle base;
	private Rectangle turret;
	private Color turretColor;
	
	public Turret() {
		this(0,0);
	}
	
	public Turret(int x, int y) {
		base = new Rectangle(x - baseWidth / 2, y - baseHeight, baseWidth, baseHeight);
		turret = new Rectangle(x - turretWidth / 2, y - turretHeight, turretWidth, turretHeight);
		turretColor = new Color(10, 20, 30);
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.setColor(turretColor);
		g.fillRect(base.x, base.y, base.width, base.height);
		g.setColor(new Color(1, 1, 1));
		g.fillRect(turret.x, turret.y, turret.width, turret.height);
	}
	
	public Rectangle getTurretTop() {
		return turret;
	}
	
	public int getTurretHeight() {
		return turretHeight;
	}
	
	public void moveRight() {
		base.x += 10;
		turret.x += 10;
	}
	
	public void moveLeft() {
		base.x -= 10;
		turret.x -= 10;
	}
}

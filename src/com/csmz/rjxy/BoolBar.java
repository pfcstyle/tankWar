package com.csmz.rjxy;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;

/**
 * 血条类
 * 
 * @author ZhouHuan 2013年8月22日20:06:45
 *
 */

public class BoolBar {

	private Tank tank;
	private static final Toolkit tk = Toolkit.getDefaultToolkit(); // 用来将图片读入Image数组
	private static Image image = null;
	static {
		image = tk.getImage(BoolBar.class.getClassLoader().getResource(
				"image/tank_1.png"));
	}

	public BoolBar(Tank tank) {
		this.tank = tank;
	}
	
	/**
	 * 画血条
	 * @param g
	 */
	public void draw(Graphics g) {
		Color c = g.getColor();

		g.drawImage(image, 10, 13, null); // 画头像

		g.setColor(Color.WHITE);
		g.fillRect(33, 15, 204, 12);
		g.setColor(c);

		g.setColor(Color.RED);
		g.fillRect(35, 17, 200, 8);
		g.setColor(c);

		g.setColor(Color.GREEN);
		int w = (200) * tank.getLife() / 100;
		g.fillRect(35, 17, w, 8);
		g.setColor(c);
	}
}

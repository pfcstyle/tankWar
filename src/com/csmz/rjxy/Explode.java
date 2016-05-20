package com.csmz.rjxy;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;

/**
 * 爆炸类
 * 
 * @author ZhouHuan 2013年8月22日20:05:54
 *
 */
public class Explode {

	private int x;
	private int y;
	/* 爆炸生命 */
	private boolean live = true;

	private static boolean inti = true; // 第一次画时将图片载入内存
	private static Toolkit tk = Toolkit.getDefaultToolkit();
	private static Image[] imgs = {
			tk.getImage(Explode.class.getClassLoader().getResource(
					"image/1.png")),
			tk.getImage(Explode.class.getClassLoader().getResource(
					"image/2.png")),
			tk.getImage(Explode.class.getClassLoader().getResource(
					"image/3.png")),
			tk.getImage(Explode.class.getClassLoader().getResource(
					"image/4.png")),
			tk.getImage(Explode.class.getClassLoader().getResource(
					"image/5.png")),
			tk.getImage(Explode.class.getClassLoader().getResource(
					"image/6.png")),
			tk.getImage(Explode.class.getClassLoader().getResource(
					"image/7.png")),
			tk.getImage(Explode.class.getClassLoader().getResource(
					"image/8.png")), };
	
	/*记录画的步骤*/
	private int step = 0;

	private GamePanel game;

	public Explode(int x, int y, GamePanel game) {
		this.x = x;
		this.y = y;
		this.game = game;
	}

	/**
	 * 画自己
	 * 
	 * @param g
	 */
	public void draw(Graphics g) {
		if (inti) {
			for (Image ima : imgs) {
				g.drawImage(ima, -100, -100, null);
			}
			inti = false;
		}
		if (!this.live) { //死掉就移除
			game.explode.remove(this);
			return;
		}
		if (step == imgs.length) {
			this.live = false;
			step = 0;
			return;
		}
		g.drawImage(imgs[step], x, y - 10, null);
		step++;

	}
}

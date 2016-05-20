package com.csmz.rjxy;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.util.ArrayList;
import java.util.List;

public class Wall {

	private static final int WIDTH = 16;
	private static final int HEIGHT = 16;
	/* 画墙 */
	private static int[][] WALL_GROUP = { { 6 * WIDTH, 0 }, { 7 * WIDTH, 0 },
			{ 8 * WIDTH, 0 }, { 18 * WIDTH, 0 }, { 19 * WIDTH, 0 },
			{ 20 * WIDTH, 0 }, { 5 * WIDTH, HEIGHT }, { 6 * WIDTH, HEIGHT },
			{ 7 * WIDTH, HEIGHT }, { 8 * WIDTH, HEIGHT },
			{ 9 * WIDTH, HEIGHT }, { 17 * WIDTH, HEIGHT },
			{ 18 * WIDTH, HEIGHT }, { 19 * WIDTH, HEIGHT },
			{ 20 * WIDTH, HEIGHT }, { 21 * WIDTH, HEIGHT },
			{ 4 * WIDTH, 2 * HEIGHT }, { 5 * WIDTH, 2 * HEIGHT },
			{ 9 * WIDTH, 2 * HEIGHT }, { 10 * WIDTH, 2 * HEIGHT },
			{ 11 * WIDTH, 2 * HEIGHT }, { 12 * WIDTH, 2 * HEIGHT },
			{ 14 * WIDTH, 2 * HEIGHT }, { 15 * WIDTH, 2 * HEIGHT },
			{ 16 * WIDTH, 2 * HEIGHT }, { 17 * WIDTH, 2 * HEIGHT },
			{ 21 * WIDTH, 2 * HEIGHT }, { 22 * WIDTH, 2 * HEIGHT },

			{ 3 * WIDTH, 3 * HEIGHT }, { 4 * WIDTH, 3 * HEIGHT },
			{ 10 * WIDTH, 3 * HEIGHT }, { 11 * WIDTH, 3 * HEIGHT },
			{ 12 * WIDTH, 3 * HEIGHT }, { 13 * WIDTH, 3 * HEIGHT },
			{ 14 * WIDTH, 3 * HEIGHT }, { 15 * WIDTH, 3 * HEIGHT },
			{ 16 * WIDTH, 3 * HEIGHT }, { 22 * WIDTH, 3 * HEIGHT },
			{ 23 * WIDTH, 3 * HEIGHT },

			{ 2 * WIDTH, 4 * HEIGHT }, { 3 * WIDTH, 4 * HEIGHT },
			{ 13 * WIDTH, 4 * HEIGHT }, { 23 * WIDTH, 4 * HEIGHT },
			{ 24 * WIDTH, 4 * HEIGHT },

			{ 1 * WIDTH, 5 * HEIGHT }, { 2 * WIDTH, 5 * HEIGHT },
			{ 24 * WIDTH, 5 * HEIGHT }, { 25 * WIDTH, 5 * HEIGHT },

			{ 0 * WIDTH, 6 * HEIGHT }, { 1 * WIDTH, 6 * HEIGHT },
			{ 25 * WIDTH, 6 * HEIGHT }, { 26 * WIDTH, 6 * HEIGHT },
			{ 0 * WIDTH, 7 * HEIGHT }, { 1 * WIDTH, 7 * HEIGHT },
			{ 25 * WIDTH, 7 * HEIGHT }, { 26 * WIDTH, 7 * HEIGHT },
			{ 0 * WIDTH, 8 * HEIGHT }, { 1 * WIDTH, 8 * HEIGHT },
			{ 25 * WIDTH, 8 * HEIGHT }, { 26 * WIDTH, 8 * HEIGHT },
			{ 0 * WIDTH, 9 * HEIGHT }, { 1 * WIDTH, 9 * HEIGHT },
			{ 25 * WIDTH, 9 * HEIGHT }, { 26 * WIDTH, 9 * HEIGHT },

			{ 1 * WIDTH, 10 * HEIGHT }, { 2 * WIDTH, 10 * HEIGHT },
			{ 24 * WIDTH, 10 * HEIGHT }, { 25 * WIDTH, 10 * HEIGHT },
			{ 1 * WIDTH, 11 * HEIGHT }, { 2 * WIDTH, 11 * HEIGHT },
			{ 24 * WIDTH, 11 * HEIGHT }, { 25 * WIDTH, 11 * HEIGHT },
			{ 1 * WIDTH, 12 * HEIGHT }, { 2 * WIDTH, 12 * HEIGHT },
			{ 24 * WIDTH, 12 * HEIGHT }, { 25 * WIDTH, 12 * HEIGHT },

			{ 2 * WIDTH, 13 * HEIGHT }, { 3 * WIDTH, 13 * HEIGHT },
			{ 4 * WIDTH, 13 * HEIGHT }, { 22 * WIDTH, 13 * HEIGHT },
			{ 23 * WIDTH, 13 * HEIGHT }, { 24 * WIDTH, 13 * HEIGHT },

			{ 5 * WIDTH, 14 * HEIGHT }, { 3 * WIDTH, 14 * HEIGHT },
			{ 4 * WIDTH, 14 * HEIGHT }, { 21 * WIDTH, 14 * HEIGHT },
			{ 22 * WIDTH, 14 * HEIGHT }, { 23 * WIDTH, 14 * HEIGHT },

			{ 4 * WIDTH, 15 * HEIGHT }, { 5 * WIDTH, 15 * HEIGHT },
			{ 6 * WIDTH, 15 * HEIGHT }, { 20 * WIDTH, 15 * HEIGHT },
			{ 21 * WIDTH, 15 * HEIGHT }, { 22 * WIDTH, 15 * HEIGHT },

			{ 5 * WIDTH, 16 * HEIGHT }, { 6 * WIDTH, 16 * HEIGHT },
			{ 7 * WIDTH, 16 * HEIGHT }, { 19 * WIDTH, 16 * HEIGHT },
			{ 20 * WIDTH, 16 * HEIGHT }, { 21 * WIDTH, 16 * HEIGHT },

			{ 6 * WIDTH, 17 * HEIGHT }, { 7 * WIDTH, 17 * HEIGHT },
			{ 8 * WIDTH, 17 * HEIGHT }, { 18 * WIDTH, 17 * HEIGHT },
			{ 19 * WIDTH, 17 * HEIGHT }, { 20 * WIDTH, 17 * HEIGHT },

			{ 7 * WIDTH, 18 * HEIGHT }, { 8 * WIDTH, 18 * HEIGHT },
			{ 9 * WIDTH, 18 * HEIGHT }, { 10 * WIDTH, 18 * HEIGHT },
			{ 11 * WIDTH, 18 * HEIGHT }, { 12 * WIDTH, 18 * HEIGHT },
			{ 13 * WIDTH, 18 * HEIGHT }, { 14 * WIDTH, 18 * HEIGHT },
			{ 15 * WIDTH, 18 * HEIGHT }, { 16 * WIDTH, 18 * HEIGHT },
			{ 17 * WIDTH, 18 * HEIGHT }, { 18 * WIDTH, 18 * HEIGHT },
			{ 19 * WIDTH, 18 * HEIGHT },

			{ 8 * WIDTH, 19 * HEIGHT }, { 9 * WIDTH, 19 * HEIGHT },
			{ 10 * WIDTH, 19 * HEIGHT }, { 11 * WIDTH, 19 * HEIGHT },
			{ 12 * WIDTH, 19 * HEIGHT }, { 13 * WIDTH, 19 * HEIGHT },
			{ 14 * WIDTH, 19 * HEIGHT }, { 15 * WIDTH, 19 * HEIGHT },
			{ 16 * WIDTH, 19 * HEIGHT }, { 17 * WIDTH, 19 * HEIGHT },
			{ 18 * WIDTH, 19 * HEIGHT },

			{ 10 * WIDTH, 20 * HEIGHT }, { 11 * WIDTH, 20 * HEIGHT },
			{ 12 * WIDTH, 20 * HEIGHT }, { 13 * WIDTH, 20 * HEIGHT },
			{ 14 * WIDTH, 20 * HEIGHT }, { 15 * WIDTH, 20 * HEIGHT },
			{ 16 * WIDTH, 20 * HEIGHT },

			{ 11 * WIDTH, 21 * HEIGHT }, { 12 * WIDTH, 21 * HEIGHT },
			{ 13 * WIDTH, 21 * HEIGHT }, { 14 * WIDTH, 21 * HEIGHT },
			{ 15 * WIDTH, 21 * HEIGHT },

			{ 13 * WIDTH, 22 * HEIGHT } };

	private int x;
	
	private int y;

	private boolean live;

	private GamePanel game;

	List<WallNot> listWall = new ArrayList<WallNot>();

	public Wall(int x, int y, GamePanel game) {
		
		this.x = x;
		
		this.y = y;
		
		this.live = true;
		
		this.game = game;
		for (int i = 0; i < WALL_GROUP.length; i++) {
			listWall
					.add(new WallNot(WALL_GROUP[i][0] + x, WALL_GROUP[i][1] + y));
		}
	}
	/**
	 * 画墙.
	 * @param g
	 */
	public void draw(Graphics g) {
		if(listWall.size() == 0) return;
		for (int i = 0; i < listWall.size(); i++) {
			listWall.get(i).draw(g);
		}
	}

	/**
	 * 子弹可以破坏墙
	 * @param m 子弹
	 * @return 子弹碰到墙返回true,否则返回false.
	 */
	public boolean miis_hit_wall(Missile m) {
		if(listWall.size() == 0) return false;
		WallNot w = null;
		for(int i=0; i<listWall.size(); i++){
			w = listWall.get(i);
			if(w.getLive() && m.isLive() && 
					w.getRect().intersects(m.getRect())){
				Explode e = new Explode(w.getX(), w.getY(), this.game);
				this.game.explode.add(e);
				w.setLive(false);
				m.setLive(false);
				return true;
			}
		}
		return false;
	}

	/**
	 * 坦克不能穿墙 
	 * @param tank Tank
	 * @return 
	 */
	public boolean tank_hit_wall(Tank t) {
		if(listWall.size() == 0) return false;
		WallNot w = null;
		for(int i=0; i<listWall.size(); i++){
			w = listWall.get(i);
			if(w.getLive() && t.isLive() 
					&& w.getRect().intersects(t.getRect())){
				t.start();
				return true;
			}
		}
		return false;
	}
	
	public int getX() {
		return x;
	}
	public void setX(int x) {
		this.x = x;
	}
	public int getY() {
		return y;
	}
	public void setY(int y) {
		this.y = y;
	}
	public boolean isLive() {
		return live;
	}
	public void setLive(boolean live) {
		this.live = live;
	}
	
}

/**
 *  墙结点 类
 * @author ZhouHuan
 *
 */
class WallNot { //

	private int x;
	private int y;
	static final Toolkit TLK = Toolkit.getDefaultToolkit(); // 用来将图片读入
	static final Image WALLIMAGE = TLK.getImage(Wall.class.getClassLoader()
			.getResource("Image/Wall.png"));
	static final int WIDTH = 16;
	static final int HEIGHT = 16;
	private Boolean live; // 墙结点生命

	public WallNot(int x, int y) {
		this.x = x;
		this.y = y;
		this.live = true;
	}
	
	/**
	 * 画结点墙方法
	 * @param g
	 */
	public void draw(Graphics g) {
		if (this.live) {
			g.drawImage(WALLIMAGE, x, y, null);
		}
	}

	/**
	 * 获取结点墙 
	 * @return Rectangle
	 */
	public Rectangle getRect() {
		return new Rectangle(this.x, this.y, WIDTH, HEIGHT);
	}

	public Boolean getLive() {
		return live;
	}

	public void setLive(Boolean live) {
		this.live = live;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

}
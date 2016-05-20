package com.csmz.rjxy;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * �ӵ���
 * 
 * @author ZhouHuan
 *
 */
public class Missile {
	private static final Toolkit tk = Toolkit.getDefaultToolkit(); // ������ͼƬ����Image����
	private static Map<String, Image> mapImage = new HashMap<String, Image>();

	/* �ӵ������ٶ� */
	private static final int MISS_YV = 10;
	private static final int MISS_XV = 10;

	/* �ӵ���С */
	public static final int WIDTH = 12;
	public static final int HEIGHT = 12;

	/* ����x,y����ֵ */
	private int old_x;
	private int old_y;

	private GamePanel game;

	/* ���� */
	private boolean live = true;

	/* �����ӵ� */
	private boolean good;
	
	/*�ӵ�ͼƬ*/
	private static Image[] image = null;
	static {
		image = new Image[] {

				tk.getImage(Tank.class.getClassLoader().getResource(
						"image/missile.png")),

				tk.getImage(Tank.class.getClassLoader().getResource(
						"image/missileD.gif")),
				tk.getImage(Tank.class.getClassLoader().getResource(
						"image/missileL.gif")),
				tk.getImage(Tank.class.getClassLoader().getResource(
						"image/missileLD.gif")),
				tk.getImage(Tank.class.getClassLoader().getResource(
						"image/missileLU.gif")),
				tk.getImage(Tank.class.getClassLoader().getResource(
						"image/missileR.gif")),
				tk.getImage(Tank.class.getClassLoader().getResource(
						"image/missileRD.gif")),
				tk.getImage(Tank.class.getClassLoader().getResource(
						"image/missileRU.gif")),
				tk.getImage(Tank.class.getClassLoader().getResource(
						"image/MissileU.gif")), };

		mapImage.put("U", image[1]);
		mapImage.put("L", image[2]);
		mapImage.put("LD", image[3]);
		mapImage.put("LU", image[4]);
		mapImage.put("R", image[5]);
		mapImage.put("RD", image[6]);
		mapImage.put("RU", image[7]);
		mapImage.put("D", image[8]);
	}

	private int x, y;

	/* �ӵ��ķ��� */
	private Direction dir;

	public Missile(int x, int y, Direction dir) {
		this.x = x;
		this.y = y;
		this.dir = dir;
	}

	public Missile(int x, int y, Direction dir, GamePanel game, boolean good) {
		this(x, y, dir);
		this.game = game;
		this.good = good;
	}
	
	/**
	 *  ���ӵ�����
	 * @param g
	 */
	public void draw(Graphics g) {
		if (!live) {
			game.miss.remove(this);
			return;
		}
		if (this.good) {// �������ӵ�������
			/**/
			switch (dir) {
			case U:
				g.drawImage(mapImage.get("U"), x, y, null);
				break;
			case RU:
				g.drawImage(mapImage.get("RU"), x, y, null);
				break;
			case R:
				g.drawImage(mapImage.get("R"), x, y, null);
				break;
			case RD:
				g.drawImage(mapImage.get("RD"), x, y, null);
				break;
			case D:
				g.drawImage(mapImage.get("D"), x, y, null);
				break;
			case LD:
				g.drawImage(mapImage.get("LD"), x, y, null);
				break;
			case L:
				g.drawImage(mapImage.get("L"), x, y, null);
				break;
			case LU:
				g.drawImage(mapImage.get("LU"), x, y, null);
				break;
			default:
				break;
			}
		} else {
			g.drawImage(image[0], x, y, null);
		}
		move(dir);
	}
	
	/**
	 * �ӵ��ƶ�����
	 * @param dir2  ̹�˷���
	 */
	private void move(Direction dir2) {
		old_x = x;
		old_y = y;
		switch (dir2) {
		case U:
			y -= MISS_YV;
			break;
		case RU:
			x += MISS_XV;
			y -= MISS_YV;
			break;
		case R:
			x += MISS_XV;
			break;
		case RD:
			x += MISS_XV;
			y += MISS_YV;
			break;
		case D:
			y += MISS_YV;
			break;
		case LD:
			x -= MISS_XV;
			y += MISS_YV;
			break;
		case L:
			x -= MISS_XV;
			break;
		case LU:
			x -= MISS_XV;
			y -= MISS_YV;
			break;
		default:
			break;
		}
		/* �����ӵ����ܳ��� */
		if (x < 6 || y < 6 || x > 530 || y > 565) {
			game.miss.remove(this);
			x = old_x;
			y = old_y;
		}
	}

	/**
	 *  �����ײ,��ȡ���ž���
	 * @return
	 */
	public Rectangle getRect() {
		return new Rectangle(x, y, WIDTH, HEIGHT);
	}

	/**
	 * �ӵ�����̹�� 
	 * @param t Tank
	 * @return �ӵ�����̹�˷���true,���򷵻�false.
	 */
	public boolean hitTank(Tank t) {
		if (this.isLive() && this.getRect().intersects(t.getRect())
				&& t.isLive() && this.good != t.isGood()) {
			/* ����Ѫ�� */
			if (t.isGood()) {
				t.setLife(t.getLife() - 20);
				if (t.getLife() <= 0) {
					t.setLive(false);
				}
			} else {
				t.setLive(false);
			}
			Explode e = new Explode(t.getX(), t.getY(), this.game);
			this.game.explode.add(e);
			this.live = false;
			return true;
		}
		return false;
	}

	/**
	 * ���̹���б��Ƿ��ӵ�����
	 * 
	 * @param tank Tank�б�
	 * @return ���̹���б��ӵ����з���true,���򷵻�false.
	 */
	public boolean hitTanks(List<Tank> tank) {
		for (int i = 0; i < tank.size(); i++) {
			if (hitTank(tank.get(i))) {
				return true;
			}
		}
		return false;
	}

	public boolean isLive() {
		return live;
	}

	public void setLive(boolean live) {
		this.live = live;
	}
}

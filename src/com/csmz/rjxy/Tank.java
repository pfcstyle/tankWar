package com.csmz.rjxy;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

/**
 * Tank ��
 * 
 * @author ZhouHuan 2013��8��22��20:27:58
 *
 */
public class Tank {

	/* ̹�˳ߴ�*/
	public static final int WIDTH = 46;
	public static final int HEIGHT = 34;

	private int x;
	private int y;

	/* ���̵ķ��� */
	private boolean BU = false;
	private boolean BL = false;
	private boolean BR = false;
	private boolean BD = false;

	/* ����x,y����ֵ */
	private int old_x;
	private int old_y;

	/* �ٶ� */
	private static final int TANK_YV = 5;
	private static final int TANK_XV = 5;

	/* ����̹�˵��� */
	private boolean good;

	/* ̹������ */
	private boolean live = true;

	/* ̹�˵�Ѫֵ */
	private int life = 100;

	/* ����������� */
	private static Random random = new Random();
	
	/* ��¼̹���ƶ��Ĳ��� */
	private int step = random.nextInt(12) + 3;

	private static final Toolkit tk = Toolkit.getDefaultToolkit(); // ������ͼƬ����Image����
	private static Map<String, Image> mapImage = new HashMap<String, Image>();  //���廵̹��ͼƬHasMap
	private static Map<String, Image> mapImage1 = new HashMap<String, Image>();	//�����̹��ͼƬHasMap
	private static Image[] image = null;
	static {
		image = new Image[] {
				tk.getImage(Tank.class.getClassLoader().getResource(
						"image/tank_D.png")),
				tk.getImage(Tank.class.getClassLoader().getResource(
						"image/tank_L.png")),
				tk.getImage(Tank.class.getClassLoader().getResource(
						"image/tank_LD.png")),
				tk.getImage(Tank.class.getClassLoader().getResource(
						"image/tank_UL.png")),
				tk.getImage(Tank.class.getClassLoader().getResource(
						"image/tank_R.png")),
				tk.getImage(Tank.class.getClassLoader().getResource(
						"image/tank_DR.png")),
				tk.getImage(Tank.class.getClassLoader().getResource(
						"image/tank_UR.png")),
				tk.getImage(Tank.class.getClassLoader().getResource(
						"image/tank_U.png")),

				tk.getImage(Tank.class.getClassLoader().getResource(
						"image/tankh_D.png")),
				tk.getImage(Tank.class.getClassLoader().getResource(
						"image/tankh_L.png")),
				tk.getImage(Tank.class.getClassLoader().getResource(
						"image/tankh_LD.png")),
				tk.getImage(Tank.class.getClassLoader().getResource(
						"image/tankh_R.png")),
				tk.getImage(Tank.class.getClassLoader().getResource(
						"image/tankh_RD.png")),
				tk.getImage(Tank.class.getClassLoader().getResource(
						"image/tankh_U.png")),
				tk.getImage(Tank.class.getClassLoader().getResource(
						"image/tankh_UL.png")),
				tk.getImage(Tank.class.getClassLoader().getResource(
						"image/tankh_UR.png")), };
		mapImage.put("D", image[0]);
		mapImage.put("L", image[1]);
		mapImage.put("LD", image[2]);
		mapImage.put("LU", image[3]);
		mapImage.put("R", image[4]);
		mapImage.put("RD", image[5]);
		mapImage.put("RU", image[6]);
		mapImage.put("U", image[7]);

		mapImage1.put("D", image[8]);
		mapImage1.put("L", image[9]);
		mapImage1.put("LD", image[10]);
		mapImage1.put("R", image[11]);
		mapImage1.put("RD", image[12]);
		mapImage1.put("U", image[13]);
		mapImage1.put("UL", image[14]);
		mapImage1.put("UR", image[15]);
	};

	/* ̹�˷��� */
	private Direction dir = Direction.STOP;

	/* ����̹���ӵ����� */
	private Direction ptdir = Direction.R;

	private GamePanel game;

	/* ���캯�� */
	public Tank(int x, int y, GamePanel game, boolean good) {
		this.x = x + 5;// 5 �߾�
		this.y = y + 5;
		this.game = game;
		this.good = good;
	}

	/* ��̹�� */
	public void draw(Graphics g) {
		if (!live) { //̹�������Ͳ���,�����Ƴ�.
			
			if (!this.good) this.game.tanks.remove(this);
				
			return;
		}
		if (!good) { //���з�̹��
			switch (ptdir) {
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
		} else { //���ҷ�̹��
			switch (ptdir) {
			case U:
				g.drawImage(mapImage1.get("U"), x, y, null);
				break;
			case RU:
				g.drawImage(mapImage1.get("UR"), x, y, null);
				// System.out.println(mapImage1.get("RU"));
				break;
			case R:
				g.drawImage(mapImage1.get("R"), x, y, null);
				// System.out.println(mapImage1.get("R"));
				break;
			case RD:
				g.drawImage(mapImage1.get("RD"), x, y, null);
				break;
			case D:
				g.drawImage(mapImage1.get("D"), x, y, null);
				break;
			case LD:
				g.drawImage(mapImage1.get("LD"), x, y, null);
				break;
			case L:
				g.drawImage(mapImage1.get("L"), x, y, null);
				break;
			case LU:
				g.drawImage(mapImage1.get("UL"), x, y, null);
				break;
			default:
				break;
			}
		}

		move();// ̹���ƶ�
	}

	/**
	 * ̹���ƶ�
	 */
	private void move() {

		old_x = x;
		old_y = y;
		switch (dir) {
		case U:
			y -= TANK_YV;
			break;
		case RU:
			x += TANK_XV;
			y -= TANK_YV;
			break;
		case R:
			x += TANK_XV;
			break;
		case RD:
			y += TANK_YV;
			x += TANK_XV;
			break;
		case D:
			y += TANK_YV;
			break;
		case LD:
			x -= TANK_XV;
			y += TANK_YV;
			break;
		case L:
			x -= TANK_XV;
			break;
		case LU:
			x -= TANK_XV;
			y -= TANK_YV;
			break;
		case STOP:
			break;
		}

		/* ����̹�����������̹�˷���һ�� */
		if (dir != Direction.STOP)
			ptdir = dir;
		/* ����̹�˲��ܳ��� */
		if (x < 5 || y < 5 || x > 495 || y > 520) {
			x = old_x;
			y = old_y;
		}
		/* �о�̹�� */
		if (!good) {
			Direction dirs[] = Direction.values();// ��ö���� ת��������
			if (step == 0) {
				step = random.nextInt(12) + 3;
				int rn = random.nextInt(dirs.length);
				dir = dirs[rn];
			}
			step--;
			if (random.nextInt(40) > 37)
				this.fight();// ���Ƶо�̹�˷����ӵ�
		}
	}

	/**
	 * ���̰��·���
	 * @param e
	 */
	public void keyPressed(KeyEvent e) {

		int key = e.getKeyCode();// ��ȡ���̼�

		switch (key) {
		case KeyEvent.VK_F2: //F2--�ҷ�̹����������
			addTank();
			//System.out.println("F2");
			break;
		case KeyEvent.VK_ENTER: //�س���������Ϸ ��ͣ/����
			if (GamePanel.isBinge()) {
				GamePanel.setBinge(false);
				GamePanel.setControl(true);
			} else {
				GamePanel.setBinge(true);
				GamePanel.setControl(false);
			}
			break;
		case KeyEvent.VK_UP:
			BU = true;
			break;
		case KeyEvent.VK_RIGHT:
			BR = true;
			break;
		case KeyEvent.VK_DOWN:
			BD = true;
			break;
		case KeyEvent.VK_LEFT:
			BL = true;
			break;
		default:
			break;
		}
		locatDirection();

	}

	/**
	 * ����̧�𷽷�
	 * @param e
	 */
	public void keyReleased(KeyEvent e) {
		int key = e.getKeyCode();// ��ȡ���̼�

		switch (key) {
		case KeyEvent.VK_CONTROL:
			fight();
			break;
		case KeyEvent.VK_A:
			superFight();
			break;
		case KeyEvent.VK_UP:
			BU = false;
			break;
		case KeyEvent.VK_RIGHT:
			BR = false;
			break;
		case KeyEvent.VK_DOWN:
			BD = false;
			break;
		case KeyEvent.VK_LEFT:
			BL = false;
			break;
		default:
			break;
		}
		locatDirection();
	}
	
	/**
	 * ���ҷ�̹���������ߵз�̹��������ӷ��� 
	 */
	private void addTank() {

		if (game.tanks.size() == 0) {
			for (int i = 0; i < 5; i++) {
				game.tanks.add(new Tank(200 + 40 * i, 50 + 80 * i, this.game,
						false));
			}
			// System.out.println("addTank�����������ˣ�");
		}
		//System.out.println(game.tanks.size());
		if (!this.game.tank.live) {  //�ҷ�̹�˸���
			this.game.tank.live = true;
			this.game.tank.life = 100;
		}
	}


	/**
	 * ���ݼ����ж�̹�˷��� 
	 */
	private void locatDirection() {
		if (BU && !BR && !BD && !BL)
			dir = Direction.U;
		else if (BU && BR && !BD && !BL)
			dir = Direction.RU;
		else if (!BU && BR && !BD && !BL)
			dir = Direction.R;
		else if (!BU && BR && BD && !BL)
			dir = Direction.RD;
		else if (!BU && !BR && BD && !BL)
			dir = Direction.D;
		else if (!BU && !BR && BD && BL)
			dir = Direction.LD;
		else if (!BU && !BR && !BD && BL)
			dir = Direction.L;
		else if (BU && !BR && !BD && BL)
			dir = Direction.LU;
		else if (!BU && !BR && !BD && !BL)
			dir = Direction.STOP;
	}

	/**
	 * ����ӵ�
	 * @return �����ӵ�
	 */
	public Missile fight() {
		if (!this.live) return null; // ̹�����˲��ܷ����ӵ�
		if (!this.good) { // �з�̹���ӵ�
			if (ptdir == Direction.RU || ptdir == Direction.LD) {
				int x = this.x + WIDTH / 2 + Missile.WIDTH - 5;
				int y = this.y + HEIGHT / 2 - Missile.HEIGHT + 5;
				Missile m = new Missile(x, y, this.ptdir, this.game, this.good);
				this.game.miss.add(m);
				return m;
			} else {
				int x = this.x + WIDTH / 2 - Missile.WIDTH;
				int y = this.y + HEIGHT / 2 - Missile.HEIGHT / 2;
				Missile m = new Missile(x, y, this.ptdir, this.game, this.good);
				this.game.miss.add(m);
				return m;
			}
		} else {
			if (ptdir == Direction.RU || ptdir == Direction.LD) {
				int x = this.x + WIDTH / 2 + Missile.WIDTH - 6;
				int y = this.y + HEIGHT / 2 - Missile.HEIGHT + 6;
				Missile m = new Missile(x, y, this.ptdir, this.game, this.good);
				this.game.miss.add(m);
				return m;
			} else {
				if (ptdir == Direction.R) {
					int x = this.x + WIDTH / 2 - Missile.WIDTH - 1;
					int y = this.y + HEIGHT / 2 - Missile.HEIGHT / 2 + 5;
					Missile m = new Missile(x, y, this.ptdir, this.game,
							this.good);
					this.game.miss.add(m);
					return m;
				} else {
					int x = this.x + WIDTH / 2 - Missile.WIDTH - 1;
					int y = this.y + HEIGHT / 2 - Missile.HEIGHT / 2;
					Missile m = new Missile(x, y, this.ptdir, this.game,
							this.good);
					this.game.miss.add(m);
					return m;
				}
			}

		}

	}
	/**
	 * ����ӵ�����
	 * @param dir  ����
	 * @return  �ӵ�
	 */
	public Missile fight(Direction dir) {
		if (!this.live)
			return null; // ̹�����˲��ܷ����ӵ�
		if (dir == Direction.RU || dir == Direction.LD) {
				int x = this.x + WIDTH / 2 + Missile.WIDTH - 6;
				int y = this.y + HEIGHT / 2 - Missile.HEIGHT + 6;
				Missile m = new Missile(x, y, dir, this.game, this.good);
				game.miss.add(m);
				return m;
		} else {
				if (dir == Direction.R) {
					int x = this.x + WIDTH / 2 - Missile.WIDTH - 1;
					int y = this.y + HEIGHT / 2 - Missile.HEIGHT / 2 + 5;
					Missile m = new Missile(x, y, dir, this.game, this.good);
					game.miss.add(m);
					return m;
				} else {
					int x = this.x + WIDTH / 2 - Missile.WIDTH - 1;
					int y = this.y + HEIGHT / 2 - Missile.HEIGHT / 2;
					Missile m = new Missile(x, y, dir, this.game, this.good);
					game.miss.add(m);
					return m;
				}
			}


	}

	/**
	 * �����ӵ�
	 */
	private void superFight() {
		Direction dirs[] = Direction.values();
		for (int i = 0; i < dirs.length; i++) {
			fight(dirs[i]);
		}
	}

	/**
	 * �ظ�ԭ��
	 */
	public void start() {
		this.x = old_x;
		this.y = old_y;
	}

	/**
	 * �����ײ,��ȡ���ž���
	 * @return Rectangle 
	 */
	public Rectangle getRect() {
		return new Rectangle(x, y, WIDTH, HEIGHT);
	}

	/**
	 * ���̹����ײ̹�� 
	 * @param tank ̹���б�
	 * @return  ��ײ����true,����false.
	 */
	public boolean tank_hit_tanks(List<Tank> tank) {
		for (int i = 0; i < tank.size(); i++) {
			Tank t = tank.get(i);
			if (this != t) {
				if (this.good || t.good) { // ����̹�˲�����ײ �����ײ �ҷ�̹��ȥѪ
					if (this.live && t.isLive()
							&& this.getRect().intersects(t.getRect())) {
						if (this.good) {
							this.setLife(this.getLife() - 10);
							if (this.getLife() <= 0)
								this.live = false;
							Explode e = new Explode(this.getX(), this.getY(),
									game);
							this.game.explode.add(e);
							return true;
						} else {
							t.setLife(t.getLife() - 10);
							if (t.getLife() <= 0)
								t.live = false;
							Explode e = new Explode(t.getX(), t.getY(), t.game);
							t.game.explode.add(e);
							return true;
						}
					}
				} else {
					if (this.live && t.isLive()
							&& this.getRect().intersects(t.getRect())) {
						this.start();
						t.start();
						return true;
					}
				}

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

	public boolean isGood() {
		return good;
	}

	public void setGood(boolean good) {
		this.good = good;
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

	public int getLife() {
		return life;
	}

	public void setLife(int life) {
		this.life = life;
	}
}

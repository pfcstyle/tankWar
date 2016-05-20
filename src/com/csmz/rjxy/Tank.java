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
 * Tank 类
 * 
 * @author ZhouHuan 2013年8月22日20:27:58
 *
 */
public class Tank {

	/* 坦克尺寸*/
	public static final int WIDTH = 46;
	public static final int HEIGHT = 34;

	private int x;
	private int y;

	/* 键盘的方向 */
	private boolean BU = false;
	private boolean BL = false;
	private boolean BR = false;
	private boolean BD = false;

	/* 保存x,y坐标值 */
	private int old_x;
	private int old_y;

	/* 速度 */
	private static final int TANK_YV = 5;
	private static final int TANK_XV = 5;

	/* 区分坦克敌我 */
	private boolean good;

	/* 坦克生命 */
	private boolean live = true;

	/* 坦克的血值 */
	private int life = 100;

	/* 随机数参数器 */
	private static Random random = new Random();
	
	/* 记录坦克移动的步骤 */
	private int step = random.nextInt(12) + 3;

	private static final Toolkit tk = Toolkit.getDefaultToolkit(); // 用来将图片读入Image数组
	private static Map<String, Image> mapImage = new HashMap<String, Image>();  //定义坏坦克图片HasMap
	private static Map<String, Image> mapImage1 = new HashMap<String, Image>();	//定义好坦克图片HasMap
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

	/* 坦克方向 */
	private Direction dir = Direction.STOP;

	/* 设置坦克子弹方向 */
	private Direction ptdir = Direction.R;

	private GamePanel game;

	/* 构造函数 */
	public Tank(int x, int y, GamePanel game, boolean good) {
		this.x = x + 5;// 5 边距
		this.y = y + 5;
		this.game = game;
		this.good = good;
	}

	/* 画坦克 */
	public void draw(Graphics g) {
		if (!live) { //坦克死掉就不画,并且移除.
			
			if (!this.good) this.game.tanks.remove(this);
				
			return;
		}
		if (!good) { //画敌方坦克
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
		} else { //画我方坦克
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

		move();// 坦克移动
	}

	/**
	 * 坦克移动
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

		/* 设置坦克射击方向与坦克方向一致 */
		if (dir != Direction.STOP)
			ptdir = dir;
		/* 设置坦克不能出界 */
		if (x < 5 || y < 5 || x > 495 || y > 520) {
			x = old_x;
			y = old_y;
		}
		/* 敌军坦克 */
		if (!good) {
			Direction dirs[] = Direction.values();// 将枚举型 转换成数组
			if (step == 0) {
				step = random.nextInt(12) + 3;
				int rn = random.nextInt(dirs.length);
				dir = dirs[rn];
			}
			step--;
			if (random.nextInt(40) > 37)
				this.fight();// 控制敌军坦克发射子弹
		}
	}

	/**
	 * 键盘按下方法
	 * @param e
	 */
	public void keyPressed(KeyEvent e) {

		int key = e.getKeyCode();// 获取键盘键

		switch (key) {
		case KeyEvent.VK_F2: //F2--我方坦克死亡复活
			addTank();
			//System.out.println("F2");
			break;
		case KeyEvent.VK_ENTER: //回车键控制游戏 暂停/继续
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
	 * 键盘抬起方法
	 * @param e
	 */
	public void keyReleased(KeyEvent e) {
		int key = e.getKeyCode();// 获取键盘键

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
	 * 当我方坦克死亡或者敌方坦克死亡添加方法 
	 */
	private void addTank() {

		if (game.tanks.size() == 0) {
			for (int i = 0; i < 5; i++) {
				game.tanks.add(new Tank(200 + 40 * i, 50 + 80 * i, this.game,
						false));
			}
			// System.out.println("addTank方法被调用了！");
		}
		//System.out.println(game.tanks.size());
		if (!this.game.tank.live) {  //我方坦克复活
			this.game.tank.live = true;
			this.game.tank.life = 100;
		}
	}


	/**
	 * 根据键盘判断坦克方向 
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
	 * 打出子弹
	 * @return 返回子弹
	 */
	public Missile fight() {
		if (!this.live) return null; // 坦克死了不能发射子弹
		if (!this.good) { // 敌方坦克子弹
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
	 * 打出子弹方法
	 * @param dir  方向
	 * @return  子弹
	 */
	public Missile fight(Direction dir) {
		if (!this.live)
			return null; // 坦克死了不能发射子弹
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
	 * 超级子弹
	 */
	private void superFight() {
		Direction dirs[] = Direction.values();
		for (int i = 0; i < dirs.length; i++) {
			fight(dirs[i]);
		}
	}

	/**
	 * 回复原点
	 */
	public void start() {
		this.x = old_x;
		this.y = old_y;
	}

	/**
	 * 检测碰撞,获取包着矩形
	 * @return Rectangle 
	 */
	public Rectangle getRect() {
		return new Rectangle(x, y, WIDTH, HEIGHT);
	}

	/**
	 * 检测坦克碰撞坦克 
	 * @param tank 坦克列表
	 * @return  碰撞返回true,否则false.
	 */
	public boolean tank_hit_tanks(List<Tank> tank) {
		for (int i = 0; i < tank.size(); i++) {
			Tank t = tank.get(i);
			if (this != t) {
				if (this.good || t.good) { // 敌我坦克不能相撞 如果相撞 我方坦克去血
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

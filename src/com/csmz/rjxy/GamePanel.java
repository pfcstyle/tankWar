package com.csmz.rjxy;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;
import javax.swing.border.EtchedBorder;

/**
 * 左边画板类
 * 
 * @author ZhouHuan 2013年8月22日20:08:22
 *
 */
public class GamePanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final Toolkit tk = Toolkit.getDefaultToolkit();
	private static Image image = null;

	private Image offScreenImage = null;

	/* 背景颜色 */
	private static Color bgColor = Color.BLACK;

	private Graphics og;
	int y = 50;
	Tank tank;
	private Explode e;
	private Wall w;

	/* 屏幕开始 */
	public static boolean playing = true;

	/* 屏幕状态 */
	public static boolean state = true;

	/* 开始 */
	public static boolean binge = false;

	/* 控制暂停字幕 */
	public static boolean control = false;

	private BoolBar boolBar;

	/* 初始位置 */
	private static int position[][] = { 
		{ 280, 45 },
		{ 85, 220 }, 
		{ 210, 220 },
		{ 380, 220 }, 
		{ 240, 500 }, 
		{ 100, 500}, 
		{ 450, 500}, 
		{ 210, 220}, 
		{ 380, 220},
		{ 450, 45},
		{ 85, 220} 
		};

	static {
		image = tk.getImage(Tank.class.getClassLoader().getResource(
				"image/gameStart.png"));
	}

	List<Missile> miss = new ArrayList<Missile>(); // 子弹列表
	List<Explode> explode = new ArrayList<Explode>(); // 爆炸列表
	List<Tank> tanks = new ArrayList<Tank>();// 敌方坦克
	Missile m = null;
	
	/*构造方法*/
	public GamePanel() {
		this.setBounds(5, 5, 545, 570);// 538, 543
		this.setBorder(new EtchedBorder(EtchedBorder.LOWERED));
		new Thread(new PaintThread()).start();  //重画线程启动
	}

	private void draw() { //synchronized
		if (og == null) {  
			if (offScreenImage == null) {  //建立虚拟图片解决坦克移动卡顿
				offScreenImage = this.createImage(540, 565);
			}
			if (offScreenImage != null)
				og = offScreenImage.getGraphics();

		}
		if (og != null) {
			/* 画开始界面 */
			if (playing) {
				og.drawImage(image, 5, 10, 534, 570, null);
				Color c = og.getColor();
				og.setColor(Color.RED);
				og.setFont(new Font("楷体", Font.BOLD, 15));
				og.drawString("沉迷游戏有害健康", 40, 50);
				og.setColor(c);
				og.setColor(Color.GREEN);
				og.setFont(new Font("楷体", Font.BOLD, 46));
				og.setColor(c);
				og.setColor(c);
				og.setColor(Color.BLACK);
				og.setFont(new Font("楷体", Font.BOLD, 16));
				og.drawString("", 215, 320);
				og.setColor(c);
			}
			/* 画坦克主界面 */
			if (binge) {
				og.setColor(bgColor); //设置游戏背景
				og.fillRect(5, 5, 540, 580);// 534 550, 538 543
				
				/*****************/
//				Color c = og.getColor();
//				og.setColor(Color.RED);
//				og.drawString(tanks.size()+ "", 300, 50);
//				og.setColor(c);
				/***************/
				/**/
				if (tanks.size() == 0) {
					for(int i=5; i<position.length; i++){
						tanks.add(new Tank(position[i][0], position[i][1], this, false));
						System.out.println(position[i][0] +"," +position[i][1]);
					}
				}

				for (int i = 0; i < miss.size(); i++) { //
					m = miss.get(i);
					m.hitTanks(tanks);// 子弹打击坦克
					m.hitTank(tank);
					w.miis_hit_wall(m);
					m.draw(og);
				}
				
				/*画爆炸*/
				for (int i = 0; i < explode.size(); i++) {
					e = explode.get(i);
					e.draw(og);
				}
				/*画坦克*/
				for (int i = 0; i < tanks.size(); i++) {
					Tank t = tanks.get(i);
					t.tank_hit_tanks(tanks);
					w.tank_hit_wall(t);
					t.draw(og);
				}
				tank.draw(og);
				boolBar.draw(og);//画血条
				tank.tank_hit_tanks(tanks);//检测坦克碰撞
				w.tank_hit_wall(tank);//检测坦克与墙相撞
				/*画墙*/
				w.draw(og);

			}
			/*游戏暂停*/
			if (control) {
				Color c = og.getColor();
				og.setColor(Color.RED);
				og.setFont(new Font("楷体", Font.BOLD, 46));
				og.drawString("游戏暂停!休息一会......", 0, 255);
				og.setColor(c);
			}
			/*重调用paint方法*/
			this.paint(this.getGraphics());

			//System.out.println("1111111111111");
		}
	}

	
	public void paint(Graphics g) {

		g.drawImage(offScreenImage, 0, 0, this);

	}

	/**
	 * 画游戏界面线程
	 * @author Thread 内部类
	 *
	 */
	private class PaintThread implements Runnable {

		@Override
		public void run() {
			while (true) {
				draw();
				try {
					Thread.sleep(35);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}

	}

	/**
	 * 游戏停止
	 */
	public void gameOver() {
		GamePanel.binge = false;
		tanks.clear();
		miss.clear();
		explode.clear();
		tank = null;
		w = null;
		boolBar = null;
	}

	/**
	 * 游戏开始
	 */
	public void gameStart() {
		for (int i = 0; i < 5; i++) {
			tanks.add(new Tank(position[i][0], position[i][1], this, false));
			//System.out.println(position[i][0]+ "," + position[i][1]);
		}
		//tanks.add(new Tank(position[0][0], position[0][1], this, false));
		tank = new Tank(20, 20, this, true);
		w = new Wall(50, 100, this);
		boolBar = new BoolBar(tank);
	}

	/**
	 * 点击
	 * @param e
	 */
	public void keyPressed(KeyEvent e) {
		tank.keyPressed(e);
	}

	/**
	 * 抬起
	 * @param e
	 */
	public void keyReleased(KeyEvent e) {
		tank.keyReleased(e);
	}

	public static boolean isPlaying() {
		return playing;
	}

	public static void setPlaying(boolean playing) {
		GamePanel.playing = playing;
	}

	public Tank getTank() {
		return tank;
	}

	public void setTank(Tank tank) {
		this.tank = tank;
	}

	public static boolean isBinge() {
		return binge;
	}

	public static void setBinge(boolean binge) {
		GamePanel.binge = binge;
	}

	public static boolean isControl() {
		return control;
	}

	public static void setControl(boolean control) {
		GamePanel.control = control;
	}

	public static Color getBgColor() {
		return bgColor;
	}

	public static void setBgColor(Color bgColor) {
		GamePanel.bgColor = bgColor;
	}

}

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
 * ��߻�����
 * 
 * @author ZhouHuan 2013��8��22��20:08:22
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

	/* ������ɫ */
	private static Color bgColor = Color.BLACK;

	private Graphics og;
	int y = 50;
	Tank tank;
	private Explode e;
	private Wall w;

	/* ��Ļ��ʼ */
	public static boolean playing = true;

	/* ��Ļ״̬ */
	public static boolean state = true;

	/* ��ʼ */
	public static boolean binge = false;

	/* ������ͣ��Ļ */
	public static boolean control = false;

	private BoolBar boolBar;

	/* ��ʼλ�� */
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

	List<Missile> miss = new ArrayList<Missile>(); // �ӵ��б�
	List<Explode> explode = new ArrayList<Explode>(); // ��ը�б�
	List<Tank> tanks = new ArrayList<Tank>();// �з�̹��
	Missile m = null;
	
	/*���췽��*/
	public GamePanel() {
		this.setBounds(5, 5, 545, 570);// 538, 543
		this.setBorder(new EtchedBorder(EtchedBorder.LOWERED));
		new Thread(new PaintThread()).start();  //�ػ��߳�����
	}

	private void draw() { //synchronized
		if (og == null) {  
			if (offScreenImage == null) {  //��������ͼƬ���̹���ƶ�����
				offScreenImage = this.createImage(540, 565);
			}
			if (offScreenImage != null)
				og = offScreenImage.getGraphics();

		}
		if (og != null) {
			/* ����ʼ���� */
			if (playing) {
				og.drawImage(image, 5, 10, 534, 570, null);
				Color c = og.getColor();
				og.setColor(Color.RED);
				og.setFont(new Font("����", Font.BOLD, 15));
				og.drawString("������Ϸ�к�����", 40, 50);
				og.setColor(c);
				og.setColor(Color.GREEN);
				og.setFont(new Font("����", Font.BOLD, 46));
				og.setColor(c);
				og.setColor(c);
				og.setColor(Color.BLACK);
				og.setFont(new Font("����", Font.BOLD, 16));
				og.drawString("", 215, 320);
				og.setColor(c);
			}
			/* ��̹�������� */
			if (binge) {
				og.setColor(bgColor); //������Ϸ����
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
					m.hitTanks(tanks);// �ӵ����̹��
					m.hitTank(tank);
					w.miis_hit_wall(m);
					m.draw(og);
				}
				
				/*����ը*/
				for (int i = 0; i < explode.size(); i++) {
					e = explode.get(i);
					e.draw(og);
				}
				/*��̹��*/
				for (int i = 0; i < tanks.size(); i++) {
					Tank t = tanks.get(i);
					t.tank_hit_tanks(tanks);
					w.tank_hit_wall(t);
					t.draw(og);
				}
				tank.draw(og);
				boolBar.draw(og);//��Ѫ��
				tank.tank_hit_tanks(tanks);//���̹����ײ
				w.tank_hit_wall(tank);//���̹����ǽ��ײ
				/*��ǽ*/
				w.draw(og);

			}
			/*��Ϸ��ͣ*/
			if (control) {
				Color c = og.getColor();
				og.setColor(Color.RED);
				og.setFont(new Font("����", Font.BOLD, 46));
				og.drawString("��Ϸ��ͣ!��Ϣһ��......", 0, 255);
				og.setColor(c);
			}
			/*�ص���paint����*/
			this.paint(this.getGraphics());

			//System.out.println("1111111111111");
		}
	}

	
	public void paint(Graphics g) {

		g.drawImage(offScreenImage, 0, 0, this);

	}

	/**
	 * ����Ϸ�����߳�
	 * @author Thread �ڲ���
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
	 * ��Ϸֹͣ
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
	 * ��Ϸ��ʼ
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
	 * ���
	 * @param e
	 */
	public void keyPressed(KeyEvent e) {
		tank.keyPressed(e);
	}

	/**
	 * ̧��
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

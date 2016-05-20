package com.csmz.rjxy;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JTextArea;
import javax.swing.border.EtchedBorder;

/**
 * Main
 * 
 * 主界面框架 
 * 
 * @author ZhouHuan 2013年8月22日0:44:36
 * 
 */

public class MainFrame extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final Toolkit tk = Toolkit.getDefaultToolkit();
	private static Image image = null;
	static {
		image = tk.getImage(MainFrame.class.getClassLoader().getResource(
				"image/zidan.png"));
	}
	/* 右边 */
	private JPanel jl_right = new JPanel();
	private JPanel jl_right_up = new JPanel();
	private JPanel jl_right_dw = new JPanel();
	private JPanel jl_center = new JPanel();

	private final JSeparator seppartor1 = new JSeparator();// 我是分割线
	private final JSeparator seppartor2 = new JSeparator();
	private final JSeparator seppartor3 = new JSeparator();

	private JButton but_start = new JButton("开始");
	private JButton but_skeep = new JButton("暂停/继续");
	private JButton but_stop = new JButton("停止");
	private JButton but_set = new JButton("设置游戏背景");

	private JTextArea text_help = new JTextArea(
			"游戏说明:\n  坦克大战是一款经"
					+ "\n典的坦克游戏!里面有\n两种坦克蓝色为我方,\n红色为敌方.\n\n\n方向键控制方向,Ctrl\n建控制坦克发射子弹\nF2增"
					+ "加敌方坦克或我\n方坦克,回车键暂停.\n\n\n\n\n\n");

	/* 游戏界面 */

	private GamePanel gamePanel = new GamePanel();

	public static void main(String[] args) {
		new MainFrame();
	}

	public MainFrame() {
		this.setTitle("Java版坦克大战");
		this.setBounds(300, 100, 700, 600);
		this.setIconImage(image);// 设置窗口左上角的小图标
		this.setResizable(false);
		this.setVisible(true);
		this.infor();
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	public void infor() {

		/* 右边面板设置 */

		jl_right.setLayout(new BorderLayout());// 设置右边布局
		jl_center.setLayout(null);
		jl_center.setPreferredSize(new Dimension(10, 600));
		// 上边
		jl_right_up.setPreferredSize(new Dimension(150, 180));
		jl_right_up.setLayout(null);
		jl_right_up.setBorder(new EtchedBorder(EtchedBorder.LOWERED));

		but_start.setBounds(25, 15, 100, 30); // 设置按钮相对Jpanel
		but_start.setFont(new Font("楷体", Font.PLAIN, 13));
		jl_right_up.add(but_start);

		seppartor1.setBounds(8, 60, 135, 28);// 设置分割线相对Jpanel
		jl_right_up.add(seppartor1);

		but_skeep.setBounds(15, 75, 120, 30);// 设置按钮相对Jpanel
		but_skeep.setFont(new Font("楷体", Font.PLAIN, 13));
		seppartor1.add(but_skeep);
		jl_right_up.add(but_skeep);
		seppartor2.setBounds(8, 120, 135, 28);// 设置分割线相对Jpanel
		jl_right_up.add(seppartor2);
		but_skeep.setEnabled(false);

		but_stop.setBounds(25, 135, 100, 30);// 设置按钮相对Jpanel
		but_stop.setFont(new Font("楷体", Font.PLAIN, 13));
		seppartor2.add(but_stop);
		jl_right_up.add(but_stop);
		but_stop.setEnabled(false);
		jl_right.add(jl_right_up, BorderLayout.NORTH);

		// 下边
		jl_right_dw.setPreferredSize(new Dimension(150, 300));
		jl_right_dw.setLayout(null);
		jl_right_dw.setBorder(new EtchedBorder(EtchedBorder.LOWERED));

		but_set.setBounds(12, 15, 125, 30);
		but_set.setFont(new Font("楷体", Font.PLAIN, 15));
		jl_right_dw.add(but_set);
		seppartor3.setBounds(8, 60, 135, 28);
		jl_right_dw.add(seppartor3);
		but_set.setEnabled(false);

		text_help.setBounds(8, 100, 130, 280);
		text_help.setFont(new Font("楷体", Font.PLAIN, 14));
		text_help.setFocusable(false);
		text_help.setBackground(this.getBackground());
		seppartor3.add(text_help);
		jl_right_dw.add(text_help);
		jl_right.add(jl_right_dw, BorderLayout.CENTER);

		/* 游戏面板设计 */

		this.setLayout(new BorderLayout());
		this.add(jl_right, BorderLayout.EAST);
		this.add(gamePanel, BorderLayout.CENTER);

		/* 按钮事件 */
		but_start.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				MainFrame.this.requestFocus();
				but_start.setEnabled(false);
				but_stop.setEnabled(true);
				but_set.setEnabled(true);
				but_skeep.setEnabled(true);
				gamePanel.gameStart();
				GamePanel.setPlaying(false);
				GamePanel.setBinge(true);
				//GamePanel.setControl(false);
			}
		});
		/* 暂停/继续 按钮 */
		but_skeep.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				MainFrame.this.requestFocus();
				if (GamePanel.isBinge()) {
					GamePanel.setBinge(false);
					GamePanel.setControl(true);
					but_skeep.setText("继续");
				} else {
					GamePanel.setBinge(true);
					GamePanel.setControl(false);
					but_skeep.setText("暂停");
				}

			}
		});
		/* 停止按钮 */
		but_stop.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				gamePanel.gameOver();
				but_start.setEnabled(true);
				but_stop.setEnabled(false);
				but_skeep.setEnabled(false);
			}
		});
		/* 设置背景颜色 按钮 */
		but_set.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				MainFrame.this.requestFocus();
				// 设置背景颜色
				Color bgColor = JColorChooser.showDialog(MainFrame.this,
						"请选择背景颜色", Color.DARK_GRAY);
				if (bgColor != null)
					GamePanel.setBgColor(bgColor);
			}
		});

		/* 键盘监听 */
		this.addKeyListener(new KeyListener() {

			@Override
			public void keyTyped(KeyEvent e) {

			}

			@Override
			public void keyReleased(KeyEvent e) {
				gamePanel.keyReleased(e);
			}

			@Override
			public void keyPressed(KeyEvent e) {
				gamePanel.keyPressed(e);
			}
		});
	}

}

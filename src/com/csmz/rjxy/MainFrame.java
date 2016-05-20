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
 * �������� 
 * 
 * @author ZhouHuan 2013��8��22��0:44:36
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
	/* �ұ� */
	private JPanel jl_right = new JPanel();
	private JPanel jl_right_up = new JPanel();
	private JPanel jl_right_dw = new JPanel();
	private JPanel jl_center = new JPanel();

	private final JSeparator seppartor1 = new JSeparator();// ���Ƿָ���
	private final JSeparator seppartor2 = new JSeparator();
	private final JSeparator seppartor3 = new JSeparator();

	private JButton but_start = new JButton("��ʼ");
	private JButton but_skeep = new JButton("��ͣ/����");
	private JButton but_stop = new JButton("ֹͣ");
	private JButton but_set = new JButton("������Ϸ����");

	private JTextArea text_help = new JTextArea(
			"��Ϸ˵��:\n  ̹�˴�ս��һ�"
					+ "\n���̹����Ϸ!������\n����̹����ɫΪ�ҷ�,\n��ɫΪ�з�.\n\n\n��������Ʒ���,Ctrl\n������̹�˷����ӵ�\nF2��"
					+ "�ӵз�̹�˻���\n��̹��,�س�����ͣ.\n\n\n\n\n\n");

	/* ��Ϸ���� */

	private GamePanel gamePanel = new GamePanel();

	public static void main(String[] args) {
		new MainFrame();
	}

	public MainFrame() {
		this.setTitle("Java��̹�˴�ս");
		this.setBounds(300, 100, 700, 600);
		this.setIconImage(image);// ���ô������Ͻǵ�Сͼ��
		this.setResizable(false);
		this.setVisible(true);
		this.infor();
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	public void infor() {

		/* �ұ�������� */

		jl_right.setLayout(new BorderLayout());// �����ұ߲���
		jl_center.setLayout(null);
		jl_center.setPreferredSize(new Dimension(10, 600));
		// �ϱ�
		jl_right_up.setPreferredSize(new Dimension(150, 180));
		jl_right_up.setLayout(null);
		jl_right_up.setBorder(new EtchedBorder(EtchedBorder.LOWERED));

		but_start.setBounds(25, 15, 100, 30); // ���ð�ť���Jpanel
		but_start.setFont(new Font("����", Font.PLAIN, 13));
		jl_right_up.add(but_start);

		seppartor1.setBounds(8, 60, 135, 28);// ���÷ָ������Jpanel
		jl_right_up.add(seppartor1);

		but_skeep.setBounds(15, 75, 120, 30);// ���ð�ť���Jpanel
		but_skeep.setFont(new Font("����", Font.PLAIN, 13));
		seppartor1.add(but_skeep);
		jl_right_up.add(but_skeep);
		seppartor2.setBounds(8, 120, 135, 28);// ���÷ָ������Jpanel
		jl_right_up.add(seppartor2);
		but_skeep.setEnabled(false);

		but_stop.setBounds(25, 135, 100, 30);// ���ð�ť���Jpanel
		but_stop.setFont(new Font("����", Font.PLAIN, 13));
		seppartor2.add(but_stop);
		jl_right_up.add(but_stop);
		but_stop.setEnabled(false);
		jl_right.add(jl_right_up, BorderLayout.NORTH);

		// �±�
		jl_right_dw.setPreferredSize(new Dimension(150, 300));
		jl_right_dw.setLayout(null);
		jl_right_dw.setBorder(new EtchedBorder(EtchedBorder.LOWERED));

		but_set.setBounds(12, 15, 125, 30);
		but_set.setFont(new Font("����", Font.PLAIN, 15));
		jl_right_dw.add(but_set);
		seppartor3.setBounds(8, 60, 135, 28);
		jl_right_dw.add(seppartor3);
		but_set.setEnabled(false);

		text_help.setBounds(8, 100, 130, 280);
		text_help.setFont(new Font("����", Font.PLAIN, 14));
		text_help.setFocusable(false);
		text_help.setBackground(this.getBackground());
		seppartor3.add(text_help);
		jl_right_dw.add(text_help);
		jl_right.add(jl_right_dw, BorderLayout.CENTER);

		/* ��Ϸ������ */

		this.setLayout(new BorderLayout());
		this.add(jl_right, BorderLayout.EAST);
		this.add(gamePanel, BorderLayout.CENTER);

		/* ��ť�¼� */
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
		/* ��ͣ/���� ��ť */
		but_skeep.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				MainFrame.this.requestFocus();
				if (GamePanel.isBinge()) {
					GamePanel.setBinge(false);
					GamePanel.setControl(true);
					but_skeep.setText("����");
				} else {
					GamePanel.setBinge(true);
					GamePanel.setControl(false);
					but_skeep.setText("��ͣ");
				}

			}
		});
		/* ֹͣ��ť */
		but_stop.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				gamePanel.gameOver();
				but_start.setEnabled(true);
				but_stop.setEnabled(false);
				but_skeep.setEnabled(false);
			}
		});
		/* ���ñ�����ɫ ��ť */
		but_set.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				MainFrame.this.requestFocus();
				// ���ñ�����ɫ
				Color bgColor = JColorChooser.showDialog(MainFrame.this,
						"��ѡ�񱳾���ɫ", Color.DARK_GRAY);
				if (bgColor != null)
					GamePanel.setBgColor(bgColor);
			}
		});

		/* ���̼��� */
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

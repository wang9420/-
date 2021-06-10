package com.tom.view;

import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class MainFrame extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() { // 将显示frame对象作为线程放入队列排队，稍后执行
			public void run() {
				try {
					MainFrame frame = new MainFrame(); // 定义一个MainFrame对象，frame
					frame.setVisible(true); // 显示frame对象
				} catch (Exception e) { // 捕捉异常，打印异常信息
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * 利用构造函数MainFrame()定义MainFrame类
	 */
	public MainFrame() {
		setTitle("图书管理系统 "); // 设置窗口标题栏内容
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // 设置窗口关闭按钮功能为关闭系统
		setBounds(200, 100, 800, 600); // 设置窗口的位置和大小，100,100:左上角坐标，800,600:窗口大小

		JMenuBar menuBar = new JMenuBar(); // 定义菜单栏
		setJMenuBar(menuBar); // 将菜单栏加入了主窗口

		JMenu menuShujuweihu = new JMenu("基本数据维护"); // 定义菜单
		// 为“基本数据维护”菜单设置图标
		menuShujuweihu.setIcon(new ImageIcon(MainFrame.class.getResource("/images/base.png")));
		menuBar.add(menuShujuweihu); // 将“基本数据维护”菜单加入菜单栏

		JMenuItem menuItemTuichu = new JMenuItem("退出系统登录"); // 定义“退出系统登录”菜单项
		// 为“退出系统登陆”菜单项添加侦听事件（单击事件）
		menuItemTuichu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// int result=JOptionPane.showConfirmDialog(null, "是否退出系统呢？");
				int result = JOptionPane.showConfirmDialog(null, "是否退出系统？", "退出确认", JOptionPane.OK_CANCEL_OPTION);
				// System.out.println(result);
				if (result == 0) {
					dispose();
				}
			}
		});
		// 为“退出系统登陆”菜单项设置图标
		menuItemTuichu.setIcon(new ImageIcon(MainFrame.class.getResource("/images/exit.png")));
		menuShujuweihu.add(menuItemTuichu); // 将“退出系统登陆”菜单项添加到“基本数据维护菜单”中

		JMenu menuTushuleibie = new JMenu("图书类别管理"); // 定义“图书类别管理”菜单
		menuTushuleibie.setIcon(new ImageIcon(MainFrame.class.getResource("/images/bookTypeManager.png")));
		menuBar.add(menuTushuleibie);

		// 定义"图书类别添加"菜单项，添加至“图书类别管理”菜单中
		// 并设置其侦听事件（单击事件）
		JMenuItem menuItemTushulbTianjia = new JMenuItem("图书类别添加");
		menuItemTushulbTianjia.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				BookTypeInnerFrame bookTypeInnerFrame = new BookTypeInnerFrame();
				bookTypeInnerFrame.setVisible(true);
				contentPane.add(bookTypeInnerFrame);
			}
		});
		menuItemTushulbTianjia.setIcon(new ImageIcon(MainFrame.class.getResource("/images/add.png")));
		menuTushuleibie.add(menuItemTushulbTianjia);

		JMenuItem menuItemTushulbCaozuo = new JMenuItem("图书类别操作");
		menuItemTushulbCaozuo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				BookTypeManagerinnerFrame bookTypeManagerinnerFrame = new BookTypeManagerinnerFrame();
				bookTypeManagerinnerFrame.setVisible(true);
				contentPane.add(bookTypeManagerinnerFrame);
			}
		});
		menuItemTushulbCaozuo.setIcon(new ImageIcon(MainFrame.class.getResource("/images/edit.png")));
		menuTushuleibie.add(menuItemTushulbCaozuo);

		JMenu menuTushu = new JMenu("图书管理"); // 定义“图书管理”菜单
		menuTushu.setIcon(new ImageIcon(MainFrame.class.getResource("/images/bookManager.png")));
		menuBar.add(menuTushu); // 将“图书管理”菜单添加到菜单栏

		JMenuItem menuItemTushuTianjia = new JMenuItem("图书添加"); // 定义“图书添加”菜单项
		menuItemTushuTianjia.setIcon(new ImageIcon(MainFrame.class.getResource("/images/add.png")));
		menuTushu.add(menuItemTushuTianjia); // 将“图书添加”菜单项添加到“图书管理”菜单中
		menuItemTushuTianjia.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				BookAddinnerFrame bookAddInnerFrame = new BookAddinnerFrame();
				bookAddInnerFrame.setVisible(true);
				contentPane.add(bookAddInnerFrame);
			}
		});

		JMenuItem menuItemTushuBianji = new JMenuItem("图书编辑操作");
		menuItemTushuBianji.setIcon(new ImageIcon(MainFrame.class.getResource("/images/edit.png")));
		menuTushu.add(menuItemTushuBianji);
		menuItemTushuBianji.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {

				BookUse bookManageInnerFrame = new BookUse();
				bookManageInnerFrame.setVisible(true);
				contentPane.add(bookManageInnerFrame);

			}
		});

		JMenu menuBanben = new JMenu("版本信息"); // 定义“版本信息”菜单
		menuBanben.setIcon(new ImageIcon(MainFrame.class.getResource("/images/about.png")));
		menuBar.add(menuBanben); // 将“版本信息”菜单添加到菜单栏

		JMenuItem menuItemAboutus = new JMenuItem("关于我们");
		menuItemAboutus.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				/*
				 * int result = JOptionPane.showConfirmDialog(null, "移动应用开发1801", "关于我们",
				 * JOptionPane.OK_CANCEL_OPTION); if(result==0) { dispose(); }
				 */
				showViewInfoactionPerformed(evt);
			}
		});
		menuItemAboutus.setIcon(new ImageIcon(MainFrame.class.getResource("/images/me.png")));
		menuBanben.add(menuItemAboutus);

		JMenuItem menuItemShujuTubiao = new JMenuItem("数据图表");
		menuItemShujuTubiao.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				/*
				 * int result = JOptionPane.showConfirmDialog(null, "此处应该是一个饼图", "数据图表",
				 * JOptionPane.OK_CANCEL_OPTION); if(result==0) { dispose(); }
				 */
				new PieChart();
			}
		});
		menuItemShujuTubiao.setIcon(new ImageIcon(MainFrame.class.getResource("/images/me.png")));
		menuBanben.add(menuItemShujuTubiao);

		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		/**
		 * 
		 */
		this.setLocationRelativeTo(null);
		// 设置jframe最大化
		this.setExtendedState(JFrame.MAXIMIZED_BOTH); // 将窗口设置为最大化
	}

	// 显示版本信息的事件
	protected void showViewInfoactionPerformed(ActionEvent evt) {
		// TODO Auto-generated method stub
		VersionInfoInnerFrame versionInfoInnerFrame = new VersionInfoInnerFrame();
		versionInfoInnerFrame.setVisible(true);
		contentPane.add(versionInfoInnerFrame);
	}

}

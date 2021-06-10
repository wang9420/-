package com.tom.view;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;

public class VersionInfoInnerFrame extends JInternalFrame {

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VersionInfoInnerFrame frame = new VersionInfoInnerFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public VersionInfoInnerFrame() {
		setIconifiable(true);
		setClosable(true);
		setTitle("关于我们");
		setBounds(100, 100, 450, 300);

		JLabel label = new JLabel("组长：王某");

		JLabel lbljava = new JLabel("信息工程学院移动班java实训项目v1.0");
		lbljava.setFont(new Font("宋体", Font.BOLD, 18));
		lbljava.setForeground(Color.LIGHT_GRAY);

		JLabel label_1 = new JLabel("组员：帅逼王");

		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(VersionInfoInnerFrame.class.getResource("/images/java1234.png")));

		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup().addGap(34)
						.addGroup(groupLayout.createParallelGroup(Alignment.LEADING).addComponent(lblNewLabel)
								.addComponent(label_1).addComponent(label)
								.addComponent(lbljava, GroupLayout.PREFERRED_SIZE, 376, GroupLayout.PREFERRED_SIZE))
						.addContainerGap(24, Short.MAX_VALUE)));
		groupLayout.setVerticalGroup(groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup().addGap(30).addComponent(lbljava).addGap(18)
						.addComponent(label).addGap(18).addComponent(label_1).addGap(31).addComponent(lblNewLabel)
						.addContainerGap(44, Short.MAX_VALUE)));
		getContentPane().setLayout(groupLayout);

	}
}

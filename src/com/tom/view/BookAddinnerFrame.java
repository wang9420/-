package com.tom.view;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.EmptyBorder;

import com.tom.dao.BookDao;
import com.tom.model.Books;
import com.tom.util.DbUtil;
import com.tom.util.StringUtil;

public class BookAddinnerFrame extends JFrame {

	private JPanel contentPane;
	private JTextField txt_bookname;
	private JTextField text_bookAnter;
	private JTextField text_cbs;
	private JTextField text_dj;
	private JTextField text_fxh;
	private JTextField teamp;
	private DbUtil dbUtil = new DbUtil();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					BookAddinnerFrame frame = new BookAddinnerFrame();
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
	public BookAddinnerFrame() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 617, 457);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);

		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.CENTER);

		JLabel lblNewLabel = new JLabel("书名");

		txt_bookname = new JTextField();
		txt_bookname.setColumns(10);

		JLabel lblNewLabel_1 = new JLabel("作者");

		text_bookAnter = new JTextField();
		text_bookAnter.setColumns(10);

		JLabel lblNewLabel_2 = new JLabel("出版社");

		text_cbs = new JTextField();
		text_cbs.setColumns(10);

		JLabel lblNewLabel_3 = new JLabel("单价");

		JLabel lblNewLabel_4 = new JLabel("发行号");

		text_dj = new JTextField();
		text_dj.setColumns(10);

		text_fxh = new JTextField();
		text_fxh.setColumns(10);

		JLabel lblNewLabel_5 = new JLabel("图书类型");

		JComboBox comboBox = new JComboBox();

		JButton btnNewButton = new JButton("添加");

		teamp = new JTextField();
		teamp.setColumns(10);
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(gl_panel.createParallelGroup(Alignment.LEADING).addGroup(gl_panel
				.createSequentialGroup().addGap(56)
				.addGroup(gl_panel.createParallelGroup(Alignment.LEADING).addComponent(lblNewLabel_2)
						.addComponent(lblNewLabel).addComponent(lblNewLabel_1))
				.addPreferredGap(ComponentPlacement.UNRELATED)
				.addGroup(gl_panel.createParallelGroup(Alignment.LEADING).addComponent(btnNewButton).addGroup(gl_panel
						.createSequentialGroup()
						.addGroup(
								gl_panel.createParallelGroup(Alignment.LEADING, false).addComponent(txt_bookname)
										.addComponent(text_bookAnter)
										.addComponent(text_cbs, GroupLayout.DEFAULT_SIZE, 116, Short.MAX_VALUE))
						.addGap(56)
						.addGroup(gl_panel.createParallelGroup(Alignment.LEADING).addGroup(gl_panel
								.createSequentialGroup()
								.addGroup(gl_panel.createParallelGroup(Alignment.LEADING, false)
										.addComponent(lblNewLabel_3).addComponent(lblNewLabel_4,
												GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
								.addGroup(gl_panel.createParallelGroup(Alignment.LEADING, false)
										.addGroup(gl_panel.createSequentialGroup().addGap(16).addComponent(text_dj,
												GroupLayout.PREFERRED_SIZE, 137, GroupLayout.PREFERRED_SIZE))
										.addGroup(gl_panel.createSequentialGroup().addGap(18).addComponent(text_fxh))))
								.addGroup(gl_panel.createSequentialGroup().addComponent(lblNewLabel_5)
										.addPreferredGap(ComponentPlacement.UNRELATED)
										.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, 54,
												GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(ComponentPlacement.UNRELATED).addComponent(teamp,
												GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
												GroupLayout.PREFERRED_SIZE)))))
				.addContainerGap(128, Short.MAX_VALUE)));
		gl_panel.setVerticalGroup(
				gl_panel.createParallelGroup(Alignment.LEADING).addGroup(gl_panel.createSequentialGroup().addGap(71)
						.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE).addComponent(lblNewLabel)
								.addComponent(txt_bookname, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE)
								.addComponent(lblNewLabel_3).addComponent(text_dj, GroupLayout.PREFERRED_SIZE,
										GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addGap(38)
						.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
								.addComponent(text_bookAnter, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE)
								.addComponent(lblNewLabel_1).addComponent(lblNewLabel_4).addComponent(text_fxh,
										GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE))
						.addGap(34)
						.addGroup(
								gl_panel.createParallelGroup(Alignment.LEADING).addComponent(lblNewLabel_2)
										.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
												.addComponent(text_cbs, GroupLayout.PREFERRED_SIZE,
														GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
												.addComponent(lblNewLabel_5)
												.addComponent(comboBox, GroupLayout.PREFERRED_SIZE,
														GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
												.addComponent(teamp, GroupLayout.PREFERRED_SIZE,
														GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
						.addGap(75).addComponent(btnNewButton).addContainerGap(102, Short.MAX_VALUE)));
		panel.setLayout(gl_panel);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AddBookinto(e);
			}
		});
	}

	protected void AddBookinto(ActionEvent e) {
		// TODO Auto-generated method stub
		String bname = this.txt_bookname.getText();
		if (StringUtil.isEmpty(bname)) {
			JOptionPane.showMessageDialog(null, "图书名称不能为空！");
			return;
		}
		String bcbs = this.text_cbs.getText();
		if (StringUtil.isEmpty(bcbs)) {
			JOptionPane.showMessageDialog(null, "出版社不能为空！");
			return;
		}
		String banter = this.text_bookAnter.getText();
		if (StringUtil.isEmpty(banter)) {
			JOptionPane.showMessageDialog(null, "作者不能为空！");
			return;
		}
		String bdj = this.text_dj.getText();
		if (StringUtil.isEmpty(bdj)) {
			JOptionPane.showMessageDialog(null, "单价不能为空！");
			return;
		}
		Float result = Float.valueOf(bdj).floatValue();
		String bfxh = this.text_fxh.getText();
		if (StringUtil.isEmpty(bfxh)) {
			JOptionPane.showMessageDialog(null, "发行号不能为空！");
			return;
		}
		String bt = this.teamp.getText();
		if (StringUtil.isEmpty(bt)) {
			JOptionPane.showMessageDialog(null, "图书内别不能为空！");
			return;
		}
		int y = Integer.parseInt(bt);
		Books book = new Books(bname, bcbs, banter, result, bfxh, y);
		Connection con = null;

		try {
			con = dbUtil.getCon(); // 打开数据库的连接

			// 通过DAO中间的add方法，将图书类别模型中的属性数据写入到bookType数据表中
			int n = BookDao.addbook(con, book);
			if (n == 1) {
				JOptionPane.showMessageDialog(null, "图书类别添加成功！！");
			} else {
				JOptionPane.showMessageDialog(null, "图书类别添加失败！");
			}
		} catch (Exception e1) {
			e1.printStackTrace();
		} finally {
			try {
				dbUtil.closeCon(con);
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}
	}

}

package com.tom.view;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.ResultSet;
import java.util.Vector;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import com.tom.dao.BookDao;
import com.tom.model.Books;
import com.tom.util.DbUtil;
import com.tom.util.StringUtil;

public class BookUse extends JFrame {

	private JPanel contentPane;
	private JTextField text_xg_name;
	private JTextField t_x_cbs;
	private JTextField t_x_zz;
	private JTextField t_x_dj;
	private JTextField t_x_cbh;
	private JTextField t_x_tslb;
	private JTable tables;
	private DbUtil dbUtil = new DbUtil();
	private JTextField t_xh;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					BookUse frame = new BookUse();
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
	public BookUse() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 809, 491);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);

		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.CENTER);

		JButton btn_xg = new JButton("修改");
		btn_xg.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				UpUpdate(e);
			}
		});

		JButton btn_delete = new JButton("删除");
		btn_delete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				deletBook(e);
			}
		});

		JButton btn_ss = new JButton("搜索");
		btn_ss.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				bookSear(e);
			}
		});

		JLabel lblNewLabel_1 = new JLabel("名称");

		text_xg_name = new JTextField();
		text_xg_name.setColumns(10);

		JLabel lblNewLabel_2 = new JLabel("出版社");

		t_x_cbs = new JTextField();
		t_x_cbs.setText("");
		t_x_cbs.setColumns(10);

		JLabel lblNewLabel_3 = new JLabel("作者");

		t_x_zz = new JTextField();
		t_x_zz.setColumns(10);

		JLabel lblNewLabel_4 = new JLabel("单价");

		t_x_dj = new JTextField();
		t_x_dj.setColumns(10);

		JLabel lblNewLabel_5 = new JLabel("出版号");

		t_x_cbh = new JTextField();
		t_x_cbh.setText("");
		t_x_cbh.setColumns(10);

		JLabel lblNewLabel_6 = new JLabel("图书类别");

		t_x_tslb = new JTextField();
		t_x_tslb.setColumns(10);

		JPanel panel_1 = new JPanel();

		JLabel lblNewLabel_8 = new JLabel("图书总览");

		JButton t_x_sx = new JButton("刷新");
		t_x_sx.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				bookshuxing(e);
			}
		});

		JLabel lblNewLabel_7 = new JLabel("序号");

		t_xh = new JTextField();
		t_xh.setEditable(false);
		t_xh.setColumns(10);

		JComboBox comboBox = new JComboBox();

		JLabel lblNewLabel = new JLabel("类别序列号");
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(gl_panel.createParallelGroup(Alignment.TRAILING).addGroup(gl_panel
				.createSequentialGroup()
				.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING).addGroup(gl_panel.createSequentialGroup()
						.addGroup(gl_panel.createParallelGroup(Alignment.LEADING).addGroup(gl_panel
								.createSequentialGroup().addContainerGap()
								.addGroup(gl_panel.createParallelGroup(Alignment.LEADING).addGroup(gl_panel
										.createParallelGroup(Alignment.LEADING, false)
										.addGroup(gl_panel.createSequentialGroup().addComponent(lblNewLabel_5)
												.addPreferredGap(ComponentPlacement.UNRELATED).addComponent(t_x_cbh))
										.addGroup(gl_panel.createSequentialGroup()
												.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
														.addComponent(lblNewLabel_2).addComponent(lblNewLabel_1)
														.addComponent(lblNewLabel_3).addComponent(lblNewLabel_4))
												.addPreferredGap(ComponentPlacement.UNRELATED)
												.addGroup(gl_panel.createParallelGroup(Alignment.LEADING, false)
														.addComponent(t_x_dj).addComponent(t_x_zz)
														.addComponent(text_xg_name, GroupLayout.DEFAULT_SIZE, 104,
																Short.MAX_VALUE)
														.addComponent(t_x_cbs))))
										.addGroup(gl_panel.createSequentialGroup()
												.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
														.addComponent(lblNewLabel_6).addComponent(lblNewLabel_7))
												.addPreferredGap(ComponentPlacement.RELATED)
												.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
														.addComponent(t_xh, GroupLayout.PREFERRED_SIZE, 50,
																GroupLayout.PREFERRED_SIZE)
														.addComponent(t_x_tslb, GroupLayout.PREFERRED_SIZE,
																GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))))
								.addPreferredGap(ComponentPlacement.RELATED, 17, Short.MAX_VALUE))
								.addGroup(gl_panel.createSequentialGroup().addGap(37).addComponent(btn_xg).addGap(26)
										.addComponent(btn_ss)))
						.addGap(18).addComponent(comboBox, GroupLayout.PREFERRED_SIZE, 53, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panel.createSequentialGroup().addContainerGap().addComponent(lblNewLabel)))
				.addPreferredGap(ComponentPlacement.RELATED)
				.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel.createSequentialGroup().addComponent(btn_delete).addGap(96)
								.addComponent(t_x_sx))
						.addGroup(gl_panel.createSequentialGroup().addComponent(lblNewLabel_8)
								.addPreferredGap(ComponentPlacement.RELATED)
								.addComponent(panel_1, GroupLayout.PREFERRED_SIZE, 517, GroupLayout.PREFERRED_SIZE)))
				.addContainerGap()));
		gl_panel.setVerticalGroup(gl_panel.createParallelGroup(Alignment.LEADING).addGroup(gl_panel
				.createSequentialGroup().addGap(39)
				.addGroup(gl_panel.createParallelGroup(Alignment.LEADING).addGroup(gl_panel.createSequentialGroup()
						.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE).addComponent(lblNewLabel_1)
								.addComponent(text_xg_name, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE)
								.addComponent(lblNewLabel_8))
						.addGap(21)
						.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING).addComponent(lblNewLabel_2)
								.addComponent(t_x_cbs, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE))
						.addGap(30)
						.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE).addComponent(lblNewLabel_3)
								.addComponent(t_x_zz, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE))
						.addGap(29)
						.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE).addComponent(lblNewLabel_4)
								.addComponent(t_x_dj, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE))
						.addGap(18)
						.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE).addComponent(lblNewLabel_5)
								.addComponent(t_x_cbh, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE))
						.addGap(18)
						.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE).addComponent(lblNewLabel_6)
								.addComponent(t_x_tslb, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE))
						.addGap(7).addComponent(lblNewLabel).addPreferredGap(ComponentPlacement.RELATED)
						.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
								.addComponent(t_xh, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE)
								.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE)
								.addComponent(lblNewLabel_7)))
						.addComponent(panel_1, GroupLayout.PREFERRED_SIZE, 275, GroupLayout.PREFERRED_SIZE))
				.addGap(38).addGroup(gl_panel.createParallelGroup(Alignment.BASELINE).addComponent(btn_xg)
						.addComponent(btn_delete).addComponent(t_x_sx).addComponent(btn_ss))
				.addContainerGap(49, Short.MAX_VALUE)));
		panel_1.setLayout(new BorderLayout(0, 0));

		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		panel_1.add(scrollPane_1, BorderLayout.CENTER);

		tables = new JTable();
		tables.setFillsViewportHeight(true);
		tables.setToolTipText("");
		tables.setSurrendersFocusOnKeystroke(true);
		tables.setColumnSelectionAllowed(true);
		tables.setCellSelectionEnabled(true);

		tables.setModel(new DefaultTableModel(
				new Object[][] { { null, null, null, null, null, null, null },
						{ null, null, null, null, null, null, null }, { null, null, null, null, null, null, null },
						{ null, null, null, null, null, null, null }, { null, null, null, null, null, null, null },
						{ null, null, null, null, null, null, null }, },
				new String[] { "序号", "名称", "出版社", "作者", "单价（元）", "出版号", "图书类别" }));
		scrollPane_1.setViewportView(tables);
		panel.setLayout(gl_panel);
		tables.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				bookTablpressed(e);
			}
		});
	}

	protected void bookTablpressed(MouseEvent e) {
		// TODO Auto-generated method stub
		int row = tables.getSelectedRow();
		text_xg_name.setText((String) tables.getValueAt(row, 1));
		t_x_cbs.setText((String) tables.getValueAt(row, 2));
		t_x_zz.setText((String) tables.getValueAt(row, 3));
		t_x_dj.setText((String) tables.getValueAt(row, 4));
		t_x_cbh.setText((String) tables.getValueAt(row, 5));
		t_x_tslb.setText((String) tables.getValueAt(row, 6));
		t_xh.setText((String) tables.getValueAt(row, 0));
	}

	protected void bookSear(ActionEvent evt) {
		String s_n = this.text_xg_name.getText();
		String s_cbs = this.t_x_cbs.getText();
		String s_fxh = this.t_x_cbh.getText();
		String s_zz = this.t_x_zz.getText();
		String s_nbh = this.t_x_tslb.getText();
		String s_dj = this.t_x_dj.getText();
		Books book = new Books();
		int c = Integer.parseInt(s_nbh);
		book.setBookAuthor(s_zz);
		book.setBookName(s_n);
		book.setBookTypeId(c);
		book.setBookISBN(s_fxh);
		book.setBookPublisher(s_cbs);
		this.fillTable(book, 0);
	}

	protected void bookshuxing(ActionEvent evt) {
		String s_n = this.text_xg_name.getText();
		Books book = new Books();
		book.setBookName(s_n);
		this.fillTable(book, 1);
	}

	private void fillTable(Books book, int a) {
		DefaultTableModel defaultTableModel = (DefaultTableModel) tables.getModel();
		defaultTableModel.setRowCount(0);
		ResultSet rs;
		java.sql.Connection con = null;
		try {
			con = dbUtil.getCon();
			if (a == 1) {
				rs = BookDao.getBookList(con, book);
			} else {
				rs = BookDao.findBook(con, book);
			}

			while (rs.next()) {
				Vector<String> v = new Vector<String>();
				v.add(rs.getString("bookId"));
				v.add(rs.getString("bookName"));
				v.add(rs.getString("bookPublisher"));
				v.add(rs.getString("bookAuthor"));
				v.add(rs.getString("bookPrice"));
				v.add(rs.getString("bookISBN"));
				v.add(rs.getString("bookTypeId"));
				defaultTableModel.addRow(v);
			}
		} catch (Exception e) {

			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				dbUtil.closeCon(con);
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}

	}

	private void UpUpdate(ActionEvent evt) {
		String name = text_xg_name.getText();
		if (StringUtil.isEmpty(name)) {
			JOptionPane.showMessageDialog(null, "图书名称不能为空！");
			return;
		}
		String bcbs = t_x_cbs.getText();
		if (StringUtil.isEmpty(bcbs)) {
			JOptionPane.showMessageDialog(null, "出版社不能为空！");
			return;
		}
		String ba = t_x_zz.getText();
		if (StringUtil.isEmpty(ba)) {
			JOptionPane.showMessageDialog(null, "作者不能为空！");
			return;
		}
		String bpr = t_x_dj.getText();
		if (StringUtil.isEmpty(bpr)) {
			JOptionPane.showMessageDialog(null, "单价不能为空！");
			return;
		}
		Float a = Float.valueOf(bpr).floatValue();
		String bI = t_x_cbh.getText();
		if (StringUtil.isEmpty(bI)) {
			JOptionPane.showMessageDialog(null, "出版号不能为空！");
			return;
		}
		String bT = t_x_tslb.getText();
		if (StringUtil.isEmpty(bT)) {
			JOptionPane.showMessageDialog(null, "图书类型不能为空！");
			return;
		}
		String xh = t_xh.getText(); // 获取图书序号
		if (StringUtil.isEmpty(xh)) {
			JOptionPane.showMessageDialog(null, "图书类型不能为空！");
			return;
		}
		int c = Integer.parseInt(xh);
		int b = Integer.parseInt(bT);
		Books book = new Books(c, name, bcbs, ba, a, bI, b);
		java.sql.Connection connn = null;
		try {
			connn = dbUtil.getCon();
			int num = BookDao.update(connn, book);
			if (num == 1) {
				JOptionPane.showMessageDialog(null, "图书修改成功");
				resetValue();
				fillTable(new Books(), 1);
			} else {
				JOptionPane.showMessageDialog(null, "修改失败");
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				dbUtil.closeCon(connn);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	private void deletBook(ActionEvent evt) {
		String id = t_xh.getText();
		if (StringUtil.isEmpty(id)) {
			JOptionPane.showMessageDialog(null, "请选择删除内容");
			return;
		}
		java.sql.Connection conn = null;
		try {
			conn = dbUtil.getCon();
			int b = BookDao.delete(conn, id);
			if (b == 1) {
				JOptionPane.showMessageDialog(null, "图书删除成功");
				resetValue();
				fillTable(new Books(), 1);
			} else {
				JOptionPane.showMessageDialog(null, "添加失败");
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				dbUtil.closeCon(conn);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			;
		}
	}

	private void resetValue() {
		// TODO Auto-generated method stub
		t_x_cbh.setText("");
		t_x_cbs.setText("");
		t_x_dj.setText("");
		t_x_tslb.setText("");
		t_x_zz.setText("");
		t_xh.setText("");
		text_xg_name.setText("");

	}
}

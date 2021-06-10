package com.tom.view;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.Vector;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import com.tom.dao.BookTypeDao;
import com.tom.model.BookType;
import com.tom.util.DbUtil;
import com.tom.util.StringUtil;

public class BookTypeManagerinnerFrame extends JInternalFrame {
	private JTextField s_bookTypeTxt; // 定义查询图书名称文本框
	private JTable booktypeTable; // 数据表容器，用于接收查询出来的结果集(select)
	private JTextField idTxt; // 定义图书id显示文本框，只能显示，不能修改
	private JTextField bookTypenameTxt; // 定义图书类别名称显示文本框，可以显示，也可以修改
	private JTextArea booktpyeDescTxt; // 定义图书类别描述显示文本框，可以显示，也可以修改

	private DbUtil dbUtil = new DbUtil(); // 定义了一个数据库连接工具类对象
	private BookTypeDao bookTypeDao = new BookTypeDao(); // 定义了一个图书类别数据访问对象（增删查改）

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					// 定义了一个图书类别管理的界面(view)
					BookTypeManagerinnerFrame frame = new BookTypeManagerinnerFrame();
					frame.setVisible(true); // 显示图书类别管理界面(view)
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public BookTypeManagerinnerFrame() {
		setIconifiable(true); // 可以显示图标
		setClosable(true); // 可以关闭
		setTitle("图书类别管理"); // 设置界面的标题栏
		setBounds(100, 100, 623, 465); // 设置界面的位置与大小

		JLabel lblNewLabel = new JLabel("图书类别："); // 定义了一个标签

		s_bookTypeTxt = new JTextField(); // 对查询图书类别文本框进行初始化
		s_bookTypeTxt.setColumns(10);

		JButton btnNewButton = new JButton("查询"); // 添加一个“查询”按钮，并进行初始化
		// 设置“查询”按钮的侦听器，单击事件
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				bookTypeSearchactionPerformed(evt);
			}
		});
		btnNewButton.setIcon(new ImageIcon(BookTypeManagerinnerFrame.class.getResource("/images/search.png")));

		JScrollPane scrollPane = new JScrollPane();

		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "\u8868\u5355\u64CD\u4F5C", TitledBorder.LEADING, TitledBorder.TOP, null,
				Color.RED));

		GroupLayout groupLayout = new GroupLayout(getContentPane()); // 设置窗体布局，包含panel和scrollPanel
		groupLayout.setHorizontalGroup(groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup().addGap(81)
						.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false).addComponent(scrollPane)
								.addGroup(groupLayout.createSequentialGroup().addComponent(lblNewLabel)
										.addPreferredGap(ComponentPlacement.UNRELATED)
										.addComponent(s_bookTypeTxt, GroupLayout.PREFERRED_SIZE, 248,
												GroupLayout.PREFERRED_SIZE)
										.addGap(29).addComponent(btnNewButton))
								.addComponent(panel, 0, 0, Short.MAX_VALUE))
						.addContainerGap(74, Short.MAX_VALUE)));
		groupLayout.setVerticalGroup(groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup().addGap(32)
						.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE).addComponent(btnNewButton)
								.addComponent(s_bookTypeTxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE)
								.addComponent(lblNewLabel))
						.addGap(18)
						.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 154, GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(panel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addGap(12)));

		JLabel label = new JLabel("图书类别编号："); // 定义了一个标签

		idTxt = new JTextField(); // 定义了一个图书类别编号文本框
		idTxt.setEditable(false); // 设置该文本框不能编辑
		idTxt.setColumns(10); // 设置该文本框初始的宽度

		JLabel label_1 = new JLabel("图书类别："); // 定义了一个图书类别名称标签

		bookTypenameTxt = new JTextField(); // 定义了一个图书类别名称文本框
		bookTypenameTxt.setColumns(10); // 设置该文本框初始宽度

		JLabel label_2 = new JLabel("图书类别备注："); // 定义了一个图书类别描述标签

		booktpyeDescTxt = new JTextArea(); // 定义了一个图书类别描述文本区域

		JButton btnModify = new JButton("修改"); // 定义了一个图书类别修改按钮
		// 设置“图书类别修改”按钮的侦听器，即单击事件
		btnModify.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				bookTypeUpdateactionPerformed(evt);
			}
		});
		btnModify.setIcon(new ImageIcon(BookTypeManagerinnerFrame.class.getResource("/images/edit.png")));

		/**
		 * 在图书类别 管理界面中添加“重置”按钮，及其侦听器
		 */
		JButton btnReset = new JButton("重置"); // 定义“重置”按钮
		// 为“重置”按钮添加图标
		btnReset.setIcon(new ImageIcon(BookTypeManagerinnerFrame.class.getResource("/images/reset.png")));
		// 为“重置”按钮添加侦听器，即单击事件
		btnReset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				bookTypeResetActionPerformed(evt);
			}
		});

		/**
		 * 在图书类别管理界面中添加“删除”按钮，及其侦听器
		 */
		JButton btnDelete = new JButton("删除"); // 定义“删除”按钮
		// 为“删除”按钮添加图标
		btnDelete.setIcon(new ImageIcon(BookTypeManagerinnerFrame.class.getResource("/images/delete.png")));
		// 为“删除”按钮添加侦听器，即单击事件
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				bookTypeDeleteActionPerformed(evt);
			}
		});

		GroupLayout gl_panel = new GroupLayout(panel); // 设置panel布局
		gl_panel.setHorizontalGroup(gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup().addGap(22)
						.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_panel.createSequentialGroup().addComponent(btnModify).addGap(46)
										.addComponent(btnReset).addGap(58).addComponent(btnDelete))
								.addGroup(gl_panel.createSequentialGroup().addComponent(label_2)
										.addPreferredGap(ComponentPlacement.RELATED)
										.addComponent(booktpyeDescTxt, GroupLayout.DEFAULT_SIZE, 258, Short.MAX_VALUE))
								.addGroup(gl_panel.createSequentialGroup().addComponent(label)
										.addPreferredGap(ComponentPlacement.RELATED)
										.addComponent(idTxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
												GroupLayout.PREFERRED_SIZE)
										.addGap(18).addComponent(label_1).addPreferredGap(ComponentPlacement.RELATED)
										.addComponent(bookTypenameTxt, GroupLayout.PREFERRED_SIZE, 156,
												GroupLayout.PREFERRED_SIZE)))
						.addGap(26)));
		gl_panel.setVerticalGroup(
				gl_panel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel.createSequentialGroup().addContainerGap()
								.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE).addComponent(label)
										.addComponent(idTxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
												GroupLayout.PREFERRED_SIZE)
										.addComponent(label_1).addComponent(bookTypenameTxt, GroupLayout.PREFERRED_SIZE,
												GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
								.addPreferredGap(ComponentPlacement.UNRELATED)
								.addGroup(gl_panel.createParallelGroup(Alignment.LEADING).addComponent(label_2)
										.addComponent(booktpyeDescTxt, GroupLayout.PREFERRED_SIZE, 71,
												GroupLayout.PREFERRED_SIZE))
								.addGap(18)
								.addGroup(gl_panel.createParallelGroup(Alignment.LEADING).addComponent(btnModify)
										.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
												.addComponent(btnReset).addComponent(btnDelete)))
								.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));
		panel.setLayout(gl_panel);

		booktypeTable = new JTable(); // table组件初始化
		booktypeTable.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent evt) {
				bookTypeTablemousePressed(evt);
			}
		});
		booktypeTable.setModel(new DefaultTableModel(
				new Object[][] { { null, null, null }, { null, null, null }, { null, null, null }, { null, null, null },
						{ null, null, null }, },
				new String[] { "\u7F16\u53F7", "\u56FE\u4E66\u7C7B\u522B\u540D\u79F0",
						"\u56FE\u4E66\u7C7B\u522B\u5907\u6CE8" }) {
			boolean[] columnEditables = new boolean[] { false, false, false };

			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		booktypeTable.getColumnModel().getColumn(1).setPreferredWidth(98);
		booktypeTable.getColumnModel().getColumn(2).setPreferredWidth(207);
		scrollPane.setViewportView(booktypeTable);
		getContentPane().setLayout(groupLayout);
		// 设置文本框边框线
		this.booktpyeDescTxt.setBorder(new LineBorder(new java.awt.Color(127, 157, 185), 1, false));

		this.fillTable(new BookType());

	}

	// 修改图书类别数据的事件处理（单击事件）
	protected void bookTypeUpdateactionPerformed(ActionEvent evt) {
		String id = idTxt.getText(); // 读取图书类别编号的数据内容
		String bookTypename = bookTypenameTxt.getText(); // 读取图书类别名称的数据内容
		String booktpyeDesc = booktpyeDescTxt.getText(); // 读取图书类别描述的数据内容

		// 如果没有选择任一条图书类别记录，直接单击“修改”按钮，其实是一种出错处理
		if (StringUtil.isEmpty(id)) {
			JOptionPane.showMessageDialog(null, "请选择需要修改的记录！");
			return;
		}

		// 如果图书类别显示文本框内容为空，则提示错误，因为图书类别名称不能为空
		if (StringUtil.isEmpty(bookTypename)) {
			JOptionPane.showMessageDialog(null, "图书类型为空不能修改！");
			return;
		}

		// 定义一个图书类别模型对象，并进行初始化(修改后的图书类别记录)
		// 使用一个带id字段的构造函数进行初始化
		BookType bookType = new BookType(Integer.parseInt(id), bookTypename, booktpyeDesc);
		Connection conn = null;
		try {
			conn = dbUtil.getCon();
			// update方法的返回值为更新记录的数目
			int modifyNum = bookTypeDao.update(conn, bookType);
			if (modifyNum == 1) {
				JOptionPane.showMessageDialog(null, "图书类型修改成功！");
				resetValue();
				fillTable(new BookType());
			} else { // 更新的记录数不等于1，这里一般指0
				JOptionPane.showMessageDialog(null, "图书类型修改失败！");
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
		}

	}

	// “重置”按钮的单击事件
	private void bookTypeResetActionPerformed(ActionEvent evt) {
		resetValue();
	}

	// 重置表格方法
	public void resetValue() {
		idTxt.setText("");
		bookTypenameTxt.setText("");
		booktpyeDescTxt.setText("");
	}

	// “删除”按钮的单击事件
	private void bookTypeDeleteActionPerformed(ActionEvent evt) {
		String id = idTxt.getText(); // 读取图书类别编号的数据内容

		// 如果没有选择任一条图书类别记录，直接单击“删除”按钮
		if (StringUtil.isEmpty(id)) {
			JOptionPane.showMessageDialog(null, "请选择需要删除的记录！");
			return;
		}

		Connection conn = null;
		try {
			conn = dbUtil.getCon();
			// delete方法的返回值为删除记录的数目
			int delNum = bookTypeDao.delete(conn, id); // 保存返回的删除记录数目
			if (delNum == 1) { // 按要求删除1条记录，成功
				JOptionPane.showMessageDialog(null, "图书类别删除成功！"); // 对话框提示成功信息
				resetValue(); // 重置图书类别相关的三个文本框
				fillTable(new BookType()); // 重新填充数据表网格
			} else {
				// 更新的记录数不等于1，这里一般指0
				JOptionPane.showMessageDialog(null, "图书类型删除失败！"); // 对话框提示错误信息
			}
		} catch (Exception e) {
			e.printStackTrace(); // 打印数据库操作异常信息
		} finally {
			try {
				dbUtil.closeCon(conn); // 关闭数据库的连接
			} catch (Exception e) {
				e.printStackTrace(); // 打印关闭数据连接的异常
			}
		}
	}

	// 表格行点击事件处理
	private void bookTypeTablemousePressed(MouseEvent evt) {
		// TODO Auto-generated method stub
		int row = booktypeTable.getSelectedRow();
		idTxt.setText((String) booktypeTable.getValueAt(row, 0));
		bookTypenameTxt.setText((String) booktypeTable.getValueAt(row, 1));
		booktpyeDescTxt.setText((String) booktypeTable.getValueAt(row, 2));
	}

	// 图书类别查询事件处理方法
	protected void bookTypeSearchactionPerformed(ActionEvent evt) {
		// TODO Auto-generated method stub
		String s_booktypename = this.s_bookTypeTxt.getText(); // 读取查询图书类别文本框的内容，并存储到一个局部变量中
		BookType bookType = new BookType(); // 定义了一个图书类别的模型对象
		bookType.setBookTypeName(s_booktypename); // 设置该模型对象的图书类别名称属性
		this.fillTable(bookType); // 将筛选后的数据填充到界面中的数据表格容器中
	}

	// 表格初始化数据的代码
	private void fillTable(BookType bookType) {
		DefaultTableModel defaultTableModel = (DefaultTableModel) booktypeTable.getModel(); // 字段数量，字段类型，字段宽度
		defaultTableModel.setRowCount(0); // 初始行数设置为0
		Connection conn = null;
		try {
			conn = dbUtil.getCon(); // 打开连接，连接到db_book数据库
			ResultSet rs = bookTypeDao.getBookTypeList(conn, bookType); // 查询方法，返回一个结果集(数据表)
			while (rs.next()) { // 通过结果集的next方法遍历(扫描)结果集的每一行(每一条记录)
				Vector<String> v = new Vector<String>(); // 声明一条记录
				v.add(rs.getString("id")); // 读取结果集当前记录的id属性(字段)值
				v.add(rs.getString("bookTypeName")); // 读取结果集当前记录的bookTypeName属性(字段)值
				v.add(rs.getString("bookTypeDesc")); // 读取结果集当前记录的bookTypeDesc属性(字段)值
				defaultTableModel.addRow(v); // 将该条记录写入数据模型中，数据集
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
		}
		// EOF: END OF FILE
	}
}

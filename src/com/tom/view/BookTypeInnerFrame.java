package com.tom.view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;

import com.tom.dao.BookTypeDao;
import com.tom.model.BookType;
import com.tom.util.DbUtil;
import com.tom.util.StringUtil;

import javax.swing.JTextArea;
import javax.swing.JButton;
import javax.swing.ImageIcon;

import java.awt.event.ActionListener;
import java.sql.Connection;
import java.awt.event.ActionEvent;

public class BookTypeInnerFrame extends JFrame {
	private JTextField bookTypeTxt;			// 声明了一个文本框，用于描述图书类别名称
    private JTextArea booktypedescAreaTxt;	// 声明了一个文本区域，用于存储图书类别描述
    
    private DbUtil dbUtil=new DbUtil();
    private BookTypeDao bookTypeDao=new BookTypeDao();
   
    
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					BookTypeInnerFrame frame = new BookTypeInnerFrame();
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
	public BookTypeInnerFrame() {
		//setMaximizable(true);
		//setIconifiable(true);
		//setClosable(true);
		setTitle("图书类别添加");
		setResizable(true);
		setBounds(100, 100, 450, 300);
		JLabel label = new JLabel("图书类别：");
		label.setIcon(new ImageIcon(BookTypeInnerFrame.class.getResource("/images/bookTypeManager.png")));
		
		JLabel label_1 = new JLabel("图书备注：");
		label_1.setIcon(new ImageIcon(BookTypeInnerFrame.class.getResource("/images/me.png")));
		
		bookTypeTxt = new JTextField();			// 定义了一个图书类别名称输入文本框
		bookTypeTxt.setColumns(10);
		
		booktypedescAreaTxt = new JTextArea();	// 定义了一个图书类别描述文本区域
		
		JButton btnNewButton = new JButton("添加");		// 定义了一个“添加”按钮
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				bookTypeAddActionPerformed(evt);
			}
		});
		btnNewButton.setIcon(new ImageIcon(BookTypeInnerFrame.class.getResource("/images/add.png")));
		
		JButton btnNewButton_1 = new JButton("重置");	// 定义了一个“重置”按钮
		btnNewButton_1.addActionListener(new ActionListener() {		// 定义了“重置”按钮侦听器
			public void actionPerformed(ActionEvent evt) {
				resetValueactionPerformed(evt);
			}
		});
		btnNewButton_1.setIcon(new ImageIcon(BookTypeInnerFrame.class.getResource("/images/reset.png")));
		
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		
		/*
		groupLayout.setHorizontalGroup(
				groupLayout.createParallelGroup(Alignment.LEADING)
					.addGroup(groupLayout.createSequentialGroup()
						.addGap(50)
						.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
							.addGroup(groupLayout.createSequentialGroup()
								.addComponent(label)
								.addComponent(bookTypeTxt))
							.addGroup(groupLayout.createSequentialGroup()
								.addComponent(label_1)
								.addComponent(booktypedescAreaTxt))
							.addGroup(groupLayout.createSequentialGroup()
								.addGap(80)
								.addComponent(btnNewButton)
								.addComponent(btnNewButton_1))))
			);
					
			groupLayout.setVerticalGroup(
				groupLayout.createParallelGroup(Alignment.LEADING)
					.addGroup(groupLayout.createSequentialGroup()
						.addGap(50)
						.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
							.addComponent(label)
							.addComponent(bookTypeTxt))
						.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
							.addComponent(label_1)
							.addComponent(booktypedescAreaTxt))
						.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
							.addComponent(btnNewButton)
							.addComponent(btnNewButton_1)))		
			);
		*/
			
		groupLayout.setHorizontalGroup(
				groupLayout.createParallelGroup(Alignment.LEADING)
					.addGroup(groupLayout.createSequentialGroup()
						.addGap(60)
						.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
							.addGroup(groupLayout.createSequentialGroup()
								.addGap(200)
								.addComponent(btnNewButton_1)
								.addContainerGap())
							.addGroup(groupLayout.createSequentialGroup()
								.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
									.addGroup(groupLayout.createSequentialGroup()
										.addComponent(label)
										.addPreferredGap(ComponentPlacement.RELATED)
										.addComponent(bookTypeTxt, GroupLayout.PREFERRED_SIZE, 223, GroupLayout.PREFERRED_SIZE))
									.addGroup(groupLayout.createSequentialGroup()
										.addComponent(label_1)
										.addPreferredGap(ComponentPlacement.RELATED)
										.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
											.addComponent(booktypedescAreaTxt)
											.addComponent(btnNewButton))))
								.addContainerGap(93, Short.MAX_VALUE))))
		);
			
		groupLayout.setVerticalGroup(
				groupLayout.createParallelGroup(Alignment.LEADING)
					.addGroup(groupLayout.createSequentialGroup()
						.addGap(50)
						.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
							.addComponent(label)
							.addComponent(bookTypeTxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addGap(30)
						.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
							.addComponent(label_1)
							.addComponent(booktypedescAreaTxt, GroupLayout.PREFERRED_SIZE, 70, GroupLayout.PREFERRED_SIZE))
						.addPreferredGap(ComponentPlacement.RELATED, 30, Short.MAX_VALUE)
						.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
							.addComponent(btnNewButton_1)
							.addComponent(btnNewButton))
						.addGap(43))
		);
		
		getContentPane().setLayout(groupLayout);

	}
	
	// “添加”按钮单击事件
    private void bookTypeAddActionPerformed(ActionEvent evt) {
		
    	String booktypename=this.bookTypeTxt.getText();			// 读取“图书类别”文本框内容
    	String booktypedesc=this.booktypedescAreaTxt.getText();	// 读取“图书类别描述”文本区域内容
    	
    	//判断图书类别不能为空
    	if (StringUtil.isEmpty(booktypename)) {
			JOptionPane.showMessageDialog(null, "图书类别不能为空！");
			return;
		}
    	
    	// 声明了一个图书类别对象，并调用不包含id属性的构造函数进行初始化
    	// 两个参数的值分别是：图书类别名称文本框与图书类别描述文本区域的内容
    	// 通过构造函数就完成了：两个文本输入框与图书类别属性的绑定
    	BookType bookType=new BookType(booktypename, booktypedesc);		
    	Connection con=null;
    	
    	try {
			con=dbUtil.getCon();					// 打开数据库的连接
			
			// 通过DAO中间的add方法，将图书类别模型中的属性数据写入到bookType数据表中
			int n=bookTypeDao.add(con, bookType);	
			if (n==1) {
				JOptionPane.showMessageDialog(null, "图书类别添加成功！！");
				resetValue();
			}else {
				JOptionPane.showMessageDialog(null, "图书类别添加失败！");
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				dbUtil.closeCon(con);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	//重置事件处理，即重置按钮的单击事件
	private  void resetValueactionPerformed(ActionEvent evt) {
		resetValue();
	}
	
	private void resetValue() {
		this.bookTypeTxt.setText("");
		this.booktypedescAreaTxt.setText("");
	}
}

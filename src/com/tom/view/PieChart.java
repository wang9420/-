package com.tom.view;

import java.awt.BorderLayout;
import java.sql.ResultSet;

import javax.swing.JFrame;
import javax.swing.JPanel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.general.DefaultPieDataset;

import com.tom.dao.BookTypeDao;
import com.tom.util.DbUtil;

public class PieChart extends JFrame {
	// constructor
	public PieChart() {
		// 构造DataSet
		DbUtil dbUtil = new DbUtil();
		java.sql.Connection con = null;
		DefaultPieDataset DataSet = new DefaultPieDataset();
		try {
			con = dbUtil.getCon();
			ResultSet rs = BookTypeDao.getBookCount(con);
			while (rs.next()) {
				String a = rs.getString("booktypename");
				int b = rs.getInt("num");
				DataSet.setValue(a, b);
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				dbUtil.closeCon(con);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		// 创建饼图
		JFreeChart chart = ChartFactory.createPieChart3D("信息工程分院图书类别比例表", DataSet, true, true, false);
		// 用来放置图表
		ChartPanel panel = new ChartPanel(chart);
		JPanel jp = new JPanel();
		jp.add(panel, BorderLayout.CENTER);
		this.add(jp);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setBounds(100, 100, 700, 500);
		this.setVisible(true);
	}

	public static void main(String[] args) {
		new PieChart();
	}

}

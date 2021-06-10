package com.tom.view;


import java.awt.BorderLayout;

import org.jfree.chart.ChartFactory;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.ChartPanel;
import javax.swing.*;

public class FreeChart extends JFrame {
	// constructor
	public FreeChart() {
		// 构造DataSet
		DefaultCategoryDataset DataSet = new DefaultCategoryDataset();
		DataSet.addValue(300, "number", "计算机类");
		DataSet.addValue(400, "number", "文学类");
		DataSet.addValue(50, "number", "土木工程类");
		DataSet.addValue(330, "number", "机械制造类");
		DataSet.addValue(420, "number", "音乐类");
		// 创建柱形图
		JFreeChart chart = ChartFactory.createBarChart3D("图书分类示意图", "图书类别",
				"数量", DataSet, PlotOrientation.VERTICAL, false, false, false);
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
		new FreeChart();
	}
}

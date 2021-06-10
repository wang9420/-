package com.tom.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.tom.model.Books;
import com.tom.util.StringUtil;

public class BookDao {
	public static int addbook(Connection con, Books book) throws Exception {
		String sql = "insert into book values(null,?,?,?,?,?,?)";
		PreparedStatement psmt = con.prepareStatement(sql);
		psmt.setString(1, book.getBookName());
		psmt.setString(2, book.getBookPublisher());
		psmt.setString(3, book.getBookAuthor());
		psmt.setFloat(4, book.getBookPrice());
		psmt.setString(5, book.getBookISBN());
		psmt.setInt(6, book.getBookTypeId());
		int a = psmt.executeUpdate();
		return a;
	}

	public static ResultSet findBook(Connection con, Books book) throws Exception {
		String str = String.valueOf(book.getBookTypeId());
		StringBuffer sql = new StringBuffer("select * from book where  ");
		if (StringUtil.isNotEmpty(book.getBookName())) {
			sql.append("bookName like '%" + book.getBookName() + "%' and ");
		}
		if (StringUtil.isNotEmpty(book.getBookPublisher())) {
			sql.append(" bookPublisher like '%" + book.getBookPublisher() + "%' and ");
		}
		if (StringUtil.isNotEmpty(book.getBookAuthor())) {
			sql.append(" bookAuthor like '%" + book.getBookAuthor() + "%' and ");
		}
		if (StringUtil.isNotEmpty(book.getBookISBN())) {
			sql.append(" bookISBN like '%" + book.getBookISBN() + "%' and ");
		}
		if (StringUtil.isNotEmpty(str)) {
			sql.append(" bookTypeid like '%%'");
		}
		PreparedStatement psmt = con.prepareStatement(sql.toString());
		return psmt.executeQuery();
	}

	public static ResultSet getBookList(Connection conn, Books book) throws Exception { // 添加图书到表格
		String sql = "select * from book ;";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		return pstmt.executeQuery();
	}

	public static int update(Connection con, Books book) throws SQLException {
		String sql = "update book set bookName = ?,bookPublisher =?,bookAuthor=?,bookPrice=?,bookISBN=?,bookTypeId=? where bookId=?";
		PreparedStatement psmt = con.prepareStatement(sql);
		psmt.setString(1, book.getBookName());
		psmt.setString(2, book.getBookPublisher());
		psmt.setString(3, book.getBookAuthor());
		psmt.setFloat(4, book.getBookPrice());
		psmt.setString(5, book.getBookISBN());
		psmt.setInt(6, book.getBookTypeId());
		psmt.setInt(7, book.getBookId());
		int a = psmt.executeUpdate();
		return a;
	}

	public static int delete(Connection con, String id) throws Exception {
		String sql = "delete from book where bookId=?";
		PreparedStatement psmt = con.prepareStatement(sql);
		psmt.setString(1, id);
		int a = psmt.executeUpdate();
		return a;

	}
}

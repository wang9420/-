package com.tom.model;

public class Books {

	private Integer bookId;			// 图书序号，自增
	private String bookName;		// 图书名称
	private String bookPublisher;	// 图书出版社
	private String bookAuthor;		// 图书作者
	private Float bookPrice;		// 图书单价
	private String bookISBN;		// 图书发行号
	private Integer bookTypeId;		// 图书类别编号，这里必须设置外键约束，参照bookType数据表的id字段
	
	// 构造函数，忽略自增字段id 
	public Books(String bookName, String bookPublisher, String bookAuthor, 
			    Float bookPrice, String bookISBN, Integer bookTypeId) {
		super();
		this.bookName = bookName;
		this.bookPublisher = bookPublisher;
		this.bookAuthor = bookAuthor;
		this.bookPrice = bookPrice;
		this.bookISBN = bookISBN;
		this.bookTypeId = bookTypeId;
	}

	// 构造函数，包括自增字段id
	public Books(Integer bookId, String bookName, String bookPublisher, String bookAuthor, 
			    Float bookPrice, String bookISBN, Integer bookTypeId) {
		super();
		this.bookId = bookId;
		this.bookName = bookName;
		this.bookPublisher = bookPublisher;
		this.bookAuthor = bookAuthor;
		this.bookPrice = bookPrice;
		this.bookISBN = bookISBN;
		this.bookTypeId = bookTypeId;
	}
	
	// 没有参数的构造函数
	public Books() {
		super();
	}
	
	// 读取和设置图书的id属性
	public Integer getBookId() {
		return bookId;
	}
	public void setBookId(Integer bookId) {
		this.bookId = bookId;
	}
	
	// 读取和设置图书的bookName属性
	public String getBookName() {
		return bookName;
	}
	public void setBookName(String bookName) {
		this.bookName = bookName;
	}
	
	// 读取和设置图书的出版社属性
	public String getBookPublisher() {
		return bookPublisher;
	}
	public void setBookPublisher(String bookPublisher) {
		this.bookPublisher = bookPublisher;
	}
	
	// 读取和设置图书的作者属性
	public String getBookAuthor() {
		return bookAuthor;
	}
	public void setBookAuthor(String bookAuthor) {
		this.bookAuthor = bookAuthor;
	}
	
	// 读取和设置图书的单价属性
	public Float getBookPrice() {
		return bookPrice;
	}
	public void setBookPrice(Float bookPrice) {
		this.bookPrice = bookPrice;
	}
	
	// 读取和设置图书的出版发行号属性
	public String getBookISBN() {
		return bookISBN;
	}
	public void setBookISBN(String bookISBN) {
		this.bookISBN = bookISBN;
	}
	
	// 读取和设置图书类别编号属性
	public Integer getBookTypeId() {
		return bookTypeId;
	}
	public void setBookTypeId(Integer bookTypeId) {
		this.bookTypeId = bookTypeId;
	}
}

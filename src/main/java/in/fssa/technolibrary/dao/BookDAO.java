package in.fssa.technolibrary.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.HashSet;
import java.util.Set;

import in.fssa.technolibrary.exception.PersistanceException;
import in.fssa.technolibrary.exception.ValidationException;
import in.fssa.technolibrary.model.Book;
import in.fssa.technolibrary.util.ConnectionUtil;
import in.fssa.technolibrary.util.Logger;

public class BookDAO {

	// Create Book
	/**
	 * 
	 * @param newBook
	 * @throws PersistanceException
	 */
	public void create(Book newBook) throws PersistanceException {
		Connection con = null;
		PreparedStatement ps = null;
		try {
			String query = "INSERT INTO books ( title, author, publisher_id, category_id,published_date,price) VALUES (?,?,?,?,?,?)";
			con = ConnectionUtil.getConnection();
			ps = con.prepareStatement(query);
			ps.setString(1, newBook.getTitle());
			ps.setString(2, newBook.getAuthor());
			ps.setInt(3, newBook.getPublisherId());
			ps.setInt(4, newBook.getCategoryId());
			java.sql.Date date = java.sql.Date.valueOf(newBook.getPublishedDate());
			ps.setDate(5, date);
			ps.setInt(6, newBook.getPrice());
			ps.executeUpdate();

			Logger.info("Book has been Added successfully");

		} catch (SQLException e) {
			Logger.error(e);
			throw new PersistanceException(e);
		} finally {
			ConnectionUtil.close(con, ps);
		}
	}

	/**
	 * 
	 * @return
	 * @throws RuntimeException
	 */
	public Set<Book> findAll() throws PersistanceException {

		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		Set<Book> bookList = new HashSet<>();

		try {
			String query = "SELECT id, published_date, title, author, publisher_id, category_id, price, is_active FROM books WHERE is_active=1";
			con = ConnectionUtil.getConnection();
			ps = con.prepareStatement(query);
			rs = ps.executeQuery();
			
			while (rs.next()) {
				Book book = new Book();
				book.setId(rs.getInt("id"));
				SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
				String dueDateStr = dateFormat.format(rs.getTimestamp("published_date"));
				book.setPublishedDate(dueDateStr);
				book.setTitle(rs.getString("title"));
				book.setAuthor(rs.getString("author"));
				book.setPublisherId(rs.getInt("publisher_id"));
				book.setCategoryId(rs.getInt("category_id"));
				book.setPrice(rs.getInt("price"));
				book.setActive(rs.getBoolean("is_active"));
				bookList.add(book);
			}

		} catch (SQLException e) {
			Logger.error(e);
			throw new PersistanceException(e);
		} finally {
			ConnectionUtil.close(con, ps, rs);
		}
		return bookList;
	}

	/**
	 * 
	 * @param id
	 * @return
	 * @throws RuntimeException
	 */
	public Book findById(int bookId) throws PersistanceException {

		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		Book book = null;

		try {
			String query = "SELECT id, published_date, title, author, publisher_id, category_id, price, is_active FROM books WHERE id = ?";
			con = ConnectionUtil.getConnection();
			ps = con.prepareStatement(query);
			ps.setInt(1, bookId);
			rs = ps.executeQuery();
			if (rs.next()) {
				book = new Book();
				book.setId(rs.getInt("id"));
				SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
				String dueDateStr = dateFormat.format(rs.getTimestamp("published_date"));
				book.setPublishedDate(dueDateStr);
				book.setTitle(rs.getString("title"));
				book.setAuthor(rs.getString("author"));
				book.setPublisherId(rs.getInt("publisher_id"));
				book.setCategoryId(rs.getInt("category_id"));
				book.setPrice(rs.getInt("price"));
				book.setActive(rs.getBoolean("is_active"));
			}

		} catch (SQLException e) {
			Logger.error(e);
			throw new PersistanceException(e);
		} finally {
			ConnectionUtil.close(con, ps, rs);
		}
		return book;
	}

	/**
	 * 
	 * @param author
	 * @return
	 * @throws RuntimeException
	 */
	public Set<Book> findByAuthor(String authorName) throws PersistanceException {

		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		Book book = null;

		Set<Book> bookList = new HashSet<>();

		try {
			String query = "SELECT id, published_date, title, author, publisher_id, category_id, price, is_active FROM books WHERE author = ?";
			con = ConnectionUtil.getConnection();
			ps = con.prepareStatement(query);
			ps.setString(1, authorName);
			rs = ps.executeQuery();
			while (rs.next()) {
				book = new Book();
				book.setId(rs.getInt("id"));
				SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
				String dueDateStr = dateFormat.format(rs.getTimestamp("published_date"));
				book.setPublishedDate(dueDateStr);
				book.setTitle(rs.getString("title"));
				book.setAuthor(rs.getString("author"));
				book.setPublisherId(rs.getInt("publisher_id"));
				book.setCategoryId(rs.getInt("category_id"));
				book.setPrice(rs.getInt("price"));
				book.setActive(rs.getBoolean("is_active"));
				bookList.add(book);
			}

		} catch (SQLException e) {
			Logger.error(e);
			throw new PersistanceException(e);
		} finally {
			ConnectionUtil.close(con, ps, rs);
		}
		return bookList;
	}

	/**
	 * 
	 * @param category_id
	 * @return
	 * @throws RuntimeException
	 */
	public Set<Book> findByCategoryId(int categoryId) throws PersistanceException {

		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		Book book = null;

		Set<Book> bookList = new HashSet<>();

		try {
			String query = "SELECT id, published_date, title, author, publisher_id, category_id, price, is_active FROM books WHERE category_id = ?";
			con = ConnectionUtil.getConnection();
			ps = con.prepareStatement(query);
			ps.setInt(1, categoryId);
			rs = ps.executeQuery();

			while (rs.next()) {
				book = new Book();
				book.setId(rs.getInt("id"));
				SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
				String dueDateStr = dateFormat.format(rs.getTimestamp("published_date"));
				book.setPublishedDate(dueDateStr);
				book.setTitle(rs.getString("title"));
				book.setAuthor(rs.getString("author"));
				book.setPublisherId(rs.getInt("publisher_id"));
				book.setCategoryId(rs.getInt("category_id"));
				book.setPrice(rs.getInt("price"));
				bookList.add(book);
			}

		} catch (SQLException e) {
			Logger.error(e);
			throw new PersistanceException(e);
		} finally {
			ConnectionUtil.close(con, ps, rs);
		}
		return bookList;
	}

	/**
	 * 
	 * @param publisher_id
	 * @return
	 * @throws RuntimeException
	 */
	public Set<Book> findByPublisherId(int publisherId) throws PersistanceException {

		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		Book book = null;

		Set<Book> bookList = new HashSet<>();

		try {
			String query = "SELECT id, published_date, title, author, publisher_id, category_id, price, is_active FROM books WHERE publisher_id = ?";
			con = ConnectionUtil.getConnection();
			ps = con.prepareStatement(query);
			ps.setInt(1, publisherId);
			rs = ps.executeQuery();

			while (rs.next()) {
				book = new Book();
				book.setId(rs.getInt("id"));
				SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
				String dueDateStr = dateFormat.format(rs.getTimestamp("published_date"));
				book.setPublishedDate(dueDateStr);
				book.setTitle(rs.getString("title"));
				book.setAuthor(rs.getString("author"));
				book.setPublisherId(rs.getInt("publisher_id"));
				book.setCategoryId(rs.getInt("category_id"));
				book.setPrice(rs.getInt("price"));
				book.setActive(rs.getBoolean("is_active"));
				bookList.add(book);
			}

		} catch (SQLException e) {
			Logger.error(e);
			throw new PersistanceException(e);
		} finally {
			ConnectionUtil.close(con, ps, rs);
		}
		return bookList;
	}

	/**
	 * 
	 * @param id
	 */
	public void delete(int bookId) throws PersistanceException {
		Connection con = null;
		PreparedStatement ps = null;
		try {
			String query = "UPDATE books SET is_active = 0 WHERE is_active = 1 AND id = ?";

			con = ConnectionUtil.getConnection();
			ps = con.prepareStatement(query);
			ps.setInt(1, bookId);
			ps.executeUpdate();
			Logger.info("Book has been successfullly deleted.");
		} catch (SQLException e) {
			Logger.error(e);
			throw new PersistanceException(e);
		} finally {
			ConnectionUtil.close(con, ps);
		}
	}

	/**
	 * 
	 * @param id
	 * @param bookUpdate
	 * @throws PersistanceException
	 */
	public void updateBook(int id, Book bookUpdate) throws PersistanceException {

		Connection con = null;
		PreparedStatement ps = null;
		try {
			String query = "UPDATE books SET title =?, author =?, publisher_id =?, category_id = ?, published_date = ?, price = ?  WHERE is_active = 1 AND id =?";

			con = ConnectionUtil.getConnection();
			ps = con.prepareStatement(query);
			ps.setString(1, bookUpdate.getTitle());
			ps.setString(2, bookUpdate.getAuthor());
			ps.setInt(3, bookUpdate.getPublisherId());
			ps.setInt(4, bookUpdate.getCategoryId());
			ps.setString(5, bookUpdate.getPublishedDate());
			ps.setInt(6, bookUpdate.getPrice());

			ps.setInt(7, id);

			ps.executeUpdate();
			Logger.info("Book has been successfullly updated.");
		} catch (SQLException e) {
			e.printStackTrace();
			Logger.error(e);
			throw new PersistanceException(e);
		} finally {
			ConnectionUtil.close(con, ps);
		}
	}

	/**
	 * 
	 * @param id
	 * @return
	 * @throws PersistanceException
	 * @throws ValidationException
	 */
	public static void bookIdAlreadyExistOrNot(int bookId) throws PersistanceException, ValidationException {
		Connection conn = null;
		PreparedStatement pre = null;
		ResultSet rs = null;

		try {
			String query = "SELECT id FROM books WHERE id = ?";
			conn = ConnectionUtil.getConnection();
			pre = conn.prepareStatement(query);
			pre.setInt(1, bookId);
			rs = pre.executeQuery();

			if (!rs.next()) {
				throw new ValidationException("Book doesn't exist");
			}
		} catch (SQLException e) {
			Logger.error(e);
			throw new PersistanceException(e);
		} finally {
			ConnectionUtil.close(conn, pre, rs);
		}
	}

	/**
	 * 
	 * @param bookName
	 * @throws PersistanceException
	 * @throws ValidationException
	 */
	public static void bookNameAlreadyExist(String bookName) throws PersistanceException, ValidationException {
		Connection conn = null;
		PreparedStatement pre = null;
		ResultSet rs = null;
		try {
			String query = "SELECT * FROM books WHERE title = ?";
			conn = ConnectionUtil.getConnection();
			pre = conn.prepareStatement(query);
			pre.setString(1, bookName);
			rs = pre.executeQuery();

			if (rs.next()) {
				throw new ValidationException("Book name already exist");
			}
		} catch (SQLException e) {
			Logger.error(e);
			throw new PersistanceException(e);
		} finally {
			ConnectionUtil.close(conn, pre, rs);
		}
	}

	/**
	 * 
	 * @param authorName
	 * @throws PersistanceException
	 * @throws ValidationException
	 */
	public static void authorAlreadyExistOrNot(String authorName) throws PersistanceException, ValidationException {
		Connection conn = null;
		PreparedStatement pre = null;
		ResultSet rs = null;

		try {
			String query = "SELECT author FROM books WHERE author = ?";
			conn = ConnectionUtil.getConnection();
			pre = conn.prepareStatement(query);
			pre.setString(1, authorName);
			rs = pre.executeQuery();

			if (!rs.next()) {
				throw new ValidationException("Author doesn't exist.");
			}
		} catch (SQLException e) {
			Logger.error(e);
			throw new PersistanceException(e);
		} finally {
			ConnectionUtil.close(conn, pre, rs);
		}
	}

}

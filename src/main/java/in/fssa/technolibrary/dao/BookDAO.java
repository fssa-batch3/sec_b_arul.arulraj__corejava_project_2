package in.fssa.technolibrary.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.HashSet;
import java.util.Set;

import in.fssa.technolibrary.exception.PersistanceException;
import in.fssa.technolibrary.model.Book;
import in.fssa.technolibrary.util.ConnectionUtil;

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
			String query = "INSERT INTO book ( title, author, publisher_id, category_id,published_date,price) VALUES (?,?,?,?,?,?)";
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

			System.out.print("Book has been Added successfully");

		} catch (SQLException e) {
			e.printStackTrace();
			throw new PersistanceException(e.getMessage());
		} finally {
			ConnectionUtil.close(con, ps);
		}
	}
	/**
	 * 
	 * @return
	 * @throws RuntimeException
	 */
	public Set<Book> findAll() throws RuntimeException {

		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		Set<Book> bookList = new HashSet<>();

		try {
			String query = "SELECT * FROM book";
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
			System.out.print(e.getMessage());
			throw new RuntimeException();
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
	public Book findById(int id) throws RuntimeException {

		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		Book book = null;

		try {
			String query = "SELECT * FROM book WHERE id = ?";
			con = ConnectionUtil.getConnection();
			ps = con.prepareStatement(query);
			ps.setInt(1, id);
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
			System.out.print(e.getMessage());
			throw new RuntimeException();
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
	public Set<Book> findByAuthor(String author) throws RuntimeException {

		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		Book book = null;

		Set<Book> bookList = new HashSet<>();

		try {
			String query = "SELECT * FROM book WHERE author = ?";
			con = ConnectionUtil.getConnection();
			ps = con.prepareStatement(query);
			ps.setString(1, author);
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
			System.out.print(e.getMessage());
			throw new RuntimeException();
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
	public Set<Book> findByCtegoryId(int category_id) throws RuntimeException {

		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		Book book = null;

		Set<Book> bookList = new HashSet<>();

		try {
			String query = "SELECT * FROM book WHERE category_id = ?";
			con = ConnectionUtil.getConnection();
			ps = con.prepareStatement(query);
			ps.setInt(1, category_id);
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
			System.out.print(e.getMessage());
			throw new RuntimeException();
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
	public Set<Book> findByPublisherId(int publisher_id) throws RuntimeException {

		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		Book book = null;

		Set<Book> bookList = new HashSet<>();

		try {
			String query = "SELECT * FROM book WHERE publisher_id = ?";
			con = ConnectionUtil.getConnection();
			ps = con.prepareStatement(query);
			ps.setInt(1, publisher_id);
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
			System.out.print(e.getMessage());
			throw new RuntimeException();
		} finally {
			ConnectionUtil.close(con, ps, rs);
		}
		return bookList;
	}
	/**
	 * 
	 * @param id
	 */
	public void delete(int id) {
		Connection con = null;
		PreparedStatement ps = null;
		try {
			String query = "UPDATE book SET is_active = false WHERE is_active = 1 AND id = ?";

			con = ConnectionUtil.getConnection();
			ps = con.prepareStatement(query);
			ps.setInt(1, id);

			ps.executeUpdate();
			System.out.println("Book has been successfullly deleted.");
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
			throw new RuntimeException(e);
		} finally {
			ConnectionUtil.close(con, ps);
		}
	}
	/**
	 * 
	 * @param id
	 * @param basicUpdate
	 * @throws PersistanceException
	 */
	public void updatePrice(int id, Book basicUpdate) throws PersistanceException{
		Connection conn = null;
		PreparedStatement ps = null;
		try {
			String query = "UPDATE book SET price = ? WHERE is_active = 1 AND id = ?";
			conn = ConnectionUtil.getConnection();
			ps = conn.prepareStatement(query);
			ps.setInt(1, basicUpdate.getPrice());
			ps.setInt(2, id);
			ps.executeUpdate();
			System.out.println("Price has been updated successfully");
		} catch (SQLException e) {
			e.printStackTrace();
			throw new PersistanceException(e.getMessage());
		} finally {
			ConnectionUtil.close(conn, ps, null);
		}
	}
	/**
	 * 
	 * @param id
	 * @param basicUpdate
	 * @throws PersistanceException
	 */
	public void updateTitleAndDate(int id, Book basicUpdate) throws PersistanceException{
		 Connection conn = null;
		    PreparedStatement ps = null;
		    try {
		        String query = "UPDATE book SET title = ? , published_date = ? WHERE is_active = 1 AND id = ?" ;
		        conn = ConnectionUtil.getConnection();
		        ps = conn.prepareStatement(query);
		        ps.setString(1, basicUpdate.getTitle());
		        ps.setString(2, basicUpdate.getPublishedDate());
		        ps.setInt(3, id);
		        ps.executeUpdate();
		        System.out.println("Title And Published Date has been updated successfully");
		    } catch (SQLException e) {
				e.printStackTrace();
				throw new PersistanceException(e.getMessage());
			} finally {
		        ConnectionUtil.close(conn, ps, null);
		    }
	}
	/**
	 * 
	 * @param id
	 * @throws PersistanceException
	 */
	public static void bookIdAlreadyExistOrNot(int id) throws PersistanceException {

		Connection conn = null;
		PreparedStatement pre = null;
		ResultSet rs = null;
		try {
			String query = "Select * From book Where id = ?";
			conn = ConnectionUtil.getConnection();
			pre = conn.prepareStatement(query);
			pre.setInt(1, id);
			rs = pre.executeQuery();
			if (!rs.next()) {
				throw new PersistanceException("Book doesn't exist");
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new PersistanceException(e.getMessage());
		} finally {
			ConnectionUtil.close(conn, pre, rs);
		}

	}
	/**
	 * 
	 * @param author
	 * @throws PersistanceException
	 */
	public static void authorAlreadyExistOrNot(String author) throws PersistanceException {

		Connection conn = null;
		PreparedStatement pre = null;
		ResultSet rs = null;
		try {
			String query = "Select * From book Where author = ?";
			conn = ConnectionUtil.getConnection();
			pre = conn.prepareStatement(query);
			pre.setString(1, author);
			rs = pre.executeQuery();
			if (!rs.next()) {
				throw new PersistanceException("Author doesn't exist");
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new PersistanceException(e.getMessage());
		} finally {
			ConnectionUtil.close(conn, pre, rs);
		}

	}

}

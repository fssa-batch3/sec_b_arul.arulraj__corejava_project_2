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
	        e.printStackTrace();
	        throw new PersistanceException("Error while fetching publishers: " + e.getMessage());
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
			e.printStackTrace();
			System.out.print(e.getMessage());
			throw new PersistanceException(e.getMessage());
		}  finally {
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
			e.printStackTrace();
			System.out.print(e.getMessage());
			throw new PersistanceException(e.getMessage());
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
			e.printStackTrace();
			System.out.print(e.getMessage());
			throw new PersistanceException(e.getMessage());
		}finally {
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
	public Set<Book> findByPublisherId(int publisherId) throws PersistanceException{

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
			e.printStackTrace();
			System.out.print(e.getMessage());
			throw new PersistanceException(e.getMessage());
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
			System.out.println("Book has been successfullly deleted.");
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.print(e.getMessage());
			throw new PersistanceException(e.getMessage());
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
	public void updatePrice(int id, Book priceUpdate) throws PersistanceException {
		Connection conn = null;
		PreparedStatement ps = null;
		try {
			String query = "UPDATE books SET price = ? WHERE is_active = 1 AND id = ?";
			conn = ConnectionUtil.getConnection();
			ps = conn.prepareStatement(query);
			ps.setInt(1, priceUpdate.getPrice());
			ps.setInt(2, id);
			ps.executeUpdate();
			System.out.println("Price has been updated successfully");
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.print(e.getMessage());
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
	public void updateTitleAndDate(int id, Book titleAndDateUpdate) throws PersistanceException {
		Connection conn = null;
		PreparedStatement ps = null;
		try {
			String query = "UPDATE books SET title = ? , published_date = ? WHERE is_active = 1 AND id = ?";
			conn = ConnectionUtil.getConnection();
			ps = conn.prepareStatement(query);
			ps.setString(1, titleAndDateUpdate.getTitle());
			ps.setString(2, titleAndDateUpdate.getPublishedDate());
			ps.setInt(3, id);
			ps.executeUpdate();
			System.out.println("Title And Published Date has been updated successfully");
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.print(e.getMessage());
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
	public void updateAuthorNamePublisherIdCategoryId(int id, Book AuthorNamePublisheIdCategoryId) throws PersistanceException {
		Connection conn = null;
		PreparedStatement ps = null;
		try {
			String query = "UPDATE books SET author = ? , publisher_id = ? , category_id = ? WHERE is_active = 1 AND id = ?";
			conn = ConnectionUtil.getConnection();
			ps = conn.prepareStatement(query);
			ps.setString(1, AuthorNamePublisheIdCategoryId.getAuthor());
			ps.setInt(2, AuthorNamePublisheIdCategoryId.getPublisherId());
			ps.setInt(3, AuthorNamePublisheIdCategoryId.getCategoryId());
			ps.setInt(4, id);
			ps.executeUpdate();
			System.out.println("Author ,Published Id And Category Id has been updated successfully");
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.print(e.getMessage());
			throw new PersistanceException(e.getMessage());
		} finally {
			ConnectionUtil.close(conn, ps);
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
            e.printStackTrace();
            throw new PersistanceException(e.getMessage());
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
            e.printStackTrace();
            throw new PersistanceException(e.getMessage());
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
            e.printStackTrace();
            throw new PersistanceException(e.getMessage());
        } finally {
            ConnectionUtil.close(conn, pre, rs);
        }
    }

}

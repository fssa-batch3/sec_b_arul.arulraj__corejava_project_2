package in.fssa.technolibrary.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.HashSet;
import java.util.Set;

import in.fssa.technolibrary.model.Book;
import in.fssa.technolibrary.util.ConnectionUtil;

public class BookDAO {
	
	//Create Book 
	
	public void create(Book newBook) throws RuntimeException {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			String query = "INSERT INTO book (id, title, author, publisher_id, category_id,published_date,price) VALUES (?,?,?,?,?,?,?)";
			con = ConnectionUtil.getConnection();
			ps = con.prepareStatement(query);
			

				ps.setInt(1, newBook.getId());
				ps.setString(2, newBook.getTitle());
				ps.setString(3, newBook.getAuthor());
				ps.setInt(4, newBook.getPublisherId());
				ps.setInt(5, newBook.getCategoryId());
				java.sql.Date date = java.sql.Date.valueOf(newBook.getPublishedDate());
		        ps.setDate(6, date);
				ps.setInt(7, newBook.getPrice());
				ps.executeUpdate();
				
				System.out.print("Book has been Added successfully");

		}catch(SQLException e)
	{
		System.out.print(e.getMessage());
		throw new RuntimeException();
	}finally
	{
		ConnectionUtil.close(con, ps);
	}
}	

	public Set<Book> findAll() throws RuntimeException {

		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		Set<Book> bookList = new HashSet<>();

		try {
			String query = "SELECT * FROM book WHERE is_active = 1";
			con = ConnectionUtil.getConnection();
			ps = con.prepareStatement(query);
			rs = ps.executeQuery();

			while (rs.next()) {
				Book book = new Book();
				book.setId(rs.getInt("id"));
				SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
				String dueDateStr = dateFormat.format(rs.getTimestamp("publishedDate"));
				book.setPublishedDate(dueDateStr);
				book.setTitle(rs.getString("title"));
				book.setAuthor(rs.getString("author"));
				book.setPublisherId(rs.getInt("publisherId"));
				book.setCategoryId(rs.getInt("categoryId"));
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

}

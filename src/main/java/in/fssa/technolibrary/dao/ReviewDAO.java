package in.fssa.technolibrary.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import in.fssa.technolibrary.exception.PersistanceException;
import in.fssa.technolibrary.exception.ValidationException;
import in.fssa.technolibrary.model.Review;
import in.fssa.technolibrary.util.ConnectionUtil;
import in.fssa.technolibrary.util.Logger;

public class ReviewDAO {
	
	/**
	 * 
	 * @param newReview
	 * @throws PersistanceException
	 */
	public void create(Review newReview) throws PersistanceException {
		Connection con = null;
		PreparedStatement ps = null;
		try {
			String query = "INSERT INTO reviews ( feedback, ratings, book_id, user_id) VALUES (?,?,?,?)";
			con = ConnectionUtil.getConnection();
			ps = con.prepareStatement(query);
			ps.setString(1, newReview.getFeedback());
			ps.setInt(2, newReview.getRatings());
			ps.setInt(3, newReview.getBook_id());
			ps.setInt(4, newReview.getUser_id());
			ps.executeUpdate();

			Logger.info("Review has been Added successfully");

		} catch (SQLException e) {
			Logger.error(e);
			throw new PersistanceException(e);
		} finally {
			ConnectionUtil.close(con, ps);
		}
	}
	
	public void hasUserNotBoughtBook(int userId, int bookId) throws PersistanceException, ValidationException {
	    Connection conn = null;
	    PreparedStatement ps = null;
	    ResultSet rs = null;

	    try {
	        String query = "SELECT user_id,book_id FROM orders WHERE user_id=? AND book_id=?";

	        conn = ConnectionUtil.getConnection();
	        ps = conn.prepareStatement(query);
	        ps.setInt(1, userId);
	        ps.setInt(2, bookId);
	        rs = ps.executeQuery();

	        if(!rs.next()) {
	        	throw new ValidationException("The user has Not bought the book.");
	        }// If there is a match, the user has bought the book
	    } catch (SQLException e) {
	        Logger.error(e);
	        throw new PersistanceException(e);
	    } finally {
	        ConnectionUtil.close(conn, ps, rs);
	    }
	}

}

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
import in.fssa.technolibrary.model.Publisher;
import in.fssa.technolibrary.util.ConnectionUtil;

public class PublisherDAO {
	// Create Publisher
	/**
	 * 
	 * @param newPublisher
	 * @throws RuntimeException
	 * @throws PersistanceException 
	 */
	public void create(Publisher newPublisher) throws RuntimeException, PersistanceException {
		Connection con = null;
		PreparedStatement ps = null;

		try {
			String query = "INSERT INTO publishers (publisher_name) VALUES (?)";
			con = ConnectionUtil.getConnection();
			ps = con.prepareStatement(query);

			ps.setString(1, newPublisher.getName());
			ps.executeUpdate();

			System.out.print("Publisher has been successfully created");

		} catch (SQLException e) {
			System.out.print(e.getMessage());
			throw new PersistanceException(e.getMessage());
		} finally {
			ConnectionUtil.close(con, ps);
		}
	}
	/**
	 * 
	 * @return
	 * @throws PersistanceException
	 */
	public Set<Publisher> findAll() throws PersistanceException {

	    Connection con = null;
	    PreparedStatement ps = null;
	    ResultSet rs = null;

	    Set<Publisher> publisherList = new HashSet<>();

	    try {
	        String query = "SELECT id,publisher_name FROM publishers";
	        con = ConnectionUtil.getConnection();
	        ps = con.prepareStatement(query);
	        rs = ps.executeQuery();

	        while (rs.next()) {
	        	Publisher publisher = new Publisher();
	        	publisher.setId(rs.getInt("id"));
	        	publisher.setName(rs.getString("publisher_name"));
	        	publisherList.add(publisher);
	        }

	    } catch (SQLException e) {
	        e.printStackTrace();
	        throw new PersistanceException("Error while fetching books: " + e.getMessage());
	    } finally {
	        ConnectionUtil.close(con, ps, rs);
	    }
	    return publisherList;
	}
	/**
	 * 
	 * @param publisherName
	 * @return
	 * @throws PersistanceException
	 */
	public int findPublisherIdByPublisherName(String publisherName) throws PersistanceException {

		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		int publisher_id = 0;

		try {
			String query = "SELECT id FROM publishers WHERE publisher_name = ?" ;
			con = ConnectionUtil.getConnection();
			ps = con.prepareStatement(query);
			ps.setString(1, publisherName);
			rs = ps.executeQuery();
			if (rs.next()) {
				publisher_id = rs.getInt("id");
	        }

		} catch (SQLException e) {
			e.printStackTrace();
			System.out.print(e.getMessage());
			throw new PersistanceException(e.getMessage());
		}  finally {
			ConnectionUtil.close(con, ps, rs);
		}
		return publisher_id;
	}

	/**
	 * 
	 * @param id
	 * @throws PersistanceException
	 * @throws ValidationException
	 */
	public static boolean publisherIdAlreadyExistOrNot(int publisherId)
			throws PersistanceException, ValidationException {

		Connection conn = null;
		PreparedStatement pre = null;
		ResultSet rs = null;
		boolean result = true;
		try {
			String query = "Select id From publishers Where id = ?";
			conn = ConnectionUtil.getConnection();
			pre = conn.prepareStatement(query);
			pre.setInt(1, publisherId);
			rs = pre.executeQuery();
			if (!rs.next()) {
				result = false;
				System.out.println("Publisher doesn't exist");
				throw new ValidationException("Publisher doesn't exist");
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new PersistanceException(e.getMessage());
		} finally {
			ConnectionUtil.close(conn, pre, rs);
		}
		return result;

	}
	/**
	 * 
	 * @param publisherName
	 * @throws PersistanceException
	 * @throws ValidationException
	 */
	public static void publisherNameAlreadyExistOrNot(String publisherName)
			throws PersistanceException, ValidationException {

		Connection conn = null;
		PreparedStatement pre = null;
		ResultSet rs = null;
		try {
			String query = "Select publisher_name From publishers Where publisher_name = ?";
			conn = ConnectionUtil.getConnection();
			pre = conn.prepareStatement(query);
			pre.setString(1, publisherName);
			rs = pre.executeQuery();
			if (!rs.next()) {
				throw new ValidationException("Publisher doesn't exist");
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new PersistanceException(e.getMessage());
		} finally {
			ConnectionUtil.close(conn, pre);
		}

	}
	/**
	 * 
	 * @param publisherName
	 * @throws PersistanceException
	 * @throws ValidationException
	 */
	public static void publisherNameAlreadyExist(String publisherName)
			throws PersistanceException, ValidationException {

		Connection conn = null;
		PreparedStatement pre = null;
		ResultSet rs = null;
		try {
			String query = "Select publisher_name From publishers Where publisher_name = ?";
			conn = ConnectionUtil.getConnection();
			pre = conn.prepareStatement(query);
			pre.setString(1, publisherName);
			rs = pre.executeQuery();
			if (rs.next()) {
				throw new ValidationException("Publisher Already exist");
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new PersistanceException(e.getMessage());
		} finally {
			ConnectionUtil.close(conn, pre, rs);
		}

	}

}

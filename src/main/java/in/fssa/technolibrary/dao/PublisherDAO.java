package in.fssa.technolibrary.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import in.fssa.technolibrary.exception.PersistanceException;
import in.fssa.technolibrary.exception.ValidationException;
import in.fssa.technolibrary.model.Publisher;
import in.fssa.technolibrary.util.ConnectionUtil;

public class PublisherDAO {
	//Create Publisher
		/**
		 * 
		 * @param newPublisher
		 * @throws RuntimeException
		 */
		public void create(Publisher newPublisher) throws RuntimeException {
			Connection con = null;
			PreparedStatement ps = null;

			try {
				String query = "INSERT INTO publisher (publisher_name) VALUES (?)";
				con = ConnectionUtil.getConnection();
				ps = con.prepareStatement(query);
				
					ps.setString(1, newPublisher.getName());
					ps.executeUpdate();
					
					System.out.print("Publisher has been successfully created");

			}catch(SQLException e)
		{
			System.out.print(e.getMessage());
			throw new RuntimeException();
		}finally
		{
			ConnectionUtil.close(con, ps);
		}
	}
	/**
	 * 	
	 * @param id
	 * @throws PersistanceException
	 * @throws ValidationException 
	 */
		public static boolean publisherIdAlreadyExistOrNot(int publisherId) throws PersistanceException, ValidationException {
			
			Connection conn = null;
			PreparedStatement pre = null;
			ResultSet rs = null;
			boolean result = true;
		  try {
				String query = "Select * From publisher Where id = ?";
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

}

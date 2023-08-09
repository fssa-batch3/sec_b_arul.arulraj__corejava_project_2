package in.fssa.technolibrary.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import in.fssa.technolibrary.model.Publisher;
import in.fssa.technolibrary.util.ConnectionUtil;

public class PublisherDAO {
	//Create Publisher
	
		public void create(Publisher newPublisher) throws RuntimeException {
			Connection con = null;
			PreparedStatement ps = null;
			ResultSet rs = null;

			try {
				String query = "INSERT INTO book (id, name) VALUES (?,?)";
				con = ConnectionUtil.getConnection();
				ps = con.prepareStatement(query);
				

					ps.setInt(1, newPublisher.getId());
					ps.setString(2, newPublisher.getName());
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

}

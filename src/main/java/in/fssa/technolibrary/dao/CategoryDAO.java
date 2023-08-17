package in.fssa.technolibrary.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import in.fssa.technolibrary.model.Category;
import in.fssa.technolibrary.util.ConnectionUtil;

public class CategoryDAO {
	//Create Category
	
	public void create(Category newCategory) throws RuntimeException {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			String query = "INSERT INTO category (category_name) VALUES (?)";
			con = ConnectionUtil.getConnection();
			ps = con.prepareStatement(query);
			
				ps.setString(1, newCategory.getName());
				ps.executeUpdate();
				
				System.out.print("Category has been successfully created");

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

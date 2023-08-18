package in.fssa.technolibrary.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import in.fssa.technolibrary.exception.PersistanceException;
import in.fssa.technolibrary.exception.ValidationException;
import in.fssa.technolibrary.model.Category;
import in.fssa.technolibrary.util.ConnectionUtil;

public class CategoryDAO {

	// Create Category
	/**
	 * 
	 * @param newCategory
	 * @throws RuntimeException
	 */
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

		} catch (SQLException e) {
			System.out.print(e.getMessage());
			throw new RuntimeException();
		} finally {
			ConnectionUtil.close(con, ps);
		}
	}

	// Category ExistOrNot
	/**
	 * 
	 * @param id
	 * @throws PersistanceException
	 */
	public void categoryIdAlreadyExistOrNot(int id) throws PersistanceException {

		Connection conn = null;
		PreparedStatement pre = null;
		ResultSet rs = null;
		try {
			String query = "Select * From category Where id = ?";
			conn = ConnectionUtil.getConnection();
			pre = conn.prepareStatement(query);
			pre.setInt(1, id);
			rs = pre.executeQuery();
			if (!rs.next()) {
				System.out.println("Category doesn't exist");
				throw new PersistanceException("Category doesn't exist");
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new PersistanceException(e.getMessage());
		} finally {
			ConnectionUtil.close(conn, pre, rs);
		}

	}
}

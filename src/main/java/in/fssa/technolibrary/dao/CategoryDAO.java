package in.fssa.technolibrary.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

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
	 * @throws PersistanceException 
	 */
	public void create(Category newCategory) throws PersistanceException {
		Connection con = null;
		PreparedStatement ps = null;

		try {
			String query = "INSERT INTO categorys (category_name) VALUES (?)";
			con = ConnectionUtil.getConnection();
			ps = con.prepareStatement(query);

			ps.setString(1, newCategory.getName());
			ps.executeUpdate();

		} catch (SQLException e) {
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
	public Set<Category> findAll() throws PersistanceException {

	    Connection con = null;
	    PreparedStatement ps = null;
	    ResultSet rs = null;

	    Set<Category> categoryList = new HashSet<>();

	    try {
	        String query = "SELECT id,category_name FROM categorys";
	        con = ConnectionUtil.getConnection();
	        ps = con.prepareStatement(query);
	        rs = ps.executeQuery();

	        while (rs.next()) {
	        	Category category = new Category();
	        	category.setId(rs.getInt("id"));
	        	category.setName(rs.getString("category_name"));
	        	categoryList.add(category);
	        }

	    } catch (SQLException e) {
	        e.printStackTrace();
	        throw new PersistanceException("Error while fetching categorys: " + e.getMessage());
	    } finally {
	        ConnectionUtil.close(con, ps, rs);
	    }
	    return categoryList;
	}
	/**
	 * 
	 * @param publisherId
	 * @return
	 * @throws PersistanceException
	 */
	public String findById(int categoryId) throws PersistanceException {

		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String category = null;

		try {
			String query = "SELECT category_name FROM categorys WHERE id = ?";
			con = ConnectionUtil.getConnection();
			ps = con.prepareStatement(query);
			ps.setInt(1, categoryId);
			rs = ps.executeQuery();
			if (rs.next()) {
				category = rs.getString("category_name");
			}

		} catch (SQLException e) {
			throw new PersistanceException(e.getMessage());
		}  finally {
			ConnectionUtil.close(con, ps, rs);
		}
		return category;
	}

	// Category ExistOrNot
	/**
	 * 
	 * @param id
	 * @throws PersistanceException
	 * @throws ValidationException
	 */
	public static boolean categoryIdAlreadyExistOrNot(int categoryId) throws PersistanceException, ValidationException {

		Connection conn = null;
		PreparedStatement pre = null;
		ResultSet rs = null;
		boolean result = true;
		try {
			String query = "SELECT id FROM categorys WHERE id = ?";
			conn = ConnectionUtil.getConnection();
			pre = conn.prepareStatement(query);
			pre.setInt(1, categoryId);
			rs = pre.executeQuery();
			if (!rs.next()) {
				throw new ValidationException("Category doesn't exist");
			}
		} catch (SQLException e) {
			throw new PersistanceException(e.getMessage());
		} finally {
			ConnectionUtil.close(conn, pre, rs);
		}
		return result;
	}

	/**
	 * 
	 * @param categoryName
	 * @throws PersistanceException
	 * @throws ValidationException 
	 */
	public static void categoryNameAlreadyExists(String categoryName) throws PersistanceException, ValidationException {

		Connection conn = null;
		PreparedStatement pre = null;
		ResultSet rs = null;
		try {
			String query = "SELECT category_name FROM categorys WHERE category_name = ?";
			conn = ConnectionUtil.getConnection();
			pre = conn.prepareStatement(query);
			pre.setString(1, categoryName);
			rs = pre.executeQuery();
			if (rs.next()) {
				throw new ValidationException("Category Name already exist");
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new PersistanceException(e.getMessage());
		} finally {
			ConnectionUtil.close(conn, pre, rs);
		}

	}
	
	
}

package in.fssa.technolibrary.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import in.fssa.technolibrary.exception.PersistanceException;
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
    public static boolean categoryIdAlreadyExistOrNot(int id) throws PersistanceException {

        Connection conn = null;
        PreparedStatement pre = null;
        ResultSet rs = null;
        boolean result = true;
        try {
            String query = "SELECT * FROM category WHERE id = ?";
            conn = ConnectionUtil.getConnection();
            pre = conn.prepareStatement(query);
            pre.setInt(1, id);
            rs = pre.executeQuery();
            if (!rs.next()) {
                throw new PersistanceException("Category with ID " + id + " doesn't exist");
            }
        } catch (SQLException e) {
            System.out.println("Category with ID " + id + " doesn't exist");
            throw new PersistanceException(e.getMessage());
        } finally {
            ConnectionUtil.close(conn, pre, rs);
        }
        return result;
    }
    
    public static void categoryNameAlreadyExists(String name) throws PersistanceException {

        Connection conn = null;
        PreparedStatement pre = null;
        ResultSet rs = null;
        try {
            String query = "SELECT category_name FROM category WHERE category_name = ?";
            conn = ConnectionUtil.getConnection();
            pre = conn.prepareStatement(query);
            pre.setString(1, name);
            rs = pre.executeQuery();
            if (rs.next()) {
                throw new PersistanceException("Category Name " + name + " already exist");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new PersistanceException(e.getMessage());
        } finally {
            ConnectionUtil.close(conn, pre, rs);
        }
        
    }
}


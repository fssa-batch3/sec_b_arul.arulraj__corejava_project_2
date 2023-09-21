package in.fssa.technolibrary.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

import in.fssa.technolibrary.exception.PersistanceException;
import in.fssa.technolibrary.exception.ValidationException;
import in.fssa.technolibrary.model.Order;
import in.fssa.technolibrary.util.ConnectionUtil;
import in.fssa.technolibrary.util.Logger;

public class OrderDAO {
	
	/**
	 * 
	 * @param newOrder
	 * @throws PersistanceException
	 */
	public void create(Order newOrder) throws  PersistanceException{
		
		Connection conn = null;
		PreparedStatement ps = null;
		try {
			String query = "INSERT INTO orders (book_id, user_id, address, order_date, status) VALUES (?,?,?,?,?)";
			conn = ConnectionUtil.getConnection();
			ps = conn.prepareStatement(query);
			ps.setInt(1, newOrder.getBook_id());
			ps.setInt(2, newOrder.getUser_id());
			ps.setString(3, newOrder.getAddress());
			ps.setString(4, newOrder.getOrder_date());
			ps.setString(5, newOrder.getStatus());
			
			ps.executeUpdate();
			Logger.info("Order has been created successfully");
		} catch (SQLException e) {
			Logger.error(e);
			throw new PersistanceException(e);
		} finally {
			ConnectionUtil.close(conn, ps);
		}
		
	}
	
	/**
	 * 
	 * @param id
	 * @param statusUpdate
	 * @throws PersistanceException
	 */
	public void updateOrderStatus(int id, Order statusUpdate) throws PersistanceException {

		Connection con = null;
		PreparedStatement ps = null;
		try {
			String query = "UPDATE orders SET status =?, lastupdate_on = ? WHERE id =?";
			
			con = ConnectionUtil.getConnection();
			ps = con.prepareStatement(query);
			ps.setString(1, statusUpdate.getStatus());
			ps.setString(2, statusUpdate.getLast_update_on());
			
			ps.setInt(3, id);

			ps.executeUpdate();
			Logger.info("Status has been successfullly updated.");
		} catch (SQLException e) {
			e.printStackTrace();
			Logger.error(e);
			throw new PersistanceException(e);
		} finally {
			ConnectionUtil.close(con, ps);
		}
	}
	
	/**
	 * 
	 * @return
	 * @throws PersistanceException
	 */
	public Set<Order> findAllOrder() throws PersistanceException {
	    Connection con = null;
	    PreparedStatement ps = null;
	    ResultSet rs = null;

	    Set<Order> orderList = new HashSet<>();

	    try {
	        String query = "SELECT id, book_id, user_id, address, order_date, status, lastupdate_on FROM orders";
	        con = ConnectionUtil.getConnection();
	        ps = con.prepareStatement(query);
	        rs = ps.executeQuery();
	        
	        while (rs.next()) {
	            Order order = new Order();
	            order.setId(rs.getInt("id"));
	            order.setBook_id(rs.getInt("book_id"));
	            order.setUser_id(rs.getInt("user_id"));
	            order.setAddress(rs.getString("address"));
	            order.setOrder_date(rs.getString("order_date"));
	            order.setStatus(rs.getString("status"));
	            order.setLast_update_on(rs.getString("lastupdate_on"));
	            orderList.add(order);
	        }
	    } catch (SQLException e) {
	        Logger.error(e);
	        throw new PersistanceException(e);
	    } finally {
	        ConnectionUtil.close(con, ps, rs);
	    }
	    return orderList;
	}
	
	/**
	 * 
	 * @param userId
	 * @return
	 * @throws PersistanceException
	 */
	public Set<Order> findOrderByUserId(int userId) throws PersistanceException {
	    Connection con = null;
	    PreparedStatement ps = null;
	    ResultSet rs = null;

	    Set<Order> orderList = new HashSet<>();

	    try {
	        String query = "SELECT id, book_id, user_id, address, order_date, lastupdate_on, status FROM orders WHERE user_id = ?";
	        con = ConnectionUtil.getConnection();
	        ps = con.prepareStatement(query);
	        ps.setInt(1, userId);
	        rs = ps.executeQuery();
	        
	        while (rs.next()) {
	            Order order = new Order();
	            order.setId(rs.getInt("id"));
	            order.setBook_id(rs.getInt("book_id"));
	            order.setUser_id(rs.getInt("user_id"));
	            order.setAddress(rs.getString("address"));
	            order.setOrder_date(rs.getString("order_date"));
	            order.setStatus(rs.getString("status"));
	            order.setLast_update_on(rs.getString("lastupdate_on"));
	            orderList.add(order);
	        }
	    } catch (SQLException e) {
	        Logger.error(e);
	        throw new PersistanceException(e);
	    } finally {
	        ConnectionUtil.close(con, ps, rs);
	    }
	    return orderList;
	}
	
	/**
	 * 
	 * @param bookId
	 * @throws ValidationException
	 * @throws PersistanceException
	 */
	public static void findBookOrderOrNot(int bookId) throws ValidationException, PersistanceException {
	    Connection con = null;
	    PreparedStatement ps = null;
	    ResultSet rs = null;

	    try {
	        String query = "SELECT id, book_id, user_id, address, order_date, lastupdate_on, status FROM orders WHERE book_id = ?";
	        con = ConnectionUtil.getConnection();
	        ps = con.prepareStatement(query);
	        ps.setInt(1, bookId);
	        rs = ps.executeQuery();
	        
	        if (!rs.next()) {
				throw new ValidationException("You can not revieing book before ordering.");
	        }
	    } catch (SQLException e) {
	        Logger.error(e);
	        throw new PersistanceException(e);
	    } finally {
	        ConnectionUtil.close(con, ps, rs);
	    }
	}
	
	/**
	 * 
	 * @param orderId
	 * @throws ValidationException
	 * @throws PersistanceException
	 */
	public static void checkIdExists(int orderId) throws ValidationException, PersistanceException{
		Connection conn = null;
		PreparedStatement pre = null;
		ResultSet rs = null;
		
		try {
			String query = "SELECT id FROM orders WHERE id = ?";
			conn = ConnectionUtil.getConnection();
			pre = conn.prepareStatement(query);
			pre.setInt(1, orderId);
			rs = pre.executeQuery();
			if (!rs.next()) {
				throw new ValidationException("Order doesn't exist");
			}
			
		}catch (SQLException e) {
			Logger.error(e);
			throw new PersistanceException(e);
		} finally {
			ConnectionUtil.close(conn, pre, rs);
		}
		
	}

	
}

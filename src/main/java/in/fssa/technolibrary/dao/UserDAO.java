package in.fssa.technolibrary.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import in.fssa.technolibrary.exception.PersistanceException;
import in.fssa.technolibrary.exception.ValidationException;
import in.fssa.technolibrary.model.User;
import in.fssa.technolibrary.util.ConnectionUtil;
import in.fssa.technolibrary.util.Logger;
import in.fssa.technolibrary.util.PasswordEncryptUtil;

public class UserDAO {

	/**
	 * Retrieves a set of all active users from the database.
	 *
	 * @return A set containing all active User objects in the database.
	 * @throws PersistenceException If a database-related error occurs during
	 *                              retrieval.
	 */
	public Set<User> findAll() throws PersistanceException {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		Set<User> userList = new HashSet<>();
		try {
			String query = "SELECT id, name, email, password, image, phone_number, is_active " +
		               "FROM users WHERE is_active = 1";

			conn = ConnectionUtil.getConnection();
			ps = conn.prepareStatement(query);
			rs = ps.executeQuery();
			while (rs.next()) {
				User user = new User();
				user.setId(rs.getInt("id"));
				user.setName(rs.getString("name"));
				user.setEmail(rs.getString("email"));
				user.setPassword(rs.getString("password"));
				user.setImage(rs.getString("image"));
				user.setPhoneNumber(rs.getLong("phone_number"));
				user.setActive(rs.getBoolean("is_active"));
				userList.add(user);
			}
		} catch (SQLException e) {
			Logger.error(e);
			throw new PersistanceException(e);
		} finally {
			ConnectionUtil.close(conn, ps, rs);
		}
		return userList;
	}

	/**
	 * Retrieves a user from the database based on their user ID.
	 *
	 * @param userId The ID of the user to retrieve.
	 * @return The User object corresponding to the provided user ID.
	 * @throws PersistenceException If a database-related error occurs during
	 *                              retrieval.
	 */
	public User findById(int userId) throws PersistanceException {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		User user = null;
		try {
			String query = "SELECT id,name,email,password,image,phone_number,is_active "
					+ "FROM users WHERE is_active = 1 AND id = ?";
			conn = ConnectionUtil.getConnection();
			ps = conn.prepareStatement(query);
			ps.setInt(1, userId);
			rs = ps.executeQuery();
			if (rs.next()) {
				user = new User();
				user.setId(rs.getInt("id"));
				user.setName(rs.getString("name"));
				user.setEmail(rs.getString("email"));
				user.setPassword(rs.getString("password"));
				user.setPhoneNumber(rs.getLong("phone_number"));
				user.setImage(rs.getString("image"));
				user.setActive(rs.getBoolean("is_active"));
			}
		} catch (SQLException e) {
			Logger.error(e);
			throw new PersistanceException(e);
		} finally {
			ConnectionUtil.close(conn, ps, rs);
		}
		return user;
	}

	/**
	 * Retrieves a user from the database based on their email address.
	 *
	 * @param email The email address of the user to retrieve.
	 * @return The User object corresponding to the provided email address.
	 * @throws PersistenceException If a database-related error occurs during
	 *                              retrieval.
	 */
	public User findByEmail(String email) throws PersistanceException {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		User user = null;
		try {
			String query = "SELECT id,name,email,password,image,phone_number,is_active "
					+ "FROM users WHERE is_active = 1 AND email = ?";
			conn = ConnectionUtil.getConnection();
			ps = conn.prepareStatement(query);
			ps.setString(1, email);
			rs = ps.executeQuery();
			if (rs.next()) {
				user = new User();
				user.setId(rs.getInt("id"));
				user.setName(rs.getString("name"));
				user.setEmail(rs.getString("email"));
				user.setPassword(rs.getString("password"));
				user.setImage(rs.getString("image"));
				user.setPhoneNumber(rs.getLong("phone_number"));
				user.setActive(rs.getBoolean("is_active"));
			}
		} catch (SQLException e) {
			Logger.error(e);
			throw new PersistanceException(e);
		} finally {
			ConnectionUtil.close(conn, ps, rs);
		}
		return user;
	}

	/**
	 * Creates a new user in the database.
	 *
	 * @param newUser The User object containing the details of the new user to
	 *                create.
	 * @throws PersistenceException If a database-related error occurs during
	 *                              creation.
	 */
	public void create(User newUser) throws PersistanceException {
		Connection conn = null;
		PreparedStatement ps = null;
		try {
			String query = "INSERT INTO users (name, email, password, image, phone_number) VALUES (?,?,?,?,?)";
			conn = ConnectionUtil.getConnection();
			ps = conn.prepareStatement(query);
			ps.setString(1, newUser.getName());
			ps.setString(2, newUser.getEmail());
			String hashPassword = PasswordEncryptUtil.encrypt(newUser.getPassword());
			ps.setString(3, hashPassword);
			if (newUser.getImage() != null) {
				ps.setString(4, newUser.getImage());
			} else {
				ps.setNull(4, java.sql.Types.VARCHAR);
			}
			ps.setLong(5, newUser.getPhoneNumber());
			ps.executeUpdate();
			Logger.info("User has been created successfully");

		} catch (SQLException e) {
			Logger.error(e);
			throw new PersistanceException(e);
		} finally {
			ConnectionUtil.close(conn, ps);
		}
	}

	/**
	 * Updates the details of an existing user in the database.
	 *
	 * @param id          The ID of the user to update.
	 * @param updatedUser The User object containing the updated details for the
	 *                    user.
	 * @throws PersistenceException If a database-related error occurs during the
	 *                              update.
	 */
	public void update(int userId, User updatedUser) throws PersistanceException {
		Connection conn = null;
		PreparedStatement ps = null;
		try {
			StringBuilder queryBuilder = new StringBuilder("UPDATE users SET ");
			List<Object> values = new ArrayList<>();

			if (updatedUser.getName() != null) {
				queryBuilder.append("name = ?, ");
				values.add(updatedUser.getName());
			}
			if (updatedUser.getPassword() != null) {
				queryBuilder.append("password = ?, ");
				values.add(updatedUser.getPassword());
			}
			if (updatedUser.getImage() != null) {
				queryBuilder.append("image = ?, ");
				values.add(updatedUser.getImage());
			}
			if (updatedUser.getPhoneNumber() != 0) {
				queryBuilder.append("phone_number = ?, ");
				values.add(updatedUser.getPhoneNumber());
			}

			queryBuilder.setLength(queryBuilder.length() - 2);

			queryBuilder.append(" WHERE is_active = 1 AND id = ?");
			conn = ConnectionUtil.getConnection();
			ps = conn.prepareStatement(queryBuilder.toString());

			for (int i = 0; i < values.size(); i++) {
				ps.setObject(i + 1, values.get(i));
			}
			ps.setInt(values.size() + 1, userId);

			ps.executeUpdate();
			Logger.info("User has been updated successfully");
		} catch (SQLException e) {
			Logger.error(e);
			throw new PersistanceException(e);
		} finally {
			ConnectionUtil.close(conn, ps, null);
		}
	}
	
	public void delete(int userId) throws PersistanceException {
		Connection conn = null;
		PreparedStatement ps = null;
		try {
			String query = "UPDATE users SET is_active = 0 WHERE id = ? AND is_active = 1";
			conn = ConnectionUtil.getConnection();
			ps = conn.prepareStatement(query);
			ps.setInt(1, userId);
			ps.executeUpdate();
			Logger.info("User has been deleted successfully");
		} catch (SQLException e) {
			Logger.error(e);
			throw new PersistanceException(e);
		} finally {
			ConnectionUtil.close(conn, ps, null);
		}
	}
	
	/**
	 * Checks if an email address already exists in the users table.
	 *
	 * @param email The email address to check.
	 * @throws ValidationException  If the email address already exists or doesn't
	 *                              match the specified criteria.
	 * @throws PersistenceException If a database-related error occurs during the
	 *                              validation process.
	 */
	// Catch
	public static void emailExists(String email) throws ValidationException, PersistanceException {
		Connection conn = null;
		PreparedStatement pre = null;
		ResultSet rs = null;

		try {
			String query = "SELECT email FROM users WHERE email = ?";
			conn = ConnectionUtil.getConnection();
			pre = conn.prepareStatement(query);
			pre.setString(1, email);
			rs = pre.executeQuery();
			if (rs.next()) {
				throw new ValidationException("Email already exists.Try with a new email id");
			}
		} catch (SQLException e) {
			Logger.error(e);
			throw new PersistanceException(e);
		} finally {
			ConnectionUtil.close(conn, pre, rs);
		}
	}

	/**
	 * Checks if a user ID exists in the users table.
	 *
	 * @param id The ID to check.
	 * @throws ValidationException  If the ID doesn't exist or doesn't match the
	 *                              specified criteria.
	 * @throws PersistenceException If a database-related error occurs during the
	 *                              validation process.
	 */
	public static void checkIdExists(int userId) throws ValidationException, PersistanceException {
		Connection conn = null;
		PreparedStatement pre = null;
		ResultSet rs = null;

		try {
			String query = "SELECT id FROM users WHERE is_active = 1 AND id = ?";
			conn = ConnectionUtil.getConnection();
			pre = conn.prepareStatement(query);
			pre.setInt(1, userId);
			rs = pre.executeQuery();

			if (!rs.next()) {
				throw new ValidationException("User Id doesn't exist");
			}
		} catch (SQLException e) {
			Logger.error(e);
			throw new PersistanceException(e);
		} finally {
			ConnectionUtil.close(conn, pre, rs);
		}
	}

	/**
	 * Checks if an email address exists for an active user.
	 *
	 * @param email The email address to check.
	 * @throws ValidationException  If the email address doesn't exist or doesn't
	 *                              match the specified criteria.
	 * @throws PersistenceException If a database-related error occurs during the
	 *                              validation process.
	 */
	public static void checkEmailExists(String email) throws ValidationException, PersistanceException {
		Connection conn = null;
		PreparedStatement pre = null;
		ResultSet rs = null;

		try {
			String query = "SELECT email FROM users WHERE is_active = 1 AND email = ?";
			conn = ConnectionUtil.getConnection();
			pre = conn.prepareStatement(query);
			pre.setString(1, email);
			rs = pre.executeQuery();
			if (!rs.next()) {
				throw new ValidationException("Email doesn't exist");
			}
		} catch (SQLException e) {
			Logger.error(e);
			throw new PersistanceException(e);
		} finally {
			ConnectionUtil.close(conn, pre, rs);
		}
	}
	
	/**
	 * Retrieves the ID of the last updated non-designer user who is active.
	 *
	 * @return The ID of the last updated non-designer user.
	 * @throws PersistenceException If a database error occurs while retrieving the
	 *                              ID.
	 */
	public static int getLastUpdatedUserId() throws PersistanceException {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		int userId = 0;
		try {
			String query = "SELECT id FROM users WHERE is_active = 1 ORDER BY created_at DESC LIMIT 1";
			conn = ConnectionUtil.getConnection();
			ps = conn.prepareStatement(query);
			rs = ps.executeQuery();
			if (rs.next()) {
				userId = rs.getInt("id");
			}
		} catch (SQLException e) {
			Logger.error(e);
			throw new PersistanceException(e);
		} finally {
			ConnectionUtil.close(conn, ps, rs);
		}
		return userId;
	}
}
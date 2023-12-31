package in.fssa.technolibrary.service;

import java.util.Set;



import in.fssa.technolibrary.dao.UserDAO;
import in.fssa.technolibrary.exception.PersistanceException;
import in.fssa.technolibrary.exception.ServiceException;
import in.fssa.technolibrary.exception.ValidationException;
import in.fssa.technolibrary.model.User;
import in.fssa.technolibrary.validator.UserValidator;

public class UserService {
	/**
	 * Retrieves a set of all users from the database.
	 *
	 * @return A set containing all User objects in the database.
	 * @throws ServiceException If a service-related error occurs during retrieval.
	 */
	public Set<User> getAllUser() throws ServiceException {
		try {
			UserDAO userDAO = new UserDAO();
			return userDAO.findAll();
		} catch (PersistanceException e) {
			throw new ServiceException("Error occurred while retrieving users.", e);
		}
	}

	/**
	 * Retrieves a user by their ID.
	 *
	 * @param id The ID of the user to retrieve.
	 * @return The User object corresponding to the given ID.
	 * @throws ValidationException If the provided ID is invalid.
	 * @throws ServiceException    If a service-related error occurs during
	 *                             retrieval.
	 */
	public User findByUserId(int userId) throws ValidationException, ServiceException {
		try {
			UserValidator.validateUserId(userId);
			UserDAO userDAO = new UserDAO();
			return userDAO.findById(userId);
		} catch (PersistanceException e) {
			throw new ServiceException("Error occurred while finding user by their id.", e);
		}
	}

	/**
	 * Retrieves a user by their email address.
	 *
	 * @param email The email address of the user to retrieve.
	 * @return The User object corresponding to the given email address.
	 * @throws ValidationException If the provided email address is invalid.
	 * @throws ServiceException    If a service-related error occurs during
	 *                             retrieval.
	 * @throws com.google.protobuf.ServiceException 
	 */
	public User findByEmail(String email) throws ValidationException, ServiceException{
		try {
			UserValidator.validateEmail(email);
			UserDAO userDAO = new UserDAO();
			return userDAO.findByEmail(email);
		} catch (PersistanceException e) {
			throw new ServiceException("Error occurred while finding user by their email.", e);
		}
	}

	/**
	 * Creates a new user.
	 *
	 * @param newUser The User object representing the new user.
	 * @throws ValidationException If the provided user data is invalid.
	 * @throws ServiceException    If a service-related error occurs during
	 *                             creation.
	 */
	public void createUser(User newUser) throws ValidationException, ServiceException {
		try {
			UserValidator.validateUser(newUser);
			UserDAO userDAO = new UserDAO();
			userDAO.create(newUser);
		} catch (PersistanceException e) {
			throw new ServiceException("Error occurred while creating user.", e);
		}
	}

	/**
	 * Updates a user's information.
	 *
	 * @param id          The ID of the user to update.
	 * @param updatedUser The User object containing updated information.
	 * @throws ValidationException If the provided data is invalid.
	 * @throws ServiceException    If a service-related error occurs during update.
	 */
	public void updateUser(int userId, User updatedUser) throws ValidationException, ServiceException {
		try {
			UserValidator.validateUserId(userId);
			if (updatedUser.getName() != null) {
				UserValidator.validateName(updatedUser.getName());
			}
			if (updatedUser.getPassword() != null) {
				UserValidator.validatePassword(updatedUser.getPassword());
			}
			if (updatedUser.getImage() != null) {
				UserValidator.validateImage(updatedUser.getImage());
			}
			if (updatedUser.getPhoneNumber() != 0) {
				UserValidator.validatePhoneNumber(updatedUser.getPhoneNumber());
			}
			UserDAO userDao = new UserDAO();
			userDao.update(userId, updatedUser);
		} catch (PersistanceException e) {
			throw new ServiceException("Error occurred while updating user.", e);
		}
	}

	/**
	 * Deletes a user.
	 *
	 * @param Id The ID of the user to delete.
	 * @throws ValidationException If the provided ID is invalid.
	 * @throws ServiceException    If a service-related error occurs during
	 *                             deletion.
	 */
	public void deleteUser(int userId) throws ValidationException, ServiceException {
		try {
			UserValidator.validateUserId(userId);
			UserDAO userDAO = new UserDAO();
			userDAO.delete(userId);
		} catch (PersistanceException e) {
			throw new ServiceException("Error occurred while deleting user.", e);
		}
	}
}
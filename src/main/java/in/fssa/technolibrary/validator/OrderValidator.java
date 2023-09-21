package in.fssa.technolibrary.validator;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import in.fssa.technolibrary.dao.BookDAO;
import in.fssa.technolibrary.dao.OrderDAO;
import in.fssa.technolibrary.dao.UserDAO;
import in.fssa.technolibrary.exception.PersistanceException;
import in.fssa.technolibrary.exception.ServiceException;
import in.fssa.technolibrary.exception.ValidationException;
import in.fssa.technolibrary.model.Order;
import in.fssa.technolibrary.util.Logger;
import in.fssa.technolibrary.util.StringUtil;

public class OrderValidator {
	
	public static void validateOrder(Order order) throws ValidationException, ServiceException {
		if (order == null) {
			throw new ValidationException("Order object cannot be null");
		}
		validateUserId(order.getUser_id());
		validateBookId(order.getBook_id());
		validateOrderDate(order.getOrder_date());
	}
	
	/**
	 * 
	 * @param userId
	 * @throws ValidationException
	 */
	public static void validateUserId(int userId) throws ValidationException {
		
		try {
			
			if (userId <= 0) {
				throw new ValidationException("User Id can not be less than zero.");
			}
			
			UserDAO.checkIdExists(userId);
		} catch (ValidationException | PersistanceException e) {
			Logger.error(e);
			throw new ValidationException("User doesn't exist");
		}
		
	}
	/**
	 * 
	 * @param userId
	 * @throws ValidationException
	 */
		public static void validateOrderId(int orderId) throws ValidationException {
		
		try {
			
			if (orderId <= 0) {

				throw new ValidationException("Orders Id can not be less than zero.");
			}
			
			OrderDAO.checkIdExists(orderId);
		} catch (ValidationException | PersistanceException e) {
			Logger.error(e);
			throw new ValidationException("Order doesn't exist");
		}
	}
	
	/**
	 * 
	 * @param bookId
	 * @throws ValidationException
	 */
	public static void validateBookId(int bookId) throws ValidationException {
		
		try {
			if (bookId <= 0) {

				throw new ValidationException("Book Id can not be less than zero.");
			}
			BookDAO.bookIdAlreadyExistOrNot(bookId);
			
		} catch (ValidationException | PersistanceException e) {
			Logger.error(e);
			throw new ValidationException("Book doesn't exist");
		}
		
	}
	
	/**
	 * 
	 * @param ordeDdate
	 * @throws ValidationException
	 */
	public static void validateOrderDate(String ordeDdate) throws ValidationException {
		StringUtil.rejectIfInvalidString(ordeDdate, "Date");
		DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		LocalDate publisheddate;
		try {
			publisheddate = LocalDate.parse(ordeDdate, inputFormatter);
		} catch (DateTimeParseException e) {
			Logger.error(e);
			throw new ValidationException("Invalid date or Invalid date format (yyyy-MM-dd)");
		}
		String formattedDate = publisheddate.format(inputFormatter);
		System.out.println("Formatted Date: " + formattedDate);
		LocalDate currentDate = LocalDate.now();
		if (!publisheddate.equals(currentDate)) {
			throw new ValidationException("Date can't be in past or future");
		}
	}
	
}

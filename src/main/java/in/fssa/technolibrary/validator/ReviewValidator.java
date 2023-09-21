package in.fssa.technolibrary.validator;

import in.fssa.technolibrary.dao.OrderDAO;
import in.fssa.technolibrary.dao.ReviewDAO;
import in.fssa.technolibrary.exception.PersistanceException;
import in.fssa.technolibrary.exception.ValidationException;
import in.fssa.technolibrary.model.Review;
import in.fssa.technolibrary.util.Logger;

public class ReviewValidator {
	
	public static void validateReview(Review review) throws ValidationException {
	ReviewDAO reviewDAO = new ReviewDAO();
		if( review == null ) {
			throw new ValidationException("User object cannot be null");		
			}
		validateBookId(review.getBook_id());
		
		try {
			reviewDAO.hasUserNotBoughtBook(review.getUser_id(), review.getBook_id());
		} catch (PersistanceException |ValidationException e) {
			Logger.error(e);
			throw new ValidationException("The user has Not bought the book.");
		} 
		
	}
	
	/**
	 * 
	 * @param bookId
	 * @throws ValidationException
	 */
	public static void validateBookId(int bookId) throws ValidationException {
		if (bookId <= 0) {
			throw new ValidationException("Book Id cannot be zero or negative");
		}
		try {
			
			
			OrderDAO.findBookOrderOrNot(bookId);
		} catch (ValidationException | PersistanceException e) {
			Logger.error(e);
			throw new ValidationException("You can not revieing book before ordering.");
			
		}
		
	}
}

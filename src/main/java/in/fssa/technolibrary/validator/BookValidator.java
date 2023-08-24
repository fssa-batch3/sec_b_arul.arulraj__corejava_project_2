package in.fssa.technolibrary.validator;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.regex.Pattern;

import in.fssa.technolibrary.dao.BookDAO;
import in.fssa.technolibrary.dao.CategoryDAO;
import in.fssa.technolibrary.dao.PublisherDAO;
import in.fssa.technolibrary.exception.PersistanceException;
import in.fssa.technolibrary.exception.ValidationException;
import in.fssa.technolibrary.model.Book;
import in.fssa.technolibrary.util.StringUtil;

public class BookValidator {
	private static final String NAME_PATTERN = "^[A-Za-z][A-Za-z\\\\s]*$";
	/**
	 * 
	 * @param book
	 * @throws ValidationException
	 * @throws PersistanceException 
	 */
	public static void validate(Book book) throws ValidationException, PersistanceException {
		if (book == null) {
			throw new ValidationException("Invalid user input");
		}
		
		validateTitle(book.getTitle());
		validateAuthorNamePattern(book.getAuthor());
		PublisherValidator.validateId(book.getPublisherId());
		CategoryValidator.validateId(book.getCategoryId());
		validatePublisherId(book.getPublisherId());
		validateCategoryId(book.getCategoryId());
		validatePrice(book.getPrice());
		validateDate(book.getPublishedDate());
		
	}
	public static void validateAthor(String author) throws ValidationException, PersistanceException {
		validateAuthorNamePattern(author);
		BookDAO.authorAlreadyExistOrNot(author);
	}
		
	/**
	 * 
	 * @param title
	 * @throws ValidationException
	 */
	public static void validateTitle(String title) throws ValidationException {
		
		StringUtil.rejectIfInvalidString(title, "Title");
		
		if (!Pattern.matches(NAME_PATTERN, title)) {
			throw new ValidationException("Title doesn't match the pattern");
		}
	
	}
	/**
	 * 
	 * @param author
	 * @throws ValidationException
	 * @throws PersistanceException 
	 */
	public static void validateAuthorNamePattern(String authorName) throws ValidationException, PersistanceException {
		
		StringUtil.rejectIfInvalidString(authorName, "Author");
		
		if (!Pattern.matches(NAME_PATTERN, authorName)) {
			throw new ValidationException("Author name doesn't match the pattern");
		}
	
	}
	/**
	 * 
	 * @param id
	 * @throws ValidationException
	 * @throws PersistanceException 
	 */
	public static void validateId(int id) throws ValidationException, PersistanceException {
		if (id <= 0) {
			throw new ValidationException("Id can not be less than zero.");
		}
		BookDAO.bookIdAlreadyExistOrNot(id);
	
	}
	/**
	 * 
	 * @param publisherId
	 * @throws ValidationException
	 * @throws PersistanceException 
	 */
	public static void validatePublisherId(int publisherId) throws ValidationException, PersistanceException {
		if (publisherId <= 0) {
			throw new ValidationException("Publisher Id can not be less than zero.");
		}
		PublisherDAO.publisherIdAlreadyExistOrNot(publisherId);
	
	}
	/**
	 * 
	 * @param category_id
	 * @throws ValidationException
	 * @throws PersistanceException 
	 */
	public static void validateCategoryId(int categoryId) throws ValidationException, PersistanceException {
		if (categoryId <= 0) {
			throw new ValidationException("Category Id can not be less than zero.");
		}
		CategoryDAO.categoryIdAlreadyExistOrNot(categoryId);
	
	}
	/**
	 * 
	 * @param price
	 * @throws ValidationException
	 */
	public static void validatePrice(int price) throws ValidationException {
		if (price <= 0) {
			throw new ValidationException("Price can not be less than zero.");
		}
	
	}
	/**
	 * 
	 * @param date
	 * @throws ValidationException
	 */
	public static void validateDate(String date) throws ValidationException {
	    StringUtil.rejectIfInvalidString(date, "Date");
	    DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
	    LocalDate dueDate;
	    try {
	        dueDate = LocalDate.parse(date, inputFormatter);
	    } catch (DateTimeParseException e) {
	        throw new ValidationException("Invalid date or Invalid date format ( yyyy-MM-dd)");
	    }
	    String formattedDate = dueDate.format(inputFormatter);
	    System.out.println("Formatted Date: " + formattedDate);
	    LocalDate currentDate = LocalDate.now();
	    if (dueDate.equals(currentDate) || dueDate.isAfter(currentDate)) {
	        throw new ValidationException("Invalid date or Invalid date format ( yyyy-MM-dd)");
	    }
	}
}

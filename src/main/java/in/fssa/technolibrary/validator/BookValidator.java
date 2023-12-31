package in.fssa.technolibrary.validator;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.regex.Pattern;

import in.fssa.technolibrary.dao.BookDAO;
import in.fssa.technolibrary.dao.CategoryDAO;
import in.fssa.technolibrary.dao.PublisherDAO;
import in.fssa.technolibrary.exception.PersistanceException;
import in.fssa.technolibrary.exception.ServiceException;
import in.fssa.technolibrary.exception.ValidationException;
import in.fssa.technolibrary.model.Book;
import in.fssa.technolibrary.util.Logger;
import in.fssa.technolibrary.util.StringUtil;

public class BookValidator {
	private static final String NAME_PATTERN = "^[A-Za-z][A-Za-z\\s]*$";
	
	private static final String BOOK_PATTERN = "^[A-Za-z0-9][A-Za-z0-9\\s]*$";


	/**
	 * 
	 * @param book
	 * @throws ValidationException
	 * @throws PersistanceException
	 * @throws ServiceException
	 */
	public static void validate(Book book) throws ValidationException {
		if (book == null) {
			throw new ValidationException("Invalid user input");
		}
		validateAuthor(book.getAuthor());
		rejectIfBookTitleExist(book.getTitle());
		validateTitle(book.getTitle());
		validatePublisherId(book.getPublisherId());
		validateCategoryId(book.getCategoryId());
		validatePrice(book.getPrice());
		validatePublishedDate(book.getPublishedDate());
	}
	/**
	 * 
	 * @param authorName
	 * @throws ValidationException
	 * @throws ServiceException
	 */
	public static void validateFindAuthor(String authorName) throws ValidationException {
		try {
			validateAuthorNamePattern(authorName);

			BookDAO.authorAlreadyExistOrNot(authorName);

		} catch (PersistanceException e) {
			throw new ValidationException("Author doesn't exist");
		}
	}
	
	public static void validateAuthor(String authorName) throws ValidationException {
			validateAuthorNamePattern(authorName);
	}

	/**
	 * 
	 * @param bookTitle
	 * @throws ValidationException
	 * @throws ServiceException 
	 */
	public static void rejectIfBookTitleExist(String bookTitle) throws ValidationException {
		try {
			BookDAO.bookNameAlreadyExist(bookTitle);
		} catch (PersistanceException e) {
			Logger.error(e);
			throw new ValidationException("Book already Exist");
		}
	}

	/**
	 * 
	 * @param title
	 * @throws ValidationException
	 */
	public static void validateTitle(String bookTitle) throws ValidationException {

		StringUtil.rejectIfInvalidString(bookTitle, "Title");

		if (!Pattern.matches(BOOK_PATTERN, bookTitle)) {
			throw new ValidationException("Title doesn't match the pattern");
		}
	}

	/**
	 * 
	 * @param author
	 * @throws ValidationException
	 * @throws PersistanceException
	 */
	public static void validateAuthorNamePattern(String authorName) throws ValidationException {

		StringUtil.rejectIfInvalidString(authorName, "Author");

		if (!Pattern.matches(NAME_PATTERN, authorName)) {
			throw new ValidationException("Author name doesn't match the pattern");
		}

	}
	
	public static void validateUpdatedBook(Book updatedBook) throws ValidationException {
		
		BookValidator.validateTitle(updatedBook.getTitle());
		BookValidator.validateCategoryId(updatedBook.getCategoryId());
		BookValidator.validatePublisherId(updatedBook.getPublisherId());
		BookValidator.validateAuthorNamePattern(updatedBook.getAuthor());
		BookValidator.validatePrice(updatedBook.getPrice());
		BookValidator.validatePublishedDate(updatedBook.getPublishedDate());
	}

	/**
	 * 
	 * @param id
	 * @throws ValidationException
	 * @throws PersistanceException
	 */
	public static void validateId(int bookId) throws ValidationException {
		try {
			if (bookId <= 0) {
				throw new ValidationException("Id can not be less than zero.");
			}
			BookDAO.bookIdAlreadyExistOrNot(bookId);
		} catch (PersistanceException e) {
			Logger.error(e);
			throw new ValidationException("Book doesn't exist");
		}
	}

	/**
	 * 
	 * @param publisherId
	 * @throws ValidationException
	 * @throws PersistanceException
	 * @throws ServiceException
	 */
	public static void validatePublisherId(int publisherId) throws ValidationException {
		try {
			if (publisherId <= 0) {
				throw new ValidationException("Publisher Id can not be less than zero.");
			}
			PublisherDAO.publisherIdAlreadyExistOrNot(publisherId);
		} catch (PersistanceException e) {
			Logger.error(e);
			throw new ValidationException("Publisher doesn't exist");
		}
	}

	/**
	 * 
	 * @param category_id
	 * @throws ValidationException
	 * @throws PersistanceException
	 * @throws ServiceException
	 */
	public static void validateCategoryId(int categoryId) throws ValidationException{
		try {
			if (categoryId <= 0) {

				throw new ValidationException("Category Id can not be less than zero.");
			}
			CategoryDAO.categoryIdAlreadyExistOrNot(categoryId);

		} catch (PersistanceException e) {
			Logger.error(e);
			throw new ValidationException("Category doesn't exist");
		}
	}
	
	

	/**
	 * 
	 * @param price
	 * @throws ValidationException
	 */
	public static void validatePrice(int bookPrice) throws ValidationException {
		if (bookPrice <= 0) {
			throw new ValidationException("Price can not be less than zero.");
		}

	}

	/**
	 * 
	 * @param date
	 * @throws ValidationException
	 */
	public static void validatePublishedDate(String publishedate) throws ValidationException {
		StringUtil.rejectIfInvalidString(publishedate, "Date");
		DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		LocalDate publisheddate;
		try {
			publisheddate = LocalDate.parse(publishedate, inputFormatter);
		} catch (DateTimeParseException e) {
			Logger.error(e);
			throw new ValidationException("Invalid date or Invalid date format (yyyy-MM-dd)");
		}
		String formattedDate = publisheddate.format(inputFormatter);
		System.out.println("Formatted Date: " + formattedDate);
		LocalDate currentDate = LocalDate.now();
		if (publisheddate.isAfter(currentDate)) {
			throw new ValidationException("Date can't be in future");
		}
	}
}

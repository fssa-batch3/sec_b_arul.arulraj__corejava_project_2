package in.fssa.technolibrary.validator;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.regex.Pattern;

import in.fssa.technolibrary.exception.ValidationException;
import in.fssa.technolibrary.model.Book;
import in.fssa.technolibrary.util.StringUtil;

public class BookValidator {
	
	private static final String NAME_PATTERN = "^[A-Za-z][A-Za-z\\\\s]*$";
	
	public static void validate(Book book) throws ValidationException {
		if (book == null) {
			throw new ValidationException("Invalid user input");
		}
		
		validateId(book.getId());
		validateTitle(book.getTitle());
		validateAuthorName(book.getAuthor());
		validatePublisherId(book.getPublisherId());
		validatePublisherId(book.getPublisherId());
		validatePrice(book.getPrice());
		validateDate(book.getPublishedDate());
	}
		
	
	public static void validateTitle(String title) throws ValidationException {
		
		StringUtil.rejectIfInvalidString(title, "Title");
		
		if (!Pattern.matches(NAME_PATTERN, title)) {
			throw new ValidationException("Title doesn't match the pattern");
		}
	
	}
	
	public static void validateAuthorName(String author) throws ValidationException {
		
		StringUtil.rejectIfInvalidString(author, "Author");
		
		if (!Pattern.matches(NAME_PATTERN, author)) {
			throw new ValidationException("Author name doesn't match the pattern");
		}
	
	}
	
	public static void validateId(int id) throws ValidationException {
		if (id <= 0) {
			throw new ValidationException("Id can not be less than zero.");
		}
	
	}
	
	public static void validatePublisherId(int publisher_id) throws ValidationException {
		if (publisher_id <= 0) {
			throw new ValidationException("Publisher Id can not be less than zero.");
		}
	
	}
	
	public static void validateCategoryId(int category_id) throws ValidationException {
		if (category_id <= 0) {
			throw new ValidationException("Category Id can not be less than zero.");
		}
	
	}
	
	public static void validatePrice(int price) throws ValidationException {
		if (price <= 0) {
			throw new ValidationException("Price can not be less than zero.");
		}
	
	}
	
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
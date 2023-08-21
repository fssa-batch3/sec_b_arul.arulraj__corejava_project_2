package in.fssa.technolibrary;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import in.fssa.technolibrary.model.Book;
import in.fssa.technolibrary.service.BookService;
import in.fssa.technolibrary.exception.PersistanceException;
import in.fssa.technolibrary.exception.ValidationException;

public class TestCreateBook {

	@Test
	public void testCreateBookWithValidDetails(){

		BookService bookService = new BookService();
		
		Book newBook = new Book();
		
		newBook.setAuthor("Tommy");
		newBook.setTitle("LK");
		newBook.setCategoryId(1);
		newBook.setPublisherId(1);
		newBook.setPublishedDate("2023-01-12");
		newBook.setPrice(5100);

		assertDoesNotThrow(() -> {
			bookService.create(newBook);
		});
	}
	
	@Test
	public void testAuthorNameNull() {

		BookService bookService = new BookService();

		Book newBook = new Book();

		newBook.setAuthor(null);
		newBook.setTitle("IR");
		newBook.setCategoryId(1);
		newBook.setPublisherId(1);
		newBook.setPublishedDate("2023-01-12");
		newBook.setPrice(1000);

		Exception exception = assertThrows(ValidationException.class, () -> {
			bookService.create(newBook);
		});

		String exceptedMessage = "Author cannot be null or empty";
		String actualMessage = exception.getMessage();

		assertTrue(exceptedMessage.equals(actualMessage));
	}
	
	@Test
	public void testAuthorNameEmpty() {

		BookService bookService = new BookService();

		Book newBook = new Book();

		newBook.setAuthor("");
		newBook.setTitle("IR");
		newBook.setCategoryId(1);
		newBook.setPublisherId(1);
		newBook.setPublishedDate("2023-01-12");
		newBook.setPrice(1000);

		Exception exception = assertThrows(ValidationException.class, () -> {
			bookService.create(newBook);
		});

		String exceptedMessage = "Author cannot be null or empty";
		String actualMessage = exception.getMessage();

		assertTrue(exceptedMessage.equals(actualMessage));
	}
	
	@Test
	public void testAuthorNamePattern() {

		BookService bookService = new BookService();

		Book newBook = new Book();

		newBook.setAuthor("142fr");
		newBook.setTitle("IR");
		newBook.setCategoryId(1);
		newBook.setPublisherId(1);
		newBook.setPublishedDate("2023-01-12");
		newBook.setPrice(1000);

		Exception exception = assertThrows(ValidationException.class, () -> {
			bookService.create(newBook);
		});

		String exceptedMessage = "Author name doesn't match the pattern";
		String actualMessage = exception.getMessage();

		assertTrue(exceptedMessage.equals(actualMessage));
	}
	@Test
	public void testAuthorAlreadyExistOrNot() {

		BookService bookService = new BookService();

		Exception exception = assertThrows(PersistanceException.class, () -> {
			bookService.findByAuthor("gt");
		});

		String exceptedMessage = "Author doesn't exist";
		String actualMessage = exception.getMessage();

		assertTrue(exceptedMessage.equals(actualMessage));
	}
	@Test
	public void testBookAlreadyExistOrNot() {

		BookService bookService = new BookService();

		Exception exception = assertThrows(PersistanceException.class, () -> {
			bookService.findById(11);
		});

		String exceptedMessage = "Book doesn't exist";
		String actualMessage = exception.getMessage();

		assertTrue(exceptedMessage.equals(actualMessage));
	}
	@Test
	public void testPublisherId() {

		BookService bookService = new BookService();

		Book newBook = new Book();

		newBook.setAuthor("Ajun");
		newBook.setTitle("IR");
		newBook.setCategoryId(1);
		newBook.setPublisherId(0);
		newBook.setPublishedDate("2023-01-12");
		newBook.setPrice(1000);

		Exception exception = assertThrows(ValidationException.class, () -> {
			bookService.create(newBook);
		});

		String exceptedMessage = "Publisher Id can not be less than zero.";
		String actualMessage = exception.getMessage();

		assertTrue(exceptedMessage.equals(actualMessage));
	}
	
	@Test
	public void testCategoryId() {

		BookService bookService = new BookService();

		Book newBook = new Book();

		newBook.setAuthor("Ajun");
		newBook.setTitle("IR");
		newBook.setCategoryId(0);
		newBook.setPublisherId(1);
		newBook.setPublishedDate("2023-01-12");
		newBook.setPrice(1000);

		Exception exception = assertThrows(ValidationException.class, () -> {
			bookService.create(newBook);
		});

		String exceptedMessage = "Category Id can not be less than zero.";
		String actualMessage = exception.getMessage();

		assertTrue(exceptedMessage.equals(actualMessage));
	}
	
	@Test
	public void testPrice() {

		BookService bookService = new BookService();

		Book newBook = new Book();

		newBook.setAuthor("Ajun");
		newBook.setTitle("IR");
		newBook.setCategoryId(1);
		newBook.setPublisherId(1);
		newBook.setPublishedDate("2023-01-12");
		newBook.setPrice(0);

		Exception exception = assertThrows(ValidationException.class, () -> {
			bookService.create(newBook);
		});

		String exceptedMessage = "Price can not be less than zero.";
		String actualMessage = exception.getMessage();

		assertTrue(exceptedMessage.equals(actualMessage));
	}
	
	@Test
	public void testDateNull() {

		BookService bookService = new BookService();

		Book newBook = new Book();

		newBook.setAuthor("Ajun");
		newBook.setTitle("IR");
		newBook.setCategoryId(1);
		newBook.setPublisherId(1);
		newBook.setPublishedDate(null);
		newBook.setPrice(1000);

		Exception exception = assertThrows(ValidationException.class, () -> {
			bookService.create(newBook);
		});

		String exceptedMessage = "Date cannot be null or empty";
		String actualMessage = exception.getMessage();

		assertTrue(exceptedMessage.equals(actualMessage));
	}
	
	@Test
	public void testDateEmpty() {

		BookService bookService = new BookService();

		Book newBook = new Book();

		newBook.setAuthor("Ajun");
		newBook.setTitle("IR");
		newBook.setCategoryId(1);
		newBook.setPublisherId(1);
		newBook.setPublishedDate("");
		newBook.setPrice(1000);

		Exception exception = assertThrows(ValidationException.class, () -> {
			bookService.create(newBook);
		});

		String exceptedMessage = "Date cannot be null or empty";
		String actualMessage = exception.getMessage();

		assertTrue(exceptedMessage.equals(actualMessage));
	}
	
	@Test
	public void testDateFormat() {

		BookService bookService = new BookService();
		
		Book newBook = new Book();

		newBook.setAuthor("Ajun");
		newBook.setTitle("IR");
		newBook.setCategoryId(1);
		newBook.setPublisherId(1);
		newBook.setPublishedDate("01-12-2023");
		newBook.setPrice(1000);

		Exception exception = assertThrows(ValidationException.class, () -> {
			bookService.create(newBook);
		});

		String exceptedMessage = "Invalid date or Invalid date format ( yyyy-MM-dd)";
		String actualMessage = exception.getMessage();

		assertTrue(exceptedMessage.equals(actualMessage));
	}
	
	@Test
	public void testDateValidRange() {

		BookService bookService = new BookService();

		Book newBook = new Book();

		newBook.setAuthor("Ajun");
		newBook.setTitle("IR");
		newBook.setCategoryId(1);
		newBook.setPublisherId(1);
		newBook.setPublishedDate("2023-09-12");
		newBook.setPrice(1000);

		Exception exception = assertThrows(ValidationException.class, () -> {
			bookService.create(newBook);
		});

		String exceptedMessage = "Invalid date or Invalid date format ( yyyy-MM-dd)";
		String actualMessage = exception.getMessage();

		assertTrue(exceptedMessage.equals(actualMessage));
	}
	
	@Test
	public void testCategoryIdAlreadyExistOrNot() {

		BookService bookService = new BookService();

		Exception exception = assertThrows(RuntimeException.class, () -> {
			bookService.findByCategoryId(10);
		});

		String exceptedMessage = "Category doesn't exist";
		String actualMessage = exception.getMessage();

		assertTrue(exceptedMessage.equals(actualMessage));
	}
	
	@Test
	public void testPublisherIdAlreadyExistOrNot() {

		BookService bookService = new BookService();

		Exception exception = assertThrows(PersistanceException.class, () -> {
			bookService.findByPublisherId(10);
		});

		String exceptedMessage = "Publisher doesn't exist";
		String actualMessage = exception.getMessage();

		assertTrue(exceptedMessage.equals(actualMessage));
	}
}

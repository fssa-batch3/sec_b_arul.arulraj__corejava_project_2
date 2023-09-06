package in.fssa.technolibrary;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Random;

import org.junit.jupiter.api.Test;

import in.fssa.technolibrary.model.Book;
import in.fssa.technolibrary.service.BookService;
import in.fssa.technolibrary.exception.ValidationException;

class TestCreateBook {

	@Test
	void testCreateBookWithValidDetails() throws Exception {

		Book newBook = new Book();

		String generatedAuthor = generateRandomAlphabeticName();
		String generatedTitle = generateRandomAlphabeticName();

		newBook.setAuthor(generatedAuthor);
		newBook.setTitle(generatedTitle);
		newBook.setCategoryId(1);
		newBook.setPublisherId(1);
		newBook.setPublishedDate("2023-01-03");
		newBook.setPrice(5100);
		assertDoesNotThrow(() -> {
			BookService.createNewBook(newBook);
		});

	}

	private String generateRandomAlphabeticName() {
		int nameLength = 10;
		Random random = new Random();
		StringBuilder sb = new StringBuilder(nameLength);
		for (int i = 1; i < nameLength; i++) {
			char randomChar = (char) (random.nextInt(26) + 'a');
			sb.append(randomChar);
		}
		return sb.toString();
	}
	
	@Test
	void testBookTitleWithAlreadyExistName() {

		Book newBook = new Book();

		newBook.setAuthor("Tjhg");
		newBook.setTitle("BookofMagic");
		newBook.setCategoryId(1);
		newBook.setPublisherId(1);
		newBook.setPublishedDate("12-01-2023");
		newBook.setPrice(1000);

		Exception exception = assertThrows(ValidationException.class, () -> {
			BookService.createNewBook(newBook);
		});

		String exceptedMessage = "Book name already exist";
		String actualMessage = exception.getMessage();
		System.out.print(actualMessage);
		assertEquals(exceptedMessage, actualMessage);
	}

	@Test
	void testAuthorNameNull() {

		Book newBook = new Book();

		newBook.setAuthor(null);
		newBook.setTitle("IR");
		newBook.setCategoryId(1);
		newBook.setPublisherId(1);
		newBook.setPublishedDate("12-01-2023");
		newBook.setPrice(1000);

		Exception exception = assertThrows(ValidationException.class, () -> {
			BookService.createNewBook(newBook);
		});

		String exceptedMessage = "Author cannot be null or empty";
		String actualMessage = exception.getMessage();

		assertTrue(exceptedMessage.equals(actualMessage));
	}

	@Test
	void testAuthorNameEmpty() {

		Book newBook = new Book();

		newBook.setAuthor("");
		newBook.setTitle("IR");
		newBook.setCategoryId(1);
		newBook.setPublisherId(1);
		newBook.setPublishedDate("12-01-2023");
		newBook.setPrice(1000);

		Exception exception = assertThrows(ValidationException.class, () -> {
			BookService.createNewBook(newBook);
		});

		String exceptedMessage = "Author cannot be null or empty";
		String actualMessage = exception.getMessage();

		assertEquals(exceptedMessage, actualMessage);
	}

	@Test
	void testAuthorNamePattern() {

		Book newBook = new Book();

		newBook.setAuthor("a24fr");
		newBook.setTitle("IR");
		newBook.setCategoryId(1);
		newBook.setPublisherId(1);
		newBook.setPublishedDate("12-01-2023");
		newBook.setPrice(1000);

		Exception exception = assertThrows(ValidationException.class, () -> {
			BookService.createNewBook(newBook);
		});

		String exceptedMessage = "Author name doesn't match the pattern";
		String actualMessage = exception.getMessage();

		assertTrue(exceptedMessage.equals(actualMessage));
	}

	@Test
	void testAuthorAlreadyExistOrNot() {

		BookService bookService = new BookService();

		Exception exception = assertThrows(ValidationException.class, () -> {
			bookService.findByAuthorName("gvhgv");
		});

		String exceptedMessage = "Author doesn't exist.";
		String actualMessage = exception.getMessage();
		assertTrue(exceptedMessage.equals(actualMessage));
	}

	@Test
	public void testBookIdAlreadyExistOrNot() {

		Exception exception = assertThrows(ValidationException.class, () -> {
			BookService.findBookById(2000);
		});

		String exceptedMessage = "Book doesn't exist";
		String actualMessage = exception.getMessage();

		assertEquals(exceptedMessage, actualMessage);
	}

	@Test
	void testPublisherId() {

		Book newBook = new Book();

		newBook.setAuthor("Ajun");
		newBook.setTitle("gczgh");
		newBook.setCategoryId(1);
		newBook.setPublisherId(0);
		newBook.setPublishedDate("12-01-2023");
		newBook.setPrice(1000);

		Exception exception = assertThrows(ValidationException.class, () -> {
			BookService.createNewBook(newBook);
		});

		String exceptedMessage = "Publisher Id can not be less than zero.";
		String actualMessage = exception.getMessage();

		assertEquals(exceptedMessage,actualMessage);
	}

	@Test
	void testCategoryId() {

		Book newBook = new Book();

		newBook.setAuthor("Ajun");
		newBook.setTitle("vdeh");
		newBook.setCategoryId(0);
		newBook.setPublisherId(1);
		newBook.setPublishedDate("12-01-2023");
		newBook.setPrice(1000);

		Exception exception = assertThrows(ValidationException.class, () -> {
			BookService.createNewBook(newBook);
		});

		String exceptedMessage = "Category Id can not be less than zero.";
		String actualMessage = exception.getMessage();
		System.out.print(actualMessage);
		assertEquals(exceptedMessage,actualMessage);	}

	@Test
	void testPrice() {

		Book newBook = new Book();

		newBook.setAuthor("Ajun");
		newBook.setTitle("gfxred");
		newBook.setCategoryId(1);
		newBook.setPublisherId(1);
		newBook.setPublishedDate("12-01-2023");
		newBook.setPrice(0);

		Exception exception = assertThrows(ValidationException.class, () -> {
			BookService.createNewBook(newBook);
		});

		String exceptedMessage = "Price can not be less than zero.";
		String actualMessage = exception.getMessage();

		assertEquals(exceptedMessage,actualMessage);
	}

	@Test
	void testDateNull() {
	    Book newBook = new Book();

	    newBook.setAuthor("Ajun");
	    newBook.setTitle("Sesslyn");
	    newBook.setCategoryId(1);
	    newBook.setPublisherId(1);
	    newBook.setPublishedDate(null); // Set publishedDate to null
	    newBook.setPrice(1000);

	    Exception exception = assertThrows(ValidationException.class, () -> {
	        BookService.createNewBook(newBook);
	    });

	    String expectedMessage = "Date cannot be null or empty";
	    String actualMessage = exception.getMessage();

	    assertEquals(expectedMessage, actualMessage);
	}


	@Test
	void testDateEmpty() {

		Book newBook = new Book();

		newBook.setAuthor("Ajun");
		newBook.setTitle("ffxd");
		newBook.setCategoryId(1);
		newBook.setPublisherId(1);
		newBook.setPublishedDate("");
		newBook.setPrice(1000);

		Exception exception = assertThrows(ValidationException.class, () -> {
			BookService.createNewBook(newBook);
		});

		String exceptedMessage = "Date cannot be null or empty";
		String actualMessage = exception.getMessage();

		assertEquals(exceptedMessage,actualMessage);
	}

	@Test
	void testDateFormat() {

		Book newBook = new Book();

		newBook.setAuthor("Ajun");
		newBook.setTitle("ghcgf");
		newBook.setCategoryId(1);
		newBook.setPublisherId(1);
		newBook.setPublishedDate("23-03-2022");
		newBook.setPrice(1000);

		Exception exception = assertThrows(ValidationException.class, () -> {
			BookService.createNewBook(newBook);
		});

		String exceptedMessage = "Invalid date or Invalid date format (yyyy-MM-dd)";
		String actualMessage = exception.getMessage();

		assertEquals(exceptedMessage,actualMessage);
	}

	@Test
	void testDateInvalideFormate() {

		Book newBook = new Book();

		newBook.setAuthor("Ajun");
		newBook.setTitle("ghchgf");
		newBook.setCategoryId(1);
		newBook.setPublisherId(1);
		newBook.setPublishedDate("2030-12-31");
		newBook.setPrice(1000);

		Exception exception = assertThrows(ValidationException.class, () -> {
			BookService.createNewBook(newBook);
		});

		String exceptedMessage = "Date can't be in future";
		String actualMessage = exception.getMessage();
		System.out.print(actualMessage);
		assertEquals(exceptedMessage,actualMessage);
	}

	@Test
	void testCategoryIdAlreadyExistOrNot() {

		BookService bookService = new BookService();

		Exception exception = assertThrows(ValidationException.class, () -> {
			bookService.findBookByCategoryId(5000);
		});

		String exceptedMessage = "Category doesn't exist";
		String actualMessage = exception.getMessage();

		assertEquals(exceptedMessage, actualMessage);
	}

	@Test
	void testPublisherIdAlreadyExistOrNot() {

		BookService bookService = new BookService();

		Exception exception = assertThrows(ValidationException.class, () -> {
			bookService.findBookByPublisherId(1000);
		});

		String exceptedMessage = "Publisher doesn't exist";
		String actualMessage = exception.getMessage();

		assertEquals(exceptedMessage, actualMessage);
	}
}

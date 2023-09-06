package in.fssa.technolibrary;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.Calendar;
import java.util.Random;
import java.util.Set;
import org.junit.jupiter.api.Test;
import in.fssa.technolibrary.exception.ServiceException;
import in.fssa.technolibrary.exception.ValidationException;
import in.fssa.technolibrary.model.Book;
import in.fssa.technolibrary.service.BookService;

class TestGetAllBook {
	@Test
	void getAllBook() throws ServiceException {
		BookService bookService = new BookService();
		Set<Book> books = bookService.findAllBook();
		System.out.print(books);
	}

	@Test
	void getBookById() throws ServiceException, ValidationException {
		Book book = BookService.findBookById(1);
		System.out.print(book);
	}

	@Test
	void getBookByAuthor() throws ValidationException, ServiceException {
		BookService bookService = new BookService();
		Set<Book> book = bookService.findByAuthorName("Ramuu");
		System.out.print(book);
	}

	@Test
	void getBookByCategoryId() throws ServiceException, ValidationException {
		BookService bookService = new BookService();
		Set<Book> book = bookService.findBookByCategoryId(1);
		System.out.print(book);
	}

	@Test
	void getBookByPublisherId() throws ServiceException, ValidationException {
		BookService bookService = new BookService();
		Set<Book> book = bookService.findBookByPublisherId(1);
		System.out.print(book);
	}

	@Test
	void testDelete() throws ValidationException, ServiceException {
		BookService bookService = new BookService();
		bookService.deleteBook(1);
	}

	@Test
	void testUpdateTitleAndDate() throws ValidationException, ServiceException {
		
		String generatedTitle = generateRandomString(7);
		String generatedDate = generateRandomPreviousDate();
		Book newData = new Book();
		newData.setPublishedDate(generatedDate);
		newData.setTitle(generatedTitle);
		BookService.updateBookTitleAndDate(2, newData);
	}

	private String generateRandomString(int length) {
		String characters = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
		StringBuilder randomString = new StringBuilder();
		Random random = new Random();
		for (int i = 0; i < length; i++) {
			int index = random.nextInt(characters.length());
			randomString.append(characters.charAt(index));
		}
		return randomString.toString();
	}

	private String generateRandomPreviousDate() {
		Calendar calendar = Calendar.getInstance();
		int year = calendar.get(Calendar.YEAR) - 1;
		int month = new Random().nextInt(12) + 1;
		int day = new Random().nextInt(28) + 1;

		String formattedDate = String.format("%04d-%02d-%02d", year, month, day);
		return formattedDate;
	}

	@Test
	void testupdateTitleAndDateWithSameTitleAndDate() throws ValidationException {
		Book newData = new Book();
		newData.setPublishedDate("2022-10-12");
		newData.setTitle("BookofMagic");

		Exception exception = assertThrows(ValidationException.class, () -> {
			BookService.updateBookTitleAndDate(6, newData);
		});

		String expectedMessage = "New Title And Published Date is the same as the old Title And Published Date.";
		String actualMessage = exception.getMessage();
		assertEquals(actualMessage,expectedMessage);

	}

	@Test
	void testUpdatePrice() throws ValidationException, ServiceException {

		// Generate a random price between a specified range
		int minPrice = 1000;
		int maxPrice = 5000;
		int randomPrice = generateRandomPrice(minPrice, maxPrice);

		Book newPrice = new Book();
		newPrice.setPrice(randomPrice);

		BookService.updateBookPrice(1, newPrice);
	}

	private int generateRandomPrice(int minPrice, int maxPrice) {
		return minPrice + (int) (Math.random() * (maxPrice - minPrice + 1));
	}

	@Test
	void testUpdatePriceWithTheSamePrice() throws ValidationException {
		Book newPrice = new Book();
		newPrice.setPrice(5000);

		Exception exception = assertThrows(ValidationException.class, () -> {
			BookService.updateBookPrice(3, newPrice);
		});

		String expectedMessage = "New price is the same as the old price.";
		String actualMessage = exception.getMessage();
		System.out.print(actualMessage);
		assertEquals(actualMessage, expectedMessage);
	}

	@Test
	void testupdateAuthorNamePublisheIdCategoryId() throws ValidationException, ServiceException {

		Book existingBook = BookService.findBookById(2);

		Book newData = new Book();

		// Generate a random author name different from the existing one
		String randomAuthor;
		do {
			randomAuthor = generateRandomAuthor();
		} while (randomAuthor.equals(existingBook.getAuthor()));
		newData.setAuthor(randomAuthor);

		// Generate a random publisher id different from the existing one
		int randomPublisherId;
		do {
			randomPublisherId = generateRandomId(1, 5);
		} while (randomPublisherId == existingBook.getPublisherId());
		newData.setPublisherId(randomPublisherId);

		// Generate a random category id different from the existing one
		int randomCategoryId;
		do {
			randomCategoryId = generateRandomId(1, 5);
		} while (randomCategoryId == existingBook.getCategoryId());
		newData.setCategoryId(randomCategoryId);

		BookService.updateBookAuthorNamePublisherIdCategoryId(2, newData);
	}

	private String generateRandomAuthor() {
		String[] authors = { "AuthorA", "AuthorB", "AuthorC", "AuthorD" };
		Random random = new Random();
		return authors[random.nextInt(authors.length)];
	}

	private int generateRandomId(int min, int max) {
		Random random = new Random();
		return random.nextInt(max - min + 1) + min;
	}

	@Test
	void testupdateAuthorNamePublisheIdCategoryIdSameDetails() {
		Book newData = new Book();
		newData.setAuthor("Ramuu");
		newData.setPublisherId(2);
		newData.setCategoryId(2);

		Exception exception = assertThrows(ValidationException.class, () -> {
			BookService.updateBookAuthorNamePublisherIdCategoryId(1, newData);
		});

		String expectedMessage = "New Author ,Published Id And Category Id is the same as the old Author ,Published Id And Category Id.";
		String actualMessage = exception.getMessage();
		assertEquals(actualMessage, expectedMessage);
	}

}

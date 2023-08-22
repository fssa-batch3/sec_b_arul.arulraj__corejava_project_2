package in.fssa.technolibrary;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Calendar;
import java.util.Random;
import java.util.Set;
import org.junit.jupiter.api.Test;
import in.fssa.technolibrary.exception.PersistanceException;
import in.fssa.technolibrary.exception.ValidationException;
import in.fssa.technolibrary.model.Book;
import in.fssa.technolibrary.service.BookService;

public class TestGetAllBook {
	@Test
	public void getAllUser() throws PersistanceException {
		BookService bookService = new BookService();

		Set<Book> books = bookService.findAll();

		System.out.print(books);
	}

	@Test
	public void getBookById() throws Exception {
		BookService bookService = new BookService();
		Book book = bookService.findById(1);
		System.out.print(book);
	}

	@Test
	public void getBookByAuthor() throws Exception {
		BookService bookService = new BookService();
		Set<Book> book = bookService.findByAuthor("Ramuu");
		System.out.print(book);
	}
	
	@Test
	public void getBookByCategoryId() throws Exception {
		BookService bookService = new BookService();
		Set<Book> book = bookService.findByCategoryId(1);
		System.out.print(book);
	}

	@Test
	public void getBookByPublisherId() throws Exception {
		BookService bookService = new BookService();
		Set<Book> book = bookService.findByPublisherId(1);
		System.out.print(book);
	}

	@Test
	public void testDelete() throws ValidationException, PersistanceException {
		BookService bookService = new BookService();
		bookService.delete(1);
	}

	 @Test
	    public void testUpdateTitleAndDate() throws Exception {
	        BookService bookService = new BookService();

	        // Generate random title
	        String generatedTitle = generateRandomString(7);

	        // Generate random previous date
	        String generatedDate = generateRandomPreviousDate();

	        Book newData = new Book();
	        newData.setPublishedDate(generatedDate);
	        newData.setTitle(generatedTitle);

	        bookService.updateTitleAndDate(2, newData);
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
	public void testupdateTitleAndDateWithSameTitleAndDate() throws ValidationException {
		BookService bookService = new BookService();
		Book newData = new Book();
		newData.setPublishedDate("2022-10-12");
		newData.setTitle("BookofMagic");

		Exception exception = assertThrows(ValidationException.class, () -> {
			bookService.updateTitleAndDate(6, newData);
		});

		String expectedMessage = "New Title And Published Date is the same as the old Title And Published Date.";
		String actualMessage = exception.getMessage();
		assertTrue(actualMessage.equals(expectedMessage));

	}

	@Test
    public void testUpdatePrice() throws Exception {
        BookService bookService = new BookService();
        
        // Generate a random price between a specified range
        int minPrice = 1000;
        int maxPrice = 5000;
        int randomPrice = generateRandomPrice(minPrice, maxPrice);

        Book newPrice = new Book();
        newPrice.setPrice(randomPrice);
        
        bookService.updatePrice(1, newPrice);
    }

    private int generateRandomPrice(int minPrice, int maxPrice) {
        return minPrice + (int) (Math.random() * (maxPrice - minPrice + 1));
    }

	@Test
	public void testUpdatePriceWithTheSamePrice() throws ValidationException, PersistanceException {
		BookService bookService = new BookService();
		Book newPrice = new Book();
		bookService.findById(1);
		newPrice.setPrice(1500);

		Exception exception = assertThrows(PersistanceException.class, () -> {
			bookService.updatePrice(1, newPrice);
		});

		String expectedMessage = "New price is the same as the old price.";
		String actualMessage = exception.getMessage();
		assertEquals(actualMessage,expectedMessage);
	}
	
	@Test
	public void testupdateAuthorNamePublisheIdCategoryId() throws ValidationException, PersistanceException {
		BookService bookService = new BookService();
		Book newData = new Book();
		newData.setAuthor("UTGb");
		newData.setPublisherId(2);
		newData.setCategoryId(5);
		bookService.updateAuthorNamePublisheIdCategoryId(2, newData);
		}
	
	@Test
	public void testupdateAuthorNamePublisheIdCategoryIdSameDetails() throws PersistanceException {
		BookService bookService = new BookService();
		Book newData = new Book();
		newData.setAuthor("Ramuu");
		newData.setPublisherId(2);
		newData.setCategoryId(2);

		Exception exception = assertThrows(ValidationException.class, () -> {
			bookService.updateAuthorNamePublisheIdCategoryId(1, newData);
		});

		String expectedMessage = "New Author ,Published Id And Category Id is the same as the old Author ,Published Id And Category Id.";
		String actualMessage = exception.getMessage();
		assertEquals(actualMessage,expectedMessage);
	}

}

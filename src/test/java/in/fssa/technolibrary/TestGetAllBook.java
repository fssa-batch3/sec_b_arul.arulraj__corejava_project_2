package in.fssa.technolibrary;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

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
		Set<Book> book = bookService.findByAuthor("Tommy");
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
	public void testupdateTitleAndDate() throws Exception {
		BookService bookService = new BookService();
		Book newData = new Book();
		newData.setPublishedDate("2022-09-12");
		newData.setTitle("BookofMagic");
		bookService.updateTitleAndDate(1, newData);
		}

	@Test
	public void testupdateTitleAndDateWithSameTitleAndDate() throws ValidationException {
		BookService bookService = new BookService();
		Book newData = new Book();
		newData.setPublishedDate("2022-10-12");
		newData.setTitle("BookofMagic");

		Exception exception = assertThrows(ValidationException.class, () -> {
			bookService.updateTitleAndDate(1, newData);
		});

		String expectedMessage = "New Title And Published Date is the same as the old Title And Published Date.";
		String actualMessage = exception.getMessage();
		assertTrue(actualMessage.equals(expectedMessage));

	}

	@Test
	public void testUpdatePrice() throws Exception {
		BookService bookService = new BookService();
		Book newPrice = new Book();
		newPrice.setPrice(1500);
		bookService.updatePrice(1, newPrice);
	}

	@Test
	public void testUpdatePriceWithTheSamePrice() throws Exception {
		BookService bookService = new BookService();
		Book newPrice = new Book();
		bookService.findById(1);
		newPrice.setPrice(1500);

		Exception exception = assertThrows(ValidationException.class, () -> {
			bookService.updatePrice(1, newPrice);
		});

		String expectedMessage = "New price is the same as the old price.";
		String actualMessage = exception.getMessage();
		assertTrue(actualMessage.equals(expectedMessage));
	}
	
	@Test
	public void testupdateAuthorNamePublisheIdCategoryId() throws ValidationException, PersistanceException {
		BookService bookService = new BookService();
		Book newData = new Book();
		newData.setAuthor("Ramuu");
		newData.setPublisherId(2);
		newData.setCategoryId(2);
		bookService.updateAuthorNamePublisheIdCategoryId(1, newData);
		}
	
	@Test
	public void testupdateAuthorNamePublisheIdCategoryIdSameDetails() throws ValidationException, PersistanceException {
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
		assertTrue(actualMessage.equals(expectedMessage));
	}

}

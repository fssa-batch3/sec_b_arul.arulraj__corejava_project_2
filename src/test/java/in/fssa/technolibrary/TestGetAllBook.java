package in.fssa.technolibrary;
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

}

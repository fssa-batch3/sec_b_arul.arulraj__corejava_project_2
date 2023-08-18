package in.fssa.technolibrary;

import java.util.Set;

import org.junit.jupiter.api.Test;

import in.fssa.technolibrary.exception.PersistanceException;
import in.fssa.technolibrary.exception.ValidationException;
import in.fssa.technolibrary.model.Book;
import in.fssa.technolibrary.service.BookService;

public class TestGetAllBook {
	@Test
	public void getAllUser() {
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
		bookService.delete(5556);
	}
}

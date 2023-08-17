package in.fssa.technolibrary;

import java.util.Set;

import org.junit.jupiter.api.Test;

import in.fssa.technolibrary.dao.BookDAO;
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
	public void delete(int taskId) {
		BookDAO taskDao = new BookDAO();
		taskDao.delete(taskId);
	}
	@Test
	public void testDelete() {
		BookService bookService = new BookService();
		Book newBook = new Book();
		
		bookService.delete(5556);
	}
}

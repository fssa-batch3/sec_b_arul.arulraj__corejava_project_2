package in.fssa.technolibrary.service;

import java.util.Set;

import in.fssa.technolibrary.dao.BookDAO;
import in.fssa.technolibrary.exception.ValidationException;
import in.fssa.technolibrary.model.Book;
import in.fssa.technolibrary.model.BookEntity;
import in.fssa.technolibrary.validator.BookValidator;
import in.fssa.technolibrary.validator.CategoryValidator;
import in.fssa.technolibrary.validator.PublisherValidator;

public class BookService {

	public static void create(Book newBook) throws Exception {
		BookValidator.validate(newBook);
		PublisherValidator.publisherIdAlreadyExistOrNot(newBook.getPublisherId());
		CategoryValidator.categoryIdAlreadyExistOrNot(newBook.getId());
		BookDAO bookDao = new BookDAO();
		bookDao.create(newBook);
	}

	public Set<Book> findAll() {
		BookDAO bookDao = new BookDAO();
		Set<Book> BookList = bookDao.findAll();
		for (BookEntity list : BookList) {
			System.out.println(list);
		}
		return BookList;
	}
	public Book findById(int id)throws Exception {
		BookValidator.validateId(id);
		BookValidator.bookIdAlreadyExistOrNot(id);
		BookDAO bookDao = new BookDAO();
		Book book = bookDao.findById(id);
		return book;
		
	}
	public Set<Book> findByAuthor(String author) throws ValidationException {
		BookValidator.validateAuthorNamePattern(author);
		BookValidator.authorAlreadyExistOrNot(author);
		BookDAO bookDao = new BookDAO();
		Set<Book> BookList = bookDao.findByAuthor(author);
		for (BookEntity list : BookList) {
			System.out.println(list);
		}
		return BookList;
	}
	public Set<Book> findByCategoryId(int category_id) throws ValidationException {
		BookValidator.validateCategoryId(category_id);
		BookDAO bookDao = new BookDAO();
		Set<Book> BookList = bookDao.findByCtegoryId(category_id);
		for (BookEntity list : BookList) {
			System.out.println(list);
		}
		return BookList;
	}
	public Set<Book> findByPublisherId(int publisher_id) throws ValidationException {
		BookValidator.validatePublisherId(publisher_id);
		PublisherValidator.publisherIdAlreadyExistOrNot(publisher_id);
		BookDAO bookDao = new BookDAO();
		Set<Book> BookList = bookDao.findByCtegoryId(publisher_id);
		for (BookEntity list : BookList) {
			System.out.println(list);
		}
		return BookList;
	}
	public void delete(int id) {
		BookDAO bookDAO = new BookDAO();
		bookDAO.delete(id);
	}
	public void basicUpdate(int id, Book newBook) throws ValidationException {
		BookValidator.validate(newBook);
		BookDAO bookDao = new BookDAO();
		bookDao.basicUpdate(id, newBook);
	}

}

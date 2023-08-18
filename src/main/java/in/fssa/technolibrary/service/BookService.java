package in.fssa.technolibrary.service;

import java.util.Set;

import in.fssa.technolibrary.dao.BookDAO;
import in.fssa.technolibrary.dao.CategoryDAO;
import in.fssa.technolibrary.dao.PublisherDAO;
import in.fssa.technolibrary.exception.PersistanceException;
import in.fssa.technolibrary.exception.ValidationException;
import in.fssa.technolibrary.model.Book;
import in.fssa.technolibrary.model.BookEntity;
import in.fssa.technolibrary.validator.BookValidator;

public class BookService {
	
	/**
	 * 
	 * @param newBook
	 * @throws Exception
	 */

	public static void create(Book newBook) throws Exception {
		BookValidator.validate(newBook);
		BookDAO bookDao = new BookDAO();
		PublisherDAO.publisherIdAlreadyExistOrNot(newBook.getPublisherId());
		CategoryDAO categoryDao = new CategoryDAO();
		categoryDao.categoryIdAlreadyExistOrNot(newBook.getId());
		bookDao.create(newBook);
	}
	/**
	 * 
	 * @return
	 */

	public Set<Book> findAll() {
		BookDAO bookDao = new BookDAO();
		Set<Book> BookList = bookDao.findAll();
		for (BookEntity list : BookList) {
			System.out.println(list);
		}
		return BookList;
	}
	
	/**
	 * 
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public Book findById(int id)throws Exception {
		BookValidator.validateId(id);
		BookDAO bookDao = new BookDAO();
		BookDAO.bookIdAlreadyExistOrNot(id);
		Book book = bookDao.findById(id);
		return book;
		
	}
	/**
	 * 
	 * @param author
	 * @return
	 * @throws ValidationException
	 * @throws PersistanceException
	 */
	public Set<Book> findByAuthor(String author) throws ValidationException, PersistanceException {
		BookValidator.validateAuthorNamePattern(author);
		BookDAO bookDao = new BookDAO();
		BookDAO.authorAlreadyExistOrNot(author);
		Set<Book> BookList = bookDao.findByAuthor(author);
		for (BookEntity list : BookList) {
			System.out.println(list);
		}
		return BookList;
	}
	/**
	 * 
	 * @param category_id
	 * @return
	 * @throws ValidationException
	 * @throws PersistanceException
	 */
	public Set<Book> findByCategoryId(int category_id) throws ValidationException, PersistanceException {
		BookValidator.validateCategoryId(category_id);
		BookDAO bookDao = new BookDAO();
		CategoryDAO categoryDao = new CategoryDAO();
		categoryDao.categoryIdAlreadyExistOrNot(category_id);
		Set<Book> BookList = bookDao.findByCtegoryId(category_id);
		for (BookEntity list : BookList) {
			System.out.println(list);
		}
		return BookList;
	}
	/**
	 * 
	 * @param publisher_id
	 * @return
	 * @throws ValidationException
	 * @throws PersistanceException
	 */
	public Set<Book> findByPublisherId(int publisher_id) throws ValidationException, PersistanceException {
		BookValidator.validatePublisherId(publisher_id);
		PublisherDAO.publisherIdAlreadyExistOrNot(publisher_id);
		BookDAO bookDao = new BookDAO();
		Set<Book> BookList = bookDao.findByPublisherId(publisher_id);
		for (BookEntity list : BookList) {
			System.out.println(list);
		}
		return BookList;
	}
	/**
	 * 
	 * @param id
	 * @throws ValidationException
	 * @throws PersistanceException
	 */
	public void delete(int id) throws ValidationException, PersistanceException {
		BookValidator.validateId(id);
		BookDAO bookDAO = new BookDAO();
		BookDAO.bookIdAlreadyExistOrNot(id);
		bookDAO.delete(id);
	}
	/**
	 * 
	 * @param id
	 * @param updatedData
	 * @throws ValidationException
	 * @throws PersistanceException
	 */
	public void updatePrice(int id, Book updatedData) throws ValidationException, PersistanceException {
		BookValidator.validateId(id);
		BookDAO bookDao = new BookDAO();
		BookDAO.bookIdAlreadyExistOrNot(id);
		int newPrice = updatedData.getPrice();
		Book oldData = bookDao.findById(id);
		int oldPrice = oldData.getPrice();
		if(oldPrice != newPrice) {
			bookDao.updatePrice(id, updatedData);
		}else {
			System.out.print("Updated price and old price is same");
		}
		
	}

}

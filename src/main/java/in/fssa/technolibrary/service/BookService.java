package in.fssa.technolibrary.service;

import java.util.Set;

import in.fssa.technolibrary.dao.BookDAO;
import in.fssa.technolibrary.exception.PersistanceException;
import in.fssa.technolibrary.exception.ServiceException;
import in.fssa.technolibrary.exception.ValidationException;
import in.fssa.technolibrary.model.Book;
import in.fssa.technolibrary.model.BookEntity;
import in.fssa.technolibrary.util.Logger;
import in.fssa.technolibrary.validator.BookValidator;

public class BookService {

	/**
	 * 
	 * @param newBook
	 * @throws PersistanceException
	 * @throws ValidationException
	 * @throws ServiceException
	 */ 

	public static void createNewBook(Book newBook) throws ServiceException, ValidationException {
		try {
			BookValidator.validate(newBook);
			BookDAO bookDAO = new BookDAO();
			bookDAO.create(newBook);
		} catch (PersistanceException e) {
			Logger.error(e);
			throw new ServiceException("Error occurred while creating book.", e);
		}
	}

	/**
	 * 
	 * @return
	 * @throws ServiceException
	 * @throws PersistanceException
	 */

	public Set<Book> findAllBook() throws ServiceException {
		try {
			BookDAO bookDAO = new BookDAO();
			return bookDAO.findAll();
		} catch (PersistanceException e) {
			Logger.error(e);
			throw new ServiceException("Error while retrieving all books");
		}
	}

	/**
	 * 
	 * @param id
	 * @return
	 * @throws PersistanceException
	 * @throws ServiceException
	 * @throws ValidationException
	 * @throws Exception
	 */
	public static Book findBookById(int bookId) throws ServiceException, ValidationException {
		try {
			BookValidator.validateId(bookId);
			BookDAO bookDAO = new BookDAO();
			Book book = bookDAO.findById(bookId);
			return book;
		} catch (PersistanceException e) {
			Logger.error(e);
			throw new ServiceException("There is no book in this book id", e);
		}

	}

	/**
	 * 
	 * @param author
	 * @return
	 * @throws ValidationException
	 * @throws PersistanceException
	 * @throws ServiceException
	 */
	public Set<Book> findByAuthorName(String author) throws ValidationException, ServiceException {
		try {
			BookValidator.validateFindAuthor(author);
			BookDAO bookDAO = new BookDAO();
			return bookDAO.findByAuthor(author);
		} catch (PersistanceException e) {
			Logger.error(e);
			throw new ServiceException("There is no book in this author name", e);
		}
	}

	/**
	 * 
	 * @param categoryId
	 * @return
	 * @throws ValidationException
	 * @throws PersistanceException
	 * @throws ServiceException
	 */
	public Set<Book> findBookByCategoryId(int categoryId) throws ValidationException, ServiceException {
		try {
			BookValidator.validateCategoryId(categoryId);
			BookDAO bookDAO = new BookDAO();
			return bookDAO.findByCategoryId(categoryId);
		} catch (PersistanceException e) {
			Logger.error(e);
			throw new ServiceException("There is no book in this category id", e);
		}
	}

	/**
	 * 
	 * @param publisher_id
	 * @return
	 * @throws ValidationException
	 * @throws ServiceException
	 * @throws PersistanceException
	 */
	public Set<Book> findBookByPublisherId(int publisherId) throws ValidationException, ServiceException {
		try {
			BookValidator.validatePublisherId(publisherId);
			BookDAO bookDAO = new BookDAO();
			Set<Book> bookList = bookDAO.findByPublisherId(publisherId);
			for (BookEntity list : bookList) {
				System.out.println(list);
			}
			return bookList;
		} catch (PersistanceException e) {
			Logger.error(e);
			throw new ServiceException("There is no book in this publisher id", e);
		}
	}

	/**
	 * 
	 * @param id
	 * @throws ValidationException
	 * @throws ServiceException
	 * @throws PersistanceException
	 */
	public void deleteBook(int bookId) throws ValidationException, ServiceException {
		try {
			BookValidator.validateId(bookId);
			BookDAO bookDAO = new BookDAO();
			bookDAO.delete(bookId);
		} catch (PersistanceException e) {
			Logger.error(e);
			throw new ServiceException("There is no book in this book id", e);
		}
	}

	
	public static void updateBook(int id, Book updatedBook) throws ServiceException {
		try {
		
		BookDAO bookDAO = new BookDAO();
		bookDAO.updateBook(id, updatedBook);
		} catch (PersistanceException e) {
			Logger.error(e);
			throw new ServiceException("There is no book in this book id", e);
		}
	}

}

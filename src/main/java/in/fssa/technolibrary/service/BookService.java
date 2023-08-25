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
	 * @throws PersistanceException 
	 * @throws ValidationException 
	 */

	public static void create(Book newBook) throws ValidationException, PersistanceException {
		BookValidator.validate(newBook);
		BookDAO bookDAO = new BookDAO();
		bookDAO.create(newBook);
	}
	/**
	 * 
	 * @return
	 * @throws PersistanceException 
	 */

	public Set<Book> findAll() throws PersistanceException {
		BookDAO bookDAO = new BookDAO();
		Set<Book> BookList = bookDAO.findAll();
		for (BookEntity list : BookList) {
			System.out.println(list);
		}
		return BookList;
	}
	
	/**
	 * 
	 * @param id
	 * @return
	 * @throws PersistanceException 
	 * @throws Exception
	 */
	public static Book findById(int bookId)throws ValidationException, PersistanceException {
		BookValidator.validateId(bookId);
		BookDAO bookDAO = new BookDAO();
		Book book = bookDAO.findById(bookId);
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
		BookValidator.validateAthor(author);
		BookDAO bookDAO = new BookDAO();
		BookDAO.authorAlreadyExistOrNot(author);
		Set<Book> bookList = bookDAO.findByAuthor(author);
		for (BookEntity list : bookList) {
			System.out.println(list);
		}
		return bookList;
	}
	/**
	 * 
	 * @param categoryId
	 * @return
	 * @throws ValidationException
	 * @throws PersistanceException
	 */
	public Set<Book> findByCategoryId(int categoryId) throws ValidationException, PersistanceException {
		BookValidator.validateCategoryId(categoryId);
		BookDAO bookDAO = new BookDAO();
		CategoryDAO.categoryIdAlreadyExistOrNot(categoryId);
		Set<Book> bookList = bookDAO.findByCtegoryId(categoryId);
		for (BookEntity list : bookList) {
			System.out.println(list);
		}
		return bookList;
	}
	/**
	 * 
	 * @param publisher_id
	 * @return
	 * @throws ValidationException
	 * @throws PersistanceException
	 */
	public Set<Book> findByPublisherId(int publisherId) throws ValidationException, PersistanceException {
		BookValidator.validatePublisherId(publisherId);
		BookDAO bookDAO = new BookDAO();
		Set<Book> bookList = bookDAO.findByPublisherId(publisherId);
		for (BookEntity list : bookList) {
			System.out.println(list);
		}
		return bookList;
	}
	/**
	 * 
	 * @param id
	 * @throws ValidationException
	 * @throws PersistanceException
	 */
	public void delete(int bookId) throws ValidationException, PersistanceException {
		BookValidator.validateId(bookId);
		BookDAO bookDAO = new BookDAO();
		bookDAO.delete(bookId);
	}
	/**
	 * 
	 * @param id
	 * @param updatedData
	 * @throws ValidationException
	 * @throws PersistanceException
	 */
	public void updatePrice(int bookId, Book updatedData) throws PersistanceException, ValidationException {
	    BookValidator.validateId(bookId);
	    BookDAO bookDAO = new BookDAO();
	    BookValidator.validatePrice(updatedData.getPrice());
	    if (!BookDAO.bookIdAlreadyExistOrNot(bookId)) {
	        throw new PersistanceException("Book does not exist.");
	    }
	    int newPrice = updatedData.getPrice();
	    Book oldData = bookDAO.findById(bookId);
	    int oldPrice = oldData.getPrice();
	    if (oldPrice != newPrice) {
	    	bookDAO.updatePrice(bookId, updatedData);
	   } else {
	        throw new PersistanceException("New price is the same as the old price.");
	    }
	}
	/**
	 * 
	 * @param id
	 * @param updatedData
	 * @throws ValidationException
	 * @throws PersistanceException
	 */
	public void updateTitleAndDate(int bookId, Book updatedData) throws ValidationException, PersistanceException {
	    BookValidator.validateId(bookId);
	    BookValidator.validateTitle(updatedData.getTitle());
	    BookValidator.validatePublishedDate(updatedData.getPublishedDate());
	    BookDAO bookDAO = new BookDAO();
	    if (!BookDAO.bookIdAlreadyExistOrNot(bookId)) {
	        throw new ValidationException("Book with ID " + bookId + " does not exist.");
	    }
	    
	    String newTitle = updatedData.getTitle();
	    String newDate = updatedData.getPublishedDate();
	    Book oldData = bookDAO.findById(bookId);
	    String oldTitle = oldData.getTitle();
	    String oldDate = oldData.getPublishedDate();
	    if (!oldTitle.equals(newTitle) && !oldDate.equals(newDate)) {
	    	bookDAO.updateTitleAndDate(bookId, updatedData);
	    } else {
	        throw new ValidationException("New Title And Published Date is the same as the old Title And Published Date.");
	    }
	}
	/**
	 * 
	 * @param id
	 * @param updatedData
	 * @throws ValidationException
	 * @throws PersistanceException
	 */
	public void updateAuthorNamePublisherIdCategoryId(int id, Book updatedData) throws ValidationException, PersistanceException {
	    BookValidator.validateId(id);
	    BookValidator.validateAuthorNamePattern(updatedData.getAuthor());
	    BookValidator.validatePublisherId(updatedData.getPublisherId());
	    BookValidator.validateCategoryId(updatedData.getCategoryId());
	    BookDAO bookDAO = new BookDAO();
	    
	    if (!BookDAO.bookIdAlreadyExistOrNot(id)) {
	        throw new ValidationException("Book with ID " + id + " does not exist.");
	    }
	    if (!PublisherDAO.publisherIdAlreadyExistOrNot(updatedData.getPublisherId())) {
	        throw new ValidationException("Publisher ID " + updatedData.getPublisherId() + " does not exist.");
	    }
	    if (!CategoryDAO.categoryIdAlreadyExistOrNot(updatedData.getCategoryId())) {
	        throw new ValidationException("Category ID " + updatedData.getCategoryId() + " does not exist.");
	    }
	    
	    String newAuthorName = updatedData.getTitle();
	    int newPublisherId = updatedData.getPublisherId();
	    int newCategoryId = updatedData.getCategoryId();
	    Book oldData = bookDAO.findById(id);
	    String oldAuthorName = oldData.getAuthor();
	    int oldPublisherId = oldData.getPublisherId();
	    int oldCategory = oldData.getCategoryId();
	    if (!oldAuthorName.equals(newAuthorName) && oldPublisherId != newPublisherId && oldCategory != newCategoryId) {
	    	bookDAO.updateAuthorNamePublisheIdCategoryId(id, updatedData);
	    } else {
	        throw new ValidationException("New Author ,Published Id And Category Id is the same as the old Author ,Published Id And Category Id.");
	    }
	}

}


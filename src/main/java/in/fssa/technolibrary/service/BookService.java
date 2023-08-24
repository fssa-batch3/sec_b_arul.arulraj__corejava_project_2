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
		BookDAO bookDao = new BookDAO();
		bookDao.create(newBook);
	}
	/**
	 * 
	 * @return
	 * @throws PersistanceException 
	 */

	public Set<Book> findAll() throws PersistanceException {
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
	 * @throws PersistanceException 
	 * @throws Exception
	 */
	public static Book findById(int id)throws ValidationException, PersistanceException {
		BookValidator.validateId(id);
		BookDAO bookDao = new BookDAO();
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
		BookValidator.validateAthor(author);
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
		CategoryDAO.categoryIdAlreadyExistOrNot(category_id);
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
	public Set<Book> findByPublisherId(int publisherId) throws ValidationException, PersistanceException {
		BookValidator.validatePublisherId(publisherId);
		BookDAO bookDao = new BookDAO();
		Set<Book> BookList = bookDao.findByPublisherId(publisherId);
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
		bookDAO.delete(id);
	}
	/**
	 * 
	 * @param id
	 * @param updatedData
	 * @throws ValidationException
	 * @throws PersistanceException
	 */
	public void updatePrice(int id, Book updatedData) throws PersistanceException, ValidationException {
	    BookValidator.validateId(id);
	    BookDAO bookDao = new BookDAO();
	    if (!BookDAO.bookIdAlreadyExistOrNot(id)) {
	        throw new PersistanceException("Book with ID " + id + " does not exist.");
	    }
	    int newPrice = updatedData.getPrice();
	    Book oldData = bookDao.findById(id);
	    int oldPrice = oldData.getPrice();
	    if (oldPrice != newPrice) {
	        bookDao.updatePrice(id, updatedData);
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
	public void updateTitleAndDate(int id, Book updatedData) throws ValidationException, PersistanceException {
	    BookValidator.validateId(id);
	    BookValidator.validateTitle(updatedData.getTitle());
	    BookValidator.validateDate(updatedData.getPublishedDate());
	    BookDAO bookDao = new BookDAO();
	    if (!BookDAO.bookIdAlreadyExistOrNot(id)) {
	        throw new ValidationException("Book with ID " + id + " does not exist.");
	    }
	    
	    String newTitle = updatedData.getTitle();
	    String newDate = updatedData.getPublishedDate();
	    Book oldData = bookDao.findById(id);
	    String oldTitle = oldData.getTitle();
	    String oldDate = oldData.getPublishedDate();
	    if (!oldTitle.equals(newTitle) && !oldDate.equals(newDate)) {
	        bookDao.updateTitleAndDate(id, updatedData);
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
	    BookDAO bookDao = new BookDAO();
	    
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
	    Book oldData = bookDao.findById(id);
	    String oldAuthorName = oldData.getAuthor();
	    int oldPublisherId = oldData.getPublisherId();
	    int oldCategory = oldData.getCategoryId();
	    if (!oldAuthorName.equals(newAuthorName) && oldPublisherId != newPublisherId && oldCategory != newCategoryId) {
	        bookDao.updateAuthorNamePublisheIdCategoryId(id, updatedData);
	    } else {
	        throw new ValidationException("New Author ,Published Id And Category Id is the same as the old Author ,Published Id And Category Id.");
	    }
	}

}


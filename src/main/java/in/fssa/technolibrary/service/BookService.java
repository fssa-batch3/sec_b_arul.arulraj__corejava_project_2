package in.fssa.technolibrary.service;

import java.util.Set;

import in.fssa.technolibrary.dao.BookDAO;
import in.fssa.technolibrary.dao.CategoryDAO;
import in.fssa.technolibrary.dao.PublisherDAO;
import in.fssa.technolibrary.exception.PersistanceException;
import in.fssa.technolibrary.exception.ServiceException;
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
	 * @throws ServiceException
	 */

	public static void createNewBook(Book newBook) throws ServiceException, ValidationException {
		try {
			BookValidator.validate(newBook);
			BookDAO bookDAO = new BookDAO();
			bookDAO.create(newBook);
		} catch (PersistanceException e) {
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
			e.printStackTrace();
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
			throw new ServiceException("There is no book in this id", e);
		}

	}

//	/**
//	 * 
//	 * @param bookTitle
//	 * @return
//	 * @throws ServiceException
//	 * @throws ValidationException
//	 */
//	public static Book findBookByTitle(String bookTitle) throws ServiceException, ValidationException {
//		try {
//			BookValidator.validateFindBookTitle(bookTitle);
//			BookDAO bookDAO = new BookDAO();
//			return bookDAO.findByBookTitle(bookTitle);
//
//		} catch (PersistanceException e) {
//			throw new ServiceException("There is no book in this Name", e);
//		}
//
//	}

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
			throw new ServiceException("There is no book in this id", e);
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
			throw new ServiceException("There is no book in this id", e);
		}
	}

	/**
	 * 
	 * @param id
	 * @param updatedData
	 * @throws ValidationException
	 * @throws ServiceException
	 * @throws PersistanceException
	 */
	public static void updateBookPrice(int bookId, Book updatedData) throws ValidationException, ServiceException {
		try {
			BookValidator.validateId(bookId);
			BookDAO bookDAO = new BookDAO();
			BookValidator.validatePrice(updatedData.getPrice());
			int newPrice = updatedData.getPrice();
			Book oldData = bookDAO.findById(bookId);
			int oldPrice = oldData.getPrice();
			if (oldPrice != newPrice) {
				bookDAO.updatePrice(bookId, updatedData);
			} else {
				throw new ValidationException("New price is the same as the old price.");
			}
		} catch (PersistanceException e) {
			throw new ServiceException("There is no book in this id", e);
		}
	}
	
	/**
	 * 
	 * @param bookId
	 * @param updatedData
	 * @throws ValidationException
	 * @throws ServiceException
	 */
	public static void updatePrice(int bookId, Book updatedData) throws ValidationException, ServiceException {
		try {
			BookValidator.validateId(bookId);
			BookDAO bookDAO = new BookDAO();

			bookDAO.updatePrice(bookId, updatedData);
		} catch (PersistanceException e) {
			throw new ServiceException("There is no book in this id", e);
		}
	}

	/**
	 * 
	 * @param bookId
	 * @param updatedData
	 * @throws ValidationException
	 * @throws ServiceException
	 */
	public static void updateTitleAndDate(int bookId, Book updatedData) throws ValidationException, ServiceException {
		try {
			BookValidator.validateUpdateBookAndTitle(updatedData);
			BookDAO bookDAO = new BookDAO();

			bookDAO.updateTitleAndDate(bookId, updatedData);
		} catch (PersistanceException e) {
			throw new ServiceException("There is no book in this id", e);
		}
	}

	/**
	 * 
	 * @param id
	 * @param updatedData
	 * @throws ValidationException
	 * @throws ServiceException
	 * @throws PersistanceException
	 */
	public static void updateBookTitleAndDate(int bookId, Book updatedData)
			throws ValidationException, ServiceException {
		try {
			BookValidator.validateUpdateBookAndTitle(updatedData);
			BookDAO bookDAO = new BookDAO();

			String newTitle = updatedData.getTitle();
			String newDate = updatedData.getPublishedDate();
			Book oldData = bookDAO.findById(bookId);
			String oldTitle = oldData.getTitle();
			String oldDate = oldData.getPublishedDate();
			if (!oldTitle.equals(newTitle) && !oldDate.equals(newDate)) {
				bookDAO.updateTitleAndDate(bookId, updatedData);
			} else {
				throw new ValidationException(
						"New Title And Published Date is the same as the old Title And Published Date.");
			}
		} catch (PersistanceException e) {
			throw new ServiceException("There is no book in this id", e);
		}
	}

	/**
	 * 
	 * @param bookId
	 * @param updatedData
	 * @throws ValidationException
	 * @throws ServiceException
	 */
	public static void updateAuthorNamePublisherIdCategoryId(int bookId, Book updatedData)
			throws ValidationException, ServiceException {
		try {
			BookValidator.validateId(bookId);
			BookValidator.validateAuthorNamePattern(updatedData.getAuthor());
			BookValidator.validatePublisherId(updatedData.getPublisherId());
			BookValidator.validateCategoryId(updatedData.getCategoryId());
			BookDAO bookDAO = new BookDAO();

			bookDAO.updateAuthorNamePublisherIdCategoryId(bookId, updatedData);
		} catch (PersistanceException e) {
			throw new ServiceException("There is no book in this id", e);
		}
	}

	/**
	 * 
	 * @param id
	 * @param updatedData
	 * @throws ValidationException
	 * @throws ServiceException
	 * @throws PersistanceException
	 */
	public static void updateBookAuthorNamePublisherIdCategoryId(int bookId, Book updatedData)
			throws ValidationException, ServiceException {
		try {
			BookValidator.validateId(bookId);
			BookValidator.validateAuthorNamePattern(updatedData.getAuthor());
			BookValidator.validatePublisherId(updatedData.getPublisherId());
			BookValidator.validateCategoryId(updatedData.getCategoryId());
			BookDAO bookDAO = new BookDAO();
			if (!PublisherDAO.publisherIdAlreadyExistOrNot(updatedData.getPublisherId())) {
				throw new ValidationException("Publisher ID " + updatedData.getPublisherId() + " does not exist.");
			}
			if (!CategoryDAO.categoryIdAlreadyExistOrNot(updatedData.getCategoryId())) {
				throw new ValidationException("Category ID " + updatedData.getCategoryId() + " does not exist.");
			}

			String newAuthorName = updatedData.getTitle();
			int newPublisherId = updatedData.getPublisherId();
			int newCategoryId = updatedData.getCategoryId();
			Book oldData = bookDAO.findById(bookId);
			String oldAuthorName = oldData.getAuthor();
			int oldPublisherId = oldData.getPublisherId();
			int oldCategory = oldData.getCategoryId();
			if (!oldAuthorName.equals(newAuthorName) && oldPublisherId != newPublisherId
					&& oldCategory != newCategoryId) {
				bookDAO.updateAuthorNamePublisherIdCategoryId(bookId, updatedData);
			} else {
				throw new ValidationException(
						"New Author ,Published Id And Category Id is the same as the old Author ,Published Id And Category Id.");
			}
		} catch (PersistanceException e) {
			throw new ServiceException("There is no book in this id", e);
		}
	}

}

package in.fssa.technolibrary.service;

import java.time.format.DateTimeParseException;
import java.util.Set;

import in.fssa.technolibrary.dao.BookDAO;
import in.fssa.technolibrary.model.Book;
import in.fssa.technolibrary.model.BookEntity;
import in.fssa.technolibrary.validator.BookValidator;

public class BookService {

	public static void create(Book newBook) throws Exception {
		try {
			BookValidator.validate(newBook);
		} catch (DateTimeParseException e) {
			throw new in.fssa.technolibrary.exception.ValidationException("Invalid date format or Invalid Date");
		}
		BookValidator.validate(newBook);
		BookDAO bookDao = new BookDAO();
		bookDao.create(newBook);
	}

	public Set<Book> findAll() {

		BookDAO bookdeo = new BookDAO();

		Set<Book> BookList = bookdeo.findAll();

		for (BookEntity cate : BookList) {
			System.out.println(cate);
		}

		return BookList;

	}

}

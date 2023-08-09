package in.fssa.technolibrary;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import in.arulajun.fitness.exception.ValidationException;
import in.arulajun.fitness.model.User;
import in.arulajun.fitness.service.UserService;
import in.fssa.technolibrary.model.Book;
import in.fssa.technolibrary.service.BookService;

public class TestCreateBook {

	@Test
	public void testCreateBookWithValidDetails() {

		BookService bookService = new BookService();
		
		Book newBook = new Book();
		
		newBook.setAuthor("Ajun");
		newBook.setTitle("IR");
		newBook.setCategoryId(1);
		newBook.setPublisherId(1);
		newBook.setPublishedDate("2023-01-12");
		newBook.setPrice(500);

		assertDoesNotThrow(() -> {
			bookService.create(newBook);
		});
	}
	
	@Test
	public void testAuthosNameNull() {

		BookService bookService = new BookService();

		Book newBook = new Book();

		newUser.setFirstName("Arul");
		newUser.setLastName("Ajun");
		newUser.setEmail(null);
		newUser.setPassword("Ajunajun55");
		newUser.setActive(true);

		Exception exception = assertThrows(ValidationException.class, () -> {
			userService.create(newUser);
		});

		String exceptedMessage = "Email cannot be null or empty";
		String actualMessage = exception.getMessage();

		assertTrue(exceptedMessage.equals(actualMessage));
	}

}

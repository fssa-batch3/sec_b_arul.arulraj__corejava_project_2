package in.fssa.technolibrary;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import in.fssa.technolibrary.exception.ValidationException;
import in.fssa.technolibrary.model.User;
import in.fssa.technolibrary.service.UserService;
import in.fssa.technolibrary.util.PasswordEncryptUtil;

class TestUpdateUser {

	@Test
	void testUpdateUser() {
		assertDoesNotThrow(() -> {
			UserService userService = new UserService();
			User newUser = new User();
			newUser.setPhoneNumber(9952393567l);
			userService.updateUser(1, newUser);
		});
	}
 
	@Test
	void testUpdateSpecificFields() {
		assertDoesNotThrow(() -> {
			UserService userService = new UserService();
			User newUser = new User();
			String hashPassword = PasswordEncryptUtil.encrypt("Admin@05");
			newUser.setPassword(hashPassword);
			userService.updateUser(1, newUser);
		});
	}

	@Test
	void testUpdateWithNonExistingId() throws ValidationException {
		UserService userService = new UserService();
		Exception exception = assertThrows(ValidationException.class, () -> {
			User newUser = new User();
			newUser.setPassword("Sess@2608");
			userService.updateUser(5000, newUser);
		});
		String expectedMessage = "User Id doesn't exist";
		String actualMessage = exception.getMessage();

		assertEquals(expectedMessage, actualMessage);
	}

}
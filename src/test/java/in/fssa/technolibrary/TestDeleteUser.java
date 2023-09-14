package in.fssa.technolibrary;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.Random;

import org.junit.jupiter.api.Test;

import in.fssa.technolibrary.dao.UserDAO;
import in.fssa.technolibrary.exception.ValidationException;
import in.fssa.technolibrary.model.User;
import in.fssa.technolibrary.service.UserService;

class TestDeleteUser { 
	
	@Test
	void testGetUserByIdLessThanZero() { 
		UserService userService = new UserService();
		Exception exception = assertThrows(ValidationException.class, () -> {
	            userService.deleteUser(-5);
		});
		String expectedMessage = "User Id cannot be less than or equal to zero";
		String actualMessage = exception.getMessage();

		assertEquals(expectedMessage, actualMessage);
	}

	 @Test
	    void testDeleteUser() {
	        assertDoesNotThrow(() -> {
	            UserService userService = new UserService();
	    	    int user = UserDAO.getLastUpdatedUserId();
	            userService.deleteUser(user);
	        });
	    }


	@Test
	void testDeleteWithNonExistingId() throws ValidationException {
		UserService userService = new UserService();
		Exception exception = assertThrows(ValidationException.class, () -> {
			userService.deleteUser(5000);
		});
		String expectedMessage = "User Id doesn't exist";
		String actualMessage = exception.getMessage();

		assertEquals(expectedMessage, actualMessage);
	}
	
	@Test
	 void testCreateUserWithValidInput() {
	    UserService userService = new UserService();
	    User newUser = new User();
	    String randomString = generateRandomString(8); 
	    newUser.setName("Ajun");
	    newUser.setEmail(randomString + "@" + "google.com");
	    newUser.setPassword("Ajunaju@555");
	    newUser.setImage("https://iili.io/J9kwBe9.jpg");
	    newUser.setPhoneNumber(9863456787L);

	    assertDoesNotThrow(() -> {
	        userService.createUser(newUser);
	    });
	}
	
	private String generateRandomString(int length) {
	    String characters = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
	    StringBuilder randomString = new StringBuilder();
	    Random random = new Random();

	    for (int i = 0; i < length; i++) {
	        int index = random.nextInt(characters.length());
	        randomString.append(characters.charAt(index));
	    }

	    return randomString.toString();
	}
}
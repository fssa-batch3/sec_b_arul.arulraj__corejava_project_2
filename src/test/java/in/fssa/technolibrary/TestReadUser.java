package in.fssa.technolibrary;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.Set;

import org.junit.jupiter.api.Test;

import in.fssa.technolibrary.exception.ValidationException;
import in.fssa.technolibrary.model.User;
import in.fssa.technolibrary.service.UserService;

class TestReadUser {
	@Test
    void testGetAllUser() {
        assertDoesNotThrow(() -> {
            UserService userService = new UserService();
            Set<User> arr = userService.getAllUser();
            System.out.println(arr);
        });
    }
    
    @Test 
    void testGetUserById() {
        assertDoesNotThrow(() -> {
            UserService userService = new UserService();
            User arr = userService.findByUserId(1);
            System.out.println(arr);
        });
    }

	@Test
	void testGetUserByIdLessThanZero() {
		UserService userService = new UserService();
		Exception exception = assertThrows(ValidationException.class, () -> {
			User arr = userService.findByUserId(-5);
		});
		String expectedMessage = "User Id cannot be less than or equal to zero";
		String actualMessage = exception.getMessage();

		assertEquals(expectedMessage, actualMessage);
	}

	@Test
	void testGetUserByNonExistingId() {
		UserService userService = new UserService();
		Exception exception = assertThrows(ValidationException.class, () -> {
			User arr = userService.findByUserId(5000);
		});
		String expectedMessage = "User Id doesn't exist";
		String actualMessage = exception.getMessage();

		assertEquals(expectedMessage, actualMessage);
	}

	 @Test
	    void testGetUserByEmailId() {
	        assertDoesNotThrow(() -> {
	            UserService userService = new UserService();
	            User arr = userService.findByEmail("ajun@gmail.com");
	            System.out.println(arr);
	        });
	    }

	@Test
	void testGetUserByNonExistingEmail() {
		UserService userService = new UserService();
		Exception exception = assertThrows(ValidationException.class, () -> {
			userService.findByEmail("sesslyn2002@gmail.com");
		});
		String expectedMessage = "Email doesn't exist";
		String actualMessage = exception.getMessage();

		assertEquals(expectedMessage, actualMessage);
	}
}
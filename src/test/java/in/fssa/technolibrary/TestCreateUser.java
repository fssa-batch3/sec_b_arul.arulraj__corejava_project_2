package in.fssa.technolibrary;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.Random;

import org.junit.jupiter.api.Test;

import in.fssa.technolibrary.exception.ValidationException;
import in.fssa.technolibrary.model.User;
import in.fssa.technolibrary.service.UserService;
import in.fssa.technolibrary.util.PasswordEncryptUtil;

class TestCreateUser {

	@Test
	 void testCreateUserWithValidInput() {
	    UserService userService = new UserService();
	    User newUser = new User();
	    String randomString = generateRandomString(8); 
	    newUser.setName("Ajun");
	    newUser.setEmail(randomString + "@" + "gmail.com");
	    String hashPassword = PasswordEncryptUtil.encrypt("Ajunajun@05");
	    newUser.setPassword(hashPassword);
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

	@Test
	 void testCreateUserWithInvalidInput() {
		UserService userService = new UserService();
		Exception exception = assertThrows(ValidationException.class, () -> {
			userService.createUser(null);
		});
		String expectedMessage = "User object cannot be null";
		String actualMessage = exception.getMessage();

		assertEquals(expectedMessage,actualMessage);
	}

	@Test
	 void testCreateUserWithNameNull() {
		UserService userService = new UserService();
		User newUser = new User();
		newUser.setName(null);
		newUser.setEmail("ajun@gmail.com");
		newUser.setPassword("Ajun@5555");
		newUser.setImage("https://iili.io/J9kwBe9.jpg");
		newUser.setPhoneNumber(9923456787L);

		Exception exception = assertThrows(ValidationException.class, () -> {
			userService.createUser(newUser);
		});
		String expectedMessage = "User Name cannot be null or empty";
		String actualMessage = exception.getMessage();

		assertEquals(expectedMessage,actualMessage);
	}

	@Test
	 void testCreateUserWithNameEmpty() {
		UserService userService = new UserService();
		User newUser = new User();
		newUser.setName("");
		newUser.setEmail("ajun@gmail.com");
		newUser.setPassword("Ajun@5555");
		newUser.setImage("https://iili.io/J9kwBe9.jpg");
		newUser.setPhoneNumber(9923456787L);

		Exception exception = assertThrows(ValidationException.class, () -> {
			userService.createUser(newUser);
		});
		String expectedMessage = "User Name cannot be null or empty";
		String actualMessage = exception.getMessage();

		assertEquals(expectedMessage,actualMessage);
	}

	@Test
	 void testCreateUserWithLessCharacters() {
		UserService userService = new UserService();
		User newUser = new User();
		newUser.setName("Aj");
		newUser.setEmail("ajun@gmail.com");
		newUser.setPassword("Ajun@5555");
		newUser.setImage("https://iili.io/J9kwBe9.jpg");
		newUser.setPhoneNumber(9923456787L);

		Exception exception = assertThrows(ValidationException.class, () -> {
			userService.createUser(newUser);
		});
		String expectedMessage = "User Name should be at least 3 characters long";
		String actualMessage = exception.getMessage();

		assertEquals(expectedMessage,actualMessage);
	}
	
	@Test
	 void testCreateUserWithNamePattern() {
		UserService userService = new UserService();
		User newUser = new User();
		newUser.setName("F7yfh7");
		newUser.setEmail("ajun@gmail.com");
		newUser.setPassword("Ajun@5555");
		newUser.setImage("https://iili.io/J9kwBe9.jpg");
		newUser.setPhoneNumber(9923456787L);

		Exception exception = assertThrows(ValidationException.class, () -> {
			userService.createUser(newUser);
		});
		String expectedMessage = "User Name should only contain alphabetic characters and allow only one space between words";
		String actualMessage = exception.getMessage();

		assertEquals(expectedMessage,actualMessage);
	}

	@Test
	 void testCreateUserWithEmailNull() {
		UserService userService = new UserService();

		User newUser = new User();
		newUser.setName("Ajun");
		newUser.setEmail(null);
		newUser.setPassword("Ajun@5555");
		newUser.setImage("https://iili.io/J9kwBe9.jpg");
		newUser.setPhoneNumber(9923456787L);

		Exception exception = assertThrows(ValidationException.class, () -> {
			userService.createUser(newUser);
		});
		String expectedMessage = "Email cannot be null or empty";
		String actualMessage = exception.getMessage();

		assertEquals(expectedMessage,actualMessage);
	}

	@Test
	 void testCreateUserWithEmailEmpty() {
		UserService userService = new UserService();

		User newUser = new User();
		newUser.setName("Ajun");
		newUser.setEmail("");
		newUser.setPassword("Ajun@5555");
		newUser.setImage("https://iili.io/J9kwBe9.jpg");
		newUser.setPhoneNumber(9923456787L);

		Exception exception = assertThrows(ValidationException.class, () -> {
			userService.createUser(newUser);
		});
		String expectedMessage = "Email cannot be null or empty";
		String actualMessage = exception.getMessage();

		assertEquals(expectedMessage,actualMessage);
	}

	@Test
	 void testCreateUserWithEmailPattern() {
		UserService userService = new UserService();

		User newUser = new User();
		newUser.setName("Arul Ajun");
		newUser.setEmail("ajun@");
		newUser.setPassword("Ajun@5555");
		newUser.setImage("https://iili.io/J9kwBe9.jpg");
		newUser.setPhoneNumber(9923456787L);

		Exception exception = assertThrows(ValidationException.class, () -> {
			userService.createUser(newUser);
		});
		String expectedMessage = "Invalid email format. Please provide a valid email address";
		String actualMessage = exception.getMessage();

		assertEquals(expectedMessage,actualMessage);
	}

	@Test
	 void testCreateUserWithEmailExists() {
		UserService userService = new UserService();

		User newUser = new User();
		newUser.setName("Ajun");
		newUser.setEmail("ajun@gmail.com");
		newUser.setPassword("Ajun@5555");
		newUser.setImage("https://iili.io/J9kwBe9.jpg");
		newUser.setPhoneNumber(6381040916L);

		Exception exception = assertThrows(ValidationException.class, () -> {
			userService.createUser(newUser);
		});
		String expectedMessage = "Email already exists.Try with a new email id";
		String actualMessage = exception.getMessage();

		assertEquals(expectedMessage,actualMessage);
	}

	@Test
	 void testCreateUserWithPasswordNull() {
		UserService userService = new UserService();
		User newUser = new User();
		newUser.setName("Ajun");
		newUser.setEmail("somu@gmail.com");
		newUser.setPassword(null);
		newUser.setImage("https://iili.io/J9kwBe9.jpg");
		newUser.setPhoneNumber(9923456787L);

		Exception exception = assertThrows(ValidationException.class, () -> {
			userService.createUser(newUser);
		});
		String expectedMessage = "Password cannot be null or empty";
		String actualMessage = exception.getMessage();

		assertEquals(expectedMessage,actualMessage);
	}

	@Test
	 void testCreateUserWithPasswordEmpty() {
		UserService userService = new UserService();
		User newUser = new User();
		newUser.setName("Ajun");
		newUser.setEmail("ramu@gmail.com");
		newUser.setPassword("");
		newUser.setImage("https://iili.io/J9kwBe9.jpg");
		newUser.setPhoneNumber(9923456787L);

		Exception exception = assertThrows(ValidationException.class, () -> {
			userService.createUser(newUser);
		});
		String expectedMessage = "Password cannot be null or empty";
		String actualMessage = exception.getMessage();

		assertEquals(expectedMessage,actualMessage);
	}

	@Test
	 void testCreateUserWithInvalidPasswordPattern() {
	    UserService userService = new UserService();
	    User newUser = new User();
	    newUser.setName("Ajun");
		newUser.setEmail("sesslyn@gmail.com");
		newUser.setPassword("A5r555xx");
		newUser.setImage("https://iili.io/J9kwBe9.jpg");
	    newUser.setPhoneNumber(9923456787L);

	    Exception exception = assertThrows(ValidationException.class, () -> {
	        userService.createUser(newUser);
	    });
	    
	    String expectedMessage = "Password must have at least 8 characters and "
	    		+ "contain at least one uppercase letter, one lowercase letter, "
	    		+ "and one special character";
	    String actualMessage = exception.getMessage();
	    
	    assertEquals(expectedMessage,actualMessage);
	}


	@Test
	 void testCreateUserWithPasswordLength() {
		UserService userService = new UserService();
		User newUser = new User();
		newUser.setName("Ajun");
		newUser.setEmail("arun@gmail.com");
		newUser.setPassword("Aj43");
		newUser.setImage("https://iili.io/J9kwBe9.jpg");
		newUser.setPhoneNumber(9923456787L);

		Exception exception = assertThrows(ValidationException.class, () -> {
			userService.createUser(newUser);
		});
		String expectedMessage = "Password should be at least 8 characters long";
		String actualMessage = exception.getMessage();
		 assertEquals(expectedMessage,actualMessage);
	}
	
	@Test
	 void testCreateUserWithNegativePhoneNumber() {
		UserService userService = new UserService();
		User newUser = new User();
		newUser.setName("Ajun");
		newUser.setEmail("arun@gmail.com");
		newUser.setPassword("Ajun@5555");
		newUser.setImage("https://iili.io/J9kwBe9.jpg");
		newUser.setPhoneNumber(-2);

		ValidationException exception = assertThrows(ValidationException.class, () -> {
			userService.createUser(newUser);
		});

		String expectedMessage = "Phone number cannot be zero or negative";
		String actualMessage = exception.getMessage();

		 assertEquals(expectedMessage,actualMessage);
	}
	
	@Test
	 void testCreateUserWithAllZeroPhoneNumber() {
		UserService userService = new UserService();
		User newUser = new User();
		newUser.setName("Ajun");
		newUser.setEmail("sesslyn@gmail.com");
		newUser.setPassword("Ajun@5555");
		newUser.setImage("https://iili.io/J9kwBe9.jpg");
		newUser.setPhoneNumber(98760567890L);

		ValidationException exception = assertThrows(ValidationException.class, () -> {
			userService.createUser(newUser);
		});

		String expectedMessage = "Phone number should be exactly 10 digits long";
		String actualMessage = exception.getMessage();

		assertEquals(expectedMessage,actualMessage);
	}

	@Test
	 void testCreateUserWithInvalidPhoneNumber() {
		UserService userService = new UserService();
		User newUser = new User();
		newUser.setName("Ajun");
		newUser.setEmail("arun@gmail.com");
		newUser.setPassword("Ajun@5555");
		newUser.setImage("https://iili.io/J9kwBe9.jpg");
		newUser.setPhoneNumber(3895673456L);

		ValidationException exception = assertThrows(ValidationException.class, () -> {
			userService.createUser(newUser);
		});

		String expectedMessage = "Invalid phone number format. Make sure not to include +91";
		String actualMessage = exception.getMessage();

		assertEquals(expectedMessage,actualMessage);
	}

	
	@Test
	 void testCreateUserWithInvalidImageUrl() {
		UserService userService = new UserService();
		User newUser = new User();
		newUser.setName("Ajun");
		newUser.setEmail("sesslyn@gmail.com");
		newUser.setPassword("Ajun@5555");
		  newUser.setImage("wp-content/uploads/2021/07/headshot-for-startup.jpg");
		newUser.setPhoneNumber(3895673456L);

		ValidationException exception = assertThrows(ValidationException.class, () -> {
			userService.createUser(newUser);
		});

		String expectedMessage = "Invalid image format. Please provide a valid image url.";
		String actualMessage = exception.getMessage();

		assertEquals(expectedMessage,actualMessage);
	}
}
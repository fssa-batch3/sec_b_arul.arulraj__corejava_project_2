package in.fssa.technolibrary;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Random;

import org.junit.jupiter.api.Test;

import in.fssa.technolibrary.exception.ValidationException;
import in.fssa.technolibrary.model.Publisher;
import in.fssa.technolibrary.service.PublisherService;
import in.fssa.technolibrary.validator.PublisherValidator;

public class TestCreatePublisher {
	
	@Test
	 public void testCreatePublisherWithValidDetails() {
	        Publisher newPublisher = new Publisher();

	        // Generate a random name with alphabetic characters
	        String generatedName = generateRandomAlphabeticName();

	        newPublisher.setName(generatedName);

	        assertDoesNotThrow(() -> {
	            PublisherService.create(newPublisher);
	        });

	        // Validate the generated name using the pattern
	        assertDoesNotThrow(() -> {
	            PublisherValidator.validateName(generatedName);
	        });
	    }

	    private String generateRandomAlphabeticName() {
	        int nameLength = 10; // Adjust the desired length
	        Random random = new Random();
	        StringBuilder sb = new StringBuilder(nameLength);
	        for (int i = 0; i < nameLength; i++) {
	            char randomChar = (char) (random.nextInt(26) + 'a');
	            sb.append(randomChar);
	        }
	        return sb.toString();
	    }
	
	@Test
	public void testPublisherNameNull() {
		
		Publisher newPublisher = new Publisher();

		newPublisher.setName(null);

		Exception exception = assertThrows(ValidationException.class, () -> {
			PublisherService.create(newPublisher);
		});

		String exceptedMessage = "Name cannot be null or empty";
		String actualMessage = exception.getMessage();

		assertTrue(exceptedMessage.equals(actualMessage));
	}
	
	@Test
	public void testPublisherNameEmpty() {
		
		Publisher newPublisher = new Publisher();

		newPublisher.setName("");

		Exception exception = assertThrows(ValidationException.class, () -> {
			PublisherService.create(newPublisher);
		});

		String exceptedMessage = "Name cannot be null or empty";
		String actualMessage = exception.getMessage();

		assertTrue(exceptedMessage.equals(actualMessage));
	}
	
	@Test
	public void testPublisherNamePattern() {
		
		Publisher newPublisher = new Publisher();

		newPublisher.setName("dcs676");

		Exception exception = assertThrows(ValidationException.class, () -> {
			PublisherService.create(newPublisher);
		});

		String exceptedMessage = "Name doesn't match the pattern";
		String actualMessage = exception.getMessage();

		assertTrue(exceptedMessage.equals(actualMessage));
	}

}

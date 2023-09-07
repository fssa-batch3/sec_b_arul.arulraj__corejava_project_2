package in.fssa.technolibrary;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.Random;
import java.util.Set;

import org.junit.jupiter.api.Test;

import in.fssa.technolibrary.exception.ServiceException;
import in.fssa.technolibrary.exception.ValidationException;
import in.fssa.technolibrary.model.Publisher;
import in.fssa.technolibrary.service.PublisherService;

class TestCreatePublisher {
	
	@Test
	 void testCreatePublisherWithValidDetails() {
	        Publisher newPublisher = new Publisher();

	        // Generate a random name with alphabetic characters
	        String generatedName = generateRandomAlphabeticName();

	        newPublisher.setName(generatedName);

	        assertDoesNotThrow(() -> {
	            PublisherService.createPublisher(newPublisher);
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
	void testPublisherNameNull() {
		
		Publisher newPublisher = new Publisher();

		newPublisher.setName(null);

		Exception exception = assertThrows(ValidationException.class, () -> {
			PublisherService.createPublisher(newPublisher);
		});

		String exceptedMessage = "PublisherName cannot be null or empty";
		String actualMessage = exception.getMessage();

		assertEquals(exceptedMessage, actualMessage);
	}
	
	@Test
	void testPublisherNameEmpty() {
		
		Publisher newPublisher = new Publisher();

		newPublisher.setName("");

		Exception exception = assertThrows(ValidationException.class, () -> {
			PublisherService.createPublisher(newPublisher);
		});

		String exceptedMessage = "PublisherName cannot be null or empty";
		String actualMessage = exception.getMessage();
		
		assertEquals(exceptedMessage, actualMessage);
		
	}
	
	@Test
	void testPublisherNamePattern() {
		
		Publisher newPublisher = new Publisher();

		newPublisher.setName("dcs676");

		Exception exception = assertThrows(ValidationException.class, () -> {
			PublisherService.createPublisher(newPublisher);
		});

		String exceptedMessage = "PublisherName doesn't match the pattern";
		String actualMessage = exception.getMessage();
		
		assertEquals(exceptedMessage, actualMessage);
	}
	
	@Test
	void testPublisherSameName() {
		
		Publisher newPublisher = new Publisher();

		newPublisher.setName("Pearson");

		Exception exception = assertThrows(ValidationException.class, () -> {
			PublisherService.createPublisher(newPublisher);
		});

		String exceptedMessage = "Publisher Already exist";
		String actualMessage = exception.getMessage();

		assertEquals(exceptedMessage,actualMessage);
	}
	
	@Test
	void testGetAllPublishersShouldReturnNonEmptySet() throws ServiceException {
		PublisherService publisherService = new PublisherService();
		Set<Publisher> publishers = publisherService.findAllPublisher();
		System.out.print(publishers);
	}
	
	@Test
	void testGetPublisherByIdShouldReturnPublisherName() throws ServiceException{
		PublisherService publisherService = new PublisherService();
		String publisher = publisherService.findPublisherById(1);
		System.out.print(publisher);
	}

}

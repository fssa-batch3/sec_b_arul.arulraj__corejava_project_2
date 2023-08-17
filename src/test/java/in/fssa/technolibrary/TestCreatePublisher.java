package in.fssa.technolibrary;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import in.fssa.technolibrary.exception.ValidationException;
import in.fssa.technolibrary.model.Publisher;
import in.fssa.technolibrary.service.PublisherService;

public class TestCreatePublisher {
	
	@Test
	public void testCreatePublisherWithValidDetails(){

		PublisherService publisherService = new PublisherService();
		
		Publisher newPublisher = new Publisher();
		
		newPublisher.setName("RajandCo");

		assertDoesNotThrow(() -> {
			publisherService.create(newPublisher);
		});
	}
	
	@Test
	public void testPublisherNameNull() {

		PublisherService publisherService = new PublisherService();
		
		Publisher newPublisher = new Publisher();

		newPublisher.setName(null);

		Exception exception = assertThrows(ValidationException.class, () -> {
			publisherService.create(newPublisher);
		});

		String exceptedMessage = "Name cannot be null or empty";
		String actualMessage = exception.getMessage();

		assertTrue(exceptedMessage.equals(actualMessage));
	}
	
	@Test
	public void testPublisherNameEmpty() {

		PublisherService publisherService = new PublisherService();
		
		Publisher newPublisher = new Publisher();

		newPublisher.setName("");

		Exception exception = assertThrows(ValidationException.class, () -> {
			publisherService.create(newPublisher);
		});

		String exceptedMessage = "Name cannot be null or empty";
		String actualMessage = exception.getMessage();

		assertTrue(exceptedMessage.equals(actualMessage));
	}
	
	@Test
	public void testPublisherNamePattern() {

		PublisherService publisherService = new PublisherService();
		
		Publisher newPublisher = new Publisher();

		newPublisher.setName("dcs676");

		Exception exception = assertThrows(ValidationException.class, () -> {
			publisherService.create(newPublisher);
		});

		String exceptedMessage = "Name doesn't match the pattern";
		String actualMessage = exception.getMessage();

		assertTrue(exceptedMessage.equals(actualMessage));
	}

}

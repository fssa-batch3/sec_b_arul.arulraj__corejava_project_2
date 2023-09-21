package in.fssa.technolibrary;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.junit.jupiter.api.Test;

import in.fssa.technolibrary.exception.ServiceException;
import in.fssa.technolibrary.exception.ValidationException;
import in.fssa.technolibrary.model.Order;
import in.fssa.technolibrary.service.OrderService;

public class TestUpdateOrder {
	
	LocalDate currentDate = LocalDate.now();
	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd"); // Define your desired date format
	String formattedDate = currentDate.format(formatter); // Format the current date as a string
	
	
	@Test
	void testUpdateOrderStatus() {
		
		
		assertDoesNotThrow(() -> {
			Order newOrder = new Order();
			newOrder.setStatus("Delivered");
			newOrder.setLast_update_on(formattedDate);
			OrderService.updateStatus(2, newOrder);
		});
	}
	
	@Test
	void testUpdateWithNonExistingId() throws ValidationException {
		
		Order newOrder = new Order();
		newOrder.setStatus("Delivered");
		newOrder.setLast_update_on(formattedDate);
		Exception exception = assertThrows(ServiceException.class, () -> {
			OrderService.updateStatus(10000, newOrder);
		});
		
		String expectedMessage = "Order doesn't exist";
		String actualMessage = exception.getMessage();

		assertEquals(expectedMessage, actualMessage);
	}

}

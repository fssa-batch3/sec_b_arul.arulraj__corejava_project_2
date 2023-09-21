package in.fssa.technolibrary;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.junit.jupiter.api.Test;

import in.fssa.technolibrary.exception.ValidationException;
import in.fssa.technolibrary.model.Order;
import in.fssa.technolibrary.service.OrderService;

public class TestCreateOrder {
	
	@Test
	void testCreateOrderWithValidDetails() throws Exception {
		LocalDate currentDate = LocalDate.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd"); // Define your desired date format
		String formattedDate = currentDate.format(formatter); // Format the current date as a string
		Order newOrder = new Order();

		newOrder.setBook_id(8);
		newOrder.setUser_id(2);
		newOrder.setOrder_date(formattedDate);
		newOrder.setAddress("10-92,vaniyakudi,Church Street,kanniyakumari-629251");
		newOrder.setStatus("Ordered");
		assertDoesNotThrow(() -> {
			OrderService.createNewOrder(newOrder);
		});
		}
	
	@Test
	 void testCreateOrderWithPastDate() {

		Order newOrder = new Order();

		newOrder.setBook_id(8);
		newOrder.setUser_id(2);
		newOrder.setOrder_date("2023-03-02");
		newOrder.setAddress("10-92,vaniyakudi,Church Street,kanniyakumari-629251");
		newOrder.setStatus("Ordered");

		Exception exception = assertThrows(ValidationException.class, () -> {
			OrderService.createNewOrder(newOrder);
		});
		
		String expectedMessage = "Date can't be in past or future";
		String actualMessage = exception.getMessage();

		assertEquals(expectedMessage,actualMessage);
	}
	
	@Test
	 void testCreateOrderWithFutureDate() {

		Order newOrder = new Order();

		newOrder.setBook_id(8);
		newOrder.setUser_id(2);
		newOrder.setOrder_date("2050-03-02");
		newOrder.setAddress("10-92,vaniyakudi,Church Street,kanniyakumari-629251");
		newOrder.setStatus("Ordered");

		Exception exception = assertThrows(ValidationException.class, () -> {
			OrderService.createNewOrder(newOrder);
		});
		String expectedMessage = "Date can't be in past or future";
		String actualMessage = exception.getMessage();

		assertEquals(expectedMessage,actualMessage);
	}
	
	@Test
	 void testCreateOrderWithWrongFormat() {

		Order newOrder = new Order();

		newOrder.setBook_id(8);
		newOrder.setUser_id(2);
		newOrder.setOrder_date("03-2023-23");
		newOrder.setAddress("10-92,vaniyakudi,Church Street,kanniyakumari-629251");
		newOrder.setStatus("Ordered");

		Exception exception = assertThrows(ValidationException.class, () -> {
			OrderService.createNewOrder(newOrder);
		});
		String expectedMessage = "Invalid date or Invalid date format (yyyy-MM-dd)";
		String actualMessage = exception.getMessage();

		assertEquals(expectedMessage,actualMessage);
	}
	
	@Test
	 void testCreateOrderWithNotExistBookId() {

		Order newOrder = new Order();

		newOrder.setBook_id(1000);
		newOrder.setUser_id(2);
		newOrder.setOrder_date("03-2023-23");
		newOrder.setAddress("10-92,vaniyakudi,Church Street,kanniyakumari-629251");
		newOrder.setStatus("Ordered");

		Exception exception = assertThrows(ValidationException.class, () -> {
			OrderService.createNewOrder(newOrder);
		});
		String expectedMessage = "Book doesn't exist";
		String actualMessage = exception.getMessage();

		assertEquals(expectedMessage,actualMessage);
	}
	
	@Test
	void testCreateOrderWithNotExistUserId() {

		Order newOrder = new Order();

		newOrder.setBook_id(1);
		newOrder.setUser_id(10000);
		newOrder.setOrder_date("03-2023-23");
		newOrder.setAddress("10-92,vaniyakudi,Church Street,kanniyakumari-629251");
		newOrder.setStatus("Ordered");

		Exception exception = assertThrows(ValidationException.class, () -> {
			OrderService.createNewOrder(newOrder);
		});
		String expectedMessage = "User doesn't exist";
		String actualMessage = exception.getMessage();

		assertEquals(expectedMessage,actualMessage);
	}
}

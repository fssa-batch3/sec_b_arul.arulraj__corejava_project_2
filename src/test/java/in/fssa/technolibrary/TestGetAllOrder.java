package in.fssa.technolibrary;

import java.util.Set;

import org.junit.jupiter.api.Test;

import in.fssa.technolibrary.exception.ServiceException;
import in.fssa.technolibrary.model.Order;
import in.fssa.technolibrary.service.OrderService;

public class TestGetAllOrder {

	@Test
	void getAllOrder() throws ServiceException {
		OrderService orderService = new OrderService();
		Set<Order> orders = orderService.findAllOrder();
		System.out.print(orders);
	}
    
    @Test
	void getAllOrderByUserId() throws ServiceException {
		OrderService orderService = new OrderService();
		Set<Order> orders = orderService.findAllOrderByUserId(2);
		System.out.print(orders);
	}
	
}

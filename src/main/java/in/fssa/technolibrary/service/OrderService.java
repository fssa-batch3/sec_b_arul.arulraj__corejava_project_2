package in.fssa.technolibrary.service;

import java.util.Set;

import in.fssa.technolibrary.dao.OrderDAO;
import in.fssa.technolibrary.exception.PersistanceException;
import in.fssa.technolibrary.exception.ServiceException;
import in.fssa.technolibrary.exception.ValidationException;
import in.fssa.technolibrary.model.Order;
import in.fssa.technolibrary.util.Logger;
import in.fssa.technolibrary.validator.OrderValidator;

public class OrderService {
	
	/**
	 * 
	 * @param newOrder
	 * @throws ServiceException
	 * @throws ValidationException
	 */
	public static void createNewOrder(Order newOrder) throws ServiceException, ValidationException {
		try {
			OrderValidator.validateOrder(newOrder);
			OrderDAO orderDAO = new OrderDAO();
			orderDAO.create(newOrder);
//			BookDAO bookDAO = new BookDAO();
//			bookDAO.delete(newOrder.getBook_id());
			
		} catch (PersistanceException e) {
			Logger.error(e);
			throw new ServiceException("Error occurred while creating order.", e);
		}
	}
	
	public Set<Order> findAllOrder() throws ServiceException {
	    try {
	        OrderDAO orderDAO = new OrderDAO();
	        return orderDAO.findAllOrder();
	    } catch (PersistanceException e) {
	        Logger.error(e);
	        throw new ServiceException("Error while retrieving all Orders");
	    }
	}
	
	/**
	 * 
	 * @param id
	 * @param updatedStatus
	 * @throws ServiceException
	 */
	public static void updateStatus(int id, Order updatedStatus) throws ServiceException {
		try {
		OrderValidator.validateOrderId(id);
		OrderDAO orderDAO = new OrderDAO();
		orderDAO.updateOrderStatus(id, updatedStatus);
		} catch (PersistanceException | ValidationException e) {
			Logger.error(e);
			throw new ServiceException("Order doesn't exist");
		}
	}
	
	/**
	 * 
	 * @param userId
	 * @return
	 * @throws ServiceException
	 */
	public Set<Order> findAllOrderByUserId(int userId) throws ServiceException {
	    try {
	        OrderValidator.validateUserId(userId);
	        OrderDAO orderDAO = new OrderDAO();
	        return orderDAO.findOrderByUserId(userId);
	    } catch (PersistanceException | ValidationException e) {
	        Logger.error(e);
	        throw new ServiceException("Error while retrieving all Orders for the user");
	    }
	}

}

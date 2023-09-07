package in.fssa.technolibrary.service;

import java.util.Set;

import in.fssa.technolibrary.dao.PublisherDAO;
import in.fssa.technolibrary.exception.PersistanceException;
import in.fssa.technolibrary.exception.ServiceException;
import in.fssa.technolibrary.exception.ValidationException;
import in.fssa.technolibrary.model.Publisher;
import in.fssa.technolibrary.util.Logger;
import in.fssa.technolibrary.validator.PublisherValidator;

public class PublisherService {
	/**
	 * 
	 * @param newPublisher
	 * @throws ServiceException 
	 * @throws ValidationException 
	 * @throws Exception
	 */
	public static void createPublisher(Publisher newPublisher) throws ValidationException, ServiceException  {
		try {
		PublisherValidator.validate(newPublisher);
		PublisherDAO publisherDao = new PublisherDAO();
		publisherDao.create(newPublisher);
		}
		catch (PersistanceException e) {
			Logger.error(e);
			throw new ServiceException("Error occurred while creating Publisher.", e);
		}
	}
	/**
	 * 
	 * @return
	 * @throws ServiceException
	 */
	public Set<Publisher> findAllPublisher() throws ServiceException {
		try {
			PublisherDAO publisherDAO = new PublisherDAO();
			return publisherDAO.findAll();
		} catch (PersistanceException e) {
			Logger.error(e);
			throw new ServiceException("Error while retrieving all publishers");
		}
	}
	
	/**
	 * 
	 * @param publisherId
	 * @return
	 * @throws ServiceException
	 */
	public String findPublisherById(int publisherId) throws ServiceException{
		try {
			PublisherDAO publisherDAO = new PublisherDAO();
			String publisher = publisherDAO.findById(publisherId);
			return publisher;
		} catch (PersistanceException e) {
			Logger.error(e);
			throw new ServiceException("There is no publisher in this id");
		}

	}


}

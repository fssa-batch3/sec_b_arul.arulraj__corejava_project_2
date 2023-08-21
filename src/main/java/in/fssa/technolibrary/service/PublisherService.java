package in.fssa.technolibrary.service;

import in.fssa.technolibrary.dao.PublisherDAO;
import in.fssa.technolibrary.model.Publisher;
import in.fssa.technolibrary.validator.PublisherValidator;

public class PublisherService {
	/**
	 * 
	 * @param newPublisher
	 * @throws Exception
	 */
	public static void create(Publisher newPublisher) throws Exception {
		PublisherValidator.validate(newPublisher);
		PublisherDAO publisherDao = new PublisherDAO();
		publisherDao.create(newPublisher);
	}


}

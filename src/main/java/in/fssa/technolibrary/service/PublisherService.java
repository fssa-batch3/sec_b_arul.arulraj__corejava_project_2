package in.fssa.technolibrary.service;

import in.fssa.technolibrary.dao.PublisherDAO;
import in.fssa.technolibrary.model.Publisher;
import in.fssa.technolibrary.validator.PublisherValidator;

public class PublisherService {
	
	public static void create(Publisher newPublisher) throws Exception {
		PublisherValidator.validate(newPublisher);
		PublisherValidator.publisherIdAlreadyExistOrNot(newPublisher.getId());
		PublisherDAO publisherDao = new PublisherDAO();
		publisherDao.create(newPublisher);
	}


}

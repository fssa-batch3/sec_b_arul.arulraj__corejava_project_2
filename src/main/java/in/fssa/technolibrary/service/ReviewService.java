package in.fssa.technolibrary.service;

import in.fssa.technolibrary.dao.ReviewDAO;
import in.fssa.technolibrary.exception.PersistanceException;
import in.fssa.technolibrary.exception.ServiceException;
import in.fssa.technolibrary.exception.ValidationException;
import in.fssa.technolibrary.model.Review;
import in.fssa.technolibrary.validator.ReviewValidator;

public class ReviewService {
	
	/**
	 * 
	 * @param newReview
	 * @throws ValidationException
	 * @throws ServiceException
	 */
	public void createReview(Review newReview) throws ValidationException, ServiceException {
		try {
			ReviewValidator.validateReview(newReview);
			ReviewDAO reviewDAO = new ReviewDAO();
			reviewDAO.create(newReview);
		} catch (PersistanceException e) {
			throw new ServiceException("Error occurred while creating Review.", e);
		}
	}

}

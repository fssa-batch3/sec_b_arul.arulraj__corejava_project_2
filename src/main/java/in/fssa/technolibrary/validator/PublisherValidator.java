package in.fssa.technolibrary.validator;

import java.util.regex.Pattern;

import in.fssa.technolibrary.dao.PublisherDAO;
import in.fssa.technolibrary.exception.PersistanceException;
import in.fssa.technolibrary.exception.ServiceException;
import in.fssa.technolibrary.exception.ValidationException;
import in.fssa.technolibrary.model.Publisher;
import in.fssa.technolibrary.util.StringUtil;

public class PublisherValidator {

	private static final String NAME_PATTERN = "^[A-Za-z][A-Za-z\\s]*$";

	/**
	 * 
	 * @param publisher
	 * @throws ValidationException
	 * @throws ServiceException
	 */
	public static void validate(Publisher publisher) throws ValidationException, ServiceException {
		if (publisher == null) {
			throw new ValidationException("Invalid user input");
		}

		validateName(publisher.getName());
	}

	/**
	 * 
	 * @param name
	 * @throws ValidationException
	 * @throws ServiceException
	 * @throws PersistanceException
	 */
	public static void validateName(String publisherName) throws ValidationException, ServiceException {
		try {
			StringUtil.rejectIfInvalidString(publisherName, "PublisherName");

			if (!Pattern.matches(NAME_PATTERN, publisherName)) {
				throw new ValidationException("PublisherName doesn't match the pattern");
			}

			PublisherDAO.publisherNameAlreadyExist(publisherName);
		} catch (PersistanceException e) {
			throw new ServiceException("Publisher Already exists");
		}
	}

	/**
	 * 
	 * @param publisherName
	 * @throws ValidationException
	 * @throws ServiceException 
	 */
	public static void validateNamePattern(String publisherName) throws ValidationException, ServiceException {
		try {
			StringUtil.rejectIfInvalidString(publisherName, "PublisherName");

			if (!Pattern.matches(NAME_PATTERN, publisherName)) {
				throw new ValidationException("PublisherName doesn't match the pattern");
			}

			PublisherDAO.publisherNameAlreadyExistOrNot(publisherName);
		} catch (PersistanceException e) {
			throw new ServiceException("Publisher doesn't exist");
		}

	}

	/**
	 * 
	 * @param id
	 * @throws ValidationException
	 * @throws PersistanceException
	 */
	public static void validateId(int id) throws ValidationException {
		try {
			if (id <= 0) {
				throw new ValidationException("Id can not be less than zero.");
			}
			PublisherDAO.publisherIdAlreadyExistOrNot(id);
		} catch (PersistanceException e) {
			throw new ValidationException("Publisher doesn't exist");
		}
	}
}

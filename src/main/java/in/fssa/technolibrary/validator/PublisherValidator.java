package in.fssa.technolibrary.validator;

import java.util.regex.Pattern;

import in.fssa.technolibrary.exception.ValidationException;
import in.fssa.technolibrary.model.Publisher;
import in.fssa.technolibrary.util.StringUtil;

public class PublisherValidator {
	
private static final String NAME_PATTERN = "^[A-Za-z][A-Za-z\\\\s]*$";
	/**
	 * 
	 * @param publisher
	 * @throws ValidationException
	 */
	public static void validate(Publisher publisher) throws ValidationException {
		if (publisher == null) {
			throw new ValidationException("Invalid user input");
		}
		
		validateName(publisher.getName());
//		publisherIdAlreadyExistOrNot(publisher.getId());
	}
	/**
	 * 
	 * @param name
	 * @throws ValidationException
	 */
	public static void validateName(String name) throws ValidationException {
		
		StringUtil.rejectIfInvalidString(name, "Name");
		
		if (!Pattern.matches(NAME_PATTERN, name)) {
			throw new ValidationException("Name doesn't match the pattern");
		}
	}
	/**
	 * 
	 * @param id
	 * @throws ValidationException
	 */
	public static void validateId(int id) throws ValidationException {
		if (id <= 0) {
			throw new ValidationException("Id can not be less than zero.");
		}
	
	}

}

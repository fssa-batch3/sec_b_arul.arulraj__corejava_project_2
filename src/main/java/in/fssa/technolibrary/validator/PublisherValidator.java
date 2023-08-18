package in.fssa.technolibrary.validator;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.regex.Pattern;

import in.fssa.technolibrary.exception.ValidationException;
import in.fssa.technolibrary.model.Publisher;
import in.fssa.technolibrary.util.ConnectionUtil;
import in.fssa.technolibrary.util.StringUtil;

public class PublisherValidator {
	
private static final String NAME_PATTERN = "^[A-Za-z][A-Za-z\\\\s]*$";
	
	public static void validate(Publisher publisher) throws ValidationException {
		if (publisher == null) {
			throw new ValidationException("Invalid user input");
		}
		
		validateName(publisher.getName());
//		publisherIdAlreadyExistOrNot(publisher.getId());
	}
	
	public static void validateName(String name) throws ValidationException {
		
		StringUtil.rejectIfInvalidString(name, "Name");
		
		if (!Pattern.matches(NAME_PATTERN, name)) {
			throw new ValidationException("Name doesn't match the pattern");
		}
	}
	
	public static void validateId(int id) throws ValidationException {
		if (id <= 0) {
			throw new ValidationException("Id can not be less than zero.");
		}
	
	}

}

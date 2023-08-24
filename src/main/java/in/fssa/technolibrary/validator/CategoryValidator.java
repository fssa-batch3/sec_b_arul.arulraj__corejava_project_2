package in.fssa.technolibrary.validator;

import java.util.regex.Pattern;

import in.fssa.technolibrary.dao.CategoryDAO;
import in.fssa.technolibrary.exception.PersistanceException;
import in.fssa.technolibrary.exception.ValidationException;
import in.fssa.technolibrary.model.Category;
import in.fssa.technolibrary.util.StringUtil;

public class CategoryValidator {
	
	private static final String NAME_PATTERN = "^[A-Za-z][A-Za-z\\\\s]*$";
	/**
	 * 
	 * @param category
	 * @throws ValidationException
	 * @throws PersistanceException 
	 */
	public static void validate(Category category) throws ValidationException, PersistanceException {
		if (category == null) {
			throw new ValidationException("Invalid user input");
		}
		
		validateName(category.getName());
		validateId(category.getId());
	}
	/**
	 * 
	 * @param name
	 * @throws ValidationException
	 */
	public static void validateName(String categorNname) throws ValidationException {
		
		StringUtil.rejectIfInvalidString(categorNname, "Name");
		
		if (!Pattern.matches(NAME_PATTERN, categorNname)) {
			throw new ValidationException("Name doesn't match the pattern");
		}
	}
	/**
	 * 
	 * @param id
	 * @throws ValidationException
	 * @throws PersistanceException 
	 */
	public static void validateId(int categoryId) throws ValidationException, PersistanceException {
		if (categoryId <= 0) {
			throw new ValidationException("Id can not be less than zero.");
		}
		CategoryDAO.categoryIdAlreadyExistOrNot(categoryId);
	}
	

}

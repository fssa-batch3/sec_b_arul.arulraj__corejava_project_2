package in.fssa.technolibrary.validator;

import java.util.regex.Pattern;

import in.fssa.technolibrary.dao.CategoryDAO;
import in.fssa.technolibrary.exception.PersistanceException;
import in.fssa.technolibrary.exception.ServiceException;
import in.fssa.technolibrary.exception.ValidationException;
import in.fssa.technolibrary.model.Category;
import in.fssa.technolibrary.util.StringUtil;

public class CategoryValidator {
	
	private static final String NAME_PATTERN = "^[A-Za-z][A-Za-z\\s]*$";
	/**
	 * 
	 * @param category
	 * @throws ValidationException
	 * @throws PersistanceException 
	 * @throws ServiceException 
	 */
	public static void validate(Category category) throws ValidationException, ServiceException {
		if (category == null) {
			throw new ValidationException("Invalid user input");
		}
		
		validateName(category.getName());
	}
	/**
	 * 
	 * @param name
	 * @throws ValidationException
	 * @throws ServiceException 
	 */
	public static void validateName(String categoryName) throws ValidationException, ServiceException {
		try {
		StringUtil.rejectIfInvalidString(categoryName, "Name");
		
		if (!Pattern.matches(NAME_PATTERN, categoryName)) {
			throw new ValidationException("Name doesn't match the pattern");
		}
		CategoryDAO.categoryNameAlreadyExists(categoryName);
		} catch (PersistanceException e) {
			throw new ServiceException("Category Name already exist");
		}
	}
	/**
	 * 
	 * @param id
	 * @throws ValidationException
	 * @throws PersistanceException 
	 * @throws ServiceException 
	 */
	public static void validateId(int categoryId) throws ValidationException, ServiceException {
		try {
		if (categoryId <= 0) {
			throw new ValidationException("Id can not be less than zero.");
		}
		CategoryDAO.categoryIdAlreadyExistOrNot(categoryId);
		} catch (PersistanceException e) {
			throw new ServiceException("Category doesn't exist");
		}
	}
	

}

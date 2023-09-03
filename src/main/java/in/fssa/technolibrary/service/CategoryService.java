package in.fssa.technolibrary.service;

import java.util.Set;

import in.fssa.technolibrary.dao.CategoryDAO;
import in.fssa.technolibrary.dao.PublisherDAO;
import in.fssa.technolibrary.exception.PersistanceException;
import in.fssa.technolibrary.exception.ServiceException;
import in.fssa.technolibrary.exception.ValidationException;
import in.fssa.technolibrary.model.Category;
import in.fssa.technolibrary.model.Publisher;
import in.fssa.technolibrary.validator.CategoryValidator;
import in.fssa.technolibrary.validator.PublisherValidator;

public class CategoryService {
	/**
	 * 
	 * @param newCategory
	 * @throws ServiceException 
	 * @throws ValidationException 
	 */
	public static void createCategory(Category newCategory) throws ValidationException, ServiceException   {
		try {
		CategoryValidator.validate(newCategory);
		
		CategoryDAO categoryDao = new CategoryDAO();
		categoryDao.create(newCategory);
	}
	catch (PersistanceException e) {
		throw new ServiceException("Error occurred while creating Publisher.", e);
	}
	}
	/**
	 * 
	 * @return
	 * @throws ServiceException
	 */
	public Set<Category> findAllcategory() throws ServiceException {
		try {
			CategoryDAO categoryDAO = new CategoryDAO();
			return categoryDAO.findAll();
		} catch (PersistanceException e) {
			e.printStackTrace();
			throw new ServiceException("Error while retrieving all publishers");
		}
	}
	public  int findIdByCategoryName(String categoryName) throws ServiceException, ValidationException {
		try {
			CategoryValidator.validateName(categoryName);
			CategoryDAO categoryDAO = new CategoryDAO();
			int category_id = categoryDAO.findCategoryIdByCategoryName(categoryName);
			return category_id;

		} catch (PersistanceException e) {
			throw new ServiceException("There is no category_id in this Name", e);
		}

	}

}

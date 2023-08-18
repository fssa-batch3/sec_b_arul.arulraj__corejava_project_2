package in.fssa.technolibrary.service;

import in.fssa.technolibrary.dao.CategoryDAO;
import in.fssa.technolibrary.model.Category;
import in.fssa.technolibrary.validator.CategoryValidator;

public class CategoryService {
	/**
	 * 
	 * @param newCategory
	 * @throws Exception
	 */
	public static void create(Category newCategory) throws Exception {
		CategoryValidator.validate(newCategory);
		CategoryDAO bookDao = new CategoryDAO();
		bookDao.categoryIdAlreadyExistOrNot(newCategory.getId());

		bookDao.create(newCategory);
	}

}

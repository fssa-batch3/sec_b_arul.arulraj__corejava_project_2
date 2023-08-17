package in.fssa.technolibrary.service;

import in.fssa.technolibrary.dao.CategoryDAO;
import in.fssa.technolibrary.model.Category;
import in.fssa.technolibrary.validator.CategoryValidator;

public class CategoryService {
	
	public static void create(Category newCategory) throws Exception {
		CategoryValidator.validate(newCategory);
		CategoryValidator.categoryIdAlreadyExistOrNot(newCategory.getId());
		CategoryDAO bookDao = new CategoryDAO();
		bookDao.create(newCategory);
	}

}

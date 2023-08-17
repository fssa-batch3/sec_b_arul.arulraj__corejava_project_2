package in.fssa.technolibrary;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import in.fssa.technolibrary.exception.ValidationException;
import in.fssa.technolibrary.model.Category;
import in.fssa.technolibrary.service.CategoryService;

public class TestCreateCategory {
	
	@Test
	public void testCreateCategoryWithValidDetails(){

		CategoryService categoryService = new CategoryService();
		
		Category newCategory = new Category();
		
		newCategory.setName("Thriller");

		assertDoesNotThrow(() -> {
			categoryService.create(newCategory);
		});
	}
	
	@Test
	public void testCategoryNameNull() {

		CategoryService categoryService = new CategoryService();
		
		Category newCategory = new Category();

		newCategory.setName(null);

		Exception exception = assertThrows(ValidationException.class, () -> {
			categoryService.create(newCategory);
		});

		String exceptedMessage = "Name cannot be null or empty";
		String actualMessage = exception.getMessage();

		assertTrue(exceptedMessage.equals(actualMessage));
	}
	
	@Test
	public void testCategoryNameEmpty() {

		CategoryService categoryService = new CategoryService();
		
		Category newCategory = new Category();

		newCategory.setName("");

		Exception exception = assertThrows(ValidationException.class, () -> {
			categoryService.create(newCategory);
		});

		String exceptedMessage = "Name cannot be null or empty";
		String actualMessage = exception.getMessage();

		assertTrue(exceptedMessage.equals(actualMessage));
	}
	
	@Test
	public void testCategoryNamePattern() {

		CategoryService categoryService = new CategoryService();
		
		Category newCategory = new Category();

		newCategory.setName("dcs676");

		Exception exception = assertThrows(ValidationException.class, () -> {
			categoryService.create(newCategory);
		});

		String exceptedMessage = "Name doesn't match the pattern";
		String actualMessage = exception.getMessage();

		assertTrue(exceptedMessage.equals(actualMessage));
	}
}

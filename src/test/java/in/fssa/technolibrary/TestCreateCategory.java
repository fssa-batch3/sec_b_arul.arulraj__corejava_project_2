package in.fssa.technolibrary;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.Random;
import java.util.Set;

import org.junit.jupiter.api.Test;

import in.fssa.technolibrary.exception.ServiceException;
import in.fssa.technolibrary.exception.ValidationException;
import in.fssa.technolibrary.model.Category;
import in.fssa.technolibrary.service.CategoryService;

class TestCreateCategory {

    @Test
    void testCreateCategoryWithRandomName() {
        Category newCategory = new Category();
        String generatedName = generateRandomAlphabeticName();

        newCategory.setName(generatedName);

        assertDoesNotThrow(() -> {
            CategoryService.createCategory(newCategory);
        });
    }

    private String generateRandomAlphabeticName() {
        int nameLength = 10; // Adjust the desired length
        Random random = new Random();
        StringBuilder sb = new StringBuilder(nameLength);
        for (int i = 0; i < nameLength; i++) {
            char randomChar = (char) (random.nextInt(26) + 'a');
            sb.append(randomChar);
        }
        return sb.toString();
    }

    @Test
    void testCategoryNameNull() {
        Category newCategory = new Category();
        newCategory.setName(null);

        Exception exception = assertThrows(ValidationException.class, () -> {
            CategoryService.createCategory(newCategory);
        });

        String expectedMessage = "Name cannot be null or empty";
        String actualMessage = exception.getMessage();

        assertEquals(expectedMessage, actualMessage);
    }

    @Test
    void testCategoryNameEmpty() {
        Category newCategory = new Category();
        newCategory.setName("");

        Exception exception = assertThrows(ValidationException.class, () -> {
            CategoryService.createCategory(newCategory);
        });

        String expectedMessage = "Name cannot be null or empty";
        String actualMessage = exception.getMessage();

        assertEquals(expectedMessage, actualMessage);
    }

    @Test
    void testCategoryNamePattern() {
        Category newCategory = new Category();
        newCategory.setName("dcs676");

        Exception exception = assertThrows(ValidationException.class, () -> {
            CategoryService.createCategory(newCategory);
        });

        String expectedMessage = "Name doesn't match the pattern";
        String actualMessage = exception.getMessage();

        assertEquals(expectedMessage, actualMessage);
    }
    
    @Test
	void testCategorySameName() {
		
		Category newCategory = new Category();

		newCategory.setName("Thriller");

		Exception exception = assertThrows(ValidationException.class, () -> {
			CategoryService.createCategory(newCategory);
		});

		String exceptedMessage = "Category Name already exist";
		String actualMessage = exception.getMessage();
		assertEquals(exceptedMessage, actualMessage);
	}
    
	@Test
	void testGetAllCategoriesShouldReturnNonEmptySet() throws ServiceException {
		CategoryService categoryService = new CategoryService();
		Set<Category> categorys = categoryService.findAllcategory();
		System.out.print(categorys);
	}
	
	@Test
	void testGetCategoryByIdShouldReturnCategoryName() throws ServiceException{
		CategoryService categoryService = new CategoryService();
		String category = categoryService.findCategoryById(1);
		System.out.print(category);
	}
}

package in.fssa.technolibrary;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Random;

import org.junit.jupiter.api.Test;

import in.fssa.technolibrary.exception.ValidationException;
import in.fssa.technolibrary.model.Category;
import in.fssa.technolibrary.service.CategoryService;
import in.fssa.technolibrary.validator.CategoryValidator;

public class TestCreateCategory {

    @Test
    public void testCreateCategoryWithRandomName() {
        Category newCategory = new Category();
        String generatedName = generateRandomAlphabeticName();

        newCategory.setName(generatedName);

        assertDoesNotThrow(() -> {
            CategoryService.createCategory(newCategory);
        });
        assertDoesNotThrow(() -> {
            CategoryValidator.validateName(generatedName);
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
    public void testCategoryNameNull() {
        Category newCategory = new Category();
        newCategory.setName(null);

        Exception exception = assertThrows(ValidationException.class, () -> {
            CategoryService.createCategory(newCategory);
        });

        String expectedMessage = "Name cannot be null or empty";
        String actualMessage = exception.getMessage();

        assertTrue(expectedMessage.equals(actualMessage));
    }

    @Test
    public void testCategoryNameEmpty() {
        Category newCategory = new Category();
        newCategory.setName("");

        Exception exception = assertThrows(ValidationException.class, () -> {
            CategoryService.createCategory(newCategory);
        });

        String expectedMessage = "Name cannot be null or empty";
        String actualMessage = exception.getMessage();

        assertTrue(expectedMessage.equals(actualMessage));
    }

    @Test
    public void testCategoryNamePattern() {
        Category newCategory = new Category();
        newCategory.setName("dcs676");

        Exception exception = assertThrows(ValidationException.class, () -> {
            CategoryService.createCategory(newCategory);
        });

        String expectedMessage = "Name doesn't match the pattern";
        String actualMessage = exception.getMessage();

        assertTrue(expectedMessage.equals(actualMessage));
    }
}

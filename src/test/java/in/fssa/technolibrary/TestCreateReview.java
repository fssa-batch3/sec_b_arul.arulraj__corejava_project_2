package in.fssa.technolibrary;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import in.fssa.technolibrary.exception.ValidationException;
import in.fssa.technolibrary.model.Review;
import in.fssa.technolibrary.service.ReviewService;

class TestCreateReview {
	
	 @Test
	    void testCreateReviewWithValidData() {
	        
		 	ReviewService reviewService = new ReviewService();
	        Review newReview = new Review();
	        
	        newReview.setFeedback("Great book!");
	        newReview.setRatings(5);
	        newReview.setBook_id(8);
	        newReview.setUser_id(2);
	        
	        assertDoesNotThrow(() -> {
	        	reviewService.createReview(newReview); 
		    });
	        
	    }

	    @Test
	    void testCreateReviewWithNullReview() {
	    	
	    	ReviewService reviewService = new ReviewService();
			Exception exception = assertThrows(ValidationException.class, () -> {
				reviewService.createReview(null);
			});
			String expectedMessage = "User object cannot be null";
			String actualMessage = exception.getMessage();

			assertEquals(expectedMessage,actualMessage);
	    }

	    @Test
	    void testCreateReviewWithInvalidBookId() {
	       
	        Review invalidReview = new Review();
	        invalidReview.setFeedback("Good book!");
	        invalidReview.setRatings(4);
	        invalidReview.setBook_id(-1);
	        invalidReview.setUser_id(2);

	        ReviewService reviewService = new ReviewService();

	        Exception exception = assertThrows(ValidationException.class, () -> {
	        	reviewService.createReview(invalidReview);
			});
			String expectedMessage = "Book Id cannot be zero or negative";
			String actualMessage = exception.getMessage();

			assertEquals(expectedMessage,actualMessage);
	    }
	    
	    @Test
	    void testCreateReviewWithOutBought() {
	       
	        Review invalidReview = new Review();
	        invalidReview.setFeedback("Good book!");
	        invalidReview.setRatings(4);
	        invalidReview.setBook_id(8);
	        invalidReview.setUser_id(4);

	        ReviewService reviewService = new ReviewService();

	        Exception exception = assertThrows(ValidationException.class, () -> {
	        	reviewService.createReview(invalidReview);
			});
			String expectedMessage = "The user has Not bought the book.";
			String actualMessage = exception.getMessage();

			assertEquals(expectedMessage,actualMessage);
	    }
	    
	    
	    @Test
	    void testCreateReviewWithNotOrderedBookId() {
	       
	        Review invalidReview = new Review();
	        invalidReview.setFeedback("Good book!");
	        invalidReview.setRatings(4);
	        invalidReview.setBook_id(1000);

	        ReviewService reviewService = new ReviewService();

	        Exception exception = assertThrows(ValidationException.class, () -> {
	        	reviewService.createReview(invalidReview);
			});
			String expectedMessage = "You can not revieing book before ordering.";
			String actualMessage = exception.getMessage();

			assertEquals(expectedMessage,actualMessage);
	    }

}

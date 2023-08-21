package in.fssa.technolibrary.util;

import in.fssa.technolibrary.exception.ValidationException;

public class StringUtil {
	/**
	 * 
	 * @param input
	 * @param inputName
	 * @throws ValidationException
	 */
	public static void rejectIfInvalidString(String input, String inputName) throws ValidationException {

	if (input == null || "".equals(input.trim())) {
			throw new ValidationException(inputName.concat(" cannot be null or empty"));
		}
	}
	/**
	 * 
	 * @param input
	 * @param inputName
	 * @return
	 */
	public static boolean isvalidString(String input, String inputName) {

		if (input == null || "".equals(input.trim())) {
			return true;
		}
		return false;
	}
	/**
	 * 
	 * @param input
	 * @param inputName
	 * @return
	 */
	public static boolean isInvalidString(String input, String inputName) {

		if (input == null || "".equals(input.trim())) {
			return true;
		}
		return false;
	}
}

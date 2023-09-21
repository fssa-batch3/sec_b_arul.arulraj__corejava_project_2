package in.fssa.technolibrary.util;

import java.util.Random;

public class Randomgenerater {
	
	public static String generateRandomAlphabeticName() {
		int nameLength = 10;
		Random random = new Random();
		StringBuilder sb = new StringBuilder(nameLength);
		for (int i = 1; i < nameLength; i++) {
			char randomChar = (char) (random.nextInt(26) + 'a');
			sb.append(randomChar);
		}
		return sb.toString();
	}

}

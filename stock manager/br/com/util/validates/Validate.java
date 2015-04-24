package com.util.validates;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validate {

	public static boolean isValidEmail(String email) {

		Pattern pattern = Pattern
				.compile("[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,4}");
		Matcher match = pattern.matcher(email.toUpperCase());
		return match.matches();

	}

}

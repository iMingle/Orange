/**
 * Copyright (c) 2015, Mingle. All rights reserved.
 */
package org.mingle.orange.java.speciality.annotations;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 *
 * @since 1.8
 * @author Mingle
 */
public class PasswordUtils {

	@UserCase(id = 47, description = "Passwords must contain at least one numeric")
	public boolean validatePassword(String password) {
		return password.matches("\\w*\\d\\w*");
	}
	
	@UserCase(id = 48)
	public String encryptPassword(String password) {
		return new StringBuilder(password).reverse().toString();
	}
	
	@UserCase(id = 49, description = "New passwords can't equal previously used ones")
	public boolean checkForNewPassword(List<String> prePasswords, String password) {
		return !prePasswords.contains(password);
	}
	
	public static void main(String[] args) {
		
	}
}

class UserCaseTracker {
	public static void trackUserCases(List<Integer> userCases, Class<?> cl) {
		for (Method m : cl.getDeclaredMethods()) {
			UserCase uc = m.getAnnotation(UserCase.class);
			if (uc != null) {
				System.out.println("Found User Case: " + uc.id() + " " + uc.description());
				userCases.remove(new Integer(uc.id()));
			}
		}
		for (int i : userCases) {
			System.out.println("Waring: Missing use case-" + i);
		}
	}
	
	public static void main(String[] args) {
		List<Integer> useCases = new ArrayList<Integer>();
		Collections.addAll(useCases, 47, 48, 49, 50);
		trackUserCases(useCases, PasswordUtils.class);
	}
}
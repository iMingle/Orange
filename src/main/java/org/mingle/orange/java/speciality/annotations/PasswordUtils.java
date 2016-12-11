/*
 * Copyright 2012 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.mingle.orange.java.speciality.annotations;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author mingle
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

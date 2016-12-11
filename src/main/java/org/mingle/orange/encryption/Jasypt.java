/*
 *
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
 * imitations under the License.
 *
 */

package org.mingle.orange.encryption;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Arrays;

import org.jasypt.util.binary.BasicBinaryEncryptor;
import org.jasypt.util.digest.Digester;
import org.jasypt.util.numeric.BasicDecimalNumberEncryptor;
import org.jasypt.util.numeric.BasicIntegerNumberEncryptor;
import org.jasypt.util.numeric.StrongIntegerNumberEncryptor;
import org.jasypt.util.password.BasicPasswordEncryptor;
import org.jasypt.util.password.ConfigurablePasswordEncryptor;
import org.jasypt.util.password.PasswordEncryptor;
import org.jasypt.util.password.StrongPasswordEncryptor;
import org.jasypt.util.text.BasicTextEncryptor;
import org.jasypt.util.text.StrongTextEncryptor;

/**
 * 
 * 
 * @author Mingle
 */
public class Jasypt {

    public static void main(String[] args) {
        Digester digester = new Digester();
        digester.setAlgorithm("SHA-1");

        byte[] binary = new byte[] { 1, 2, 3, 4 };
        byte[] result = digester.digest(binary);
        System.out.println(Arrays.toString(result));

        String userPassword = "password";
        PasswordEncryptor passwordEncryptor = new BasicPasswordEncryptor();
        passwordEncryptor = new StrongPasswordEncryptor();
        passwordEncryptor = new ConfigurablePasswordEncryptor();
        String encryptedPassword = passwordEncryptor
                .encryptPassword(userPassword);

        if (passwordEncryptor.checkPassword("password", encryptedPassword)) {
            System.out.println("correct");
        } else {
            System.out.println("incorrect");
        }
        
        BasicTextEncryptor basicTextEncryptor = new BasicTextEncryptor();
        basicTextEncryptor.setPassword("password");
        String myEncryptedText = basicTextEncryptor.encrypt("text");
        String plainText = basicTextEncryptor.decrypt(myEncryptedText);
        
        System.out.println(myEncryptedText.equals(plainText));
        
        StrongTextEncryptor strongTextEncryptor = new StrongTextEncryptor();
        strongTextEncryptor.setPassword("password");
        myEncryptedText = strongTextEncryptor.encrypt("text");
        plainText = strongTextEncryptor.decrypt(myEncryptedText);
        System.out.println(myEncryptedText.equals(plainText));
        
        BasicIntegerNumberEncryptor basicIntegerEncryptor = new BasicIntegerNumberEncryptor();
        basicIntegerEncryptor.setPassword("password");
        BigInteger myEncryptedNumber = basicIntegerEncryptor.encrypt(BigInteger.valueOf(1234));
        BigInteger plainNumber = basicIntegerEncryptor.decrypt(myEncryptedNumber);
        System.out.println(plainNumber.equals(BigInteger.valueOf(1234)));
        
        StrongIntegerNumberEncryptor strongIntegerEncryptor = new StrongIntegerNumberEncryptor();
        strongIntegerEncryptor.setPassword("password");
        myEncryptedNumber = strongIntegerEncryptor.encrypt(BigInteger.valueOf(1234));
        plainNumber = strongIntegerEncryptor.decrypt(myEncryptedNumber);
        System.out.println(plainNumber.equals(BigInteger.valueOf(1234)));
        
        BasicDecimalNumberEncryptor decimalEncryptor = new BasicDecimalNumberEncryptor();
        decimalEncryptor.setPassword("password");
        BigDecimal myEncryptedNumber1 = decimalEncryptor.encrypt(BigDecimal.valueOf(1.23));
        BigDecimal plainNumber1 = decimalEncryptor.decrypt(myEncryptedNumber1);
        System.out.println(plainNumber1.equals(BigDecimal.valueOf(1.23)));
        
        BasicBinaryEncryptor binaryEncryptor = new BasicBinaryEncryptor();
        binaryEncryptor.setPassword("password");
        byte[] myEncryptedBinary = binaryEncryptor.encrypt(binary);
        byte[] plainBinary = binaryEncryptor.decrypt(myEncryptedBinary);
        System.out.println(plainBinary.equals(binary));
    }

}

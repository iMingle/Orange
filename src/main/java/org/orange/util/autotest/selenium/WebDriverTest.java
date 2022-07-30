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

package org.orange.util.autotest.selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.orange.util.WebDriverUtils;

import java.net.URISyntaxException;
import java.time.Duration;

/**
 * @author mingle
 */
public class WebDriverTest {

    public static void main(String[] args) throws URISyntaxException {
        WebDriver driver = WebDriverUtils.driver();

        driver.get("https://www.google.com");

        // Puts a Implicit wait, Will wait for 10 seconds before throwing
        // exception
        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(500));

        // Maximize the browser
        driver.manage().window().maximize();

        // Find the text input element by its name
        WebElement searchBox = driver.findElement(By.name("q"));
        WebElement searchButton = driver.findElement(By.name("btnK"));

        searchBox.sendKeys("Selenium");
        searchButton.click();

        searchBox = driver.findElement(By.name("q"));

        // Check the title of the page
        System.out.println("Value is: " + searchBox.getAttribute("value"));
        System.out.println("Page title is: " + driver.getTitle());

        // Close the browser
        driver.quit();
    }
}

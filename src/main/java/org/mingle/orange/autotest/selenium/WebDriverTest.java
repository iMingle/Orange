/**
 * Copyright (c) 2016, Mingle. All rights reserved.
 */
package org.mingle.orange.autotest.selenium;

import java.net.URISyntaxException;
import java.util.concurrent.TimeUnit;

import org.mingle.orange.util.WebDriverUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 *
 *
 * @since 1.0
 * @author Mingle
 */
public class WebDriverTest {

    public static void main(String[] args) throws URISyntaxException {
        WebDriver driver = WebDriverUtils.driver();

        // Puts a Implicit wait, Will wait for 10 seconds before throwing
        // exception
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        driver.get("https://www.google.com");

        // Maximize the browser
        driver.manage().window().maximize();

        // Find the text input element by its name
        WebElement element = driver.findElement(By.name("q"));

        // Enter something to search for
        element.sendKeys("Cheese!");

        // Now submit the form. WebDriver will find the form for us from the
        // element
        element.submit();

        // Check the title of the page
        System.out.println("Page title is: " + driver.getTitle());

        // Google's search is rendered dynamically with JavaScript.
        // Wait for the page to load, timeout after 10 seconds
        (new WebDriverWait(driver, 10)).until(new ExpectedCondition<Boolean>() {
            @Override
            public Boolean apply(WebDriver d) {
                return d.getTitle().toLowerCase().startsWith("cheese!");
            }
        });

        // Should see: "cheese! - Google Search"
        System.out.println("Page title is: " + driver.getTitle());

        // Close the browser
        driver.quit();
    }
}

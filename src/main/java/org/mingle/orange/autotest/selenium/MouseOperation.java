/**
 * Copyright (c) 2016, Mingle. All rights reserved.
 */
package org.mingle.orange.autotest.selenium;

import java.net.URISyntaxException;

import org.mingle.orange.util.WebDriverUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

/**
 *
 *
 * @since 1.0
 * @author Mingle
 */
public class MouseOperation {

    public static void main(String[] args) throws InterruptedException, URISyntaxException {
        WebDriver driver = WebDriverUtils.driver();

        driver.get("https://github.com");
        driver.manage().window().maximize();
        Thread.sleep(1000);

        Actions action = new Actions(driver);

        WebElement element = driver.findElement(By.xpath("html/body/header/div/div/nav[2]/div/form/label/input"));
        element.sendKeys("spring-framework");
        element.click();

        action.keyDown(Keys.CONTROL).sendKeys("t").perform();
        action.keyDown(Keys.CONTROL).keyDown(Keys.SHIFT).keyDown(Keys.TAB).perform();
        action.contextClick(element).perform();

        Thread.sleep(6000);

        driver.quit();
    }

}

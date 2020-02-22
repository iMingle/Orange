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

import java.net.URISyntaxException;

import org.orange.util.WebDriverUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

/**
 *
 *
 * @author mingle
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

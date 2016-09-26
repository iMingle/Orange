/**
 * Copyright (c) 2016, Mingle. All rights reserved.
 */
package org.mingle.orange.util;

import java.io.File;
import java.net.URISyntaxException;

import org.mingle.orange.autotest.selenium.WebDriverTest;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;

/**
 *
 *
 * @since 1.0
 * @author Mingle
 */
public abstract class WebDriverUtils {

    /**
     * @return
     * @throws URISyntaxException
     */
    public static WebDriver driver() throws URISyntaxException {
        System.setProperty("webdriver.chrome.driver", "C:/Program Files (x86)/Google/Chrome/Application/chrome.exe");

        ChromeDriverService chromeDriverService = new ChromeDriverService.Builder().usingDriverExecutable(
                new File(WebDriverTest.class.getResource("/documents/autotest/chromedriver.exe").toURI()))
                .usingAnyFreePort().build();

        WebDriver driver = new ChromeDriver(chromeDriverService);

        return driver;
    }

}

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
 * @author mingle
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

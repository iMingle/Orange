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

package org.orange.util;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.orange.util.autotest.selenium.WebDriverTest;

import java.io.File;
import java.net.URISyntaxException;

/**
 * @author mingle
 */
public abstract class WebDriverUtils {

    public static WebDriver driver() throws URISyntaxException {
        System.setProperty("webdriver.chrome.driver", "/documents/autotest/chromedriver");

        ChromeDriverService chromeDriverService = new ChromeDriverService.Builder().usingDriverExecutable(
                        new File(WebDriverTest.class.getResource("/documents/autotest/chromedriver").toURI()))
                .usingAnyFreePort().build();

        return new ChromeDriver(chromeDriverService);
    }

}

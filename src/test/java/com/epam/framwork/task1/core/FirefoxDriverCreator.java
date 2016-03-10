package com.epam.framwork.task1.core;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxBinary;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;

import java.io.File;
import java.util.concurrent.TimeUnit;

/**
 * Created by siarhei_chyhir on 2/1/2016.
 */
public class FirefoxDriverCreator extends WebDriverCreator {

    public static final String PATH_TO_FIREFOX = "c:\\Program Files (x86)\\Mozilla Firefox\\firefox.exe";

    @Override
    public WebDriver factoryMethod() {
        FirefoxBinary binary = new FirefoxBinary(new File(PATH_TO_FIREFOX));
        FirefoxProfile profile = new FirefoxProfile();
        WebDriver driver = new FirefoxDriver(binary,profile);
        driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        return driver;
    }
}

package com.epam.framwork.task1.core;

import org.openqa.selenium.WebDriver;

/**
 * Created by siarhei_chyhir on 3/10/2016.
 */
public class WebDriverSingletone {
    private static WebDriver driver;

    private WebDriverSingletone(){

    }
    public static WebDriver getWebDriverInstance() throws Exception {
        if (driver == null){
            driver = DriverFactory.Create("ff");
        }
        return driver;
    }




}

package com.epam.framwork.task1.core;

import org.openqa.selenium.WebDriver;

/**
 * Created by siarhei_chyhir on 3/10/2016.
 */
public class DriverFactory {

    public static WebDriver Create(String browser) throws Exception {
        WebDriverCreator driverCreator;
        if (browser == "ff") {
            driverCreator = new FirefoxDriverCreator();
        }else if (browser == "chrome"){
            driverCreator =new ChromeDriverCreator();
        }else throw new Exception("Unknown browser");

        return new CustomDriverDecorator(driverCreator.factoryMethod());
    }
}

package com.epam.framwork.task1.core;

import org.openqa.selenium.WebDriver;

/**
 * Created by siarhei_chyhir on 2/1/2016.
 */
public abstract class WebDriverCreator {
    public static WebDriver driver;
//    protected WebDriver driver;

    public abstract WebDriver factoryMethod();

}

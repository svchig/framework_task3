package com.epam.framwork.task1.pages;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.IOException;

/**
 * Created by Siarhei_Chyhir on 1/11/2016.
 */
public abstract class Page {

    protected String PAGE_TITLE;

    protected final WebDriver driver;

    public Page(WebDriver driver) {
        this.driver = driver;
    }

    public WebDriver getDriver(){
        return this.driver;
    }

    public Boolean isElementPresent(By locator){
        return driver.findElements(locator).size()>0;
    }

    public String getPageTitle(){
        return driver.getTitle();
    }

    public String getCurrentURL(){return driver.getCurrentUrl();}

    public String takeScreenShot(WebDriver driver){
        String path;
        try{
            File source = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
            path = "./target/screenshots/" + source.getName();
            FileUtils.copyFile(source, new File(path));
        }
        catch(IOException e){
            path = "Failed to capture screenshots: " + e.getMessage();
        }
        return path;
    }

}

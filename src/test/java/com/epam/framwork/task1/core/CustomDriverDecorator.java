package com.epam.framwork.task1.core;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.*;

import java.util.List;
import java.util.Set;

/**
 * Created by siarhei_chyhir on 3/10/2016.
 */

public class CustomDriverDecorator implements WebDriver, TakesScreenshot {
    private WebDriver driver;

    Logger logger = LogManager.getLogger();


    public CustomDriverDecorator(WebDriver driver){
        this.driver = driver;
    }

    public void get(String s) {
        logger.debug("The element " + s + "was got");
        driver.get(s);
    }

    public String getCurrentUrl() {
        logger.debug("Getting Current URL");
        return driver.getCurrentUrl();
    }

    public String getTitle() {
        logger.debug("Getting Title");
        return driver.getTitle();
    }

    public List<WebElement> findElements(By by) {
        logger.debug("Finding Elements by " + by);
        return driver.findElements(by);
    }

    public WebElement findElement(By by) {
        logger.debug("Finding Element by " + by);
        String bg = driver.findElement(by).getCssValue("backgroundColor");
        JavascriptExecutor js = ((JavascriptExecutor) driver);
        js.executeScript("arguments[0].style.backgroundColor = '" + "yellow" + "'", driver.findElement(by));
        sleep(2000); // just for demo
        js.executeScript("arguments[0].style.backgroundColor = '" + bg + "'", driver.findElement(by));
        return driver.findElement(by);
    }

    private void sleep(int millis) {
        try
        {
            Thread.sleep(millis);
        }
        catch (InterruptedException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public String getPageSource() {
        logger.debug("Getting Page Source");
        return driver.getPageSource();
    }

    public void close() {
        logger.debug("Close");
        driver.close();
    }

    public void quit() {
        logger.debug("Quit");
        driver.quit();
    }

    public Set<String> getWindowHandles() {
        logger.debug("Getting Windows Handles");
        return driver.getWindowHandles();
    }

    public String getWindowHandle() {
        logger.debug("Getting Windows Handle");
        return driver.getWindowHandle();
    }

    public TargetLocator switchTo() {
        logger.debug("Switch To");
        return driver.switchTo();
    }

    public Navigation navigate() {
        logger.debug("Navigate");
        return driver.navigate();
    }

    public Options manage() {
        logger.debug("Manage");
        return driver.manage();
    }

    public <X> X getScreenshotAs(OutputType<X> outputType) throws WebDriverException {
        return ((TakesScreenshot)driver).getScreenshotAs(outputType);
    }
}

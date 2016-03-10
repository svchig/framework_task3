package com.epam.framwork.task1.core;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

/**
 * Created by siarhei_chyhir on 2/1/2016.
 */
public class ChromeDriverCreator extends WebDriverCreator{

    public static final String PATH_TO_CHROMEDRIVER = "C:\\Java\\chromedriver.exe";

    @Override
    public WebDriver factoryMethod() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--test-type");
        options.addArguments("start-maximized");
        DesiredCapabilities capabilities = DesiredCapabilities.chrome();
        capabilities.setCapability(ChromeOptions.CAPABILITY, options);

        ChromeDriverService service = new ChromeDriverService.Builder().usingDriverExecutable(
                new File(PATH_TO_CHROMEDRIVER)).build();
        try{
            service.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
        driver = new ChromeDriver(service, capabilities);
        driver.manage().timeouts().pageLoadTimeout(60, TimeUnit.SECONDS);
        driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
        return driver;
    }


}

package com.epam.framwork.task1.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/**
 * Created by Siarhei_Chyhir on 1/4/2016.
 */
public class StartPage extends Page{

//    private final String URL = "http://ebay.com";
    //private WebDriver driver;

    @FindBy(xpath= "//a[contains(@href,'signin.ebay.com')]")
    private WebElement linkSignIn;

    @FindBy(xpath= "//span[@class='gh-eb-Geo-txt']")
    private WebElement allLanguages;

    public StartPage(WebDriver driver){
        super(driver);//this.driver = driver;
        PageFactory.initElements((WebDriver) getDriver(), this);
    }

    public LoginPage singIn(){
        linkSignIn.click();
        return new LoginPage(getDriver());
    }

    public StartPage openPage(String URL){
        getDriver().navigate().to(URL);
        return this;
    }

    /*!!!!!!!!!!!!Need to investigate javascript I cannot get second language*/
//    public StartPage setLanguageSetting(String languageSetting){
//        for (WebElement language: allLanguages){
//            if (language.getText().equalsIgnoreCase(languageSetting)){
//                getDriver().findElement(By.xpath("//span[@class='gh-eb-Geo-txt' and contains(text(), " + languageSetting +")]")).click();
//            }
//        }
//        return this;
//    }
}

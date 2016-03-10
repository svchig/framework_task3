package com.epam.framwork.task1.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/**
 * Created by Siarhei_Chyhir on 1/4/2016.
 */
public class LoginPage extends Page{

    @FindBy(xpath= "//form[@id='SignInForm']//input[@class='fld'][@type='text']")
    private WebElement inputLogin;

    @FindBy(xpath= "//form[@id='SignInForm']//input[@class='fld'][@type='password']")
    private WebElement inputPassword;

    @FindBy(xpath= "//input[@id='sgnBt'][@type='submit']")
    private WebElement buttonSignIn;

    public LoginPage(WebDriver driver){
        super(driver);//this.driver = driver;
        PageFactory.initElements(getDriver(), this);
    }

    public HomePage login(String username, String password){
        inputLogin.sendKeys(username);
        inputPassword.sendKeys(password);
        buttonSignIn.click();
        return new HomePage(getDriver());
    }
}

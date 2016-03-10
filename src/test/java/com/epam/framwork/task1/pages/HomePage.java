package com.epam.framwork.task1.pages;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * Created by _Chyhir on 1/4/2016.
 */
public class HomePage extends Page{


    @FindBy(xpath= "//input[@id='gh-ac'][@type='text']")
    private WebElement searchTextBox;

    @FindBy(xpath= "//input[@id='gh-btn'][@type='submit']")
    private WebElement buttonSearch;

    @FindBy(xpath= "//i[@id='gh-cart-i']")
    private WebElement buttonCartNavigation;

    @FindBy(xpath = "//a[@id='gh-ug']/b")
    private WebElement accountNamelink;

    @FindBy(xpath= "//a[@id='gh-ug'][@class='gh-ua gh-control'][@role='button']")
    private WebElement buttonAccountControl;

    @FindBy(xpath= "//li[@id='gh-uo']/a[contains(@href,'signin.ebay.com')]")
    private WebElement linkAccountSignOut;

    @FindBy(xpath= "//*[@id='gh-eb-Geo'][@class='gh-eb-li gh-dd gh-dd-click']")
    private WebElement languageMenu;

    @FindBy(xpath= "//span[@class='gh-eb-Geo-txt']")
    private List<WebElement> allLanguages;


    public HomePage(WebDriver driver){
        super(driver);//this.driver = driver;
        PageFactory.initElements(getDriver(), this);
    }

    public ProductListPage productSearch(String productName){
        searchTextBox.sendKeys(productName);
        buttonSearch.click();
        return new ProductListPage(getDriver());
    }
    public String getAccountName(){
        if (isElementPresent(By.xpath("//a[@id='gh-ug']/b"))){
            return accountNamelink.getText();
        }else return "";
    }

    public CartPage navigateToCart(){
        buttonCartNavigation.click();
        return new CartPage(getDriver());
    }

    public HomePage selectLanguageMenuAndHold(){
//        Actions builder = new Actions((WebDriver) driver);
//        Action selectAndHold = builder.moveToElement(languageMenu).build();
//        selectAndHold.perform();
        languageMenu.click();
        return new HomePage(getDriver());
    }

    public ProductListPage typeProductNameInSearchBoxAndExecute(String productName){
//        Actions builder = new Actions((WebDriver) driver);
//        Action selectAndHold = builder.sendKeys(searchTextBox, productName).click(buttonSearch).build();
//        selectAndHold.perform();
        searchTextBox.sendKeys(productName);
        buttonSearch.click();
        return new ProductListPage(getDriver());
    }

    public HomePage selectAcountControl(){
        buttonAccountControl.click();
        return new HomePage(getDriver());
    }

    public StartPage signOutFromEBay(){
        linkAccountSignOut.click();
        return new StartPage(getDriver());
    }


    public HomePage setLanguageSetting(String languageSetting){
       for (WebElement language: allLanguages){
            if (language.getText().equalsIgnoreCase(languageSetting)){
                language.click();
            }
       }
       return new HomePage(getDriver());
    }

    public HomePage highlightLanguageMenu() throws IOException {
        String bg = languageMenu.getCssValue("backgroundColor");
        JavascriptExecutor js = ((JavascriptExecutor) driver);
        js.executeScript("arguments[0].style.backgroundColor = '" + "yellow" + "'", languageMenu);
        File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(scrFile, new File("D:\\testScreenShot.jpg"));
        sleep(2000); // just for demo
        js.executeScript("arguments[0].style.backgroundColor = '" + bg + "'", languageMenu);

        return new HomePage(getDriver());
    }

    public void sleep(int millis)
    {
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
}

package com.epam.framwork.task1.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by _Chyhir on 1/11/2016.
 */
public class CartPage extends Page{

    @FindBy(xpath= "//div[@id='ShopCart']/div/div//div[@class='mr10']/div[1]")
    private List<WebElement> productNameLinks;

    @FindBy(xpath= "//i[@id='gh-cart-i']")
    private WebElement buttonCartNavigation;

    @FindBy(xpath= "//div[starts-with(@id, 'sellerBucket_')]//div[a[@class='action actionLink']]/a[1]")
    private List<WebElement> buttonCartRemove;

//    @FindBy(xpath= "//a[@class='action actionLink'][@aria-label='Удалить']")
//    private WebElement buttonCartRemoveRu;

    @FindBy(xpath= "//div[@id='ShopCart']/div/div")
    private List<WebElement> allProductsInCart;

    @FindBy(xpath= "//a[@id='gh-ug'][@class='gh-ua gh-control'][@role='button']")
    private WebElement buttonAccountControl;

    @FindBy(xpath= "//li[@id='gh-uo']/a[contains(@href,'signin.ebay.com')]")
    private WebElement linkAccountSignOut;

    private static final String USER_XPATH ="//div[starts-with(@id, 'sellerBucket_') and .//span/a[text()='%s']]//div[a[@class='action actionLink']]/a[1]";
    private WebElement getRemoveButtonByName(String name) {
        String fullXpath = String.format(USER_XPATH,name);
        return driver.findElement(By.xpath(fullXpath));
    }


    public CartPage(WebDriver driver){
        super(driver);//this.driver = driver;
        PageFactory.initElements(getDriver(), this);
    }

    public int getProductCount(){
        return allProductsInCart.size();
    }

    public CartPage navigateToCart(){
        buttonCartNavigation.click();
        return new CartPage(getDriver());
    }

    public CartPage removeProductFromCartByName(String name){
        getRemoveButtonByName(name).click();
        return this;
//        if (isElementPresent(By.xpath("//a[@class='action actionLink'][@aria-label='Remove']")))
//            buttonCartRemoveEng.click();
//        else if(isElementPresent(By.xpath("//a[@class='action actionLink'][@aria-label='Удалить']")))
//            buttonCartRemoveRu.click();
//        return null;
    }

    public boolean findProductInCart(String productName){
        ArrayList<String> productNames = new ArrayList<String>();
        for (WebElement we : productNameLinks){
            if (we.getText().contains(productName)){
                return true;
            }
        }
        return false;
    }

    public CartPage removeAllProductsFromCart(){
        int i = getProductCount();
        for (int j = 0; j < i; j++) {
            buttonCartRemove.get(0).click();
        }
        return this;
    }

    public CartPage selectAccountControl(){
        buttonAccountControl.click();
        return new CartPage(getDriver());
    }

    public StartPage signOutFromEBay(){
        linkAccountSignOut.click();
        return new StartPage(getDriver());
    }

/*    public Product getProductByIndex(int index) {
        Product product = new Product();
//        product.setName(this.productName.getText());
//        double cost = Double.parseDouble(productCost.getText());
        product.setCost(cost);
        return product;
    }*/
}

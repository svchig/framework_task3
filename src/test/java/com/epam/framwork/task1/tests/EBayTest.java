package com.epam.framwork.task1.tests;

import com.epam.framwork.task1.core.WebDriverSingletone;
import com.epam.framwork.task1.model.Product;
import com.epam.framwork.task1.pages.*;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 * Created by siarhei_chyhir on 2/3/2016.
 */
public class EBayTest {

    private WebDriver driver;

    String productName ="knives";
    String locale = "English";
    String URL = "http://ebay.com";
    public static final String USERNAME = "siarhei_chyhir@epam.com";
    public static final String PASSWORD = "Sergh13";

    @BeforeMethod(description = "WebDriver init")
    public void prepare() throws Exception {
//        WebDriverCreator creator = new ChromeDriverCreator();
        driver = WebDriverSingletone.getWebDriverInstance();
//        WebDriverCreator creator = DriverFactory.Create("ff");//new FirefoxDriverCreator();
//        driver = creator.factoryMethod();
    }

    @Test(description = "eBay smoke test")
    public void eBayTest() {

        StartPage startPage = new StartPage(driver);
        startPage.openPage(URL);
        LoginPage loginPage = startPage.singIn();
        HomePage homePage = loginPage.login(USERNAME, PASSWORD);
        Assert.assertTrue(homePage.getPageTitle().contains("Electronics, Cars, Fashion, Collectibles, Coupons and More | eBay")
                , "eBay page is not opened");
        Assert.assertTrue(homePage.getCurrentURL().contains("ebay.com"), "eBay home page is not opened");
        Assert.assertTrue(homePage.getAccountName().contains("Siarhei"), "User did not login on eBay");
        homePage.selectLanguageMenuAndHold();
        homePage.setLanguageSetting(locale);

        ProductListPage productListPage = homePage.typeProductNameInSearchBoxAndExecute(productName);
        Assert.assertTrue(productListPage.getPageTitle().contains("knives")
                , "Search Result List with knives does not present on eBay");
        Assert.assertTrue(productListPage.searchProductsCount() > 0
                , "No products in Search Result List on eBay");

        productListPage = productListPage.selectFilterBuyItNow();
        Assert.assertTrue(productListPage.getPageTitle().contains("knives")
                , "Search Result List with knives does not present on eBay");
        Assert.assertTrue(productListPage.searchProductsCount() > 0
                , "No products in Search Result List on eBay");

        ProductPage productPage = productListPage.selectProductFromList();
//        Product expectedProduct = productPage.getProductDetails();
//        Assert.assertTrue(productPage.getPageTitle().toUpperCase().contains("TAC FORCE ")
//                , "The product page is not opened");

        productPage.takeScreenShot(driver);
        Product product = productPage.getProductDetails();
        System.out.println(product.getName());
        System.out.println(product.getCost());

        CartPage cartPage = productPage.addProductToCart();
        Assert.assertTrue(cartPage.getCurrentURL().contains("cart.payments.ebay.com")
                , "The shopping cart page is not opened");


        Assert.assertTrue(cartPage.findProductInCart(product.getName())
                , "The " + product.getName() + "name exist in the Cart");

        cartPage.removeProductFromCartByName(product.getName());
//        cartPage.removeProductFromCart();
//        cartPage.selectAccountControl();
//
//        startPage = cartPage.signOutFromEBay();
//        System.out.println(startPage.getPageTitle());
//        Assert.assertTrue(startPage.getCurrentURL().contains("signin.ebay"), "Sighin eBay page is not opened");


    }

    @AfterMethod(description = "WebDriver clean up")
    public void cleanUp() {
        CartPage cartPage = new CartPage(driver);
        cartPage.removeAllProductsFromCart();
        cartPage.selectAccountControl();

        StartPage startPage = cartPage.signOutFromEBay();
        System.out.println(startPage.getPageTitle());
        Assert.assertTrue(startPage.getCurrentURL().contains("signin.ebay"), "Sighin eBay page is not opened");

        driver.quit();
        driver = null;
    }
}
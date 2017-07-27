package com.example;

import org.junit.Test;

import static com.codeborne.selenide.Selenide.open;
import static com.example.pages.Hotline.*;


public class HotlineTest {


    @Test
    public void testSearchAndChooseItem() {
//        System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
//        Configuration.browser = "chrome";
        open("http://hotline.ua");
        searchItem("Iphone");
        chooseFromResults("Apple iPhone 7");
        switchToAllOffersTab();
        chooseShopFromOffersList(6, 10);
        assertShopPage("tehnoezh.ua");
    }


}

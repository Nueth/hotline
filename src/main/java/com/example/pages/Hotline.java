package com.example.pages;

import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.ex.ElementNotFound;

import java.util.ArrayList;
import java.util.List;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.WebDriverRunner.url;
import static org.junit.Assert.assertTrue;

public class Hotline {

    public static void searchItem(String item) {
        $("#searchbox").shouldBe(visible).setValue(item).pressEnter();
    }

    public static void chooseFromResults(String item) {
        $$(".gd-box").find(text(item)).shouldBe(visible).$(".m_r-10>a").click();
    }

    public static void switchToAllOffersTab() {
        $(".cell3.cell5-640>a").shouldBe(visible).click();
    }

    public static void chooseShopFromOffersList(int warrantyMoreThan, int reviewsMoreThan) {
        List<Integer> filteredShops = new ArrayList<>();
        List<Integer> filteredShops2 = new ArrayList<>();
        int k = 0;
        int j = $$(".box-line").shouldHave(CollectionCondition.sizeGreaterThan(0)).size();
        for (int i = 0; j > 0; i++, j--) {
            try {
                if (Integer.parseInt($$(".box-line").get(i).$(".sum.g_statistic").getText().replace("Отзывов: ", "")) > reviewsMoreThan) {
                    filteredShops.add(k, i);
                    k++;
                }
            } catch (ElementNotFound e) {
            }
        }
        int u = 0;
        int h = filteredShops.size();
        for (int g = 0; h > 0; h--, g++) {
            try {
                if (Integer.parseInt($$(".box-line").get(filteredShops.get(g)).$(".delivery-th").getText().substring(0, 2).replace(" ", "")) >= warrantyMoreThan) {
                    filteredShops2.add(u, filteredShops.get(g));
                    u++;
                }
            } catch (NumberFormatException e) {
            }
        }
        int t = filteredShops2.size();
        int y = 0;

        double minPrice = getItemPrice(filteredShops2.get(0));
        for (u = 0; t > 0; t--, u++) {
            if (getItemPrice(filteredShops2.get(u)) < minPrice) {
                minPrice = getItemPrice(filteredShops2.get(u));
                y = u;
            }
        }
        $$(".box-line").get(filteredShops2.get(y)).$("#gotoshop-img").click();
        switchTo().window(1);

    }

    public static double getItemPrice(int index) {
        return Double.parseDouble($$(".box-line").get(index).$("#gotoshop-price").getText().replace(" ", "").replace(",", "."));
    }

    public static void assertShopPage(String expectedUrl) {
        assertTrue(url().contains(expectedUrl));
    }
}

package com.test.qabattle;

import com.codeborne.selenide.Configuration;
import org.testng.annotations.Test;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static com.test.qabattle.AllureHelpers.takeScreenshot;

public class FirstTest extends BaseTestClass {

    @Test
    public void test() {
        System.out.println(Configuration.baseUrl);
        open("/");

        $("#registrationContainer").waitUntil(appear, 1000);
        $(".card-header").shouldBe(visible).shouldHave(exactText("Welcome to Propeller Championship!"));

        takeScreenshot();
    }

    @Test
    public void test2() {
        System.out.println(Configuration.baseUrl);
        open("/");

        $("#registrationContainer").waitUntil(appear, 1000);
        $(".card-header").shouldBe(visible).shouldHave(exactText("Welcome to Propeller Championship!"));

        takeScreenshot();
    }

}

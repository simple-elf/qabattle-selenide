package com.test.qabattle;

import com.codeborne.selenide.Condition;
import org.testng.annotations.Test;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static com.test.qabattle.AllureHelpers.takeScreenshot;

public class FirstTest extends BaseTestClass {

    @Test
    public void test() {
        open("http://127.0.0.1:8080/");
        //open("http://212.237.55.99:8081/");

        $("#registrationContainer").should(appear);
        $(".card-header").shouldBe(visible).shouldHave(exactText("Welcome to Propeller Championship!"));

        takeScreenshot();
    }

}

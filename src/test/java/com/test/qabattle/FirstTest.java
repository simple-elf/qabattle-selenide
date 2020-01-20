package com.test.qabattle;

import org.testng.annotations.Test;

import static com.codeborne.selenide.Selenide.open;
import static com.test.qabattle.AllureHelpers.takeScreenshot;

public class FirstTest extends BaseTestClass {

    @Test
    public void test() {
        open("http://127.0.0.1:8080/");
        //open("http://212.237.55.99:8081/");
        takeScreenshot();
        System.out.println("tests!!!");
    }

}

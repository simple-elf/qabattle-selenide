package com.test.qabattle.pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.Cookie;

import java.util.Calendar;
import java.util.Date;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;
import static io.qameta.allure.Allure.step;

public class LoginForm {

    public static SelenideElement registrationContainer = $("#registrationContainer");

    public static SelenideElement cardHeader = $("div.card-header h4");
    public static SelenideElement cardBody = $("div.card-body");
    public static SelenideElement bodyHeader = cardBody.$("h6");

    public static SelenideElement loginInput = $("input#loginInput");
    public static SelenideElement loginHelp = $("#loginHelp");
    public static SelenideElement passInput = $("input#passwordInput");
    public static SelenideElement passHelp = $("#passwordHelp");

    public static SelenideElement loginButton = $$("button").findBy(exactText("Hover me faster!"));
    public static SelenideElement loginButton2 = $$("button").findBy(exactText("Wait for some time..."));
    public static SelenideElement signInButton = $("img[src='sign.png']");

    @Step("Открыть страницу логина")
    public static void openLoginForm() {
        open("/index.html");
    }

    @Step("Подождать появление кнопки 'Sign In' и нажать на неё")
    public static void loginClick() {
        loginButton.shouldNotHave(attribute("disabled")).hover();
        loginButton2.should(appear).shouldNotHave(attribute("disabled"));
        signInButton.waitUntil(appears, 10000).click();
    }

    @Step("Подтверждения алертов")
    public static void acceptAlerts(boolean first, boolean second) {
        logAlert("Are you sure you want to login?", first);
        if (!first)
            return;
        //sleep(1000);
        step("Ожидание второго алерта");
        logAlert("Really sure?", second);
    }

    @Step("Подтверждения алерта")
    public static void logAlert(String expectedText, boolean accept) {
        if (accept)
            confirm(expectedText);
        else
            dismiss(expectedText);
    }

    @Step("Ввод логина и пароля")
    public static void setLoginPass(String login, String pass) {
        setLogin(login);
        setPass(pass);
    }

    @Step("Ввод логина")
    public static void setLogin(String login) {
        loginInput.parent().click();
        loginInput.shouldNotHave(attribute("disabled")).setValue(login);
    }

    @Step("Ввод пароля")
    public static void setPass(String pass) {
        passInput.parent().click();
        passInput.shouldNotHave(attribute("disabled")).setValue(pass);
    }

    @Step("Авторизация через cookies")
    public static void loginByCookies() {
        Cookie s = new Cookie("secret", "IAmSuperSeleniumMaster", null, "/", tomorrow());
        getWebDriver().manage().addCookie(s);
        refresh();
    }

    public static Date tomorrow() {
        Date date = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_YEAR, 1);
        date = calendar.getTime();
        return date;
    }


}

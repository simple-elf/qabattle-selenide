package com.test.qabattle.tests;

import com.test.qabattle.lib.BaseTestClass;
import com.test.qabattle.pages.ArticlesPage;
import io.qameta.allure.Feature;
import org.testng.annotations.Test;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;
import static com.test.qabattle.pages.LoginForm.*;
import static io.qameta.allure.Allure.step;

@Feature("Форма авторизации")
public class LoginTest extends BaseTestClass {

    @Test(description = "Валидация формы авторизации")
    public void loginFormTest() {
        openLoginForm();
        step(title()); // WelcomE to ...
        registrationContainer.shouldBe(visible);
        cardHeader.shouldBe(visible).shouldHave(exactText("Welcome to Propeller Championship!"));
        bodyHeader.shouldBe(visible).shouldHave(exactText("Fill login form to sign in"));
        loginInput.shouldBe(visible).shouldHave(attribute("placeholder", "Your Login")).shouldHave(attribute("disabled"));
        loginHelp.shouldBe(visible).shouldHave(exactText("We'll never share your email with anyone else."));
        passInput.shouldBe(visible).shouldHave(attribute("placeholder", "Your Password")).shouldHave(attribute("disabled"));
        passHelp.shouldBe(visible).shouldHave(exactText("We'll never save your password anywhere."));
        loginButton.shouldBe(visible).shouldHave(attribute("disabled"));
    }

    @Test(description = "Успешная авторизация")
    public void successfulLoginTest() {
        openLoginForm();
        setLoginPass("test", "test");
        loginClick();
        acceptAlerts(true, true);
        //registrationContainer.should(disappear);
        ArticlesPage.mainContainer.should(appear);
    }

    @Test(description = "Авторизация с некорректными данными")
    public void incorrectLoginTest() {
        openLoginForm();
        setLoginPass("wrong", "wrong");
        loginClick();
        acceptAlerts(true, true);
        registrationContainer.should(disappear);
        ArticlesPage.mainContainer.shouldNot(appear);
    }

    @Test(description = "Авторизация с отменой первого алерта")
    public void dismissFirstAlertLoginTest() {
        openLoginForm();
        setLoginPass("test", "test");
        loginClick();
        acceptAlerts(false, false);
        registrationContainer.shouldNot(disappear);
        ArticlesPage.mainContainer.shouldNot(appear);
        signInButton.shouldBe(visible);
    }

    @Test(description = "Авторизация с отменой второго алерта")
    public void dismissSecondAlertLoginTest() {
        openLoginForm();
        setLoginPass("test", "test");
        loginClick();
        acceptAlerts(true, false);
        registrationContainer.should(disappear);
        ArticlesPage.mainContainer.shouldNot(appear);
        $("body[background='fail.png']").should(appear);
    }

    @Test(description = "Авторизация через Cookie")
    public void loginByCookiesTest() {
        openLoginForm();
        loginByCookies();
        ArticlesPage.mainContainer.should(appear);
        openLoginForm();
        ArticlesPage.mainContainer.should(appear);
        clearBrowserCookies();
        refresh();
        ArticlesPage.mainContainer.should(disappear);
        registrationContainer.should(appear);
    }

    @Test(description = "Ввод и очистка полей логина и пароля")
    public void clearLoginAndPassTest() {
        openLoginForm();
        setLoginPass("1", "1");
        loginClick();
        acceptAlerts(false, false);
        setLoginPass("", "");
        signInButton.should(disappear);
    }

}

package com.test.qabattle.tests;

import com.codeborne.selenide.SelenideElement;
import com.test.qabattle.lib.BaseTestClass;
import io.qameta.allure.Feature;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static com.codeborne.selenide.CollectionCondition.exactTexts;
import static com.codeborne.selenide.Condition.*;
import static com.test.qabattle.pages.DataCard.cardTitle;
import static com.test.qabattle.pages.DataCard.dataCard;
import static com.test.qabattle.pages.LoginForm.loginByCookies;
import static com.test.qabattle.pages.LoginForm.openLoginForm;
import static com.test.qabattle.pages.ArticlesPage.*;

@Feature("Главная страница")
public class ArticlesTest extends BaseTestClass {

    @BeforeMethod
    public void beforeTest() {
        openLoginForm();
        loginByCookies();
    }

    @Test(description = "Валидация главной страницы")
    public void mainPageTest() {
        mainContainer.shouldBe(visible);
        profileLink.shouldBe(visible);
        articlesHeader.shouldBe(visible).shouldHave(exactText("Articles to read"));
        mainButtons.shouldHaveSize(3);
        mainButtons.get(0).shouldHave(exactText("Advertisers"));
        mainButtons.get(1).shouldHave(exactText("Publishers"));
        mainButtons.get(2).shouldHave(exactText("Top level clients"));
    }

    @Test(description = "Валидация вложенных статей")
    public void mainPageArticlesTest() {
        advertisers.click();
        treeButtons(advertisers).shouldHaveSize(2)
                .shouldHave(exactTexts("Test Advertiser", "Adidas"));

        publishers.click();
        treeButtons(publishers).shouldHaveSize(2)
                .shouldHave(exactTexts("Youtube", "Instagram"));

        topLevelClients.click();
        treeButtons(topLevelClients).shouldHaveSize(10)
                .shouldHave(exactTexts("Jon Snow", "Artur Fleck", "Tim Cook", "Bugs Bunny", "Sasha Grey", "You",
                        "Leonel Messi", "Tony Stark", "Elon Musk", "Darth Vader"));

        // cookies?
    }

    @Test(description = "Проверка скачивания описания статьи")
    public void downloadArticleTest() {
        advertisers.click();
        SelenideElement subArticle = treeButtons(advertisers).get(0);
        subArticle.click();

        dataCard.should(appear);
        cardTitle.shouldHave(exactText(subArticle.getText()));
    }

}

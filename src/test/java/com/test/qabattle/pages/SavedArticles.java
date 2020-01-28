package com.test.qabattle.pages;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class SavedArticles {

    public static SelenideElement savedHeader = $$(".card-header").findBy(exactText("Saved articles"));
    public static SelenideElement savedCard = savedHeader.parent();
    public static SelenideElement savedBody = savedCard.$("card-body");

    public static ElementsCollection savedButtons() {
        return savedBody.$$("button.tree-button");
    }

}

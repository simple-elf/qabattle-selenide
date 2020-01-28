package com.test.qabattle.pages;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class ArticlesPage {

    public static SelenideElement mainContainer = $("#mainContainer");
    public static SelenideElement profileLink = $("img#avatar"); // #avatarContainer
    public static SelenideElement articlesHeader = $(".card-header");
    public static SelenideElement articlesBody = $(".card-body");

    public static ElementsCollection mainButtons = articlesBody.$$("button.tree-main-button");


    public static ElementsCollection treeButtons(SelenideElement mainButton){
        return mainButton.parent().$$("button.tree-button");
    }

}

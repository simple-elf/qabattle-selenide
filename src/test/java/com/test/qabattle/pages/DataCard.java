package com.test.qabattle.pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Selenide.$;

public class DataCard {

    public static SelenideElement dataCard = $("#dataCard .card .card-body");
    public static SelenideElement cardTitle = dataCard.$("h5.card-title");
    public static SelenideElement cardText = dataCard.$(".card-text");
    public static SelenideElement textarea = dataCard.$("textarea.form-control");
    public static SelenideElement downloadButton = dataCard.$$("button").findBy(exactText("Download info"));
    public static SelenideElement cardText2 = dataCard.$("div .card-text");
    public static SelenideElement heroImage = dataCard.$("#heroImage");
    public static SelenideElement uiSlider = dataCard.$(".ui-slider");
    public static SelenideElement saveButton = dataCard.$$("button").findBy(exactText("Move to saved"));
    public static SelenideElement removeButton = dataCard.$$("button").findBy(exactText("Removed from saved"));





}

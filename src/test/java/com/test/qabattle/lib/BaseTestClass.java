package com.test.qabattle.lib;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import io.qameta.allure.selenide.LogType;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;

import java.util.logging.Level;

import static com.codeborne.selenide.Selenide.closeWebDriver;
import static com.test.qabattle.lib.AllureHelpers.takeScreenshot;

public class BaseTestClass {

    @BeforeClass
    public static void beforeClass() {
        System.out.println("beforeClass");
        if (isUnix()) {
            Configuration.browser = MyRemoteWebDriverClass.class.getName();
            Configuration.browserSize = "1920x1080";
            Configuration.startMaximized = true;
            System.out.println("RemoteWebDriver");
        } else {
            Configuration.browser = MyChromeBrowserClass.class.getName();
            Configuration.startMaximized = true;
            System.out.println("LocalWebDriver");
        }

        Configuration.reportsFolder = "target/reports";
        Configuration.screenshots = false;
        //Configuration.fastSetValue = true;

        Configuration.baseUrl = System.getProperty("base.url");

        SelenideLogger.addListener("AllureSelenide", new AllureSelenide().enableLogs(LogType.BROWSER, Level.ALL));
    }

    @AfterMethod
    public void afterMethod() {

        takeScreenshot();
        closeWebDriver();
    }

    public static boolean isUnix() {
        String os = System.getProperty("os.name").toLowerCase();
        //System.out.println("isUnix: " + os);
        return (os.contains("nix") || os.contains("nux"));// || isWindows(); // linux or unix
    }

}

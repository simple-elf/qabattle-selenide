package com.test.qabattle.lib;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
//import io.qameta.allure.selenide.LogType;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;

import java.util.logging.Level;

//import static com.codeborne.selenide.Selenide.closeWebDriver;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;
import static com.test.qabattle.lib.AllureHelpers.takeScreenshot;
import static com.test.qabattle.lib.MyChromeBrowserClass.getChromeOptions;
import static com.test.qabattle.lib.SelenoidVideo.attachAllureVideo;

//@Listeners(MyBrowserPerTest.class)
public class BaseTestClass {

    @BeforeClass
    public static void beforeClass() {
        System.out.println("beforeClass");
        if (isUnix()) {
            //TODO
            //Configuration.browser = MyRemoteWebDriverClass.class.getName();

            Configuration.remote = "http://127.0.0.1:4444/wd/hub";
            Configuration.browserCapabilities = new DesiredCapabilities();
            Configuration.browserCapabilities.setCapability("browserName", "firefox");
            //Configuration.browser = "firefox";
            //TODO
            Configuration.browserSize = "1920x1080";
            Configuration.startMaximized = true;
            System.out.println("RemoteWebDriver");
        } else {
            Configuration.browser = MyChromeBrowserClass.class.getName();
            //Configuration.browser = MyFirefoxWebDriver.class.getName();
            Configuration.startMaximized = true;
            System.out.println("LocalWebDriver");
        }

        Configuration.reportsFolder = "target/reports";
        Configuration.screenshots = false;
        //Configuration.fastSetValue = true;

        Configuration.baseUrl = System.getProperty("base.url");

        SelenideLogger.addListener("AllureSelenide", new AllureSelenide().includeSelenideSteps(false)
                //.enableLogs(LogType.BROWSER, Level.ALL)
                //.enableLogs(LogType.PERFORMANCE, Level.ALL)
        );
    }

    @AfterMethod
    public void afterMethod() {
        String sessionId = getSessionId();
        takeScreenshot();
        //closeWebDriver();

        if ("true".equals(System.getProperty("video.enabled"))) {
            //sleep(5000);
            attachAllureVideo(sessionId);
        }
    }

    public static boolean isUnix() {
        String os = System.getProperty("os.name").toLowerCase();
        //System.out.println("isUnix: " + os);
        return (os.contains("nix") || os.contains("nux"));// || isWindows(); // linux or unix
    }

    public static String getSessionId() {
        return ((RemoteWebDriver) getWebDriver()).getSessionId().toString();
    }

}

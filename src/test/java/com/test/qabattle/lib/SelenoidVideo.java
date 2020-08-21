package com.test.qabattle.lib;

import com.google.common.io.Files;
import io.qameta.allure.Allure;
import io.qameta.allure.Attachment;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import static com.codeborne.selenide.WebDriverRunner.getWebDriver;

public class SelenoidVideo {

    public static String selenoidUrl = "http://127.0.0.1:4444";

    //@Step
    public static void attachAllureVideo(String sessionId) {
        try {
            URL videoUrl = new URL(selenoidUrl + "/video/" + sessionId + ".mp4");
            InputStream is = getSelenoidVideo(videoUrl);
            Allure.addAttachment("Video", "video/mp4", is, "mp4");
            deleteSelenoidVideo(videoUrl);
        } catch (Exception e) {
            System.out.println("attachAllureVideo");
            e.printStackTrace();
        }
    }

    public static InputStream getSelenoidVideo(URL url) {
        int lastSize = 0;
        int exit = 2;
        for (int i = 0; i < 20; i++) {
            try {
                int size = Integer.parseInt(url.openConnection().getHeaderField("Content-Length"));
                System.out.println("Content-Length: " + size);
                System.out.println("i: " + i);
                if (size > lastSize) {
                    lastSize = size;
                    Thread.sleep(1500);
                } else if (size == lastSize) {
                    System.out.println("Content-Length: " + size);
                    System.out.println("exit: " + exit);
                    exit--;
                    Thread.sleep(1000);
                }
                if (exit < 0) {
                    System.out.println("video ok!");
                    return url.openStream();
                }
            } catch (Exception e) {
                System.out.println("getSelenoidVideo: " + e.getMessage());
                //e.printStackTrace();
            }
        }

        return null;
    }

    public static void deleteSelenoidVideo(URL url) {
        try {
            HttpURLConnection deleteConn = (HttpURLConnection) url.openConnection();
            deleteConn.setDoOutput(true);
            deleteConn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            deleteConn.setRequestMethod("DELETE");
            deleteConn.connect();
            System.out.println("deleteSelenoidVideo");
            System.out.println(deleteConn.getResponseCode());
            System.out.println(deleteConn.getResponseMessage());
            deleteConn.disconnect();
        } catch (IOException e) {
            System.out.println("deleteSelenoidVideo");
            e.printStackTrace();
        }
    }

}

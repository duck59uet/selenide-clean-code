package com.example.selenidetemplate;

import com.codeborne.selenide.WebDriverProvider;
import com.example.selenidetemplate.driver.DriverFactory;
import com.example.selenidetemplate.listeners.ScreenshotListener;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Listeners;

import javax.annotation.Nonnull;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Listeners(ScreenshotListener.class)
public class DriverBase {

    protected static final Logger LOG = (Logger) LogManager.getLogger(DriverBase.class);
    private static final List<DriverFactory> webDriverThreadPool = Collections.synchronizedList(new ArrayList<>());
    private static ThreadLocal<DriverFactory> driverFactoryThread;

    @BeforeSuite(alwaysRun = true)
    public static void instantiateDriverObject() {
        driverFactoryThread = ThreadLocal.withInitial(() -> {
            DriverFactory driverFactory = new DriverFactory();
            webDriverThreadPool.add(driverFactory);
            return driverFactory;
        });
    }

    public static RemoteWebDriver getDriver() throws Exception {
        return driverFactoryThread.get().getDriver();
    }

    public static String getInfo() throws Exception{
        RemoteWebDriver currentDriver = driverFactoryThread.get().getDriver();
        Capabilities cap = currentDriver.getCapabilities();
        String browserName = cap.getBrowserName();
        String platform = cap.getPlatformName().toString();
        String version = cap.getBrowserVersion();

        return String.format("browser: %s v: %s platform: %s", browserName, version, platform);
    }

    @AfterMethod(alwaysRun = true)
    public static void clearCookies() {
        try {
            driverFactoryThread.get().getStoredDriver().manage().deleteAllCookies();
        } catch (Exception ignored) {
            LOG.warn("Unable to clear cookies, driver object is not viable...");
        }
    }

    @AfterSuite(alwaysRun = true)
    public static void closeDriverObjects() {
        for (DriverFactory driverFactory : webDriverThreadPool) {
            driverFactory.quitDriver();
        }
    }
}

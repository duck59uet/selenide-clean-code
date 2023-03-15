package com.example.selenidetemplate.tests;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.WebDriverRunner;
import com.example.selenidetemplate.DriverBase;
import com.example.selenidetemplate.config.ConfigurationManager;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.*;

import java.util.List;

import static com.codeborne.selenide.Selenide.sleep;

import static com.codeborne.selenide.Selenide.*;

public class InitTest extends DriverBase {

    private static String mnemonic = ConfigurationManager.getMnemonic();

    private static String password = ConfigurationManager.getProp("password");

    WebDriver driver;


    @BeforeMethod
    public void setupDriver() throws Exception {
        driver = getDriver();
        WebDriverRunner.setWebDriver(driver);
        setUpWallet();
    }


    private void setUpWallet(){

        open("chrome-extension://dmkamcknogkgcdfhhbddcghachkejeap/popup.html#/register");

        $(By.xpath("//button[text()='Import existing account']")).click();
        sleep(100);
        List<SelenideElement> passInputs = $$(By.xpath("//div[contains(@class, 'mnemonic-word-form-group')]//div[contains(@class, 'input-group')]//input[@type='password']"));

        String[] mnemonicArr = mnemonic.split(" ");
        for (int i = 0; i < passInputs.size(); i++) {
            passInputs.get(i).sendKeys(mnemonicArr[i]);
        }

        $(By.name("name")).sendKeys("TestAccount");

        $(By.xpath("//div[contains(@class, 'form-inner-container')]//input[@name='password']")).sendKeys(password);
        $(By.name("confirmPassword")).sendKeys(password);

        sleep(100);
        $(By.xpath("//button[@type='submit']")).click();

    }
}

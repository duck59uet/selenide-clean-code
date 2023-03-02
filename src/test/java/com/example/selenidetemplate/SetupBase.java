package com.example.selenidetemplate;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.WebDriverRunner;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import java.util.List;

import static com.codeborne.selenide.Selenide.sleep;

import static com.codeborne.selenide.Selenide.*;

@Listeners()
public class SetupBase extends DriverBase {

    private static String mnemonic = "bless harvest huge empower join laundry toddler cheap glass crop deny anxiety";

    @Test
    public void googleCheeseExample() throws Exception {
        WebDriver driver = getDriver();

        WebDriverRunner.setWebDriver(driver);

        open("chrome-extension://dmkamcknogkgcdfhhbddcghachkejeap/popup.html#/register");

        $(By.xpath("//button[text()='Import existing account']")).click();
        sleep(100);
        List<SelenideElement> passInputs = $$(By.xpath("//div[contains(@class, 'mnemonic-word-form-group')]//div[contains(@class, 'input-group')]//input[@type='password']"));

        String[] mnemonicArr = mnemonic.split(" ");
        for (int i = 0; i < passInputs.size(); i++) {
            passInputs.get(i).sendKeys(mnemonicArr[i]);
        }

        $(By.name("name")).sendKeys("TestAccount");

        $(By.xpath("//div[contains(@class, 'form-inner-container')]//input[@name='password']")).sendKeys("Test@123!");
        $(By.name("confirmPassword")).sendKeys("Test@123!");

        sleep(100);
        $(By.xpath("//button[@type='submit']")).click();
    }
}

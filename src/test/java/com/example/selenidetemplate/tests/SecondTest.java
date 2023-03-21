package com.example.selenidetemplate.tests;

import com.codeborne.selenide.Condition;
import com.example.selenidetemplate.config.ConfigurationManager;
import org.openqa.selenium.By;
import org.testng.annotations.Test;

import static com.codeborne.selenide.Selenide.*;

public class SecondTest extends BaseTest {
    private static String mnemonic1 = ConfigurationManager.getProp("mnemonic1");
    private static String mnemonic2 = ConfigurationManager.getProp("mnemonic2");

    @Test(description = "Login wallet then connect wallet on Seekhype")
    public void TestConnect(){
        open("chrome-extension://dmkamcknogkgcdfhhbddcghachkejeap/popup.html#/register");
        setUpWallet(mnemonic1);
        open("https://hub.dev.twilight.space/");
        $("app-wallet").shouldHave(Condition.visible).click();
        $(By.xpath("//div[@class=\"wallet-item ng-star-inserted\"]//div[contains(text(),'Keplr')]")).shouldBe(Condition.visible).click();
        switchTo().window("Keplr");
        $(By.xpath("//button[contains(text(), 'Approve')]")).shouldHave(Condition.visible).click();
        switchTo().window("Keplr");
        $(By.xpath("//button[contains(text(), 'Approve')]")).shouldHave(Condition.visible).click();
    }
}

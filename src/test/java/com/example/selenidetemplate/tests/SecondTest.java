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
        open("chrome-extension://dmkamcknogkgcdfhhbddcghachkejeap/popup.html#/register");
        setUpWallet(mnemonic2);
        open("https://hub.dev.twilight.space/");
        sleep(17000);
        $("app-wallet").shouldHave(Condition.visible).click();
        $(By.xpath("//div[@class=\"wallet-item ng-star-inserted\"]//div[contains(text(),'Keplr')]")).shouldBe(Condition.visible).click();
        sleep(10000);
        switchTo().window("Keplr");
        $(By.xpath("//button[contains(text(), 'Approve')]")).shouldHave(Condition.visible).click();
        sleep(10000);
        switchTo().window("Keplr");
        $(By.xpath("//button[contains(text(), 'Approve')]")).shouldHave(Condition.visible).click();
        sleep(5000);

        open("https://hub.dev.twilight.space/");
        sleep(17000);
        $("app-wallet").shouldHave(Condition.visible).click();
        $(By.xpath("//div[@class=\"wallet-item ng-star-inserted\"]//div[contains(text(),'Keplr')]")).shouldBe(Condition.visible).click();
        sleep(2000);
        switchTo().window("Keplr");
        $(By.xpath("//button[contains(text(), 'Approve')]")).shouldHave(Condition.visible).click();
        sleep(5000);
        switchTo().window("Keplr");
        $(By.xpath("//button[contains(text(), 'Approve')]")).shouldHave(Condition.visible).click();
        $(By.xpath("//div[@id='walletBox']")).shouldHave(Condition.visible).click();
        sleep(5000);
    }
}

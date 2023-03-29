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
    public void MintNFT(){
        //Connect wallet
        restoreUpC98Wallet(mnemonic1);
        open("https://hub.serenity.twilight.space");
        $("app-wallet").shouldBe(Condition.visible);
        $("app-wallet").click();
        $(By.xpath("//div[@class=\"wallet-item ng-star-inserted\"]//div[contains(text(),'Coin98')]")).shouldBe(Condition.visible);
        $(By.xpath("//div[@class=\"wallet-item ng-star-inserted\"]//div[contains(text(),'Coin98')]")).click();
        switchTo().window("Coin98 Extension");
        $(By.xpath("//button[contains(text(), 'Connect')]")).shouldBe(Condition.visible);
        $(By.xpath("//button[contains(text(), 'Connect')]")).click();

        sleep(1000);
        switchTo().window("Coin98 Extension");
        $(By.xpath("//button[contains(text(), 'Confirm')]")).shouldBe(Condition.visible);
        $(By.xpath("//button[contains(text(), 'Confirm')]")).click();

        sleep(1000);
        switchTo().window("Coin98 Extension");
        $(By.xpath("//button[contains(text(), 'Confirm')]")).shouldBe(Condition.visible);
        $(By.xpath("//button[contains(text(), 'Confirm')]")).click();

        //Mint NFT
        sleep(5000);
        //You should switch to main window
        switchTo().window("SeekHYPE");
        open("https://hub.serenity.twilight.space/launchpad");
        sleep(10000);
    }
}

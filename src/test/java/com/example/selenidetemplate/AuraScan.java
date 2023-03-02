package com.example.selenidetemplate;

import com.codeborne.selenide.Condition;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;

import static com.codeborne.selenide.Selenide.*;

public class AuraScan extends DriverBase{
    @Test
    public void search() throws Exception {
        WebDriver driver = getDriver();

        driver.get("https://serenity.aurascan.io/");

        $("app-wallet-connect").click();

        $(By.xpath("//li[@class='wallet-item']//span[contains(text(),'Keplr')]")).shouldBe(Condition.visible).click();

//        sleep(5000);
        switchTo().window("Keplr");
        $(By.xpath("//button[contains(text(), 'Approve')]")).wait(2000);
        $(By.xpath("//button[contains(text(), 'Approve')]")).click();
        sleep(1000);
        switchTo().window(0);
        screenshot("result");
    }
}

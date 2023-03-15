package com.example.selenidetemplate.tests;

import com.codeborne.selenide.Condition;
import org.openqa.selenium.By;
import org.testng.annotations.Test;

import static com.codeborne.selenide.Selenide.*;

public class SecondTest extends InitTest{
    @Test(priority = 1)
    public void TestConnect(){
        open("https://hub.dev.twilight.space");
        sleep(2000);
        $("app-wallet").shouldBe(Condition.visible).click();
        $(By.xpath("//div[@class=\"wallet-item ng-star-inserted\"]//div[contains(text(),'Keplr')]")).shouldBe(Condition.visible).click();
    }


}

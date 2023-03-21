package com.example.selenidetemplate.tests;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.WebDriverRunner;
import com.example.selenidetemplate.DriverBase;
import com.example.selenidetemplate.config.ConfigurationManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;

import java.util.List;

import static com.codeborne.selenide.Selenide.sleep;

import static com.codeborne.selenide.Selenide.*;

public class BaseTest extends DriverBase {

    private static String mnemonic = ConfigurationManager.getMnemonic();

    private static String password = ConfigurationManager.getProp("password");

    WebDriver driver;


    @BeforeMethod
    public void setupDriver() throws Exception {
        driver = getDriver();
        WebDriverRunner.setWebDriver(driver);
    }


    public void setUpWallet(String mnemonic){

        $(By.xpath("//button[text()='Import existing account']")).click();
        sleep(100);
        List<SelenideElement> passInputs = $$(By.xpath("//div[contains(@class, 'mnemonic-word-form-group')]//div[contains(@class, 'input-group')]//input[@type='password']"));

        String[] mnemonicArr = mnemonic.split(" ");

        // Choose type mnemonic
        $(By.xpath("//div[contains(@class, 'dropdown')]")).click();
        if(mnemonicArr.length == 12){
            $(By.xpath("//button[text()='12 words']")).click();
        }else if(mnemonicArr.length == 24){
            $(By.xpath("//button[text()='24 words']")).click();
        }

        //Input mnemonic
        for (int i = 0; i < passInputs.size(); i++) {
            passInputs.get(i).sendKeys(mnemonicArr[i]);
        }


        $(By.name("name")).sendKeys("TestAccount");

        //Set password
        if($(By.name("confirmPassword")).is(Condition.enabled)){
            $(By.xpath("//div[contains(@class, 'form-inner-container')]//input[@name='password']")).sendKeys(password);
            $(By.name("confirmPassword")).sendKeys(password);
        }

        sleep(100);
        $(By.xpath("//button[@type='submit']")).click();

    }
}

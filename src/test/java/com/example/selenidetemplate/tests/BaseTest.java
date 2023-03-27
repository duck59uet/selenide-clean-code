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

    public void setUpKeplrWallet(String mnemonic){
        open("chrome-extension://dmkamcknogkgcdfhhbddcghachkejeap/popup.html#/register");
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

    public void createUpC98Wallet(){
        //Setup password
        open("chrome-extension://aeachknmefphepccionboohckonoeemg/popup.html");
        $(By.xpath("//button[text()='Create Wallet']")).click();
        $(By.xpath("//div[text()='Text Password']")).click();
        $(By.xpath("//button[text()='Ok']")).click();
        List<SelenideElement> passwordInputs = $$(By.xpath("//div[contains(@class, 'input-container')]//input"));
        for (int i = 0; i < passwordInputs.size(); i += 1){
            passwordInputs.get(i).sendKeys("abc123456!");
        }
        $(By.xpath("//button[text()='Setup Password']")).click();
        $(By.xpath("//button[text()='OK']")).click();
        $(By.xpath("//button[text()='Confirm']")).click();

        //Choose multi-chain
        $(By.xpath("//div[contains(@class, 'chain-item')]//div[text()='Multi-Chain']")).click();

        //Setup wallet name
        $("input").sendKeys("test1");
        $("input").shouldHave(Condition.text("test1"));
        $(By.xpath("//button[text()='Next']")).click();
        String mnemonic = $("cursor-pointer").getText();

    }

    public void restoreUpC98Wallet(String mnemonic){
        //Setup password
        open("chrome-extension://aeachknmefphepccionboohckonoeemg/popup.html");
        $(By.xpath("//button[text()='Restore Wallet']")).click();
        $(By.xpath("//div[text()='Text Password']")).click();
        $(By.xpath("//button[text()='Ok']")).click();
        List<SelenideElement> passwordInputs = $$(By.xpath("//div[contains(@class, 'input-container')]//input"));
        for (int i = 0; i < passwordInputs.size(); i += 1){
            passwordInputs.get(i).sendKeys("abc123456!");
        }
        $(By.xpath("//button[text()='Setup Password']")).click();
        $(By.xpath("//button[text()='OK']")).click();
        $(By.xpath("//button[text()='Confirm']")).click();

        //Choose multi-chain
        $(By.xpath("//div[contains(@class, 'chain-item')]//div[text()='Multi-Chain']")).click();

        //Setup wallet name
        $(By.xpath("//input[@placeholder='Wallet name']")).sendKeys("test1");
        $(".content-editable--password").setValue(mnemonic);
        $(By.xpath("//button[text()='Restore']")).click();
    }


}

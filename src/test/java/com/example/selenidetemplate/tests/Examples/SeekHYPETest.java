package com.example.selenidetemplate.tests.Examples;

import com.codeborne.selenide.Condition;
import com.example.selenidetemplate.config.ConfigurationManager;
import org.json.JSONObject;
import org.jtwig.JtwigModel;
import org.jtwig.JtwigTemplate;
import org.openqa.selenium.By;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import static com.codeborne.selenide.Selenide.*;

import com.codeborne.selenide.testng.TextReport;


@Listeners(TextReport.class)
public class SeekHYPETest extends TestSetup {

    JSONObject config = new JSONObject(ConfigurationManager.readConfiguration());

    @Test(description = "Login wallet then connect wallet on Seekhype")
    public void MintNFT(){

        //Connect wallet account 1

        JSONObject account1 = config.getJSONObject("account1");

        restoreUpC98Wallet((String) account1.get("mnemonic"));
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
        //When switch between windows (or URLs) you should
        switchTo().window("SeekHYPE");
        open("https://hub.serenity.twilight.space/launchpad");

        /**
         * TO-DO
         * Mint NFT has launched
         * Scrolling view
         */
        $(By.id("walletBox")).shouldHave(Condition.visible);
        $(By.id("walletBox")).scrollIntoView("{behavior: \"instant\", block: \"end\", inline: \"nearest\"}");

        sleep(100);

    }

    @Test
    public void TransferNFT(){
        /**
         * TO-DO
         * Transfer NFT
         * The first, must be checked owner has any NFT.
         * There are 2 options: 1. RestAPI 2. ListElements in UI
         */
        open("https://hub.serenity.twilight.space/profile");
        sleep(1000);
    }

    public void queryOwnedNFT(){
        String path = "https://graphql.staging.seekhype.ai/v1/graphql";

        JSONObject query = new JSONObject("\n" +
                "      query getStandardContractList($limit: Int = 10, $offset: Int = 0, $active: String = \"active\") {\n" +
                "        standard_contracts(limit: $limit, offset: $offset, where: {status: {_eq: $active}}) {\n" +
                "          code_id\n" +
                "          id\n" +
                "          name\n" +
                "          status\n" +
                "        }\n" +
                "        standard_contracts_aggregate(where: {status: {_eq: $active}}) {\n" +
                "          aggregate {\n" +
                "            count\n" +
                "          }\n" +
                "        }\n" +
                "      }\n" +
                "    ");

        JSONObject variable = new JSONObject();

        JtwigTemplate template = JtwigTemplate.classpathTemplate("getNFT.json");
        JtwigModel model = JtwigModel.newModel()
                .with("name", "guru")
                .with("email", "guru@gmail.com");

    }
}

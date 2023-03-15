package com.example.selenidetemplate.tests;

import org.testng.annotations.Test;

import static com.codeborne.selenide.Selenide.open;

public class SecondTest extends InitTest{
    @Test(priority = 1)
    public void TestConnect(){
        open("https://trufflesuite.com/docs/truffle/reference/configuration/");
    }
}

# Example Test Automation Project using Java and Selenium WebDriver

**This project delivers to you a complete lean test architecture for your web tests using the best frameworks and
practices.**

It has a complete solution to run tests in different ways:

* local testing using the browser on your local machine
* testing using remote browser in the future

## Languages and Frameworks

This project uses the following languages and frameworks:

* [Java 17](https://openjdk.java.net/projects/jdk/17/) as the programming language
* [TestNG](https://testng.org/doc/) as the UnitTest framework to support the test creation
* [Selenium WebDriver](https://www.selenium.dev/) as the web browser automation framework using the Java binding
* [Selenide](https://selenide.org/) as higher automation framework to test on UI
* [AssertJ](https://joel-costigliola.github.io/assertj) as the fluent assertion library
* [Allure Report](https://docs.qameta.io/allure/) as the testing report strategy
* [WebDriverManager](https://github.com/bonigarcia/webdrivermanager) as the Selenium binaries management

## Test architecture

This project can be your initial test architecture for a faster start.
You will see the following items in this architecture:

* [Page Objects pattern](#page-objects-pattern)
* [Write test](#write-test)
* [Configuration files](#configuration-files)

### Page Objects pattern

In development progress, Duplication usually occurs when multiple programmers are working on different parts of the 
same program at the same time. Since they’re working on different tasks,
they may be unaware their colleague has already written similar code that could be repurposed for their own needs.

![Duplicate Code](assets/duplicate-code-01.png)

The problem can be resolved by instead of writing duplicate code, we write extract class. Then we replace duplicate code by calling extract class.
In this architecture, we use `Page Object Pattern`. 

The Selenium Documentations gives a full of description about [Page Objects](https://www.selenium.dev/documentation/test_practices/encouraged/page_object_models/). 

```
Page Object is a Design Pattern that has become popular in test automation for enhancing test maintenance and reducing 
code duplication. A page object is an object-oriented class that serves as an interface to a page of your AUT. 
The tests then use the methods of this page object class whenever they need to interact with the UI of that page. 
The benefit is that if the UI changes for the page, the tests themselves don’t need to change, only the code within 
the page object needs to change. Subsequently, all changes to support that new UI are located in one place.
```

Next few days, i refactoring code cleaner. :)) But now, i have no idea to do.

### Write test

Now deep in the process of creating test script.
Let's begin. The first, must be instantiated web driver to run process test on this. This action like you open browser.
You can see how to do this in `TestSetup` file. 

```Java
    @BeforeMethod
    public void setupDriver() throws Exception {
        driver = getDriver();
        WebDriverRunner.setWebDriver(driver);
    }
```

When web-driver created successfully, write each scenario by test script. Project compiles and run line by line from top
to bottom. So you must define what to do first and what to do latter.

```Java
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
```

### Configuration file

I store configuration variable in `config_default.yml`. In there, a little comment for you that because i want to configuration
file can be parsed to JSON. It has advantage is easily to manage and pass variable to function when write test.
Example: In scenario check account balances, Instead of write to many line 

```properties
account1.address = aura...
account1.mnemonic = ....
```

I want to more clear, then i write line like this

```properties
account1:
  mnemonic: ...
  address: aura1...
```

### Run test

Main stack used in this architecture is JAVA. Like other language, JAVA use MAVEN or GRADLE to manage dependency. If 
you want to run test script, run script:

```bash
maven clean test
```


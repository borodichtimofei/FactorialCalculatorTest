package tests.base;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import pages.CalculatorPage;
import steps.CalculatorSteps;

import static com.codeborne.selenide.WebDriverRunner.getWebDriver;

public class BaseTest {

    protected CalculatorSteps calculatorSteps;
    protected CalculatorPage calculatorPage;

    @BeforeMethod(description = "Opening browser")
    public void setup() {
        Configuration.baseUrl = "https://qainterview.pythonanywhere.com/";
        Configuration.browser = "chrome";
        Configuration.headless = false;
        Configuration.timeout = 1000;

        SelenideLogger.addListener("AllureSelenide", new AllureSelenide());
        Configuration.reportsFolder = "target/allure-results";

        calculatorPage = new CalculatorPage();
        calculatorSteps = new CalculatorSteps();
    }

    @AfterMethod(alwaysRun = true, description = "Closing browser")
    public void close() {
        getWebDriver().quit();
    }
}

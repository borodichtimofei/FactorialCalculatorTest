package pages;

import com.codeborne.selenide.Condition;
import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;

import java.util.Locale;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static org.testng.Assert.assertEquals;

@Log4j2
public class CalculatorPage {

    public static final String VALUE_INPUT = "#number";
    public static final String CALCULATE_BUTTON = "#getFactorial";
    public static final By RESULT_MESSAGE = By.xpath("//p[contains(@style, 'rgb(0, 0, 0);')]");
    public static final By ERROR_MESSAGE = By.xpath("//p[@id='resultDiv']");

    public CalculatorPage isPageOpened() {
        $(CALCULATE_BUTTON).shouldBe(Condition.visible);
        return this;
    }

    @Step("Opening Calculator page")
    public CalculatorPage openPage() {
        log.info("Opening Calculator page");
        open("https://qainterview.pythonanywhere.com/");
        return this;
    }

    @Step("Input value: '{value}'")
    public CalculatorPage calculation(String value) {
        log.info("Input value {}", value);
        $(VALUE_INPUT).sendKeys(value);
        log.info("Click on button 'Calculate!'");
        $(CALCULATE_BUTTON).click();
        return new CalculatorPage();
    }

    /*
    Видел, что расчет результата просходит в скрипте в DOM, но не смог его оттуда вытянуть,
    не хватает знаний по JavaScript, поэтому реализовал вот так, с привязкой в Xpath к атрибуту
    цвета сообщения
     */
    @Step("Getting the result message")
    public String getResultMessage() {
        try {
            return $(RESULT_MESSAGE).getText().split(":")[1].substring(1).toUpperCase(Locale.ROOT).replace("+", "");
        } catch (com.codeborne.selenide.ex.ElementNotFound e) {
            return $(ERROR_MESSAGE).getText();
        }
    }

    /*
    Метод расчета факториала по заданному значению для проверки
    правильности калькуляции факториала приложением. Не получилось использовать рекурсию, т.к.
    меняется значение входящих данных и не может вызвать сам себя.
    */
    public double getFactorial(String value) {
        int f = Integer.parseInt(value);
        double result = 1;
        for (int i = 1; i <= f; i++) {
            result = result * i;
        }
        return result;
    }

    @Step("Validation of result calculation when input correct value")
    public void validateResultFactorialCalculation(String value) {
        log.info("Validation result calculation");
        assertEquals(
                Double.parseDouble(getResultMessage()),
                getFactorial(value),
                "Result calculation factorial is not valid");
    }

    @Step("Validation of error message when input incorrect value")
    public void validateErrorMessage() {
        log.info("Validation error message");
        assertEquals(
                getResultMessage(),
                "Please enter an integer",
                "Missing error message");
    }
}
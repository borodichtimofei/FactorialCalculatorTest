package tests;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import tests.base.BaseTest;

public class CalculatorTest extends BaseTest {

    @DataProvider(name = "Input data for positive calculation tests")
    public Object[][] loginDataPositive() {
        return new Object[][]{
                {"1"},
                {"5"},
                {"10"},
                {"50"},
                {"100"},
                {"0"}
        };
    }

    @DataProvider(name = "Input data for negative calculation tests")
    public Object[][] loginDataNegative() {
        return new Object[][]{
                {"q"},
                {"1.1"},
                {"--!@(&^%"},
                {"-1"},
                {"0b101"},
                {"0x141D12"},
                {" "}
        };
    }

    @Test(dataProvider = "Input data for positive calculation tests", description = "Calculation with correct data")
    public void withCorrectDataCalculationShouldBePerformed(String value) {
        calculatorSteps.calculation(value);
        calculatorPage.validateResultFactorialCalculation(value);
    }

    @Test(dataProvider = "Input data for negative calculation tests", description = "Calculation with incorrect data")
    public void withIncorrectDataCalculationShouldBeNotPerformed(String value) {
        calculatorSteps.calculation(value);
        calculatorPage.validateErrorMessage();
    }
}



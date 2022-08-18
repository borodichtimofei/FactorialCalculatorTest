package tests;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.CalculatorPage;

import static org.testng.Assert.assertEquals;

public class GetFactorialTest {

    CalculatorPage calculatorPage;

    public GetFactorialTest() {
        calculatorPage = new CalculatorPage();
    }

    /*
    Тестирование метода расчета факториала, который используется для проверки правильности расчета приложением.
    В данном случае, тот самый unit test, которому я не смог дать определения в первом интервью
     */
    @DataProvider(name = "Calculation factorial")
    public Object[][] loginDataNegative() {
        return new Object[][]{
                {"0", 1.0},
                {"1", 1.0},
                {"5", 120.0},
                {"50", 3.0414093201713376e+64}
        };
    }

    @Test(dataProvider = "Calculation factorial", description = "Calculation factorial with correct data")
    public void withCorrectDataCalculationShouldBePerformedCorrectly(String value, Double result) {
        assertEquals(
                calculatorPage.getFactorial(value),
                result,
                "Result calculation factorial is not valid");
    }
}

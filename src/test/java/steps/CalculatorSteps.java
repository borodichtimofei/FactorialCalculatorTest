package steps;

import pages.CalculatorPage;

public class CalculatorSteps {
    CalculatorPage calculatorPage;

    public CalculatorSteps() {
        calculatorPage = new CalculatorPage();
    }

    public void calculation(String value) {
        calculatorPage.openPage()
                .isPageOpened()
                .calculation(value)
                .getResultMessage();
    }
}

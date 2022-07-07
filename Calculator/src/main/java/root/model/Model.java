package root.model;

import org.springframework.stereotype.Component;

import root.utility.customExceptions.UnsupportedOperationExceptionCustom;

@Component
public class Model {

    public Double processNumbers(Double firstNumber, Double secondNumber, String sign) throws UnsupportedOperationExceptionCustom {

        // Определяем операцию по переданному знаку, при отсутствии таковой - исключение
        switch (sign) {
            case "+": {
                return add(firstNumber, secondNumber);
            }
            case "-": {
                return subtract(firstNumber, secondNumber);
            }
            case "*": {
                return multiply(firstNumber, secondNumber);
            }
            case "/": {
                return divide(firstNumber, secondNumber);
            }
            default:
        }
        throw new UnsupportedOperationExceptionCustom("Такая операция не поддерживается.");
    }

    private Double add(Double firstNumber, Double secondNumber) {
        return firstNumber + secondNumber;
    }

    private Double subtract(Double firstNumber, Double secondNumber) {
        return firstNumber - secondNumber;
    }

    private Double multiply(Double firstNumber, Double secondNumber) {
        return firstNumber * secondNumber;
    }

    private Double divide(Double firstNumber, Double secondNumber) {
        return firstNumber / secondNumber;
    }

}

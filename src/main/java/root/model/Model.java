package root.model;

import org.springframework.stereotype.Component;

@Component
public class Model {

    public double processNumbers(double firstNumber, double secondNumber, String sign) {

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
                throw new UnsupportedOperationException();
        }
    }

    private double add(double firstNumber, double secondNumber) {
        return firstNumber + secondNumber;
    }

    private double subtract(double firstNumber, double secondNumber) {
        return firstNumber - secondNumber;
    }

    private double multiply(double firstNumber, double secondNumber) {
        return firstNumber * secondNumber;
    }

    private double divide(double firstNumber, double secondNumber) {
        return firstNumber / secondNumber;
    }

}

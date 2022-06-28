package root.view;

import org.springframework.stereotype.Component;

@Component
public class View {

    public void writeMessage(String str, Double result) {
        if (result == null) {
            System.out.println(str);
        } else {
            System.out.printf(str, result);
        }
    }

    public void showInterface() {
        writeMessage("Пожалуйста введите два числа через пробел.", null);
        writeMessage("При разделении дробного числа используйте точку", null);
        writeMessage("Если не хотите продолжать работу с калькулятором, напишите \"end\"", null);
    }

    public void notifyAboutWrongInput() {
        writeMessage("Неправильный ввод.", null);
    }

    public void operationTypeRequest() {
        writeMessage("Введите название операции из списка:\n" +
                "\t\"сложение\"  - Введите \"+\"\n" +
                "\t\"вычитание\" - Введите \"-\"\n" +
                "\t\"умножение\" - Введите \"*\"\n" +
                "\t\"деление\"   - Введите \"/\"\n", null);
    }

    public void showResult(double number1, double number2, String operation, double result) {
        writeMessage("Ответ: " +
                number1 + " " +
                operation + " " +
                number2 +
                " = %.3f\n\n", result);
    }

    public void noSuchOperationMessage() {
        writeMessage("Такая операция не поддерживается, повторите запрос.", null);
    }
}

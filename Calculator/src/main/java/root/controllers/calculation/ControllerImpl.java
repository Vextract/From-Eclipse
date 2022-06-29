package root.controllers.calculation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import root.controllers.Controller;
import root.loggers.AbstractLogger;
import root.loggers.entities.LogEntry;
import root.main.*;
import root.model.Model;
import root.utility.customExceptions.NotEnoughArgumentsException;
import root.utility.customExceptions.UnsupportedOperationExceptionCustom;
import root.utility.view.View;

@Component
public class ControllerImpl implements Controller {

    private Model model;
    private View view;
    private final AbstractLogger logger;

    @Autowired
    public ControllerImpl(Model model, View view, AbstractLogger logger) {
        this.model = model;
        this.view = view;
        this.logger = logger;
    }

    public Response processIncomingInformation(String[] args) {

        double number1;
        double number2;
        String operation;

        try {
            number1 = Double.parseDouble(args[0]);
            number2 = Double.parseDouble(args[1]);
            operation = args[2];
        } catch (NumberFormatException e) {
            try {
                return new Response(e);
            } finally {
                logger.error(new LogEntry(e));
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            try {
                return new Response(new NotEnoughArgumentsException());
            } finally {
                logger.error(new LogEntry(new NotEnoughArgumentsException()));
            }
        }

        Response response;

        try {
            final String operationString = number1 + " " + operation + " " + number2;
            final double answer = model.processNumbers(number1, number2, operation);
            response = new Response(operationString, answer);
        } catch (UnsupportedOperationException e) {
            try {
                response = new Response(new UnsupportedOperationExceptionCustom(args[2]));
                return response;
            } finally {
                logger.error(new LogEntry(new UnsupportedOperationExceptionCustom(args[2])));
            }
        }
        try {
            return response;
        } finally {
            logger.log(response);
        }
    }

    public void emptyMethod1(int one) {

    }

    public void emptyMethod2(String[] array) {

    }
}

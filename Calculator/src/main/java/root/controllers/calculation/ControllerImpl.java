package root.controllers.calculation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import root.controllers.Controller;
import root.loggers.AbstractLogger;
import root.loggers.entities.LogEntry;
import root.main.*;
import root.model.Model;
import root.utility.customExceptions.FilterValidityException;
import root.utility.customExceptions.NotEnoughArgumentsException;
import root.utility.customExceptions.UnsupportedOperationExceptionCustom;
import root.utility.view.View;

@Component
public class ControllerImpl implements Controller {

	private final AbstractLogger logger;
    private final CalculatorService service;

    @Autowired
    public ControllerImpl(AbstractLogger logger, CalculatorService service) {
        this.logger = logger;
        this.service = service;
    }

    public Response processIncomingInformation(String[] args) {

    	try {
			return new Response(createStringForLog(Double.parseDouble(args[0]), Double.parseDouble(args[1]), args[2]), 
					service.validateAndProcessOperation(
							new CalculationPackage(Double.parseDouble(args[0]), Double.parseDouble(args[1]), args[2])), null);
		} catch (Exception e) {
			try {
				return new Response(e.getMessage(), null, e);
			} finally {
				logger.error(new LogEntry(new NumberFormatException(e.getMessage())));
			}
		} 
    }

    private String createStringForLog(double number1, double number2, String operation) {
    	return number1 + " " + operation + " " + number2;
    }
}

package root.controllers.calculation;

import org.controlsfx.control.spreadsheet.SpreadsheetCellEditor.DoubleEditor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import root.controllers.Controller;
import root.loggers.AbstractLogger;
import root.loggers.entities.LogEntry;
import root.main.CalculationPackage;
import root.main.Response;
import root.utility.customExceptions.FilterValidityException;
import root.utility.customExceptions.UnsupportedOperationExceptionCustom;

@RequestMapping("calculation_api")
@RestController
public class CalculationController implements Controller {

    private final AbstractLogger logger;
    private final CalculatorService service;

    @Autowired
    public CalculationController(AbstractLogger logger, CalculatorService service) {
        this.logger = logger;
        this.service = service;
    }

    @RequestMapping("/process")
    @GetMapping
    public Response processIncomingInformation(@RequestBody CalculationPackage calcPackage) {
    	try {
    		Double answer = service.validateAndProcessOperation(calcPackage);
    		String messageOrOperation = createStringForLog(calcPackage.getA(), calcPackage.getB(), calcPackage.getSign());
			return new Response(messageOrOperation, answer, null);
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

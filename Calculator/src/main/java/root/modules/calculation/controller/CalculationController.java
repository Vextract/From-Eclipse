package root.modules.calculation.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import root.interfaces.AbstractLogger;
import root.interfaces.Controller;
import root.loggers.entities.LogEntry;
import root.modules.calculation.entities.CalculationPackage;
import root.modules.calculation.entities.Response;
import root.modules.calculation.services.CalculatorService;

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
    		final Double answer = service.validateAndProcessOperation(calcPackage);
    		final String messageOrOperation = createStringForLog(calcPackage.getA(), calcPackage.getB(), calcPackage.getSign());
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

package root.modules.calculation.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import root.modules.calculation.entities.CalculationPackage;
import root.modules.calculation.infrastructure.Model;
import root.utility.ValidationResponse;
import root.utility.customExceptions.FilterValidityException;
import root.utility.customExceptions.UnsupportedOperationExceptionCustom;

@Component
public class CalculatorService {
	
	private CalcPackageValidator validator;
	private Model model;

	@Autowired
	public CalculatorService(CalcPackageValidator validator, Model model) {
		this.validator = validator;
		this.model = model;
	}
	
	public Double validateAndProcessOperation(CalculationPackage calcPackage) throws FilterValidityException, UnsupportedOperationExceptionCustom {
		
		ValidationResponse resp = validator.validate(calcPackage);
		
		// Если ответ невалидный - бросаем исключение с сообщением.
        if (!resp.isValid()) {
            throw new FilterValidityException(resp.getMessage());
        } else {
        	return model.processNumbers(calcPackage.getA(), calcPackage.getB(), calcPackage.getSign());
        }
	}

}

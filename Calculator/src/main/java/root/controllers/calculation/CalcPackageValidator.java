package root.controllers.calculation;

import org.springframework.stereotype.Component;

import root.main.CalculationPackage;
import root.repository.validator.ValidationResponse;

@Component
public class CalcPackageValidator {
	
	public ValidationResponse validate(CalculationPackage calcPackage) {
		
		if (calcPackage.getA() == null || calcPackage.getB() == null || calcPackage.getSign() == null) {
			return new ValidationResponse(false,"Неправильный формат аргументов");
		}
		
		return new ValidationResponse(true,null);
	}

}

package fr.dawan.projet1.ws.validator.custom;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import fr.dawan.projet1.ws.beans.CompagnieAerienne;

public class CheckCaseValidator implements ConstraintValidator<CheckCase, CompagnieAerienne> {

	private CaseMode caseMode;

	@Override
	public void initialize(CheckCase constraintAnnotation) {
		caseMode = constraintAnnotation.caseMode();

	}

	@Override
	public boolean isValid(CompagnieAerienne com, ConstraintValidatorContext context) {
		boolean result;
		String object = com.getNom();
		if (object == null)
			result=  true;

		if (caseMode == CaseMode.UPPER)
			result =  object.equals(object.toUpperCase());
		else
			result = object.equals(object.toLowerCase());
		
		System.out.println("CheckCaseValidator processé et is valide à " + result);
		return result;
				
	}

}

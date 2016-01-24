package fr.dawan.projet1.ws.validator.custom;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Target(value={ElementType.TYPE})
@Retention(value=RetentionPolicy.RUNTIME)
@Constraint(validatedBy = {CheckCaseValidator.class})
@Documented
public @interface CheckCase {

	CaseMode caseMode();
	String message() default "validation custom de CheckCase";
	
	//permet de regrouper les annot dans un meme group
	Class<?>[] groups() default {};
	
	//charge utile
	Class<? extends Payload>[] payload() default{};
	
	

}

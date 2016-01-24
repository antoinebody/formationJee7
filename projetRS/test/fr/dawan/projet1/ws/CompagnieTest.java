package fr.dawan.projet1.ws;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Payload;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.junit.Before;
import org.junit.Test;

import fr.dawan.projet1.ws.beans.CompagnieAerienne;
import fr.dawan.projet1.ws.validator.custom.Severity;
import junit.framework.TestCase;

public class CompagnieTest extends TestCase{
	
	private ValidatorFactory vf;
	private Validator validator;
	
	private CompagnieAerienne compagnieA;
	
	@Before
	public void setUpBeforeClass(){
		
		
	}

	@Test
	public void test() {
		System.out.println("launch test unit");
		vf = Validation.buildDefaultValidatorFactory();
		validator = vf.getValidator();
		
		compagnieA = new CompagnieAerienne(50,"aaAa");
		//indentifyViolations(compagnieA,0);
		Set<ConstraintViolation<CompagnieAerienne>> violations = validator.validate(compagnieA);
		
		if(violations.isEmpty())
			System.out.println("Aucune Violation");
		else{
			for (ConstraintViolation<CompagnieAerienne> constraintViolation : violations) {
				System.out.println("----" + constraintViolation.getMessage());
				Set<Class<? extends Payload>> payload = constraintViolation.getConstraintDescriptor().getPayload();
				for (Class<? extends Payload> class1 : payload) {
					if(class1.equals(Severity.Info.class)){
						System.out.println("\tError de type info");
					}
					else if(class1.equals(Severity.Info.class)){
						System.out.println("\tError de type ERROR");
					}
				}
				
				
			}
			
		}
		assertEquals(violations.size(), 0);
		
		
	}

	private void indentifyViolations(CompagnieAerienne compagnieA2, int i) {

		
	}

}

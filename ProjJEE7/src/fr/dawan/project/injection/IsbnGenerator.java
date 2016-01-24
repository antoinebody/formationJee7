package fr.dawan.project.injection;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.enterprise.inject.Default;


//@Named // si aucun nom donné le bean s'appelera isbnGenerator
@Default
@Audited
public class IsbnGenerator {
	
	private static int NB;
	
	public IsbnGenerator(){
		System.out.println("inside IsbnGenerator constructor");
	}
	
	public String generateNumber(){
		return "XXXXX-" + ++NB;
	}
	
	/**
	 * pas obligé que le nom match l annotation
	 */
	@PostConstruct
	public void postConstruct(){
		System.out.println("Après construction");
	}
	
	@PreDestroy
	public void preDestroy(){
		System.out.println("Avant la destruction");
	}

}

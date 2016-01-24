package fr.dawan.project.jpa.heritage.singletable;

import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

@SuppressWarnings("serial")
@Entity
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
public class CompteEpargneS extends CompteBancaireS{
	
	private double taux;

	public CompteEpargneS() {
		super();
	}

	public CompteEpargneS(long id, double montant,double taux) {
		super(id,montant);
		this.taux= taux;
	}

	public double getTaux() {
		return taux;
	}

	public void setTaux(double taux) {
		this.taux = taux;
	}

	
	

}

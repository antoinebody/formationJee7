package fr.dawan.project.jpa.heritage.joined;

import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

@SuppressWarnings("serial")
@Entity
@Inheritance(strategy=InheritanceType.JOINED)
@Table(name="tableperclass_CompteEpargne")
public class CompteEpargneJ extends CompteBancaireJ{
	
	private double taux;

	public CompteEpargneJ() {
		super();
	}

	public CompteEpargneJ(long id, double montant,double taux) {
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

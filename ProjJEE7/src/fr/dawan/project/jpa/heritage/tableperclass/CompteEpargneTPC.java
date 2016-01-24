package fr.dawan.project.jpa.heritage.tableperclass;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

@SuppressWarnings("serial")
@Entity
@Inheritance(strategy=InheritanceType.TABLE_PER_CLASS)
public class CompteEpargneTPC extends CompteBancaireTPC{
	
	private double taux;

	public CompteEpargneTPC() {
		super();
	}

	public CompteEpargneTPC(long id, double montant,double taux) {
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

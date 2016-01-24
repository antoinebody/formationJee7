package fr.dawan.project.jpa;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@SuppressWarnings("serial")
@Entity
public class ReferenceProduit implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	private String isin_reference;

	@OneToOne
	private Produit produit;

	public ReferenceProduit() {
		super();
	}

	public ReferenceProduit(long id, String isin_reference, Produit produit) {
		super();
		this.id = id;
		this.isin_reference = isin_reference;
		this.produit = produit;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getIsin_reference() {
		return isin_reference;
	}

	public void setIsin_reference(String isin_reference) {
		this.isin_reference = isin_reference;
	}

	public Produit getProduit() {
		return produit;
	}

	public void setProduit(Produit produit) {
		this.produit = produit;
	}

}

package fr.dawan.project.jpa;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.PrePersist;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

@SuppressWarnings("serial")
@Entity
public class Produit implements Serializable {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="pr_id")
	private long id;

	@Column(nullable=false,length=256)
	private String description;

	//permet d avoir une java.util.Date sans heures minutes et secondes
	@Temporal(TemporalType.DATE)
	private Date dateCreation;
	
	//possible d ajouter sur l ordinal de l enum (comportement par défaut)
	@Enumerated(EnumType.STRING)
	private EtatProduit etat;
	
	//Collections simples
	//va faire une jointure
	@ElementCollection
	//optionnel pour définir la structure de la table crée
	@CollectionTable(name="t_composants",joinColumns=@JoinColumn(name="PROD_ID"))
	@Column(name="PROD_COMPOSANT")
	private List<String> composants;

	@Transient
	private String nonSerialise;
	
	//une marque a plusieurs produit, un produit a une seule marque
	@ManyToOne(cascade=CascadeType.ALL)
	private Marque marque;
	
	//idem on met le champ java de l autre champ qui mappe
	//sinon vu que y a 2  @ManyToMany (un sur chaque class)
	//ca créera deux tables de jointure
	
	//il vaut mieux mettre les cascades des deux coté de la relation
	//la cascade sera fait sur toutes les opérations
	@ManyToMany(mappedBy="produits",cascade=CascadeType.ALL)
	private List<Fournisseur> fournisseurs;
	
	//la cascade n'aura lieu qu'au moment du persist CascadeType.ALL 
	@OneToOne(mappedBy="produit",cascade=CascadeType.ALL)
	private ReferenceProduit reference;
	

	public Produit() {
		super();
	}

	public Produit(int id, String description, Date dateCreation,EtatProduit etat) {
		super();
		this.id = id;
		this.description = description;
		this.dateCreation = dateCreation;
		this.etat = etat;
		this.composants = new ArrayList<>();
		fournisseurs = new ArrayList<>();
		this.nonSerialise = "non serialized";
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getDateCreation() {
		return dateCreation;
	}

	public void setDateCreation(Date dateCreation) {
		this.dateCreation = dateCreation;
	}

	public String getNonSerialise() {
		return nonSerialise;
	}

	public void setNonSerialise(String nonSerialise) {
		this.nonSerialise = nonSerialise;
	}
	
	@PrePersist
	public void avantPersist(){
		System.out.println("@PrePersist -> Avant la persistance");
	}

	public List<String> getComposants() {
		return composants;
	}

	public void setComposants(List<String> composants) {
		this.composants = composants;
	}

	public Marque getMarque() {
		return marque;
	}

	public void setMarque(Marque marque) {
		this.marque = marque;
	}

	public List<Fournisseur> getFournisseurs() {
		return fournisseurs;
	}

	public void setFournisseurs(List<Fournisseur> fournisseurs) {
		this.fournisseurs = fournisseurs;
	}

	public ReferenceProduit getReference() {
		return reference;
	}

	public void setReference(ReferenceProduit reference) {
		this.reference = reference;
	}

}

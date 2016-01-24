package fr.dawan.project.jpa;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedNativeQueries;
import javax.persistence.NamedNativeQuery;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.NamedStoredProcedureQueries;
import javax.persistence.NamedStoredProcedureQuery;
import javax.persistence.OneToMany;
import javax.persistence.ParameterMode;
import javax.persistence.StoredProcedureParameter;


//les names queries peuvent etre directement utilisé dans un DAO avec le entitymanager
//permet de factoriser les query
@NamedQueries(value = { @NamedQuery(name = "req1JPQL", query = "select m From Marque m") })
@NamedNativeQueries(value = @NamedNativeQuery(name = "req2Sql", query = "select id, nom from t_marques") )
@NamedStoredProcedureQueries(value = {
		@NamedStoredProcedureQuery(name = "myQuery", procedureName = "recupProduit", parameters = {
				@StoredProcedureParameter(type = String.class, mode = ParameterMode.IN) }) })
@SuppressWarnings("serial")
@Entity
public class Marque implements Serializable{
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;
	private String nomMarque;
	
	//on indique le nome de l'attribut java qui match dans l autre classe
	@OneToMany(mappedBy="marque")
	private List<Produit> produits;
	
	public Marque() {
		super();
	}

	public Marque(long id, String nomMarque) {
		super();
		this.id = id;
		this.nomMarque = nomMarque;
		this.produits = new ArrayList<>();
	}

	
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getNomMarque() {
		return nomMarque;
	}

	public void setNomMarque(String nomMarque) {
		this.nomMarque = nomMarque;
	}

	public List<Produit> getProduits() {
		return produits;
	}

	public void setProduits(List<Produit> produits) {
		this.produits = produits;
	}

	@Override
	public String toString() {
		return "Marque [id=" + id + ", nomMarque=" + nomMarque + ", produits=" + produits + "]";
	}
	
	

}

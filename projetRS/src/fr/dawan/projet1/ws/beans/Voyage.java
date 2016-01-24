package fr.dawan.projet1.ws.beans;

import java.io.Serializable;
import java.util.Date;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

// Possible d'utiliser XJC pour générer les classe à partir d un xsd

@XmlRootElement(name="voyage")
@XmlAccessorType(XmlAccessType.FIELD)
public class Voyage implements Serializable{
	
	
	//surchage du nom possible  

	/**
	 * 
	 */
	private static final long serialVersionUID = 8199351429211435345L;
	//en attribut de la balise voyage
	@XmlAttribute(name="voyage_id")
	protected long id;
	// en element de la balise voyage
	@XmlElement(name="ville_depart")
	protected String villeDepart;
	@XmlElement
	protected String villeArrivee;
	@XmlElement
	protected Date datedepart;
	
	public Voyage() {
	}

	public Voyage(long id, String villeDepart, String villeArrivee, Date datedepart) {
		this.id = id;
		this.villeDepart = villeDepart;
		this.villeArrivee = villeArrivee;
		this.datedepart = datedepart;
	}


	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getVilleDepart() {
		return villeDepart;
	}

	public void setVilleDepart(String villeDepart) {
		this.villeDepart = villeDepart;
	}

	public String getVilleArrivee() {
		return villeArrivee;
	}

	public void setVilleArrivee(String villeArrivee) {
		this.villeArrivee = villeArrivee;
	}

	public Date getDatedepart() {
		return datedepart;
	}

	public void setDatedepart(Date datedepart) {
		this.datedepart = datedepart;
	}

	@Override
	public String toString() {
		return "Voyage [id=" + id + ", villeDepart=" + villeDepart + ", villeArrivee=" + villeArrivee + ", datedepart="
				+ datedepart + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (id ^ (id >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Voyage other = (Voyage) obj;
		if (id != other.id)
			return false;
		return true;
	}

}

package fr.dawan.projet1.ws;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;

import fr.dawan.projet1.dao.VoyageDao;
import fr.dawan.projet1.ws.beans.Voyage;

@Path("voyages")
public class VoyageWS {
	
	//fichier beans.xml dans le WEB-INF pour palier à certains bugs de weld sur la découverte des beans CDI
	@Inject
	private VoyageDao voyageDao;
	
	
	private static SimpleDateFormat SDF = new SimpleDateFormat("dd-MM-yyyy");

	@GET
	@Path("/{id}")
	@Produces({ MediaType.TEXT_XML, MediaType.APPLICATION_XML })
	// @Produces({MediaType.APPLICATION_JSON})
	public Voyage getVoyage(@PathParam("id") long id) {
		return voyageDao.rechercher(id);
	}

	// afin de faciliter la gestion des types génértiques, ils ont crée la
	// classe
	// GenericEntity : wrapper de types génériques
	//

	@GET
	//@Produces({ MediaType.TEXT_XML, MediaType.APPLICATION_XML })
	@Produces({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
	public GenericEntity<List<Voyage>> getVoyages() {
		List<Voyage> list = voyageDao.lister();
		GenericEntity<List<Voyage>> res = new GenericEntity<List<Voyage>>(list) {
		};
		return res;
	}

	@Path("add")
	@GET
	//@Produces({ MediaType.TEXT_XML, MediaType.APPLICATION_XML })
	@Produces({MediaType.APPLICATION_JSON})
	public Voyage addVoyages(@QueryParam("ville_depart") String villeDepart,
			@QueryParam("villeArrivee") String villeArrivee, @QueryParam("datedepart") String datedepart) throws ParseException {
		Voyage voyage = new Voyage();
		voyage.setVilleDepart(villeDepart);
		voyage.setVilleArrivee(villeArrivee);
		voyage.setDatedepart(SDF.parse(datedepart));
		return voyageDao.ajouter(voyage);
	}
	@Path("delete")
	@GET
	//@Produces({ MediaType.TEXT_XML, MediaType.APPLICATION_XML })
	@Produces({MediaType.APPLICATION_JSON})
	public String deleteVoyages(@QueryParam("voyage_id") long id){
		boolean ok = voyageDao.supprimer(id);
		if(ok)
			return "Voyage " + id + "deleted";
		return "Error while deleting voyage id " + id ;
	}
	
	@Path("modify")
	@GET
	//@Produces({ MediaType.TEXT_XML, MediaType.APPLICATION_XML })
	@Produces({MediaType.APPLICATION_JSON})
	public String modifyVoyage(@QueryParam("voyage_id") long id,@QueryParam("ville_depart") String villeDepart,
			@QueryParam("villeArrivee") String villeArrivee, @QueryParam("datedepart") String datedepart) throws ParseException {
		Voyage voyage = new Voyage();
		voyage.setId(id);
		voyage.setVilleDepart(villeDepart);
		voyage.setVilleArrivee(villeArrivee);
		voyage.setDatedepart(SDF.parse(datedepart));
		return (voyageDao.modifier(voyage) ? "ok" : "ko");
	}
}

package fr.dawan.projet1.ws;

import java.util.List;

import javax.inject.Inject;
import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import fr.dawan.projet1.dao.CompagnieDao;
import fr.dawan.projet1.ws.beans.CompagnieAerienne;

@Path("/compagnies")
public class CompagnieWS {
	
	@Inject
	private CompagnieDao compagnieDao; 
	
	
	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	//on peut mettre plusieurs produces, sera géré en fct de l accept du client
	//@Produces({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML)
	public CompagnieAerienne getCompagnieJson(@PathParam("id")long id){
		return compagnieDao.rechercher(id);
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public @Size(min=1) @NotNull GenericEntity<List<CompagnieAerienne>> getAllCompagnies(){
		return new GenericEntity<List<CompagnieAerienne>>(compagnieDao.lister()){
			
		};
	}
	
	@POST
	@Path("/add")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response add(@Valid  CompagnieAerienne aerienne){
		compagnieDao.ajouter(aerienne);
		
		return Response.ok(aerienne).build();
	}

	@DELETE
	@Path("/delete/{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces("text/html; charset=utf-8")
	public Response delete(@Max(500) @PathParam("id") long id){
		compagnieDao.supprimer(id);
		return Response.ok("supprimé").build();
	}

}

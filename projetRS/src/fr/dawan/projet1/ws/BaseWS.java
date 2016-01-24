package fr.dawan.projet1.ws;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Random;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ws.rs.Consumes;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.MatrixParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.core.UriInfo;

import org.glassfish.jersey.media.multipart.ContentDisposition;
import org.glassfish.jersey.media.multipart.FormDataBodyPart;
import org.glassfish.jersey.media.multipart.FormDataContentDisposition;
import org.glassfish.jersey.media.multipart.FormDataMultiPart;
import org.glassfish.jersey.media.multipart.FormDataParam;

@Path("/bases")
public class BaseWS {

	@Context
	private UriInfo uriInfo;

	// Injections executées à chaque appel
	@PostConstruct
	public void init() {
		System.out.println("__________ post construct");
	}

	// Injections executées à chaque appel
	@PreDestroy
	public void destroy() {
		System.out.println("__________ pre destroy");
	}

	// passage de parametres
	@GET
	@Path("/{p1}")
	// @Path("/{p1 : [a-zA-Z][0-9]}")
	public Response getMsg(@PathParam("p1") String msg) {
		String res = "Bonjour " + msg;

		// on peut récupérer les paramètres depuis urInfo si ?nom=valeur
		// uriInfo.getQueryParameters().getFirst("p1");
		// reponse normale
		// return
		// Response.status(javax.ws.rs.core.Response.Status.OK).entity(res).build();
		// pour la gestion de l'encodage : methode encoding
		// return
		// Response.status(Response.Status.OK).encoding(StandardCharsets.UTF_8).entity(res).build();
		// pour la gestion de l encodage/type MIMe
		return Response.status(Response.Status.OK).entity(res)
				.header(HttpHeaders.CONTENT_TYPE, MediaType.TEXT_HTML + ";charset=utf-8").build();
	}

	@GET
	@Path("/{year}/{month}/{day}")
	@Produces(MediaType.TEXT_HTML + "; charset=utf-8")
	public String getDate(@PathParam("year") int year, @PathParam("month") int month, @PathParam("day") int day) {
		return day + "/" + month + "/" + year;
	}

	// expression régulière : {variableName [:expression]}
	// {id : [a-zA-Z][0-9]} ; le param id doit contenir une lettre et un chiffre

	// QueryParam : pour capturer les params dans l url
	// ?p1=5&p2=45&p1=toto : on peut avoir plusieurs fois p1 donc ça rend une
	// liste
	// récuperation depuis UriInfo our @QueryParam

	// Exemple de la requete : /rest/base/email?from=toto&to=titi&to=tata
	@GET
	@Path("/email")
	@Produces(MediaType.TEXT_HTML + "; charset=utf-8")
	public String testQueryParam(@QueryParam("from") String from, @QueryParam("to") List<String> to,
			@QueryParam("subject") String subject,
			@DefaultValue(value = "empty message") @QueryParam("msg") String msg) {
		return "mail envoyé depuis " + from + " à " + to;
	}

	@GET
	@Path("/email2")
	@Produces(MediaType.TEXT_HTML + "; charset=utf-8")
	public String testQueryParam(@Context UriInfo uriInfo) {

		return "mail envoyé depuis " + uriInfo.getQueryParameters().getFirst("from");
	}

	// avec matrix param acteur=keanu;hero=neo
	@GET
	@Path("/matrix/{p1}")
	@Produces(MediaType.TEXT_HTML + "; charset=utf-8")
	public String testMatrixParam(@PathParam("p1") int date, @MatrixParam("acteur") String acteur,
			@MatrixParam("heros") String heros) {
		return acteur + " a jouer " + heros + " en " + date;
	}

	// Form Param __________________________________________________________
	@POST
	@Path("/produit/add")
	public String ajouterProduit(@FormParam("description") String description, @FormParam("prix") double prix) {
		return "ajout du produit : " + description + " au prix de " + prix;
	}

	// header : soit on récup une info particulière
	// ou récup le RequestHeader en entier
	@GET
	@Path("/user-agent")
	@Produces(MediaType.TEXT_HTML + "; charset=utf-8")
	public String getUserAgent(@HeaderParam("user-agent") String userAgent) {
		return "Navigateur : " + userAgent;
	}

	@GET
	@Path("/headers")
	@Produces(MediaType.TEXT_HTML + "; charset=utf-8")
	public String getUserAgent(@Context HttpHeaders headers) {
		StringBuffer sb = new StringBuffer();
		// headers.getRequestHeaders().entrySet().stream().map((e)->(e.getkey()
		// +" -> " + e.getValue()).collect("<br/>");
		headers.getRequestHeaders().forEach((k, v) -> sb.append(k + "=" + v + " <br/>"));

		return sb.toString();
	}

	@GET
	@Path("download-file")
	@Produces("application/xml")
	public Response downloadFile() {
		URL url = Thread.currentThread().getContextClassLoader().getResource("produits.xml");
		File f;
		try {
			System.out.println("--------------------- URL :" + url);
			f = new File(url.toURI());
		} catch (URISyntaxException | NullPointerException e) {
			e.printStackTrace();
			return Response.status(Status.NOT_FOUND).build();
		}
		ResponseBuilder respB = Response.ok(f);
		respB.header("Content-Disposition", "attachment; filename=\"produits.xml\"");
		return respB.build();
	}

	// Upload 
	// __________________________________________________________
	
	
	
	//Upload d un seul fichier
	@POST
	@Path("upload")
	@Produces(MediaType.TEXT_HTML + "; charset=utf-8")
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	public String uploadFile(@FormDataParam("file") InputStream inputStream,
			@FormDataParam("file") FormDataContentDisposition fileDetails) {
		File dest = new File("C:/Formation" + File.separator + +(new Random()).nextInt(1000)+"__" + fileDetails.getFileName() );
		
		try {
			Files.copy(inputStream, Paths.get(dest.toURI()));
		} catch (IOException e) {  
			return e.getMessage();
			
		}
		return "file uploaded in " + dest.toURI() ;
	}
	//Upload de plusieurs fichiers dans un seul formulaire
	@POST
	@Path("/upload2")
	@Produces("text/html; charset=utf-8")
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	public String uploadFile2(FormDataMultiPart form){
		//Avantages ; pouvoir intercepter plusieurs pièces jointes
		//file représente le name du input type="file"
		FormDataBodyPart  bodyPart =  form.getField("file");
		ContentDisposition fileDetails =  bodyPart.getContentDisposition();
		InputStream uploadedInputStream = bodyPart.getValueAs(InputStream.class);
		
		String output = "";
		String fullName = fileDetails.getFileName();
		//System.out.println("fullName : " + fullName);
		String ext = fullName.substring(fullName.lastIndexOf("."));
		String uploadLocation = "C:/tempBidon/myFile."+ ext;
		try {
			Files.copy(uploadedInputStream, Paths.get(uploadLocation));
			output = "fichier uploadé !";
		} catch (Exception e) {
			output = "erreur lors de l'upload : " + e.getMessage();
			e.printStackTrace();
		}
		return output;
	}
	

}

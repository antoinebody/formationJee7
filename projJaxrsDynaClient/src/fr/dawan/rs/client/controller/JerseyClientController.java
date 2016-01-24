package fr.dawan.rs.client.controller;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.glassfish.jersey.jackson.JacksonFeature;

import fr.dawan.rs.client.beans.CompagnieAerienne;

/**
 * Servlet implementation class JerseyClientController
 */
@WebServlet("/JerseyClientController")
public class JerseyClientController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		postJersey(response);
		//getJersey(response);
		//deleteJersey(response);

	}

	private void deleteJersey(HttpServletResponse response) {
		CompagnieAerienne compagnieAerienne = new CompagnieAerienne(1L, "tomas cook");
		Client client = ClientBuilder.newClient().register(JacksonFeature.class);
		Response res = client.target("http://localhost:8080/projetRS/rest/compagnies/delete").path("/{id}").resolveTemplate("id",1).request().delete();
		//.accept(MediaType.APPLICATION_JSON).method("DELETE", Entity.entity(compagnieAerienne, MediaType.APPLICATION_JSON));
		if(res.getStatus()!=200)
			throw new RuntimeException("failed htpp " + res.getStatus());
		try {
			response.getWriter().write(res.readEntity(String.class));
		} catch (IOException e) {
			try {
				e.printStackTrace(response.getWriter());
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		client.close();
		
	}
	private void postJersey(HttpServletResponse response) {
		CompagnieAerienne compagnieAerienne = new CompagnieAerienne(1L, "tomSSSs cook");
		Client client = ClientBuilder.newClient().register(JacksonFeature.class);
		Response res = client.target("http://localhost:8080/projetRS/rest/compagnies/add").request().post(Entity.entity(compagnieAerienne, MediaType.APPLICATION_JSON));
		//.accept(MediaType.APPLICATION_JSON).method("DELETE", Entity.entity(compagnieAerienne, MediaType.APPLICATION_JSON));
		if(res.getStatus()!=200)
			throw new RuntimeException("failed htpp " + res.getStatus());
		try {
			response.getWriter().write(res.readEntity(CompagnieAerienne.class).toString());
		} catch (IOException e) {
			try {
				e.printStackTrace(response.getWriter());
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}	
		client.close();
		
	}

	private void getJersey(HttpServletResponse response) throws IOException {
		
		Client client = ClientBuilder.newClient().register(JacksonFeature.class);
		Response res = client.target("http://localhost:8080/projetRS/rest/compagnies").request().get();

		if(res.getStatus()!=200)
			throw new RuntimeException("failed htpp " + res.getStatus());
		GenericType<List<CompagnieAerienne>> compagniesTypes = new GenericType<List<CompagnieAerienne>>(){};
		List<CompagnieAerienne> list = res.readEntity(compagniesTypes);
		
		String resutl = list.stream().map(CompagnieAerienne::toString).collect(Collectors.joining("<br/>"));
		
		response.getWriter().write(resutl);
		client.close();
	}

}
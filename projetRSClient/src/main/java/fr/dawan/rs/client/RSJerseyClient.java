package fr.dawan.rs.client;

import javax.ws.rs.core.MediaType;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;

import fr.dawan.projet1.ws.beans.CompagnieAerienne;

public class RSJerseyClient {

	public static void main(String[] args) throws Exception {

		jerseyWay();

	}


	private static void jerseyWay() {
		
		CompagnieAerienne input = new CompagnieAerienne(6L, "Framzzez");
		ClientConfig clientConfig = new DefaultClientConfig();
		//clientConfig.getFeatures().put(JSONConfiguration.FEATURE_POJO_MAPPING, Boolean.TRUE);

		Client client = Client.create(clientConfig);

		WebResource webResource = client.resource("http://localhost:8080/projetRS/rest/compagnies/add");
		ClientResponse response = webResource.type(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON).post(ClientResponse.class, input);

		

		if (response.getStatus() != 200) {
			throw new RuntimeException("Failed : HTTP error code : " + response.getStatus() );
		}

		System.out.println("Output from Server .... \n");
		CompagnieAerienne output = response.getEntity(CompagnieAerienne.class);
		System.out.println(output);

	}

}

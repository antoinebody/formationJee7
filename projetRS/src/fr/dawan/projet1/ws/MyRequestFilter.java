package fr.dawan.projet1.ws;

import java.io.IOException;

import javax.annotation.Priority;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.container.PreMatching;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.Provider;
import javax.ws.rs.core.UriInfo;

@PreMatching //pour appliquer l interception avant le match req/params de l'url
@Priority(1)
@Provider
public class MyRequestFilter implements ContainerRequestFilter {
	
	//test pour voir s il est bien instancié
	public MyRequestFilter() {
		System.out.println("init MyRequestFilter");
	}

	@Override
	public void filter(ContainerRequestContext requestContext) throws IOException {
		System.out.println("dans le filter");
		UriInfo uriInfo = requestContext.getUriInfo();
		MultivaluedMap<String, String> queryParameters = uriInfo.getQueryParameters();
		String user = queryParameters.getFirst("user");
		if("want to fail".equals(user))
			requestContext.abortWith(Response.serverError().status(Status.BAD_REQUEST).build());
		

	}

}

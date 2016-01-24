package fr.dawan.projet1.ws;

import java.io.IOException;

import javax.annotation.Priority;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerResponseContext;
import javax.ws.rs.container.ContainerResponseFilter;
import javax.ws.rs.ext.Provider;

@Priority(1)
@Provider
public class MyResponseFilter implements ContainerResponseFilter {
	
	//test pour voir s il est bien instancié
	public MyResponseFilter() {
		System.out.println("init MyResponseFilter");
	}

	@Override
	public void filter(ContainerRequestContext requestContext, ContainerResponseContext responseContext)
			throws IOException {
		System.out.println("Content-Length " + responseContext.getHeaderString("Content-Length"));
	}

//	@Override
//	public void filter(ContainerRequestContext requestContext) throws IOException {
//		System.out.println("dans le filter");
//		UriInfo uriInfo = requestContext.getUriInfo();
//		MultivaluedMap<String, String> queryParameters = uriInfo.getQueryParameters();
//		String user = queryParameters.getFirst("user");	
//		if("want to fail".equals(user))
//			requestContext.abortWith(Response.serverError().status(Status.BAD_REQUEST).build());
//		
//
//	}

}

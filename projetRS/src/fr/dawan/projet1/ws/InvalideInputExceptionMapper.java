package fr.dawan.projet1.ws;

import java.io.IOException;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;

//type générique, on met l exception qu'on veut
public class InvalideInputExceptionMapper implements ExceptionMapper<IOException> {

	@Override
	public Response toResponse(IOException exception) {
		return Response.status(Status.BAD_REQUEST).build();
	}

}

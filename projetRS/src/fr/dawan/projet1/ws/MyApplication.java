package fr.dawan.projet1.ws;

import javax.ws.rs.ApplicationPath;

import org.glassfish.jersey.media.multipart.MultiPartFeature;
import org.glassfish.jersey.server.ResourceConfig;

@ApplicationPath("rest")
public class MyApplication extends ResourceConfig {

	public MyApplication() {
		//package pour scan des annot
		packages(getClass().getPackage().getName(),getClass().getPackage().getName()+".validator.custom");
		
		//support de l upload
		register(MultiPartFeature.class);
	}

}

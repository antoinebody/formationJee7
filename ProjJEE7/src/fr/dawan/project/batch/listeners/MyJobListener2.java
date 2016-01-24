package fr.dawan.project.batch.listeners;

import javax.batch.api.listener.JobListener;
import javax.batch.runtime.context.JobContext;
import javax.inject.Inject;
import javax.inject.Named;

@Named
//@RequestScoped
public class MyJobListener2 implements JobListener{
	
	@Inject
	private JobContext jobContext;
	

	@Override
	public void afterJob() throws Exception {
		System.out.println("MyJobListener::afterJob");
		System.out.println(jobContext.getProperties().toString());
	}

	@Override
	public void beforeJob() throws Exception {
		System.out.println("MyJobListener::beforeJob");		
	}

}

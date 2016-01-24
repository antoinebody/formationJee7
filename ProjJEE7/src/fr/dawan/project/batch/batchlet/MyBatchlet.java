package fr.dawan.project.batch.batchlet;

import javax.batch.api.AbstractBatchlet;
import javax.batch.runtime.BatchStatus;
import javax.inject.Named;

/**
 *Execution d'une step d'une job Batch 
 *une batchlet est une step simple donc elle ne suit pas le model reader/processeur/writer
 *
 */
@Named
//@RequestScoped // pour dire par ex que pour chaque execution de batch, nouvelle instance
public class MyBatchlet extends AbstractBatchlet {

	@Override
	public String process() throws Exception {
		System.out.println("Long running process (MyBatchlet::process)");
		return BatchStatus.COMPLETED.toString();
	}

	@Override
	public void stop() throws Exception {
		System.out.println("MyBatchlet stop");
	}
	

}

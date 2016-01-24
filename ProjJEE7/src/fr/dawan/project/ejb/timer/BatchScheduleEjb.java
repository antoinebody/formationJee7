package fr.dawan.project.ejb.timer;

import javax.ejb.LocalBean;
import javax.ejb.PostActivate;
import javax.ejb.Schedule;
import javax.ejb.Singleton;
import javax.ejb.Timer;

//par défaut local
@Singleton
public class BatchScheduleEjb {
	
	@PostActivate
	private void postActivate(){
		System.out.println("********* BatchScheduleEjb @PostActivate");
	}

   
	//tous les jours à 9h55
	@Schedule(hour="11",minute="*/30")
    private void launchJob(final Timer t) {
		//System.out.println("------- In ejb timer BatchScheduleEjb");
//		JobOperator jobOperator = BatchRuntime.getJobOperator();
//		long executionId = jobOperator.start("myJob", new Properties());
//		JobExecution jobExec = jobOperator.getJobExecution(executionId);
//		BatchStatus batchStatus = jobExec.getBatchStatus();
//		System.out.println("batch status " + batchStatus);
		
    }
	
}
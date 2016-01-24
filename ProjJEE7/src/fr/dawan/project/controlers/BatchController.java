package fr.dawan.project.controlers;

import java.io.IOException;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.Properties;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ScheduledFuture;

import javax.annotation.Resource;
import javax.batch.operations.JobOperator;
import javax.batch.runtime.BatchRuntime;
import javax.batch.runtime.BatchStatus;
import javax.batch.runtime.JobExecution;
import javax.enterprise.concurrent.LastExecution;
import javax.enterprise.concurrent.ManagedScheduledExecutorService;
import javax.enterprise.concurrent.Trigger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class JSONServlet
 */
@SuppressWarnings("serial")
@WebServlet("/BatchController")
public class BatchController extends HttpServlet {
	
	@Resource(name = "concurrent/__defaultManagedScheduledExecutorService")
	private ManagedScheduledExecutorService scheduledExecutorService;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		//Ex1 : lancement d un batch depuis le controler
//		JobOperator jobOperator = BatchRuntime.getJobOperator();
//		long executionId = jobOperator.start("myJob", new Properties());
//		JobExecution jobExec = jobOperator.getJobExecution(executionId);
//		BatchStatus batchStatus = jobExec.getBatchStatus();
//		resp.getWriter().append("batch status " + batchStatus);
		
		//Ex 2 : lancement d un batch depuis un EJB Timer
		//@See fr.dawan.project.ejb.timer.BatchScheduleEjb
		System.out.println("In Batch Controller");
		//Ex 3 lancement avec un executor
		Instant now = Instant.now();
		Instant targetInstant = now.plus(5, ChronoUnit.SECONDS);
		ScheduledFuture<BatchStatus> schedule = scheduledExecutorService.schedule(new BatchCallable(), new TriggerSingleDate(Date.from(targetInstant)));
		try {
			BatchStatus batchStatus = schedule.get();
			System.out.println("*********** " + batchStatus);
		} catch (InterruptedException | ExecutionException e) {
			e.printStackTrace();
		}
		
	}
	
	private class TriggerSingleDate implements Trigger{
		   private Date fireTime;
		      
		      public TriggerSingleDate(Date newDate) {
		          fireTime = newDate;
		      }

		      public Date getNextRunTime(
		         LastExecution lastExecutionInfo, Date taskScheduledTime) {
		         
		         if(taskScheduledTime.after(fireTime)) {
		             return null;
		         }
		         return fireTime;
		      }

		      public boolean skipRun(LastExecution lastExecutionInfo, Date scheduledRunTime) {
		          return scheduledRunTime.after(fireTime);
		      }

	}

	
	private class BatchCallable implements Callable<BatchStatus>{

		@Override
		public BatchStatus call() throws Exception {
			JobOperator jobOperator = BatchRuntime.getJobOperator();
			long executionId = jobOperator.start("myJob", new Properties());
			JobExecution jobExec = jobOperator.getJobExecution(executionId);
			BatchStatus batchStatus = jobExec.getBatchStatus();
			return batchStatus;
			
		}
		
	}
	

}

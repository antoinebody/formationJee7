package fr.dawan.project.controlers;

import java.io.IOException;
import java.util.Properties;

import javax.batch.operations.JobOperator;
import javax.batch.runtime.BatchRuntime;
import javax.batch.runtime.BatchStatus;
import javax.batch.runtime.JobExecution;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class JSONServlet
 */
@SuppressWarnings("serial")
@WebServlet("/BatchController2")
public class BatchController2 extends HttpServlet {
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		//Ex1 : lancement d un batch depuis le controler
		JobOperator jobOperator = BatchRuntime.getJobOperator();
		long executionId = jobOperator.start("myJob2", new Properties());
		JobExecution jobExec = jobOperator.getJobExecution(executionId);
		BatchStatus batchStatus = jobExec.getBatchStatus();
		resp.getWriter().append("batch status " + batchStatus);
		
}
	

}

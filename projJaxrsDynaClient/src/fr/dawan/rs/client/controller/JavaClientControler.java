package fr.dawan.rs.client.controller;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.core.MediaType;

import fr.dawan.rs.client.beans.CompagnieAerienne;

/**
 * Servlet implementation class JavaClientControler
 */
@WebServlet("/JavaClientControler")
public class JavaClientControler extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String exemple = request.getParameter("exemple");
		switch (exemple) {
		case "1":
			getXml(response,"http://localhost:8080/projetRS/rest/voyages",MediaType.APPLICATION_JSON);
			break;
		case "2":
			getXml(response,"http://localhost:8080/projetRS/rest/compagnies",MediaType.APPLICATION_JSON);
			break;
		case "3":
			postToWS(response,"http://localhost:8080/projetRS/rest/compagnies/add",MediaType.APPLICATION_JSON);
			break;
		default:
			break;
		}
	}

	private void postToWS(HttpServletResponse response, String urlStr, String mimeType) {
		URL url;
		try {

			url = new URL(urlStr);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			//pour envoyer l objet dans le output
			conn.setDoOutput(true);
			conn.setRequestMethod("POST");
			conn.setRequestProperty("Accept",mimeType );
			conn.setRequestProperty("Content-Type",mimeType );
			
			System.out.println("conn ok");
			//ecriture des parametres
			OutputStream os = conn.getOutputStream();
			CompagnieAerienne compagnieAerienne = new CompagnieAerienne(54l,"blulueluelu");
			os.write(JsonTools.toJsonStream(compagnieAerienne).getBytes());
			os.flush();
			
			
			if(conn.getResponseCode()!=200)
				throw new RuntimeException("failed htpp " + conn.getResponseCode());
			Scanner scan = new Scanner(new BufferedInputStream(conn.getInputStream()));
			StringBuffer sb = new StringBuffer();
			while(scan.hasNextLine()){
				sb.append(scan.nextLine());
				//System.out.println(scan.nextLine());
			}
			scan.close();
			response.getWriter().write(sb.toString());
			
		} catch (IOException e) {
			try {
				e.printStackTrace(response.getWriter());
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
		
	}

	private void getXml(HttpServletResponse response, String urlStr, String mimeType) {
		URL url;
		try {

			url = new URL(urlStr);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
			conn.setRequestProperty("Accept",mimeType );
			if(conn.getResponseCode()!=200)
				throw new RuntimeException("failed htpp " + conn.getResponseCode());
			Scanner scan = new Scanner(new BufferedInputStream(conn.getInputStream()));
			StringBuffer sb = new StringBuffer();
			while(scan.hasNextLine()){
				sb.append(scan.nextLine());
				//System.out.println(scan.nextLine());
			}
			scan.close();
			response.getWriter().write(sb.toString());
			
		} catch (IOException e) {
			try {
				e.printStackTrace(response.getWriter());
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			
		} 
		
	}

}

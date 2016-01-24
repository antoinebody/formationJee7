package fr.dawan.project.controlers;

import java.io.IOException;
import java.util.List;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.dawan.project.ejb.SearchEjb;
import fr.dawan.project.ejb.model.ResultatRecherche;

/**
 * Servlet implementation class JSONServlet
 */
@SuppressWarnings("serial")
@WebServlet("/SearchConcurrentController")
public class SearchConcurrentController extends HttpServlet {
	
	@Inject
	private SearchEjb ejb;

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("Enter doPost");
		String recherche = req.getParameter("searchfield");
		List<ResultatRecherche> res = ejb.search(recherche);
		req.setAttribute("recherche", recherche);
		req.setAttribute("resultat", res);
		req.getRequestDispatcher("resutatRecherche.jsp").forward(req, resp);
	}


}

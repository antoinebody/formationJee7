package fr.dawan.project.controlers;

import java.io.IOException;

import javax.enterprise.inject.Default;
import javax.enterprise.inject.spi.AnnotatedType;
import javax.enterprise.inject.spi.BeanManager;
import javax.enterprise.inject.spi.CDI;
import javax.enterprise.inject.spi.InjectionTarget;
import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.dawan.project.injection.IsbnGenerator;

@SuppressWarnings("serial")
@WebServlet("/CDIControler")
public class CDIControler extends HttpServlet {

	@Inject
	@Default
	private IsbnGenerator numberGen;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String isbn = numberGen.generateNumber();
		response.getWriter().append("isbn => " + isbn);

		/*****************************************************************************
		 * exemple pour détruire soit meme un bean géré par CDI (et donc le bean
		 * manager)
		 * *********************************************************************
		 */
		// on peut accéder au bean manager & manipuler des instances
		// interface : BeanManager et CreationalContext<T>
		BeanManager beanManager = CDI.current().getBeanManager();
		// CDI utilise un AnnotatedType object pour lire les annotations de la
		// classe
		AnnotatedType<IsbnGenerator> type = beanManager.createAnnotatedType(IsbnGenerator.class);
		InjectionTarget<IsbnGenerator> it = beanManager.createInjectionTarget(type);
		it.preDestroy(numberGen); // appel de la méthode preDestroy
		it.dispose(numberGen);
		System.out.println("numberGen supprime du BeanManager...");
		/*****************************************************************************
		 * fin exemple pour détruire soit meme un bean géré par CDI (et donc le
		 * bean manager)
		 * *********************************************************************
		 */

	}

}

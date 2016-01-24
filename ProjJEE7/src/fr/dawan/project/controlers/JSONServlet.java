package fr.dawan.project.controlers;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.json.JsonReader;
import javax.json.JsonWriter;
import javax.json.stream.JsonGenerator;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.dawan.project.model.Person;
import fr.dawan.project.tools.JsonTools;

/**
 * Servlet implementation class JSONServlet
 */
@WebServlet("/JSONController")
public class JSONServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private static final JsonObject JSON_PERSON = getJsonPerson();

	private static JsonObject getJsonPerson() {
		JsonArray phones = Json.createArrayBuilder().add("01111111").add("022222222").build();
		return Json.createObjectBuilder().add("prenom", "Antoine").add("nom", "Body").add("phones", phones).build();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String parameter = request.getParameter("callType");
		if (parameter == null) {
			response.getWriter().append("parameter callType is null!!!");
			return;
		}
		try {
			switch (parameter) {
			case "1":
				writeJSON(response);
				break;
			case "2":
				readJSONString(response);
				break;
			case "3":
				readJSONFromUrl(response);
				break;
			case "4":
				readJSONStreaming(response);
				break;
			default:
				break;
			}
		} catch (Exception e) {
			e.printStackTrace();
			response.getWriter().append("Error " + e.getMessage());
		}
	}

	private void writeJSON(HttpServletResponse response) throws IOException {
		response.setContentType("text/html");
		Map<String, Boolean> config = new HashMap<>();
		config.put(JsonGenerator.PRETTY_PRINTING, Boolean.TRUE);
		try (PrintWriter pw = response.getWriter();
				JsonWriter jsonWriter = Json.createWriterFactory(config).createWriter((pw));) {

			jsonWriter.writeObject(JSON_PERSON);

		}
	}

	private void readJSONString(HttpServletResponse response) throws IOException {
		response.setContentType("text/html");
		URL resource = Thread.currentThread().getContextClassLoader().getResource("json.txt");

		InputStream is = resource.openStream();
		JsonReader jsonReader = Json.createReader(is);
		
		JsonObject obj = jsonReader.readObject();
		PrintWriter pw = response.getWriter();
		pw.write("Nom : " + obj.getString("prenom"));
		is.close();
	}
	
	private void readJSONFromUrl(HttpServletResponse response) throws IOException {
		response.setContentType("text/html");
		URL resource = new URL("http://maps.googleapis.com/maps/api/geocode/json?address=11+rue+antoine+bourdelle+paris&sensor=false");
		InputStream is = resource.openStream();
		JsonReader jsonReader = Json.createReader(is);
		
		JsonObject obj = jsonReader.readObject();
		PrintWriter pw = response.getWriter();
		pw.write("Nom : " + obj.getString("address_components"));
		is.close();
	}

	private void readJSONStreaming(HttpServletResponse response) throws InstantiationException, IllegalAccessException, IOException {
		Person p = new Person("antoine", "body");
		String json = JsonTools.toJsonStream(p);
		Person fromJson = JsonTools.fromJson(json, p.getClass());
		PrintWriter pw = response.getWriter();
		pw.write("Egalité objets : " + (p == fromJson));
		pw.write(fromJson.getFirstName());
		
	}



}

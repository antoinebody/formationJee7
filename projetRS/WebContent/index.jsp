<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>WebServices with Jaxrs</title>
</head>
<body>
	<h2>Passage de paramètres</h2>
	<a href="rest/bases/Dupont">Appel simple GET avec @PathParam</a>
	<br/>
	<a href="rest/bases/2015/05/30">GET avec @Produces et multiples params</a>
	<br/>
	<a href="rest/bases/email?to=hall&from=antoine&to=john">GET avec queryparam</a>
	<br/>
	<a href="rest/bases/email2?to=hall&from=antoine&to=john">GET avec uriInfo</a>

	<br/>
	<a href="rest/bases/matrix/1999;acteur=keanu;heros=neo">GET avec MatrixParam</a>
	<h2>Recupération d un formulaire</h2>
	<form method="post" action="rest/bases/produit/add">
		<label for="desc">Description : </label>
		<input type="text" name="description" id="desc"/>
		<br />
		<label for="prix">Prix : </label>
		<input type="text" name="prix" id="prix"/>
		<br/>
		<input type="submit" value="GO"/>
	</form>
	
	<br/>
	<h2>Headers</h2>
	<a href="rest/bases/user-agent">User agent</a>
	<br/>
	<a href="rest/bases/headers">All headers</a>
	<br/>
	<h3>Download </h3>
	<a href='rest/bases/download-file'> Test download</a>
	<br/>
	<h3>Upload (annotation @Consumes)</h3>
	<!-- 1) ajouter le support dans le configurator dans MyApplication -->
	<form method="post" action="rest/bases/upload" enctype="multipart/form-data">
		<input type="file" name="file" id="f"/> 
		<br/>
		<input type="submit" value="Upload"/>
	</form>
	
	<br/>
	<h3>Manipulation d objets (voyages)</h3>
	<a href="rest/voyages/1">Récup d un voyage</a>
	<br/>
	<a href="rest/voyages">Récup d une liste de voyages</a>
	<br/>
	<a href="rest/voyages/add?ville_depart=paris&villeArrivee=londres&datedepart=13-01-2016">Ajouter un voyage (queryparam)</a>
	<br/>
	<a href="rest/voyages/delete?voyage_id=2">Supprimer un voyage</a>
	<br/>
	<a href="rest/voyages/modify?voyage_id=1&ville_depart=paris&villeArrivee=singap&datedepart=13-01-2016">modifier voyage</a>
	
	<h3>Manipulation d objets (compagnies)</h3>
	<a href="rest/compagnies/1">Récup d une compagnie</a>
	<br/>
	<a href="rest/compagnies">Récup all compagnies</a>
	<br/>
	
</body>
</html>
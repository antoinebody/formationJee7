<?xml version="1.0" encoding="utf-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!doctype html>
<html lang="fr">
<head>
<meta charset="utf-8">
<title>projet jee7</title>
</head>
<body>
	<h3>json-p</h3>
	<a title="json-p" href="JSONController?calltype=1">traitement json</a>
	<br/>
	
	<h3>cdi</h3>
	<a title="json-p" href="CDIControler">ioc (cdi1.1)</a>
	<br/>
	<h3>concurrent resources</h3>
	<a title="concurrent resource" href="SearchConcurrentController">gestion de la concurrence coté (coté contener j2ee)</a>
	<br/>
	<form method="post" action="SearchConcurrentController">
		<label for="search">recherche </label>
		<input type="text" name="searchfield" id="search" />
		<br />
		<input type="submit" value="lancer la recherche"/>
	</form>
	<br />
	<h3>Batch Processing for batchlet</h3>
	<a title="batchProcessing" href="BatchController">batch controller</a>
	<br/>
	
	<h3>Batch Processing for chunk</h3>
	<a title="batchProcessing" href="BatchController2">Processing for chunk</a>
	<br/>
	
	<h3>JMS sender</h3>
	<a title="batchProcessing" href="JMSController">Test JMS</a>
	<br/>
	
	<h3>JPA </h3>
	<a title="JPA" href="JPAController">JPA (implementation EclipsLink) </a>
		<br/>
	
	
</body>
</html>
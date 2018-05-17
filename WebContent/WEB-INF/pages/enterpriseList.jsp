<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="css/reset.css">
<link rel="stylesheet" href="css/site.css">
<title>Enterprise List page</title>
</head>
<body>

	<main>
	<h1 class="titulo-principal">Enterprise List</h1>

	<div class="container">

		<h2 class="subtit ulo-texto">Exhibiting a list of all Enterprise
			inserted into database!</h2>

		<ul>
			<c:forEach var="enterprise" items="${enterprises}">
				<li>${enterprise.id}:${enterprise.nome}</li>
			</c:forEach>
		</ul>
		
	</div>
	</main>
	
	<aside class="navegacao-site">
		<h1>Other</h1>
		<nav>
			<ul>
				<li><a href="http://localhost:8080/manager">Home</a></li>
				<li>DataBase</li>
				<li>Graph</li>
			</ul>
		</nav>
	</aside>
	<footer class="rodape-pagina"> &copy; Renan Rosa 2018 </footer>
</body>
</html>
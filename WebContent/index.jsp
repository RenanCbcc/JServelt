<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="css/reset.css">
<link rel="stylesheet" href="css/site.css">
<title>Web Manager</title>
</head>
<body>
	<main>
	<h1 class="titulo-principal">Alexandria's Library</h1>
	<div class="container">
		<h2 class="subtitulo-texto">Welcome to Alexandria's Library the
			greatest library of the ancient world!</h2>
		<p>I built the web page to practice my knowledge's programming in
			Java Web, HTML,CSS and MySQL database. This project intent to be
			simple in order to help other students or curious to theirs own
			knowledge base on that areas. Furthermore, I also hope the anyone
			interested in this project may fork it an improve it.</p>

		<h2 class="subtitulo-texto">How this project is organized?</h2>
		<p>
			The database content was retrieve from the book <a target="_blank"
				href="http://a.co/fMrG07a"> Mathematical Structures for Computer
				Science </a> . In spite of the book is not as comprehensible as it
			should, the book has been chosen due to its useful exercises and
			accessible responses at the section "selected answers".
		</p>

		<h2 class="subtitulo-texto">The Database</h2>
		<p>You can install the MySQL client as follow:</p>
		<p>sudo apt-get install</p>
		<p>mysql -u root -p</p>
		<p>After this, populate the database running the command</p>

		<p>The database's diagram will look like the image below:</p>

		<img src="img/db.png" alt="DataBase image Diagram">
		<h2 class="subtitulo-texto">How execute a search?</h2>
		<p>
			To see whether the server is working as as expected,run the command
			on you browser:
			<site> http://localhost:8080/manager/search?filter=Alura </site>
		</p>
		
		<h2>
		New Enterprise
		</h2>
		<p>Now, I want to add a new enterprise, to make sure that all that stuff is actually
		working and it is not silly or a complete waste of time.
		<a href="formEnterprise.html" >Nova Empresa</a>
		</p>
		
		<h2>
		Sing In
		</h2>
		<p>Now, I want to <a href="formLogin.html" >login</a>
		</p>
		
		<h2>
		Logout
		</h2>
		<p>Now, I want to <a href="formLogout.html" >logout</a>
		</p>
		
		
		
		
	</div>
	</main>
	<aside class="navegacao-site">
		<h1>Learn more</h1>
		<c:if test="${not empty userLoggedin}">
		Logged as ${userLoggedin.email}
		</c:if>
		
		<nav>
			<ul>
				<li>Programming</li>
				<li>DataBase</li>
				<li>Graph</li>
			</ul>
		</nav>
	</aside>
	<footer class="rodape-pagina"> &copy; Renan Rosa 2018 </footer>
</body>
</html>

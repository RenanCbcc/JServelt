<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="css/reset.css">
<link rel="stylesheet" href="css/site.css">
<title>Enterprise page</title>
</head>
<body>

	<main>
	<h1 class="titulo-principal">Enterprise</h1>

	<div class="container">

		<h2 class="subtitulo-texto">A new Enterprise was inserted into
			database!</h2>
		<p>${enterprise.getNome()}</p>

	</div>
	</main>
<aside class="navegacao-site">
		<h1>Learn more</h1>
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
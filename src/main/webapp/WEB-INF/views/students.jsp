<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page isELIgnored="false"%>
<!doctype html>
<html lang="en">
<head>
<!-- Required meta tags -->
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">

<!-- Bootstrap CSS -->
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css"
	integrity="sha384-TX8t27EcRE3e/ihU7zmQxVncDAy5uIKz4rEkgIXeMed4M0jlfIDPvg6uqKI2xXr2"
	crossorigin="anonymous">

<title>Hello, world!</title>
</head>
<body>
	<!-- Optional JavaScript; choose one of the two! -->

	<!-- Option 1: jQuery and Bootstrap Bundle (includes Popper) -->
	<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"
		integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj"
		crossorigin="anonymous"></script>
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/js/bootstrap.bundle.min.js"
		integrity="sha384-ho+j7jyWK8fNQe+A12Hb8AhRq26LrZ/JpcUGGOn+Y7RsweNrtN/tE3MoK7ZeZDyx"
		crossorigin="anonymous"></script>

	<!-- Option 2: jQuery, Popper.js, and Bootstrap JS
    <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js" integrity="sha384-9/reFTGAW83EW2RDu2S0VKaIzap3H66lZH81PoYlFhbGU+6BZp6G7niu735Sk7lN" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/js/bootstrap.min.js" integrity="sha384-w1Q4orYjBQndcko6MimVbzY0tgp4pWB4lZ7lr30WKz0vr/aWKhXdBNmNb5D92v7s" crossorigin="anonymous"></script>
    -->

	<nav
		class="navbar navbar-expand-lg navbar-light bg-light navbar-dark bg-dark">
		<a class="navbar-brand" href="/librarymanagement">Libro-Pro</a>
		<button class="navbar-toggler" type="button" data-toggle="collapse"
			data-target="#navbarSupportedContent"
			aria-controls="navbarSupportedContent" aria-expanded="false"
			aria-label="Toggle navigation">
			<span class="navbar-toggler-icon"></span>
		</button>

		<div class="collapse navbar-collapse" id="navbarSupportedContent">
			<ul class="navbar-nav mr-auto">
				<c:if test="${user ne null|| librarian ne null }">
					<li class="nav-item"><a class="nav-link"
						href="/librarymanagement/profile">profile</a></li>
				</c:if>
				<li class="nav-item "><a class="nav-link"
					href="/librarymanagement/books">Books</a></li>
				<li class="nav-item"><a class="nav-link"
					href="/librarymanagement/issuedbooks">Issued Books</a></li>
				<c:if test="${admin ne null }">
					<li class="nav-item"><a class="nav-link"
						href="/librarymanagement/librarians">librarians</a></li>
				</c:if>
				<c:if test="${admin ne null || librarian ne null }">
					<li class="nav-item active"><a class="nav-link"
						href="/librarymanagement/students">students</a><span
						class="sr-only">(current)</span></li>
				</c:if>
			</ul>
			<a href="logout">
				<button class="btn btn-outline-success my-2 my-sm-0">Log
					Out</button>
			</a>

		</div>
	</nav>
	<c:if test="${ signal ne null }">
		<div class="alert alert-warning alert-dismissible" role="alert">
			<span type="button" class="close" data-dismiss="alert"
				aria-label="Close"><span aria-hidden="true">&times;</span></span> <strong>
				${signal }</strong>
		</div>
	</c:if>

	<!-- Search tab -->
	<div class="list-group">
		<h1>
			<label style="color: green; position: relative; left: 50%;">Students
			</label>
		</h1>
		<form class="form-inline my-2 my-lg-0 "
			style="justify-content: center;">
			<input class="form-control mr-sm-2 " type="search"
				placeholder="Search" aria-label="Search" style="width: 50%;"
				name="query" value="search students">
			<button class="btn btn-outline-success my-2 my-sm-0" type="submit">Search</button>
		</form>






		<c:forEach begin="0" end="${qresult.size()}" var="q"
			items="${qresult}">


			<div class="card border border-success">
				<h5 class="card-header   bg-info text-white">${q.getName()}</h5>
				<div class="card-body">
					<h5 class="card-title">
						<strong>Address: ${q.address}</strong><br> <strong>University:
						</strong>${q.getUniversityName()}
					</h5>
					<h5 class="card-title">
						<strong>Email: </strong> ${q.email }
					</h5>
					<div>
						<p class="card-text">
						<h5>
							<strong>Gender: </strong>${q.sex }</h5>
						<form action="removestudent" method="GET">
							<input type="hidden" id="id" name="studentid" value="${q.email }">
							<button class="btn btn-danger btn-lg" type="submit"
								style="position: absolute; right: 0px; top: 0px; padding: 10px;">remove</button>
						</form>
						<c:if test="${admin ne null }">
							<form action="student/updateprofile" method="GET">
								<input type="hidden" id="librarianid" name="id"
									value="${q.email }">
								<button class="btn btn-success btn-lg" type="submit"
									style="position: absolute; right: 120px; top: 0px; padding: 10px, 20px;">edit</button>


							</form>
						</c:if>
					</div>
				</div>
			</div>
		</c:forEach>
	</div>






</body>
</html>
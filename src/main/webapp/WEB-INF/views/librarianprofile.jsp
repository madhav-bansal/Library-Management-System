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
			
		</button>


		<div class="collapse navbar-collapse" id="navbarSupportedContent">
			<ul class="navbar-nav mr-auto">
				<li class="nav-item active"><a class="nav-link"
					href="/librarymanagement/profile">profile</a></li>

				<li class="nav-item "><a class="nav-link"
					href="/librarymanagement/books">Books</a></li>
				<li class="nav-item"><a class="nav-link"
					href="/librarymanagement/issuedbooks">Issued Books</a>></li>
				<c:if test="${admin ne null }">
					<li class="nav-item"><a class="nav-link"
						href="/librarymanagement/librarians">librarians</a></li>
				</c:if>
				<c:if test="${admin ne null || librarian ne null }">
					<li class="nav-item"><a class="nav-link"
						href="/librarymanagement/students">students</a></li>
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
	<div class="container col-md-8">

		<div class="row">
			<div class=" col-md-11 toppad">

				<div class="panel panel-info">
					<div class="panel-heading">
						<h3 class="panel-title text-info ">
							<strong>${librarian.name }</strong>
						</h3>
					</div>
					<br>
					<div class="panel-body">
						<div class="row">

							<div class=" col-md-9 col-lg-9 ">
								<table class="table table-user-information">
									<tbody>
										<tr>
											<td>Name:</td>
											<td>${librarian.name}</td>
										</tr>

										<tr>
											<td>email:</td>
											<td>${librarian.email}</td>
										</tr>
										<tr>
											<td>Address:</td>
											<td>${librarian.address}</td>
										</tr>
										<tr>
											<td>Phone Number:</td>
											<td>${librarian.mobileNumber}</td>

										</tr>
										<tr>
											<td>Password:</td>
											<td>${librarian.password}</td>
										</tr>
										<tr>
											<td>sex:</td>
											<td>${librarian.sex}</td>

										</tr>

									</tbody>
								</table>
								<div class="container">
									<div class="row">
										<div class="col">
											<form action="updateprofile">
												<input type="hidden" name="id" value="${librarian.email }">
												<a href="updateprofile"> <input type="submit"
													value="edit" name="submit" class="btn btn-success btn-lg">
												</a>
											</form>
										</div>

									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>

		<br>
</body>
</html>
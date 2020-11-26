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
				<li class="nav-item"><a class="nav-link" href="#">Home</a> <span
					class="sr-only">(current)</span></li>

				<li class="nav-item active"><a class="nav-link" href="addbook">Books
						Book</a></li>
				<li class="nav-item"><a class="nav-link" href="#">Link</a></li>
				<li class="nav-item"><a class="nav-link" href="#">Link</a></li>

			</ul>

			<button class="btn btn-outline-success my-2 my-sm-0">
				<a href="/librarymanagement/librarian/logout">Log Out</a>
			</button>

		</div>
	</nav>
	<c:if test="${ signal ne null }">
		<div class="alert alert-warning alert-dismissible" role="alert">
			<span type="button" class="close" data-dismiss="alert"
				aria-label="Close"><span aria-hidden="true">&times;</span></span> <strong>
				${signal }</strong>
		</div>
	</c:if>


	<!-- form starts here -->
	<div class="container ml-3 mt-3">
		<label><strong>Email: ${lib.email }</strong></label>
		<form action="updateprofile" method="POST">
			<div class="form-group">
				<label for="formGroupExampleInput">Name</label> <input type="text"
					class="form-control" name="name" value="${lib.name }">
			</div>
			<div class="form-group">
				<label for="formGroupExampleInput2">E-mail</label> <input
					name="email" type="text" class="form-control"
					id="formGroupExampleInput2" value="${lib.email }" readonly>
			</div>
			<div class="form-group">
				<label for="formGroupExampleInput2">password</label> <input
					name="password" type="text" class="form-control"
					id="formGroupExampleInput2" value="${lib.password }">
			</div>
			<div class="form-group">
				<label for="formGroupExampleInput2">mobile number</label> <input
					name="mobileNumber" type="tel" class="form-control"
					id="formGroupExampleInput2" value="${lib.mobileNumber }">
			</div>
			<div class="form-group">
				<label for="formGroupExampleInput2">Address</label> <input
					name="address" type="text" class="form-control"
					id="formGroupExampleInput2" value="${lib.address }">
			</div>
			<div class="form-group">
				<label for="formGroupExampleInput2">Gender</label> <input
					name="sex" type="text" class="form-control"
					id="formGroupExampleInput2" value="${lib.sex}"readonly>
			</div>
			<div class="form-group">
				<button type="submit" class="btn btn-primary btn-block">
					update</button>
			</div>
		</form>
	</div>




</body>
</html>
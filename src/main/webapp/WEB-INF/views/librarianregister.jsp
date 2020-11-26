<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<link
	href="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"
	rel="stylesheet" id="bootstrap-css">
<script
	src="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
<script
	src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script>
	function validateForm() {
		var form = document.getElementById("form");
		
	}
</script>
<!------ Include the above in your HEAD tag ---------->

<link rel="stylesheet"
	href="https://use.fontawesome.com/releases/v5.0.8/css/all.css">

<div class="p-3 mb-2 bg-primary text-white" style="text-align: center;">
	<h3>Register Librarian!</h3>
</div>
<div class="container">



	<div class="row justify-content-center">
		<div class="col-md-6">
			<div class="card">
				<header class="card-header">
					<a href="login" class="float-right btn btn-outline-primary mt-1">Log
						in</a>
					<h4 class="card-title mt-2">Sign up</h4>
				</header>
				<article class="card-body">
					<form id="form" action="register" method="post"
						onsubmit="return validateForm()">
						<!-- 		name -->
						<div class="form-row">
							<div class="col form-group">
								<label>Name </label> <input name="Name" type="text"
									class="form-control" placeholder="" required>
								<div class="invalid-feedback">Please choose a username.</div>
							</div>

							<!-- form-group end.// -->
						</div>
						<!-- form-row end.// -->
						<div class="form-group">
							<label>Email address</label> <input name="email" type="email"
								class="form-control" placeholder="" required> <small
								class="form-text text-muted">We'll never share your
								email with anyone else.</small>
						</div>
						<!-- form-group end.// -->
						<div class="form-group" >
							<label class="form-check form-check-inline"> <input
								class="form-check-input" type="radio" name="gender" value="M" required>
								<span class="form-check-label"> Male </span>
							</label> <label class="form-check form-check-inline"> <input
								class="form-check-input" type="radio" name="gender" value="F">
								<span class="form-check-label"> Female</span>
							</label>
						</div>
						<!-- form-row end.// -->
						<div class="form-group">
							<label>Mobile-number (don't add +91)</label> <input type="tel"
								name="mobileNumber" class="form-control" placeholder="" pattern="[0-9]{10}"required>
						</div>
						<!-- form-group end.// -->
						<!-- form-row end.// -->
						<div class="form-group">
							<label>Address</label> <input type="text" name="address"
								class="form-control" placeholder="" required>
						</div>
						<!-- form-group end.// -->


						<div class="form-group">
							<label>Create password</label> <input class="form-control"
								name="password" type="password" required>
						</div>
						<!-- form-group end.// -->


						<div class="form-group">
							<button type="submit" class="btn btn-primary btn-block">
								Register</button>
						</div>



						<!-- form-group// -->
						<small class="text-muted">By clicking the 'Sign Up'
							button, you confirm that you accept our <br> Terms of use
							and Privacy Policy.
						</small>
					</form>
				</article>
				<!-- card-body end .// -->
				<div class="border-top card-body text-center">
					Have an account? <a href="">Log In</a>
				</div>
			</div>
			<!-- card.// -->
		</div>
		<!-- col.//-->

	</div>
	<!-- row.//-->


</div>
<!--container end.//-->

<br>
<br>

<article class="bg-secondary mb-3">
	<div class="card-body text-center">
		<h3 class="text-white mt-3">Libro-pro</h3>
		<p class="h5 text-white">Weediogamers product</p>


	</div>
	<br> <br>
</article>
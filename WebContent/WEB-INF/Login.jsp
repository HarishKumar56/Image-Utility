<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<link rel='stylesheet' type='text/css' media='screen'
	href='${pageContext.request.contextPath}/css/bootstrap.css'>
<link rel='stylesheet' type='text/css' media='screen'
	href='${pageContext.request.contextPath}/css/style.css'>
<meta charset="ISO-8859-1">
<title>Image Utility</title>
</head>
<body>
	<%
		boolean invalid = false;
		if (request.getAttribute("invalid") != null) {
			invalid = (boolean) request.getAttribute("invalid");
		}
	%>

	<div class="container">
		<div class="card mx-auto vcenter ">
			<div class="card-header">
				<h2 class="card-title">Login</h2>
			</div>
			<div class="card-body">
				<c:if test="${invalid}">
					<div class="alert alert-danger" role="alert">Invalid
						Email/Password .......</div>
				</c:if>

				<form action="${pageContext.request.contextPath}/login"
					method="post">
					<div class="form-group">
						<label for="exampleInputEmail1">Email address</label> <input
							name="email" required type="email" class="form-control"
							id="exampleInputEmail1" aria-describedby="emailHelp"
							placeholder="Enter email">
					</div>
					<div class="form-group">
						<label for="exampleInputPassword1">Password</label> <input
							required name="password" type="password" class="form-control"
							id="exampleInputPassword1" placeholder="Password">
					</div>
					<button type="submit" class="btn btn-primary">Login</button>
				</form>
			</div>

			<div class="card-footer">
				<div class="d-flex justify-content-center links">
					Don't have an account?<a
						href="${pageContext.request.contextPath}/register">Sign Up</a>
				</div>
				<div class="d-flex justify-content-center">
					<a href="${pageContext.request.contextPath}/reset">Forgot your
						password?</a>
				</div>
			</div>

		</div>
	</div>




</body>
</html>
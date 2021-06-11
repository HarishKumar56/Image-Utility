<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>

<link rel='stylesheet' type='text/css' media='screen'
	href='${pageContext.request.contextPath}/css/bootstrap.css'>
<link rel='stylesheet' type='text/css' media='screen'
	href='${pageContext.request.contextPath}/css/style.css'>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<meta charset="ISO-8859-1">
<title>Image Utility</title>
</head>
<body>

	<%
		String name = request.getAttribute("name").toString();
		boolean userExist = false;
		if (request.getAttribute("userExist") != null) {
			userExist = (boolean) request.getAttribute("userExist");
		}

		boolean userNotExist = false;
		if (request.getAttribute("userNotExist") != null) {
			userNotExist = (boolean) request.getAttribute("userNotExist");
		}
	%>

	<div class="container">
		<div class="card mx-auto vcenter ">
			<div class="card-header">
				<h2 class="card-title"><%=name.toUpperCase()%></h2>
			</div>
			<div class="card-body">
				<c:if test="${userExist}">
					<div class="alert alert-danger" role="alert">User Does Not
						Exist</div>
				</c:if>
				<c:if test="${userNotExist}">
					<div class="alert alert-danger" role="alert">User Already
						Registered</div>
				</c:if>
				<form action="${pageContext.request.contextPath}/${name}"
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
					<button type="submit" class="btn btn-primary"><%=name.toUpperCase()%></button>
				</form>
			</div>

		</div>
	</div>


</body>
</html>
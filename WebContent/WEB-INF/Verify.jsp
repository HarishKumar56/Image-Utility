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
				<h2 class="card-title">Verify OTP</h2>
			</div>
			<div class="card-body">
				<c:if test="${invalid}">
					<div class="alert alert-danger" role="alert">Invalid OTP
						.......</div>
				</c:if>
				<form action="${pageContext.request.contextPath}/verify"
					method="post">
					<div class="form-group">
						<label for="exampleInputEmail1">OTP</label> <input name="inOtp"
							required type="number" class="form-control"
							placeholder="Enter the OTP">
					</div>
					<button type="submit" class="btn btn-primary">Verify</button>
				</form>
			</div>

			<div class="card-footer">
				<div class="d-flex justify-content-center links">
					Don't recieved OTP?<a
						href="${pageContext.request.contextPath}/verify">Re-Send</a>
				</div>
			</div>

		</div>
	</div>

</body>
</html>

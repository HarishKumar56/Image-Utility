<%@page import="org.apache.tomcat.util.codec.binary.Base64"%>
<%@page import="java.util.List"%>
<%@page import="org.nagarro.model.ImageFile"%>
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
		List<ImageFile> allImages = (List<ImageFile>) request.getAttribute("images");
		int i = 0;
		String name = "";
		long size = 0;
		String img = "";
		long total = 10240;
		long usedCap = (long) request.getAttribute("capacity");
		long available = total - usedCap;
		double percent = (usedCap * 100) / total;
		String message = (String) request.getAttribute("message");
	%>

	<header class="p-1">
		<h2 class="text-center">Image Management Utility</h2>
		<hr />
	</header>




	<div class="card m-1 mx-auto" style="width: 25rem;">
		<div class="card-header">
			<h4 class="card-title">Upload Image</h4>
		</div>
		<div class="card-body">
			<%
				if (message != null) {
			%>
			<div class="alert alert-danger" role="alert"><%=message%></div>


			<%
				}
			%>
			<form action="${pageContext.request.contextPath}/home?action=upload"
				method="post" enctype="multipart/form-data">

				<div class="form-group">
					<input name="name" required type="text" class="form-control"
						id="exampleInputEmail1" aria-describedby="emailHelp"
						placeholder="Name for Image">
				</div>
				<div class="input-group mb-3">
					<div class="custom-file">
						<input type="file" name="image" class="custom-file-input"
							id="customFileInput"> <label class="custom-file-label"
							for="customFileInput">Choose file</label>
					</div>
				</div>


				<button id="customFileInput" type="submit" class="btn btn-primary">Upload</button>
			</form>
		</div>

	</div>

	<div class="container text-center">
		<h4 class="m-4">Uploaded Images</h4>



		<table class="table table-bordered mx-auto">
			<tr>
				<th>S.No</th>
				<th>Name</th>
				<th>Size(KB)</th>
				<th>Preview</th>
				<th>Action</th>
			</tr>
			<%
				for (ImageFile image : allImages) {
					System.out.println(image.getFileName());
					name = image.getFileName();
					size = image.getSize();
					i++;
					img = Base64.encodeBase64String(image.getImage());
			%>
			<tr>
				<th><%=i%></th>
				<td><%=name%></td>
				<td><%=size%></td>
				<td><img src="data:image/png;base64,<%=img%>"
					style="width: 300px;"></td>
				<td>
					<div class="">
						<form
							action="${pageContext.request.contextPath}/home?action=update"
							method="post">
							<input type="hidden" name="image" value=<%=image.getImageId()%>>
							<button type="submit" class="m-4 btn btn-warning">Update</button>
						</form>
						<form
							action="${pageContext.request.contextPath}/home?action=delete"
							method="post">
							<input type="hidden" name="image" value=<%=image.getImageId()%>>
							<button type="submit" class="m-4 btn btn-danger">Delete</button>
						</form>

					</div>
				</td>
			</tr>
			<%
				}
			%>
		</table>

	</div>


	<div class="container p-4">
		<p>
			Total Capacity :
			<%=total%></p>
		<p>
			Used Capacity :
			<%=usedCap%></p>
		<p>
			Available Capacity :
			<%=available%></p>
		<div class="progress">
			<div class="progress-bar" role="progressbar"
				style="width: <%=percent%>%;" aria-valuenow="<%=usedCap % 100%>"
				aria-valuemin="0" aria-valuemax="100"><%=percent%>%
			</div>
		</div>
	</div>
</body>

<script>
  document.querySelector('.custom-file-input').addEventListener('change', function (e) {
    var name = document.getElementById("customFileInput").files[0].name;
    var nextSibling = e.target.nextElementSibling
    nextSibling.innerText = name
  })
</script>
</html>
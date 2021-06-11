<%@page import="org.apache.tomcat.util.codec.binary.Base64"%>
<%@page import="org.nagarro.model.ImageFile"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
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
		ImageFile image = (ImageFile) request.getAttribute("image");
		String name = image.getFileName();
		byte[] byteImage = image.getImage();
		String encodedImage = Base64.encodeBase64String(byteImage);
		int id = image.getImageId();
		String message = (String) request.getAttribute("message");
	%>

	<header class="p-1">
		<h2 class="text-center">Image Management Utility</h2>
		<hr />
	</header>




	<div class="card mx-auto vcenter" style="width: 25rem;">
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
			<form action="${pageContext.request.contextPath}/home?action=save"
				method="post" enctype="multipart/form-data">

				<div class="form-group">
					<input name="name" required type="text" class="form-control"
						id="exampleInputEmail1" aria-describedby="emailHelp"
						value="<%=name%>">
				</div>
				<div class="input-group mb-3">
					<div class="custom-file">
						<input type="file" name="image" class="custom-file-input"
							id="customFileInput"> <label class="custom-file-label"
							for="customFileInput">Choose file to change</label>
					</div>
				</div>
				<input type="hidden" name="imageId" value="<%=id%>">



				<button type="submit" id="customFileInput" class="btn btn-primary">Update</button>
			</form>


		</div>
		<div class="card-footer text-center">
			<h4>Original Image</h4>
			<img src="data:image/png;base64,<%=encodedImage%>"
				style="width: 300px;">
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
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.List"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://ckeditor.com" prefix="ckeditor"%>

<%@ page import="mvcpackage.model.bean.Category"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>ClaireBlogs</title>
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/assets/css/main.css" />
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3"
	crossorigin="anonymous">
</head>
<body>
	<%
	List<Category> categories = (List<Category>) session.getAttribute("countCategory");
	String username = (String) session.getAttribute("username");
	%>
	<div class="container">
		<div class=" header">
			<div class="header-menu">
				<div class="logo-div">
					<a href="/ClaireBlogs/post?action=listRecentPost"
						class="btn btn-secondary"> ClaireBlogs </a>
				</div>
				<div class="menu-bar">
					<ul class="menu-bar-list">
						<a href="/ClaireBlogs/post?action=listPost">
							<li class="menu-bar-item">ALL POSTS</li>
						</a>

						<li class="menu-bar-item has-menu">CATEGORIES
							<ul class="second-menu-bar">
								<%
								for (Category category : categories) {
								%>
								<a
									href="/ClaireBlogs/post?action=<%=category.getCategoryName().toLowerCase()%>">
									<li class="second-menu-li"><%=category.getCategoryName()%></li>
								</a>
								<%
								}
								%>

							</ul>
						</li>

						<a href="/ClaireBlogs/about">
							<li class="menu-bar-item">ABOUT PAGE</li>
						</a>
						<%
						if (username != null) {
						%>
						<a href="/ClaireBlogs/admin">
							<li class="menu-bar-item" id="login-button">ADMIN</li>
						</a>
						<%
						} else {
						%>
						<a href="/ClaireBlogs/login">
							<li class="menu-bar-item" id="login-button">LOGIN</li>
						</a>
						<%
						}
						%>
					</ul>
				</div>
			</div>



			<!--Recent post-->


			<div class="category-header">
				<h2 class="">
					<c:out value="${listCategory}" />
				</h2>
				<div class="profile-bar">
					<form action="/ClaireBlogs/search" method="GET">
						<input class="mr-3 search-btn desktop-search-btn" type="search"
							name="q" placeholder="Search" value="" />
					</form>
				</div>
			</div>
			<div class="admin-mode row">
				<div class="admin-left col-8 mr-20">
					<form action="admin?action=addNewPost" method="get"
						id="textareaForm">
						<label for="title" class="form-label"><b>Title*</b></label> <input
							type="text" class="form-control" placeholder="Enter Title"
							id="title" name="title" value="${selectedPost.getTitle()}"
							required> <br> <label for="category"
							class="form-label"><b>Category*</b></label> <input type="text"
							class="form-control" placeholder="Enter category" id="category"
							name="category" value="${selectedPost.getCategory()}" required><br>
						<label for="dateIssue" class="form-label"><b>Date
								Issue(YY-MM-DD)*</b></label> <input type="text" class="form-control"
							placeholder="Enter date" id="dateIssue" name="dateIssue"
							value="${selectedPost.getDateIssue()}" required> <br>
						<label for="imageUrl" class="form-label"><b>ImageUrl</b></label> <input
							type="text" placeholder="Type ImageUrl" class="form-control"
							id="imageUrl" name="imageUrl"
							value="${selectedPost.getImageUrl()}"><br> <label
							for="preview" class="form-label"><b>Preview</b></label> <input
							class="form-control" type="text" placeholder="Enter Preivew"
							id="preview" name="preview" class="form-label"
							value="${selectedPost.getPreview()}" required><br> <input
							type="submit" class="btn btn-primary"><br> <br>
					</form>
					<textarea name="textareaId" id="textareaId" form="textareaForm">Type your content in this box and click "Submit"</textarea>
					<ckeditor:replace replace="textareaId"
						basePath="${pageContext.request.contextPath}/assets/ckeditor" />
				</div>


				<%
				if (username != null) {
				%>
				<div class="admin-right col-4">
					<ul class="list-group">
						<a href="/ClaireBlogs/admin?action=createNewPost">
							<li class="list-group-item">Create new post</li>
						</a>
						<a href="/ClaireBlogs/category?action=createNewCategory">
						<li class="list-group-item" onclick="addCategory()">Create
							new Category</li></a>
						<a href="/ClaireBlogs/admin?action=editAboutPage">
							<li class="list-group-item">Edit About Page</li>
						</a>
						<a href="/ClaireBlogs/logout">
							<li class="list-group-item">Log out</li>
						</a>
					</ul>
				</div>
				<%
				}
				%>

			</div>
		</div>
	</div>
</body>
</html>
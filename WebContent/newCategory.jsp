<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="java.util.List"%>
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
				<div class="admin-left col-8">
					
		<form action="category?action=newCategory" method="post">
			<label for="newCategory" class="form-label"><b>New
					Category</b></label> <input class="form-control" type="text"
				placeholder="Enter New Category" id="newCategory" name="newCategory"
				required><br> <input type="submit"
				class="btn btn-primary">
		</form>
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
	<script src="${pageContext.request.contextPath}/assets/js/main.js" />
</body>
</html>
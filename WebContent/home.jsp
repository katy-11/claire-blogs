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
					<a href="/ClaireBlogs/home" class="btn btn-secondary"> ClaireBlogs </a>
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
			<c:forEach var="p" items="${listPost}">
				<div class="post">
					<div class="image">
						<img src="<c:out value="${p.getImageUrl()}" />" />
					</div>
					<div class="post-content">
						<div class="post-title">
							<a href="/ClaireBlogs/post?id=${p.getPid()}"> <c:out
									value="${p.getTitle()}" /></a>
						</div>
						<div class="post-category">
							<a href="/ClaireBlogs/post?action=${p.getCategory().toLowerCase()}"><c:out value="${p.getCategory()}" /></a><span class="ml-3"><c:out
									value="${p.getDateIssue()}" /></span>
						</div>
						<div class="post-preview">
							<c:out value="${p.getPreview()}" />
						</div>
					</div>
				</div>
			</c:forEach>


		</div>
	</div>
	<script src="${pageContext.request.contextPath}/assets/js/main.js" />
</body>
</html>
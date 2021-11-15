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
	String content = (String) session.getAttribute("content");
	%>
	<div class="container">
		<div class=" header">
			<div class="header-menu">
				<div class="logo-div">
					<a href="/ClaireBlogs/home" class="btn btn-secondary">
						ClaireBlogs </a>
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

			<div class="row">
				<div class="about-image "></div>
				<div class="about-body ">
					<h1 style="text-align: center;">About us</h1>
					<%
					if (content != null) {
					%>
					<c:out value="${content}"></c:out>
					<%
					} else {
					%>
					<p class="about-intro" style="text-align: center;">Name: Thi
						Huyen Ho</p>
					<p class="about-intro" style="text-align: center;">Student ID:
						1507635</p>
					<p class="about-desc">
						<span class="span-bold">Salter-Harris</span><span> Type IV
							physeal fracture of lower end of unspecified tibia, subsequent
							encounter for fracture with nonunion. Displaced midcervical
							fracture of unspecified femur, initial encounter for open
							fracture type IIIA, IIIB, or IIIC.</span>
					</p>
					<%
					}
					%>
					<div style="text-align: center;" class="mb-3">
						<img
							src="https://images.unsplash.com/photo-1633113089635-115b38c66c49?ixid=MnwxMjA3fDF8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&ixlib=rb-1.2.1&auto=format&fit=crop&w=870&q=80">
					</div>
					<h1 class="mt-20">Contact us</h1>
					<form action="about" method="post" >
						<label for="email" class="form-label"><b>Email*</b></label> <input
							type="text" placeholder="Enter your email" id="email"
							name="email" class="form-control" required> <br> <label
							for="description" class="form-label"><b>Description*</b></label>
						<input type="text" placeholder="How can we help you?"
							id="description" name="description" class="form-control" required>
						<br> <input class="btn btn-primary" type="submit" />
					</form>
				</div>
			</div>

		</div>
	</div>
</body>
</html>



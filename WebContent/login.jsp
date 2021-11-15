<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3"
	crossorigin="anonymous">
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/assets/css/main.css" />
</head>
<body class="login-form">
	<form action="login" method="post">
		<p>
			<label for="uname" class="form-label"><b>Username</b></label> <input
				class="form-control" type="text" placeholder="Enter Username"
				name="username" required>
		</p>
		<p>
			<label for="psw" class="form-label"><b>Password</b></label> <input
				class="form-control" type="password" placeholder="Enter Password"
				name="password" required>
		</p>
		<button type="submit" class="btn btn-primary">Login</button>
	</form>
</body>
</html>
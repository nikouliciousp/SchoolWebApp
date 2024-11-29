<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Welcome</title>
  <link rel="stylesheet" href="${pageContext.request.contextPath}/schoolapp/static/styles/index.css">
</head>
<body>
<div class="welcome-container">
  <h1>Welcome to the School Web Application</h1>
  <p>Your gateway to managing school activities efficiently and effectively.</p>
  <a href="${pageContext.request.contextPath}/login.jsp" class="btn">Login</a>
</div>
</body>
</html>
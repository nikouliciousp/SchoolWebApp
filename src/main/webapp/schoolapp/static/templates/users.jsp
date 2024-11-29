<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Users Management</title>
  <link rel="stylesheet" href="${pageContext.request.contextPath}/schoolapp/static/styles/users.css">
  <script>
    function confirmDelete(username) {
      if (confirm("Are you sure you want to delete the user " + username + "?")) {
        document.getElementById("deleteForm_" + username).submit();
      }
    }
  </script>
</head>
<body>
<div class="container">
  <h2>Users Management</h2>
  <table class="users-table">
    <thead>
    <tr>
      <th>Username</th>
      <th>Password</th>
      <th>Actions</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="user" items="${users}">
      <tr>
        <td>${user.username}</td>
        <td>••••••••</td> <!-- Κρυφό password -->
        <td>
          <form id="deleteForm_${user.username}" action="${pageContext.request.contextPath}/secure/deleteUser" method="post" style="display:inline;">
            <input type="hidden" name="username" value="${user.username}">
            <button type="button" onclick="confirmDelete('${user.username}')">Delete</button>
          </form>
          <form action="${pageContext.request.contextPath}/secure/updateUser" method="get" style="display:inline;">
            <input type="hidden" name="username" value="${user.username}">
            <button type="submit">Update</button>
          </form>
        </td>
      </tr>
    </c:forEach>
    </tbody>
  </table>
  <button onclick="window.location.href='${pageContext.request.contextPath}/secure/insertUser'">Insert</button>
  <div>
  <button class="logout-button" onclick="window.location.href='${pageContext.request.contextPath}/logout'">Logout</button>
  </div>
</div>
</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Insert User</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/schoolapp/static/styles/insertUser.css">
    <script>
        function hideMessage() {
            var messageBox = document.getElementById("messageBox");
            if (messageBox) {
                setTimeout(function() {
                    messageBox.style.display = "none";
                }, 5000); // Μήνυμα εξαφανίζεται μετά από 5 δευτερόλεπτα
            }
        }
        window.onload = hideMessage;
    </script>
</head>
<body>
<div class="insert-container">
    <h2>Insert User</h2>
    <form action="${pageContext.request.contextPath}/secure/insertUser" method="post">
        <div class="form-group">
            <label for="username">Username:</label>
            <input type="text" id="username" name="username" required>
        </div>
        <div class="form-group">
            <label for="password">Password:</label>
            <input type="password" id="password" name="password" required>
        </div>
        <button type="submit">Insert</button>
    </form>
    <div id="messageBox" class="message-box">
        <c:if test="${not empty message}">
            ${message}
        </c:if>
    </div>
    <button class="back-button" onclick="window.location.href='${pageContext.request.contextPath}/secure/users'">Back</button>
</div>
</body>
</html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Messages</title>
</head>
<body>
    <h2>Your Messages</h2>
    <c:forEach var="message" items="${messages}">
        <p>From: ${message.fromUser} - To: ${message.toUser} - ${message.text}</p>
    </c:forEach>
    <a href="index.jsp">Back to Home</a>
</body>
</html>

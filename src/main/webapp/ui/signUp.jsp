<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Sign Up</title>
</head>
<body>
    <form action="register" method="post">
        <label for="login">Login:</label>
        <input type="text" id="login" name="login"><br>
        <label for="password">Password:</label>
        <input type="password" id="password" name="password"><br>
        <label for="fullName">Full Name:</label>
        <input type="text" id="fullName" name="fullName"><br>
        <label for="birthDate">Birth Date:</label>
        <input type="date" id="birthDate" name="birthDate"><br>
        <input type="submit" value="Sign Up">
    </form>
</body>
</html>

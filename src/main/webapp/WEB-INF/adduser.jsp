<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Edit page</title>
</head>
<div align="center">
<body>
<h2>
        Username: ${username}
    </h2>
    <h2>
        First name: ${firstName}
    </h2>
    <h2>
        Last name: ${lastName}
    </h2>
<p>
        ${error}
    </p>
    <form method="post">
        <input type="text" placeholder="Enter new username" name="newUsername" /><br>
        <input type="password" placeholder="Enter new password" name="newPassword" ><br>
        <input type="password" placeholder="Confirm password" name="confirmPassword" required/><br>
        <input type="text" placeholder="Enter new first name" name="firstName" /><br>
        <input type="text" placeholder="Enter new last name" name="lastName" /><br>
        <input type="submit" value="Edit user" name="edit" required/>
    </form>
    <p>
        ${usernameMessage}
    </p>
    <p>
        ${passwordMessage}
    </p>
    <p>
        ${firstNameMessage}
    </p>
    <p>
        ${lastNameMessage}
    </p>

    <a href="/"> Back</a>
</body>
</html>

<%--
  Created by IntelliJ IDEA.
  models.User: pao741
  Date: 7/14/19
  Time: 10:30 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Edit page</title>
</head>
<body>
<title>Edit</title>
<div align="center">
<a href="/"> Back</a>


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
        <input type="submit" value="Edit user" name="editUser" required/>
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

</body>
</html>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Edit page</title>
</head>
<div align="center">
<body>
<h2> username: ${username} </h2>
<h2> first name: ${first_name}</h2>
<h2> last name: ${last_name}</h2>

<a href="/"> Back</a>
<p>
        ${error}
    </p>
    <form method="post">
        <input type="text" placeholder="Enter new username" name="newUsername" /><br>
        <input type="password" placeholder="Enter password" name="password" required/><br>
        <input type="text" placeholder="Enter new first name" name="firstName" /><br>
        <input type="text" placeholder="Enter new last name" name="lastName" /><br>
        <input type="submit" value="edit user" name="edit_user" required/>
    </form>

</body>
</div>
</html>

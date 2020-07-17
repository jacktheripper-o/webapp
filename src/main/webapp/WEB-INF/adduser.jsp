<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<title>AddUser</title>
<div align="center">
<body>
<h2>
    Add user
</h2>

<a href="/"> Back</a>

<p>${adding_error}</p>
<form method="post">
    <input type="text" name="adding_username" placeholder="username" required /><br>
    <input type="password" name="adding_password" placeholder="password" required/><br>
    <input type="password" name="confirm_password" placeholder="confirm password" required>
    <br>
    <input type="submit" name="add_user" value="Add user" required/>
</form>
</body>
</div>
</html>
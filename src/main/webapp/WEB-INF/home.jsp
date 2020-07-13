<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<title>Home Page</title>
<div align="center">

<body>
<h2>Welcome, ${user.getUsername()}</h2>
<h2>${user.getFirstName()} ${user.getLastName()}</h2>
<br/>
<form method="post">
    <input type="submit" name="logout" value="Log out"/>
</form>

<form method="post">
    <input type="submit" name="add User" value="adduser"/>
</form>

<p>${removing_error}</p>
    <table border="2">
        <tr><td>Username List</td></tr>
        <c:forEach items="${userList}" var="usr">
            <tbody style="vertical-align: center">
            <tr>
                <td>
                        ${usr}
                    <td>
                            <form method="post">
                                <input type="hidden" name="user_to_use" value="${usr}"/>
                            <c:choose>
                                <c:when test="${usr!=username}">
                                    <input type="submit" name="removing_user" value="remove" onclick="{return confirm('Are you sure you want to remove this user?')}"/>
                                </c:when>
                                <c:otherwise>  </c:otherwise>
                            </c:choose>
                                <input type="submit" name="do_edit" value="edit" />
                            </form>
                    </td>
                </td>
            </tr>
            </tbody>
        </c:forEach>
    </table>
</body>
</html>

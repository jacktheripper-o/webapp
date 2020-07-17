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

<p>${removing_error}</p>
    <table border="1">
        <tr>
            <td>Username List</td>
        </tr>
        <c:forEach items="${userList}" var="user">
            <tbody style="vertical-align: center">
            <tr>
                <td>
                        ${user}
                </td>
                <td>
                            <form method="post">
                                <input type="hidden" name="user_to_use" value="${user}"/>
                            <c:choose>
                                <c:when test="${user!=username}">
                                    <input type="submit" name="removing_user" value="remove" onclick="{return confirm('Are you sure you want to remove this user?')}"/>
                                </c:when>
                            </c:choose>
                                <input type="submit" name="do_edit" value="edit" />
                            </form>
                    </td>
                </td>
            </tr>
            </tbody>
        </c:forEach>
    </table>


    <form method="get" action="/adduser">
        <button type="submit" name="add_user" value="add_user">  Add user </button>
    </form>


</body>
</div>
</html>
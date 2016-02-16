<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:useBean id="tournaments" scope="request" type="java.util.Collection"/>

<html>
<head><title>Tournament List</title></head>
<body>
<h3>Tournament List</h3>

<c:choose>
    <c:when test="${empty tournaments}">
        No Tournaments
    </c:when>
    <c:otherwise>
        <table>
            <tr>
                <td>Tournament Name</td>
                <td>current round</td>
                <td>Start date</td>
            </tr>
            <c:forEach items="${tournaments}" var="item">
                <jsp:useBean id="item" scope="page" type="dk.tskogemann.data.entities.Tournament"/>
                <tr>
                    <td><c:out value="${item.name}"/></td>
                    <td><c:out value="${item.currentRound}"/></td>
                    <td><c:out value="${item.tournamentStart}"/></td>
                    <td><a href="<c:url value="/tournament/view?id="/><c:out value="${item.id}"/>">Edit</a> </td>
                </tr>
            </c:forEach>
        </table>
    </c:otherwise>
</c:choose>

<br>

<h3>Create new tournament</h3>

<form action="<c:url value="/tournament/create"/>" method="post">
    <input type="text" name="name" value="">
    <input type="text" name="date" value="2016-02-16 09:00">
    <input type="submit" value="Create Tournament">
</form>

</body>
</html>
<%@ page import="dk.tskogemann.data.entities.Position" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:useBean id="players" scope="request" type="java.util.Collection"/>
<html>
<head><title>Player List</title></head>
<body>
<c:choose>
    <c:when test="${empty players}">
        No Players
    </c:when>
    <c:otherwise>
        <table>
            <tr>
                <td>First name</td>
                <td>Last Name</td>
                <td>Positions</td>
                <td>Teams #</td>
                <td>Tournaments #</td>
                <td>Delete</td>
            </tr>
            <c:forEach items="${players}" var="item">
                <jsp:useBean id="item" scope="page" type="dk.tskogemann.data.entities.Player"/>
                <tr>
                    <td><c:out value="${item.firstName}"/></td>
                    <td><c:out value="${item.lastName}"/></td>
                    <td><c:out value="${item.positionsAsString}"/></td>
                    <td><c:out value="${item.teams.size()}"/></td>
                    <td><c:out value="${item.tournamentParticipations.size()}"/></td>
                    <td><a href="<c:url value="/player/delete?id=${item.id}"/>">Delete</a> </td>
                </tr>
            </c:forEach>
        </table>
    </c:otherwise>
</c:choose>

<br>

<h3>Add Player</h3>

<c:set var="positions" value="<%=Position.values()%>"/>

<form action="<c:url value="/player/add"/>" method="post" accept-charset="UTF-8">
    <label for="firstName">First name</label>
    <input id="firstName" type="text" name="firstName" value="">
    <br>
    <label for="lastName">Last Name</label>
    <input id="lastName" type="text" name="lastName" value="">
    <br>
    <c:forEach items="${positions}" var="pos">
        <label for="${pos}">${pos}</label>
        <input id="${pos}" type="checkbox" name="position" value="${pos}">
    </c:forEach>
    <br>
    <input type="submit" value="Add Player">
</form>

</body>
</html>
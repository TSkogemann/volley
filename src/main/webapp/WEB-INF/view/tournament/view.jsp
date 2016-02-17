<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:useBean id="available" scope="request" type="java.util.Collection"/>
<jsp:useBean id="tournament" scope="request" type="dk.tskogemann.data.entities.Tournament"/>

<html>
<head><title>View Tournament</title></head>
<body>
<h3>Tournament: ${tournament.name}</h3>
<table width="100%">
    <tr>
        <td width="50%">
            <table>
                <tr>
                    <td>
                        Participants
                    </td>
                </tr>

                <c:forEach items="${tournament.tournamentParticipants}" var="participant">
                    <tr>
                        <td>
                            <a href="<c:url value="/tournament/removePlayer?id=${tournament.id}&playerId=${participant.player.id}"/>">${participant.player.fullName}</a>
                        </td>
                    </tr>
                </c:forEach>

            </table>
        </td>
        <td width="50%">
            <table>
                <tr>
                    <td>Available</td>
                </tr>

                <c:forEach items="${available}" var="player">
                    <tr>
                        <jsp:useBean id="player" scope="page" type="dk.tskogemann.data.entities.Player"/>
                        <td>
                            <a href="<c:url value="/tournament/addPlayer?id=${tournament.id}&playerId=${player.id}"/>">${player.fullName}</a>
                        </td>
                    </tr>
                </c:forEach>

            </table>
        </td>
    </tr>

</table>


</body>
</html>
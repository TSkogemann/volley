<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:useBean id="round" scope="request" type="dk.tskogemann.data.entities.Round"/>
<html>
<head><title>View Round</title></head>
<body>

<h3>${round.tournament.name}, round ${round.round} </h3>
<b>Number of matches ${round.matches.size()} </b>

<!-- printing the teams for each match -->
<table>
    <c:forEach items="${round.matches}" var="match" varStatus="roundStatus">
        <jsp:useBean id="match" scope="page" type="dk.tskogemann.data.entities.Match"/>
        <jsp:useBean id="roundStatus" scope="page" type="javax.servlet.jsp.jstl.core.LoopTagStatus"/>
        <tr>
            <td>
                <b>Match ${roundStatus.index}</b>
                <table>
                    <tr>
                        <c:forEach items="${match.teams}" var="team" varStatus="teamStatus">
                            <jsp:useBean id="team" scope="page" type="dk.tskogemann.data.entities.Team"/>
                            <jsp:useBean id="teamStatus" scope="page" type="javax.servlet.jsp.jstl.core.LoopTagStatus"/>

                            <td>
                                <table>
                                    <b>Team ${teamStatus.index}</b>
                                    <c:forEach items="${team.players}" var="member">
                                        <jsp:useBean id="member" scope="page"
                                                     type="dk.tskogemann.data.entities.TeamParticipation"/>
                                        <tr>
                                            <td>
                                                    ${member.player.fullName}
                                            </td>
                                        </tr>
                                    </c:forEach>
                                </table>
                            </td>

                        </c:forEach>
                    </tr>
                </table>
            </td>
        </tr>
    </c:forEach>
</table>


</body>
</html>
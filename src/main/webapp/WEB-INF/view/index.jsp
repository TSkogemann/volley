<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head><title>Welcome</title></head>
<body>
<table>
    <tr>
        <td>
            <a href="<c:url value="/tournament/list"/>">List tournaments</a>
        </td>
    </tr>
    <tr>
        <td>
            <a href="<c:url value="/player/list"/>">List Players</a>
        </td>
    </tr>
</table>





</body>
</html>
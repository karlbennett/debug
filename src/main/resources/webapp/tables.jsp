<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<HTML>
    <HEAD>
        <LINK REL="stylesheet" TYPE="text/css" HREF="css/style.css" />
        <TITLE>vBase 3 debug</TITLE>
    </HEAD>
    <BODY>
        <DIV CLASS="main">
            <%@include file='/mainTabs.jsp'%>
            <%@include file='/tableTabs.jsp'%>
            <DIV CLASS="content">
                <TABLE>
                    <TR>
                        <c:forEach var="column" items="${tableRows[0]}">
                            <TD><c:out value="${column.key}" /></TD>
                        </c:forEach>
                    </TR>
                    <c:forEach var="row" items="${tableRows}">
                        <TR>
                            <c:forEach var="column" items="${row}">
                                <TD><c:out value="${column.value}" /></TD>
                            </c:forEach>
                        </TR>
                    </c:forEach>
                </TABLE>
            </DIV>
        </DIV>
    </BODY>
</HTML> 
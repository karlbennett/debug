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
                            <TD CLASS="cell headingCell">
                                <LABEL CLASS="tableHeading fontSet"><c:out value="${column.key}" /></LABEL>
                            </TD>
                        </c:forEach>
                    </TR>
                    <c:forEach var="row" items="${tableRows}">
                        <TR>
                            <c:forEach var="column" items="${row}">
                                <TD CLASS="cell normalCell">
                                    <LABEL CLASS="tableLabel fontSet">
                                        <c:out value="${column.value}" />
                                    </LABEL>
                                </TD>
                            </c:forEach>
                        </TR>
                    </c:forEach>
                </TABLE>
            </DIV>
        </DIV>
    </BODY>
</HTML> 
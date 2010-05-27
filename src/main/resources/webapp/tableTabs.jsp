<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<DIV CLASS="tab_row_2">
    <UL CLASS="tabList">
        <c:forEach var="tableName" items="${tableNames}">
            <c:choose>
                <c:when test="${param.tableName == fn:toLowerCase(tableName)}">
                    <LI CLASS="tab selectedTab">
                        <A HREF="?tableName=<c:out value="${fn:toLowerCase(tableName)}"/>"
                            CLASS="tabLink fontSet selectedTabLink">
                            <c:out value="${fn:toLowerCase(tableName)}"/>
                        </A>
                    </LI>
                </c:when>
                <c:otherwise>
                    <LI CLASS="tab">
                        <A HREF="?tableName=<c:out value="${fn:toLowerCase(tableName)}"/>"
                            CLASS="tabLink fontSet">
                            <c:out value="${fn:toLowerCase(tableName)}"/>
                        </A>
                    </LI>
                </c:otherwise>
            </c:choose>
        </c:forEach>
    </UL>
</DIV>
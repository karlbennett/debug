<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<DIV CLASS="tableTabs">
    <FORM CLASS="schemaForm" METHOD="post" ACTION="setschema.html">
        <SELECT NAME="schemaName">
            <c:forEach var="schemaName" items="${schemaNames}">
            <c:choose>
                <c:when test="${schemaName == currentSchemaName}">
                    <OPTION VALUE="${schemaName}" SELECTED="selected">${schemaName}</OPTION>
                </c:when>
                <c:otherwise>
                    <OPTION VALUE="${schemaName}">${schemaName}</OPTION>
                </c:otherwise>
            </c:choose>
            </c:forEach>
        </SELECT>
        <INPUT TYPE="submit" VALUE="Set Schema" />
    </FORM>
    <UL CLASS="tableTabList">
        <c:forEach var="tableName" items="${tableNames}">
            <c:choose>
                <c:when test="${param.tableName == fn:toLowerCase(tableName)}">
                    <LI CLASS="tableTab selectedTab">
                        <A NAME="<c:out value="${fn:toLowerCase(tableName)}"/>"
                            HREF="?tableName=<c:out value="${fn:toLowerCase(tableName)}"/>#<c:out value="${fn:toLowerCase(tableName)}"/>"
                            CLASS="tabLink fontSet selectedTabLink">
                            <c:out value="${fn:toLowerCase(tableName)}"/>
                        </A>
                    </LI>
                </c:when>
                <c:otherwise>
                    <LI CLASS="tableTab">
                        <A NAME="<c:out value="${fn:toLowerCase(tableName)}"/>"
                            HREF="?tableName=<c:out value="${fn:toLowerCase(tableName)}"/>#<c:out value="${fn:toLowerCase(tableName)}"/>"
                            CLASS="tabLink fontSet">
                            <c:out value="${fn:toLowerCase(tableName)}"/>
                        </A>
                    </LI>
                </c:otherwise>
            </c:choose>
        </c:forEach>
    </UL>
</DIV>
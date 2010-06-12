<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<DIV class="tableTabs">
    <FORM class="schemaForm" method="post" action="setschema.html">
        <SELECT name="schemaName">
            <c:forEach var="schemaName" items="${schemaNames}">
            <c:choose>
                <c:when test="${schemaName == currentSchemaName}">
                    <OPTION value="${schemaName}" selected="selected">${schemaName}</OPTION>
                </c:when>
                <c:otherwise>
                    <OPTION value="${schemaName}">${schemaName}</OPTION>
                </c:otherwise>
            </c:choose>
            </c:forEach>
        </SELECT>
        <INPUT type="submit" value="Set Schema" />
    </FORM>
    <UL class="tableTabList">
        <c:forEach var="tableName" items="${tableNames}">
            <c:choose>
                <c:when test="${param.tableName == fn:toLowerCase(tableName)}">
                    <LI class="tableTab selectedTab">
                        <A name="<c:out value="${fn:toLowerCase(tableName)}"/>"
                            href="?tableName=<c:out value="${fn:toLowerCase(tableName)}"/>#<c:out value="${fn:toLowerCase(tableName)}"/>"
                            class="tabLink fontSet selectedTabLink">
                            <c:out value="${fn:toLowerCase(tableName)}"/>
                        </A>
                    </LI>
                </c:when>
                <c:otherwise>
                    <LI class="tableTab">
                        <A name="<c:out value="${fn:toLowerCase(tableName)}"/>"
                            href="?tableName=<c:out value="${fn:toLowerCase(tableName)}"/>#<c:out value="${fn:toLowerCase(tableName)}"/>"
                            class="tabLink fontSet">
                            <c:out value="${fn:toLowerCase(tableName)}"/>
                        </A>
                    </LI>
                </c:otherwise>
            </c:choose>
        </c:forEach>
    </UL>
</DIV>
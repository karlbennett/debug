<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<DIV CLASS="tab_row_1">
    <UL CLASS="tabList">
        <c:choose>
            <c:when test="${param.tab == 'tables'}">
                <LI CLASS="tab selectedTab">
                    <A HREF="tables.html" CLASS="tabLink fontSet selectedTabLink">Tables</A>
                </LI>
            </c:when>
            <c:otherwise>
                <LI CLASS="tab">
                    <A HREF="tables.html" CLASS="tabLink fontSet">Tables</A>
                </LI>
            </c:otherwise>
        </c:choose>
        <c:choose>
            <c:when test="${param.tab == 'webservices'}">
                <LI CLASS="tab selectedTab">
                    <A HREF="webservices.html" CLASS="tabLink fontSet selectedTabLink">Webservices</A>
                </LI>
            </c:when>
            <c:otherwise>
                <LI CLASS="tab">
                    <A HREF="webservices.html" CLASS="tabLink fontSet">Webservices</A>
                </LI>
            </c:otherwise>
        </c:choose>
        <c:choose>
            <c:when test="${param.tab == 'logs'}">
                <LI CLASS="tab selectedTab">
                    <A HREF="logs.html" CLASS="tabLink fontSet selectedTabLink">Logs</A>
                </LI>
            </c:when>
            <c:otherwise>
                <LI CLASS="tab">
                    <A HREF="logs.html" CLASS="tabLink fontSet">Logs</A>
                </LI>
            </c:otherwise>
        </c:choose>
        <LI>
            <FORM method="post" action="setschema.html">
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
        </LI>
    </UL>
</DIV>
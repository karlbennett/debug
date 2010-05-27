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
    </UL>
</DIV>
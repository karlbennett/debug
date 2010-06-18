<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<DIV CLASS="tab_row_2">
    <c:choose>
        <c:when test="${param.tab == 'admin'}">
            <LI CLASS="tab selectedTab">
                <A HREF="logs.html?logName=admin" CLASS="tabLink fontSet selectedTabLink">Admin log</A>
            </LI>
        </c:when>
        <c:otherwise>
            <LI CLASS="tab">
                <A HREF="logs.html?logName=admin" CLASS="tabLink fontSet">Admin log</A>
            </LI>
        </c:otherwise>
    </c:choose>
    <c:choose>
        <c:when test="${param.tab == 'webservice'}">
            <LI CLASS="tab selectedTab">
                <A HREF="logs.html?logName=webservice" CLASS="tabLink fontSet selectedTabLink">Webservice log</A>
            </LI>
        </c:when>
        <c:otherwise>
            <LI CLASS="tab">
                <A HREF="logs.html?logName=webservice" CLASS="tabLink fontSet">Webservice log</A>
            </LI>
        </c:otherwise>
    </c:choose>
    <c:choose>
        <c:when test="${param.monitoring == true}">
            <LI CLASS="tab selectedTab">
                <A HREF="logs.html?logName=${param.tab}" CLASS="tabLink fontSet selectedTabLink">Stop monitoring</A>
            </LI>
        </c:when>
        <c:otherwise>
            <LI CLASS="tab">
                <A HREF="logs.html?logName=${param.tab}&monitoring=true#bottom" CLASS="tabLink fontSet">Monitor</A>
            </LI>
        </c:otherwise>
    </c:choose>
</DIV>
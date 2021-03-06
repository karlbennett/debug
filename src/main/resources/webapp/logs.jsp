<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<HTML>
    <HEAD>
        <LINK REL="stylesheet" TYPE="text/css" HREF="css/style.css" />
        <TITLE>vBase 3 debug</TITLE>
    </HEAD>
    <BODY>
        <A NAME="top"/>
        <c:choose>
            <c:when test="${empty param.lineNum}">
                <c:set var="lineNum" value="500" />
            </c:when>
            <c:otherwise>
                <c:set var="lineNum" value="${param.lineNum}" />
            </c:otherwise>
        </c:choose>
        <c:import url="/mainTabs.jsp">
            <c:param name="tab" value="logs"/>
        </c:import>
        <c:import url="/logTabs.jsp">
            <c:param name="tab" value="${param.logName}"/>
            <c:param name="monitoring" value="${param.monitoring}"/>
            <c:param name="lineNum" value="${lineNum}"/>
            <c:param name="truncate" value="${truncate}"/>
        </c:import>
        <c:if test="${param.lineNum > 0}">
            <A HREF="#bottom" CLASS="tabLink fontSet logNav">To bottom</A>
            <c:choose>
                <c:when test="${param.monitoring == true}">
                    <A HREF="logs.html?logName=${param.logName}&lineNum=${lineNum}" CLASS="tabLink fontSet selectedTabLink">Stop monitoring</A>
                </c:when>
                <c:otherwise>
                    <A HREF="logs.html?logName=${param.logName}&lineNum=${lineNum}&monitoring=true#bottom" CLASS="tabLink fontSet selectedTabLink">Monitor log</A>
                </c:otherwise>
            </c:choose>
            <P CLASS="logText">
                <c:forEach var="line" items="${logCollection}">
                    ${line}    
                </c:forEach>
            </P>
            <A NAME="bottom" HREF="#top" CLASS="tabLink fontSet logNav">To top</A>
            <c:if test="${param.monitoring == true}">
                <META HTTP-EQUIV="REFRESH" CONTENT="5; URL=?logName=${param.logName}&lineNum=${lineNum}&monitoring=true#bottom">
                <A HREF="logs.html?logName=${param.logName}&lineNum=${lineNum}" CLASS="tabLink fontSet selectedTabLink">Stop monitoring</A>
            </c:if>
        </c:if>
    </BODY>
</HTML> 
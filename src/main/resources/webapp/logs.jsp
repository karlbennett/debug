<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<HTML>
    <HEAD>
        <LINK REL="stylesheet" TYPE="text/css" HREF="css/style.css" />
        <TITLE>vBase 3 debug</TITLE>
    </HEAD>
    <BODY>
        <A NAME="top"/>
        <c:import url="/mainTabs.jsp">
            <c:param name="tab" value="logs"/>
        </c:import>
        <c:import url="/logTabs.jsp">
            <c:param name="tab" value="${logName}"/>
            <c:param name="monitoring" value="${param.monitoring}"/>
            <c:param name="logLineNum" value="${logLineNum}"/>
            <c:param name="truncate" value="${truncate}"/>
        </c:import>
        <c:if test="${logString != null && logString != ''}">
            <A HREF="#bottom" CLASS="tabLink fontSet logNav">To bottom</A>
            <p>${logString}</p>
            <A NAME="bottom" HREF="#top" CLASS="tabLink fontSet logNav">To top</A>
            <c:if test="${param.monitoring == true}">
                <META HTTP-EQUIV="REFRESH" CONTENT="5; URL=?logName=${logName}&monitoring=true#bottom">
                <A HREF="logs.html?logName=${param.logName}" CLASS="tabLink fontSet selectedTabLink">Stop monitoring</A>
            </c:if>
        </c:if>
    </BODY>
</HTML> 
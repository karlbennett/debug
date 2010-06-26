<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<DIV CLASS="tab_row_2">
    <UL CLASS="tabList">
        <c:choose>
            <c:when test="${param.tab == 'admin'}">
                <LI CLASS="tab selectedTab">
                    <A HREF="logs.html?logName=admin&lineNum=${param.lineNum}" CLASS="tabLink fontSet selectedTabLink">Admin log</A>
                </LI>
            </c:when>
            <c:otherwise>
                <LI CLASS="tab">
                    <A HREF="logs.html?logName=admin&lineNum=${param.lineNum}" CLASS="tabLink fontSet">Admin log</A>
                </LI>
            </c:otherwise>
        </c:choose>
        <c:choose>
            <c:when test="${param.tab == 'webservice'}">
                <LI CLASS="tab selectedTab">
                    <A HREF="logs.html?logName=webservice&lineNum=${param.lineNum}" CLASS="tabLink fontSet selectedTabLink">Webservice log</A>
                </LI>
            </c:when>
            <c:otherwise>
                <LI CLASS="tab">
                    <A HREF="logs.html?logName=webservice&lineNum=${param.lineNum}" CLASS="tabLink fontSet">Webservice log</A>
                </LI>
            </c:otherwise>
        </c:choose>
        <LI CLASS="tab">
            <FORM CLASS="logTabForm" METHOD="get" ACTION="logs.html">
                <c:choose>
                    <c:when test="${param.lineNum > -1}">
                        <INPUT TYPE="hidden" NAME="logName" VALUE="${param.tab}" />
                        <INPUT TYPE="text" NAME="lineNum" SIZE="6" VALUE="${param.lineNum}" />
                        <INPUT TYPE="submit" VALUE="Set log line number" />
                    </c:when>
                    <c:otherwise>
                        <INPUT TYPE="text" NAME="lineNum" SIZE="6" DISABLED />
                        <INPUT TYPE="submit" VALUE="Set log line number" DISABLED />
                    </c:otherwise>
                </c:choose>
            </FORM>
        </LI>
        <c:choose>
            <c:when test="${param.lineNum <= -1}">
                <LI CLASS="tab selectedTab">
                    <A HREF="logs.html?logName=${param.tab}&lineNum=500" CLASS="tabLink fontSet selectedTabLink">Truncate log</A>
                </LI>
            </c:when>
            <c:otherwise>
                <LI CLASS="tab">
                    <A HREF="logs.html?logName=${param.tab}&lineNum=-1" CLASS="tabLink fontSet">Full log</A>
                </LI>
            </c:otherwise>
        </c:choose>
    </UL>
</DIV>
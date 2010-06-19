<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<DIV CLASS="tab_row_2">
    <UL CLASS="tabList">
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
        <LI CLASS="tab">
            <FORM CLASS="logTabForm" METHOD="post" ACTION="setloglinenum.html">
                <c:choose>
                    <c:when test="${param.truncate == true}">
                        <INPUT TYPE="text" NAME="logLineNum" SIZE="6" VALUE="${param.logLineNum}" />
                        <INPUT TYPE="submit" VALUE="Set log line number" />
                    </c:when>
                    <c:otherwise>
                        <INPUT TYPE="text" NAME="logLineNum" SIZE="6" VALUE="${param.logLineNum}" DISABLED />
                        <INPUT TYPE="submit" VALUE="Set log line number" DISABLED />
                    </c:otherwise>
                </c:choose>
            </FORM>
        </LI>
        <c:choose>
            <c:when test="${param.truncate == false}">
                <LI CLASS="tab selectedTab">
                    <FORM CLASS="logTabForm" METHOD="post" ACTION="setlogtruncate.html">
                        <INPUT TYPE="hidden" NAME="truncate" VALUE="true" />
                        <INPUT TYPE="submit" VALUE="Truncate log" />
                    </FORM>
                </LI>
            </c:when>
            <c:otherwise>
                <LI CLASS="tab">
                    <FORM CLASS="logTabForm" METHOD="post" ACTION="setlogtruncate.html">
                        <INPUT TYPE="hidden" NAME="truncate" VALUE="false" />
                        <INPUT TYPE="submit" VALUE="Full log" />
                    </FORM>
                </LI>
            </c:otherwise>
        </c:choose>
    </UL>
</DIV>
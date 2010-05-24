<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<HTML>
    <HEAD>
        <LINK REL="stylesheet" TYPE="text/css" HREF="css/style.css" />
        <TITLE>vBase 3 debug</TITLE>
    </HEAD>
    <BODY>
        <DIV CLASS="content">
            <DIV CLASS="tab_row_1">
                <UL CLASS="tabList">
                    <LI CLASS="tab">
                        <A HREF="?row1_tab=tables" CLASS="tabLink">Tables</A>
                    </LI>
                    <LI CLASS="tab">
                        <A HREF="?row1_tab=webservices" CLASS="tabLink">Webservices</A>
                    </LI>
                    <LI CLASS="tab">
                        <A HREF="?row1_tab=logs" CLASS="tabLink">Logs</A>
                    </LI>
                </UL>
            </DIV>
            <c:if test="${param.row1_tab == 'tables'}">
                <DIV CLASS="tab_row_2">
                    <UL CLASS="tabList">
                        <LI CLASS="tab">
                            <A HREF="" CLASS="tabLink">Collectives</A>
                        </LI>
                        <LI CLASS="tab">
                            <A HREF="" CLASS="tabLink">Vuos</A>
                        </LI>
                        <LI CLASS="tab">
                            <A HREF="" CLASS="tabLink">Users</A>
                        </LI>
                        <LI CLASS="tab">
                            <A HREF="" CLASS="tabLink">Roles</A>
                        </LI>
                        <LI CLASS="tab">
                            <A HREF="" CLASS="tabLink">Volunteers</A>
                        </LI>
                        <LI CLASS="tab">
                            <A HREF="" CLASS="tabLink">Organisations</A>
                        </LI>
                        <LI CLASS="tab">
                            <A HREF="" CLASS="tabLink">Opportunities</A>
                        </LI>
                    </UL>
                </DIV>
            </c:if>
        </DIV>
    </BODY>
</HTML> 
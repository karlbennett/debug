<DIV CLASS="tab_row_2">
    <UL CLASS="tabList">
        <c:forEach var="tableName" items="${tableNames}">
            <LI CLASS="tab">
                <A HREF="" CLASS="tabLink"><c:out value="${fn:toLowerCase(tableName)}"/></A>
            </LI>
        </c:forEach>
    </UL>
</DIV>
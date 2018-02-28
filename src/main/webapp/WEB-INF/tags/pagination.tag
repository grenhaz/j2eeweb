<%@tag description="put the tag description here" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@attribute name="type" required="true" rtexprvalue="true"%>
<%@attribute name="current" required="true" rtexprvalue="true"%>
<%@attribute name="pages" required="true" rtexprvalue="true"%>
<c:if test="${pages > 1}">
    <ul class="pagination">
        <c:forEach var="i" begin="1" end="${pages}">
            <li><a href="#articles_${type}_${i}"><c:out value="${i}" /></a></li>
        </c:forEach>
    </ul>
</c:if>
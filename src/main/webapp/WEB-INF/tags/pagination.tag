<%@tag description="put the tag description here" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@attribute name="destination" required="true" rtexprvalue="true"%>
<%@attribute name="pre" required="true" rtexprvalue="true"%>
<%@attribute name="current" required="true" rtexprvalue="true"%>
<%@attribute name="pages" required="true" rtexprvalue="true"%>
<%@attribute name="menu" required="false" type="java.lang.Boolean" rtexprvalue="true"%>
<c:if test="${pages > 1}">
    <ul class="pagination">
        <c:forEach var="i" begin="1" end="${pages}">
            <li ${i == current ? "class='active'" : ""}>
                <a class="go" 
                   data-menu="${menu}" 
                   data-destination="${destination}" 
                   href="#${pre}_${i}">
                    <c:out value="${i}" />
                </a>
            </li>
        </c:forEach>
    </ul>
</c:if>
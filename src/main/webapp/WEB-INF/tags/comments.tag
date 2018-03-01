<%@tag description="Commetns list" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="ui" uri="http://www.obarcia.com/tags" %>
<%@attribute name="comments" required="true" type="org.obarcia.demo.models.ListPagination" rtexprvalue="true" %>
<div class="header"><spring:message code="title.comments" /></div>
<c:if test="${not empty comments.records}">
    <c:forEach var="article" items="${comments.records}">
        <ui:comment comment="${comment}" />
    </c:forEach>
    
    <!-- TODO: PAGINATION -->
</c:if>
<c:if test="${empty comments.records}">
    <div class="articles-empty"><spring:message code="comments.empty" /></div>
</c:if>
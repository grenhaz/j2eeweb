<%@tag description="Article" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@attribute name="article" required="true" type="org.obarcia.demo.models.Article" rtexprvalue="true" %>
<%@attribute name="position" required="true" %>
<c:set var="base" value="${pageContext.getServletContext().getContextPath()}" />
<div class="article ${position}">
    <!-- IMAGE -->
    <div class="image">
        <a href="<c:url value="/article/${article.id}" />">
            <img src="${base}/resources/images/game.jpg" />
        </a>
    </div>
    <!-- TEXT -->
    <div class="text">
        <div class="type"><spring:message code="article.type.${article.type}" /></div>
        <div class="title"><a href="<c:url value="/article/${article.id}" />"><c:out value="${article.title}" /></a></div>
        <div class="content"><c:out value="${article.shortContent}" /></div>
        <div class="info"><i class="fa fa-comment"></i> 0 | <c:out value="${article.formattedPublish}" /> | <c:out value="${article.tags}" /></div>
    </div>
</div>
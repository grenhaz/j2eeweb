<%@tag description="Article" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@attribute name="article" required="true" type="org.obarcia.demo.models.article.Article" rtexprvalue="true" %>
<%@attribute name="position" required="true" %>
<c:set var="base" value="${pageContext.getServletContext().getContextPath()}" />
<div class="article ${position}">
    <!-- IMAGE -->
    <div class="image">
        <a href="<c:url value="/article/${article.id}" />">
            <!-- <img src="${base}/resources/images/game.jpg" /> -->
            <c:if test="${article.type == 'review'}">
                <div class="puntuation"><c:out value="${article.puntuation}" /></div>
            </c:if>
        </a>
    </div>
    <!-- TEXT -->
    <div class="text">
        <div class="type">
            <spring:message code="article.type.${article.type}" />
        </div>
        <div class="title">
            <a href="<c:url value="/article/${article.id}" />">
                <c:out value="${article.title}" />
            </a>
        </div>
        <div class="content">
            <c:out value="${article.description}" />
        </div>
        <div class="info"><i class="fa fa-comment"></i><c:out value="${article.comments}" /> | <c:out value="${article.formattedPublish}" /> | <c:out value="${article.tags}" /></div>
    </div>
</div>
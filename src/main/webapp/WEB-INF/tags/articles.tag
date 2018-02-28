<%@tag description="Articles list" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="ui" uri="http://www.obarcia.com/tags" %>
<%@attribute name="articles" required="true" type="org.obarcia.demo.models.ListPagination" rtexprvalue="true" %>
<div class="row">
    <div class="col-xs-12">
        <ul class="list-sections">
            <li><a href="#articles_all">TODOS</a></li>
            <li><a href="#articles_new">NOTICIAS</a></li>
            <li><a href="#articles_review">ANÁLISIS</a></li>
            <li><a href="#articles_guide">GUÍAS</a></li>
            <li><a href="#articles_video" >VIDEOS</a></li>
            <li><a href="#articles_special">ESPECIALES</a></li>
        </ul>
    </div>
</div>
<c:if test="${not empty articles.records}">
    <c:forEach var="article" items="${articles.records}">
        <ui:article position="left" article="${article}" />
    </c:forEach>

    <ui:pagination type="${articles.type}" current="${articles.current}" pages="${articles.pages}" />
</c:if>
<c:if test="${empty articles.records}">
    <div class="articles-empty"><spring:message code="articles.empty" /></div>
</c:if>
<%@tag description="Articles list" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="ui" uri="http://www.obarcia.com/tags" %>
<%@attribute name="articles" required="true" type="org.obarcia.demo.models.ListPagination" rtexprvalue="true" %>
<div class="row">
    <div class="col-xs-12">
        <ul class="list-sections">
            <li ${articles.type == "all" ? "class='active'" : "" }>
                <a class="go" href="#${articles.tag}_all"><spring:message code="articles.type.all" /></a>
            </li>
            <li ${articles.type == "new" ? "class='active'" : "" }>
                <a class="go" href="#${articles.tag}_new"><spring:message code="articles.type.new" /></a>
            </li>
            <li ${articles.type == "review" ? "class='active'" : "" }>
                <a class="go" href="#${articles.tag}_review"><spring:message code="articles.type.review" /></a>
            </li>
            <li ${articles.type == "guide" ? "class='active'" : "" }>
                <a class="go" href="#${articles.tag}_guide"><spring:message code="articles.type.guide" /></a>
            </li>
            <li ${articles.type == "video" ? "class='active'" : "" }>
                <a class="go" href="#${articles.tag}_video" ><spring:message code="articles.type.video" /></a>
            </li>
            <li ${articles.type == "special" ? "class='active'" : "" }>
                <a class="go" href="#${articles.tag}_special"><spring:message code="articles.type.special" /></a>
            </li>
        </ul>
    </div>
</div>
<c:if test="${not empty articles.records}">
    <c:forEach var="article" items="${articles.records}">
        <ui:article position="left" article="${article}" />
    </c:forEach>

    <ui:pagination tag="${articles.tag}" type="${articles.type}" current="${articles.current}" pages="${articles.pages}" />
</c:if>
<c:if test="${empty articles.records}">
    <div class="articles-empty"><spring:message code="articles.empty" /></div>
</c:if>
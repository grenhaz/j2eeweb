<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="ui" uri="http://www.obarcia.com/tags" %>
<spring:message code="title" var="title" />
<ui:layout title="${title}">
    <div class="row">
        <div class="col-sm-12">
            <div class="section">
                <div class="header"><spring:message code="title.results" /></div>
                <div class="row">
                    <c:if test="${not empty results.records}">
                        <c:forEach var="article" items="${results}">
                            <div class="col-sm-4">
                                <ui:article position="top" article="${article}" />
                            </div>
                        </c:forEach>

                        <ui:pagination tag="${articles.tag}" type="${results.type}" current="${results.current}" pages="${results.pages}" />
                    </c:if>
                    <c:if test="${empty results.records}">
                        <div class="articles-empty"><spring:message code="articles.empty" /></div>
                    </c:if>
                </div>
            </div>
        </div>
    </div>
</ui:layout>
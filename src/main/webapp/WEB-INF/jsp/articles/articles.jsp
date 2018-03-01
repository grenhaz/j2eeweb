<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="ui" uri="http://www.obarcia.com/tags" %>
<spring:message code="title" var="title" />
<ui:layout title="${title}" tag="${tag}">
    <script>var __TAG = '<c:out value="${articles.tag}" default="" />';</script>
    <div class="row">
        <div class="col-sm-8">
            <div class="section">
                <c:if test="${not empty importants}">
                    <div class="header"><spring:message code="title.importants" /></div>
                    <div class="row">
                        <c:forEach var="article" items="${importants}">
                            <div class="col-sm-4">
                                <ui:article position="top" article="${article}" />
                            </div>
                        </c:forEach>
                    </div>
                </c:if>
                
                <a id="return-top"></a>
                <div class="header"><spring:message code="title.news" /></div>
                <div class="articles"></div>
            </div>
        </div>
        <div class="col-sm-4">
            <div class="section">
                <c:if test="${not empty guides}">
                    <div class="header"><spring:message code="title.guides" /></div>
                    <c:forEach var="article" items="${guides}">
                        <ui:article position="top" article="${article}" />
                    </c:forEach>
                </c:if>
            </div>
        </div>
    </div>
    <div class="row">
        <div class="col-xs-12">
            <div class="section">
                <c:if test="${not empty reviews}">
                    <div class="header"><spring:message code="title.last_reviews" /></div>
                    <div class="row">
                        <c:forEach var="article" items="${reviews}">
                            <div class="col-sm-3">
                                <ui:article position="top.analisis" article="${article}" />
                            </div>
                        </c:forEach>
                    </div>
                </c:if>

                <div class="header"><spring:message code="title.more_views" /></div>
                <c:if test="${not empty moreComments}">
                    <div class="header"><spring:message code="title.last_reviews" /></div>
                    <div class="row">
                        <c:forEach var="article" items="${moreComments}">
                            <div class="col-sm-3">
                                <ui:article position="left.mini" article="${article}" />
                            </div>
                        </c:forEach>
                    </div>
                </c:if>
            </div>
        </div>
    </div>
</ui:layout>
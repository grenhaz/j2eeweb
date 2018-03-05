<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="ui" uri="http://www.obarcia.com/tags" %>
<spring:message code="title.admin" var="title" />
<ui:layout title="${title}" tag="admin" flash="${flash}">
    <div class="section">
        <div class="header"><spring:message code="label.admin.index" /></div>
        <div class="row">
            <c:if test="${not empty lastComments}">
                <div class="col-sm-6">
                    <div class="header"><spring:message code="title.last_comments" /></div>
                    <div class="row">
                        <c:forEach var="comment" items="${lastComments}">
                            <div class="col-xs-12">
                                <ui:mcomment comment="${comment}" />
                            </div>
                        </c:forEach>
                    </div>
                </div>
            </c:if>
        </div>
    </div>
</ui:layout>
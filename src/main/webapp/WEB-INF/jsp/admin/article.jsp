<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="ui" uri="http://www.obarcia.com/tags" %>
<spring:message code="title.admin" var="title" />
<ui:layout title="${title}" tag="admin" flash="${flash}">
    <div class="section">
        <div class="header"><spring:message code="label.admin.article" />: <c:out value="${form.id}" /></div>
        <form:form commandName="form" method="POST">
            <form:hidden path="id" />
            <div class="row">
                <div class="col-xs-12">
                    <div class="form-group">
                        <form:label path="title"><spring:message code="label.title" /></form:label>
                        <form:input class="form-control" path="title" />
                        <form:errors path="title" cssClass="help-block help-error" />
                    </div>
                </div>
            </div>
            <div class="row">
                <div class="col-xs-12">
                    <div class="form-group">
                        <form:label path="description"><spring:message code="label.description" /></form:label>
                        <form:textarea rows="4" class="form-control" path="description" />
                        <form:errors path="description" cssClass="help-block help-error" />
                    </div>
                </div>
            </div>
            <div class="row">
                <div class="col-xs-12">
                    <div class="form-group">
                        <form:label path="content"><spring:message code="label.content" /></form:label>
                        <form:textarea rows="4" class="form-control" path="content" />
                        <form:errors path="content" cssClass="help-block help-error" />
                    </div>
                </div>
            </div>
            <div class="form-group">
                <form:button class="btn btn-primary"><spring:message code="button.save" /></form:button>
            </div>
        </form:form>
    </div>
</ui:layout>
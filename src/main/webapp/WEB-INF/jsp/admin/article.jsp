<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="ui" uri="http://www.obarcia.com/tags" %>
<spring:message code="title.admin" var="title" />
<ui:layout title="${title}" tag="admin" flash="${flash}">
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
        <div class="form-group">
            <form:button class="btn btn-primary"><spring:message code="button.save" /></form:button>
        </div>
    </form:form>
</ui:layout>
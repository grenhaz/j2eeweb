<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="ui" uri="http://www.obarcia.com/tags" %>
<spring:message code="title.contact" var="title" />
<ui:layout title="${title}">
    <h1>${title}</h1>
    
    <form:form method="POST" commandName="model">
        <div class="form-group">
            <form:label class="control-label" path="name"><spring:message code="label.name" /></form:label>
            <form:input class="form-control" path="name" />
            <form:errors path="name" cssClass="help-block"/>
        </div>
        <div class="form-group">
            <form:label class="control-label" path="email"><spring:message code="label.email" /></form:label>
            <form:input class="form-control" path="email" />
            <form:errors path="email" cssClass="help-block"/>
        </div>
        <div class="form-group">
            <form:label class="control-label" path="subject"><spring:message code="label.subject" /></form:label>
            <form:input class="form-control" path="subject" />
            <form:errors path="subject" cssClass="help-block"/>
        </div>
        <div class="form-group">
            <form:label class="control-label" path="message"><spring:message code="label.message" /></form:label>
            <form:textarea class="form-control" rows="8" path="message" />
            <form:errors path="message" cssClass="help-block"/>
        </div>
        <div class="form-group">
            <form:button class="btn btn-primary"><spring:message code="button.submit" /></form:button>
        </div>
    </form:form>
</ui:layout>
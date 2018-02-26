<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="ui" uri="http://www.obarcia.com/tags" %>
<spring:message code="title.contact" var="title" />
<ui:layout title="${title}">
    <h1>${title}</h1>
    
    <div class="alert alert-success">Message sended</div>
</ui:layout>
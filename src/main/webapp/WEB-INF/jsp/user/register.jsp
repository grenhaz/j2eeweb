<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="ui" uri="http://www.obarcia.com/tags" %>
<spring:message code="title" var="title" />
<ui:layout title="${title}" tag="user">
    <form:form commandName="model" method="POST">
        <form:input class="form-control" path="username" />
        <form:input class="form-control" path="password" />
    </form:form>
</ui:layout>

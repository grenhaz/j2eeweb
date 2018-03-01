<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="ui" uri="http://www.obarcia.com/tags" %>
<spring:message code="title.error" var="title" />
<ui:layout title="${title}" classCss="error" tag="games">
    <div><spring:message code="${message}" /></div>
    <div><c:out value="${exception}" /></div>
</ui:layout>
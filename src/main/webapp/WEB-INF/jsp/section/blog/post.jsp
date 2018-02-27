<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="ui" uri="http://www.obarcia.com/tags" %>
<%@taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<spring:message code="title.blog" var="title" />
<ui:layout title="${title}">
    <h1><c:out value="${model.title}" /></h1>
    <div class="post">
        <div class="content"><c:out value="${model.content}" /></div>
        <div class="publish"><c:out value="${model.formattedPublish}" /></div>
    </div>
    
    <h3>COMMENTS</h3>
    
    <!-- ADD COMMENTS -->
    <sec:authorize access="isAuthenticated()">
        <form:form method="POST" commandName="comment">
            <div class="form-group">
                <form:textarea class="form-control" rows="8" path="content" />
            </div>
            <form:button class="btn btn-primary">Submit</form:button>
        </form:form>
    </sec:authorize>

    <!-- COMMENTS -->
    <div class="comments"></div>
</ui:layout>
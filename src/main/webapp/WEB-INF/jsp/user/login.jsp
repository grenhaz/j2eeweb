<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="ui" uri="http://www.obarcia.com/tags" %>
<spring:message code="title.login" var="title" />
<ui:layout title="${title}">
    <h1>${title}</h1>
    
    <form method="POST">
        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
        
        <div class="form-group">
            <label class="control-label" id="username">Username</label>
            <input class="form-control" id="username" name="username" value="test" />
        </div>
        <div class="form-group">
            <label class="control-label" id="password">Password</label>
            <finput type="password" class="form-control" id="password" name="password" value="test" />
        </div>
        <div class="form-group">
            <form:button class="btn btn-primary">Submit</form:button>
        </div>
    </form>
</ui:layout>

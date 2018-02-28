<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="ui" uri="http://www.obarcia.com/tags" %>
<c:set var="base" value="${pageContext.getServletContext().getContextPath()}" />
<spring:message code="title.login" var="title" />
<ui:layout title="${title}">
    <div class="login">
        <h1>${title}</h1>

        <div class="section">
            <div class="header"><spring:message code="title.login.info" /></div>
            <div class="row">
                <div class="col-xs-4 left">
                    <form method="POST">
                        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>

                        <div class="form-group">
                            <label class="control-label" id="username"><spring:message code="label.username" /></label>
                            <input class="form-control" id="username" name="username" value="user" />
                        </div>
                        <div class="form-group">
                            <label class="control-label" id="password"><spring:message code="label.password" /></label>
                            <input type="password" class="form-control" id="password" name="password" value="password" />
                        </div>
                        <div><a class="small" href="${base}/userforgot"><spring:message code="label.user.forgot" /></a></div>
                        <br />
                        <div class="form-group">
                            <input type="submit" class="btn btn-primary" value="<spring:message code="button.login" />" />
                        </div>
                    </form>
                </div>
                <div class="col-xs-8 right">
                    <div class="text-center">
                        <h3><spring:message code="label.user.register1" /></h3>
                        <h1><a href="${base}/register"><spring:message code="label.user.register2" /></a></h1>
                    </div>
                </div>
            </div>
        </div>
    </div>
</ui:layout>

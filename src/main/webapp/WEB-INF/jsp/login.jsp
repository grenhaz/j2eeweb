<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="ui" uri="http://www.obarcia.com/tags" %>
<spring:message code="title.login" var="title" />
<ui:layout title="${title}">
    <div class="login">
        <h1>${title}</h1>

        <div class="section">
            <div class="header">PARA INICIAR LA SESIÓN DEBES ESTAR REGISTRADO COMO USUARIO</div>
            <div class="row">
                <div class="col-xs-4 left">
                    <form method="POST">
                        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>

                        <div class="form-group">
                            <label class="control-label" id="username">Username</label>
                            <input class="form-control" id="username" name="username" value="user" />
                        </div>
                        <div class="form-group">
                            <label class="control-label" id="password">Password</label>
                            <input type="password" class="form-control" id="password" name="password" value="password" />
                        </div>
                        <div class="form-group">
                            <input type="submit" class="btn btn-primary" value="<spring:message code="button.login" />" />
                        </div>
                    </form>
                </div>
                <div class="col-xs-4 right">

                </div>
            </div>
        </div>
    </div>
</ui:layout>

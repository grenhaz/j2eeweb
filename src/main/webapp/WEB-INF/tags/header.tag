<%@tag description="Header" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@attribute name="tag" required="true" rtexprvalue="true" %>
<%@attribute name="username" rtexprvalue="true" %>
<header>
    <div class="container">
        <div class="row">
            <div class="col-xs-12">
                <!-- LOGO -->
                <!-- BUSCADOR -->
                <!-- USER -->
                <div class="user">
                    <sec:authorize access="!isAuthenticated()">
                        <div class="avatar">
                            <a href="<c:url value="/login" />"><img src="<c:url value="/resources/images/anonymous.png" />" /></a>
                        </div>
                        <div class="text">
                            <div class="username"><a href="<c:url value="/login" />"><spring:message code="label.user.anonymous" /></a></div>
                            <div class="actions"><a href="<c:url value="/login" />"><spring:message code="label.user.login" /></a> | <a href="<c:url value="/register" />"><spring:message code="label.user.register" /></a></div>
                        </div>
                    </sec:authorize>
                    <sec:authorize access="isAuthenticated()">
                        <div class="avatar">
                            <a href="<c:url value="/profile" />"><img src="<c:url value="/resources/images/anonymous.png" />" /></a>
                        </div>
                        <div class="text">
                            <div class="username"><a href="<c:url value="/profile" />"><c:out value="${username}" /></a></div>
                            <div class="actions">
                                <sec:authorize access="hasRole('ROLE_ADMIN')">
                                    <a href="<c:url value="/admin/index" />"><spring:message code="label.user.admin" /></a> | 
                                </sec:authorize>
                                <a href="<c:url value="/logout" />"><spring:message code="label.user.logout" /></a>
                            </div>
                        </div>
                    </sec:authorize>
                </div>
            </div>
        </div>
        <div class="row">
            <div class="col-xs-12">
                <div class="sections">
                    <ul class="list-sections">
                        <li ${tag == "games" ? "class='active'" : "" }>
                            <a href="<c:url value="/" />">Inicio</a>
                        </li>
                        <li ${tag == "pc" ? "class='active'" : "" }>
                            <a href="<c:url value="/web/pc" />">PC</a>
                        </li>
                    </ul>
                </div>
            </div>
        </div>
    </div>
</header>
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
                <!-- TODO: LOGO -->
                <!-- TODO: BUSCADOR -->
                <!-- USER -->
                <div class="user">
                    <sec:authorize access="!isAuthenticated()">
                        <div class="avatar">
                            <a href="<c:url value="/user/login" />"><img src="<c:url value="/resources/images/anonymous.png" />" /></a>
                        </div>
                        <div class="text">
                            <div class="username"><a href="<c:url value="/user/login" />"><spring:message code="label.user.anonymous" /></a></div>
                            <div class="actions"><a href="<c:url value="/user/login" />"><spring:message code="label.user.login" /></a> | <a href="<c:url value="/user/register" />"><spring:message code="label.user.register" /></a></div>
                        </div>
                    </sec:authorize>
                    <sec:authorize access="isAuthenticated()">
                        <div class="avatar">
                            <a href="<c:url value="/user/profile" />"><img src="<c:url value="/resources/images/anonymous.png" />" /></a>
                        </div>
                        <div class="text">
                            <div class="username"><a href="<c:url value="/user/profile" />"><c:out value="${username}" /></a></div>
                            <div class="actions">
                                <sec:authorize access="hasRole('ROLE_ADMIN')">
                                    <a href="<c:url value="/admin/index" />"><spring:message code="label.user.admin" /></a> | 
                                </sec:authorize>
                                <a href="<c:url value="/user/logout" />"><spring:message code="label.user.logout" /></a>
                            </div>
                        </div>
                    </sec:authorize>
                </div>
            </div>
        </div>
        <div class="menu">
            <div class="row">
                <div class="col-xs-12">
                    <ul class="list-sections">
                        <li ${tag == "games" ? "class='active'" : "" }>
                            <a href="<c:url value="/" />"><spring:message code="label.section.home" /></a>
                        </li>
                        <li ${tag == "pc" ? "class='active'" : "" }>
                            <a href="<c:url value="/web/pc" />"><spring:message code="label.section.pc" /></a>
                        </li>
                        <li ${tag == "ps4" ? "class='active'" : "" }>
                            <a href="<c:url value="/web/ps4" />"><spring:message code="label.section.ps4" /></a>
                        </li>
                        <li ${tag == "xbox" ? "class='active'" : "" }>
                            <a href="<c:url value="/web/xbox" />"><spring:message code="label.section.xbox" /></a>
                        </li>
                        <li ${tag == "wii" ? "class='active'" : "" }>
                            <a href="<c:url value="/web/wii" />"><spring:message code="label.section.wii" /></a>
                        </li>
                        <li ${tag == "user" ? "class='active'" : "" }>
                            <sec:authorize access="!isAuthenticated()">
                                <a href="<c:url value="/user/login" />"><spring:message code="label.section.community" /></a>
                            </sec:authorize>
                            <sec:authorize access="isAuthenticated()">
                                <a href="<c:url value="/user/profile" />"><spring:message code="label.section.community" /></a>
                            </sec:authorize>
                        </li>
                    </ul>
                </div>
            </div>
        </div>
    </div>
</header>
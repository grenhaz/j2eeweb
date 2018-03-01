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
                                <a href="<c:url value="/user/profile" />"><spring:message code="label.user.profile" /></a> |
                                <a href="<c:url value="/user/logout" />"><spring:message code="label.user.logout" /></a>
                            </div>
                        </div>
                    </sec:authorize>
                </div>
            </div>
        </div>
    </div>
</header>
<header>
    <div class="container">
        <div class="row">
            <div class="col-xs-12">
                <ul class="list-tags">
                    <li ${tag == "games" ? "class='active'" : "" }>
                        <a href="<c:url value="/" />"><spring:message code="label.section.home" /></a>
                    </li>
                    <spring:eval expression="@configProperties.getProperty('sections.tags')" var="tags" />
                    <c:forEach items="${tags}" var="t">
                        <li ${tag == t.toLowerCase() ? "class='active'" : "" }>
                            <a href="<c:url value="/web/${t.toLowerCase()}" />"><c:out value="${t}" /></a>
                        </li>
                    </c:forEach>
                    <li ${tag == "user" ? "class='active'" : "" }>
                        <sec:authorize access="!isAuthenticated()">
                            <a href="<c:url value="/user/login" />"><spring:message code="label.section.community" /></a>
                        </sec:authorize>
                        <sec:authorize access="isAuthenticated()">
                            <a href="<c:url value="/user/profile" />"><spring:message code="label.section.community" /></a>
                        </sec:authorize>
                    </li>
                    <sec:authorize access="hasRole('ROLE_ADMIN')">
                        <li ${tag == 'admin' ? "class='active'" : "" }>
                            <a href="<c:url value="/admin/index" />"><spring:message code="label.section.admin" /></a>
                        </li>
                    </sec:authorize>
                </ul>
                <ul class="list-stags">
                    <c:if test="${tag == 'user'}">
                        <sec:authorize access="!isAuthenticated()">
                            <li><a href="<c:url value="/user/login" />"><spring:message code="label.user.login" /></a></li>
                            <li><a href="<c:url value="/user/register" />"><spring:message code="label.user.register" /></a></li>
                        </sec:authorize>
                        <sec:authorize access="isAuthenticated()">
                            <li><a href="<c:url value="/user/profile" />"><spring:message code="label.user.profile" /></a></li>
                        </sec:authorize>
                    </c:if>
                    <c:if test="${tag == 'admin'}">
                        <sec:authorize access="hasRole('ROLE_ADMIN')">
                            <li><a href="<c:url value="/admin/users" />"><spring:message code="label.admin.users" /></a></li>
                            <li><a href="<c:url value="/admin/articles" />"><spring:message code="label.admin.articles" /></a></li>
                        </sec:authorize>
                    </c:if>
                    <c:if test="${tag != 'user' and tag != 'admin'}">
                        <li><a class="go" href="<c:url value="#${tag}_all" />"><spring:message code="articles.type.home" /></a></li>
                        <spring:eval expression="@configProperties.getProperty('sections.types')" var="types" />
                        <c:forEach items="${types}" var="t">
                            <li><a class="go" href="<c:url value="#${tag}_${t}" />"><spring:message code="articles.type.${t}" /></a></li>
                        </c:forEach>
                    </c:if>
                </ul>
            </div>
        </div>
    </div>
</header>
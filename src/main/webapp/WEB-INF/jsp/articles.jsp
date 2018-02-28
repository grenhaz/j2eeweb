<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="ui" uri="http://www.obarcia.com/tags" %>
<spring:message code="title" var="title" />
<c:set var="base" value="${pageContext.getServletContext().getContextPath()}" />
<ui:layout title="${title}">
    <div class="row">
        <div class="col-sm-8">
            <div class="section">
                <c:if test="${not empty importants}">
                    <div class="header">DESTACADO</div>
                    <div class="row">
                        <c:forEach var="article" items="${importants}">
                            <div class="col-sm-4">
                                <ui:article position="top" article="${article}" />
                            </div>
                        </c:forEach>
                    </div>
                </c:if>
                
                <div class="header">LO �LTIMO EN VIDEOJUEGOS</div>
                <div class="articles">
                    <ui:articles base="${base}" articles="${articles}" />
                </div>
            </div>
        </div>
        <div class="col-sm-4">
            <div class="section">
                <c:if test="${not empty guides}">
                    <div class="header">TRUCOS Y GUIAS</div>
                    <c:forEach var="article" items="${guides}">
                        <ui:article position="top" article="${article}" />
                    </c:forEach>
                </c:if>
            </div>
        </div>
    </div>
    <div class="row">
        <div class="col-xs-12">
            <div class="section">
                <c:if test="${not empty reviews}">
                    <div class="header">LOS �LTIMOS AN�LISIS</div>
                    <div class="row">
                        <c:forEach var="article" items="${reviews}">
                            <div class="col-sm-3">
                                <ui:article position="top.analisis" article="${article}" />
                            </div>
                        </c:forEach>
                    </div>
                </c:if>

                <div class="header">LO M�S VISTO Y COMENTADO</div>
            </div>
        </div>
    </div>
</ui:layout>
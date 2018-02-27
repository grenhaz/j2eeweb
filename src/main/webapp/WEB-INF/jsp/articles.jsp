<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="ui" uri="http://www.obarcia.com/tags" %>
<spring:message code="title" var="title" />
<ui:layout title="${title}">
    <div class="row">
        <div class="col-sm-8">
            <div class="section">
                <c:if test="${not empty destacados}">
                    <div class="header">DESTACADO</div>
                    <div class="row">
                        <c:forEach var="article" items="${destacados}">
                            <div class="col-sm-4">
                                <ui:article position="top" article="${article}" />
                            </div>
                        </c:forEach>
                    </div>
                </c:if>
                
                <c:if test="${not empty articles}">
                    <div class="header">LO ÚLTIMO EN VIDEOJUEGOS</div>
                    <c:forEach var="article" items="${articles}">
                        <ui:article position="left" article="${article}" />
                    </c:forEach>
                </c:if>
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
                <c:if test="${not empty analisis}">
                    <div class="header">LOS ÚLTIMOS ANÁLISIS</div>
                    <div class="row">
                        <c:forEach var="article" items="${analisis}">
                            <div class="col-sm-3">
                                <ui:article position="top.analisis" article="${article}" />
                            </div>
                        </c:forEach>
                    </div>
                </c:if>

                <div class="header">LO MÁS VISTO Y COMENTADO</div>
            </div>
        </div>
    </div>
</ui:layout>
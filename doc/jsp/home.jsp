<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="ui" uri="http://www.obarcia.com/tags" %>
<%@taglib prefix="test" uri="/tlds/test" %>
<ui:layout title="Home">
    <div class="container">
        <div><a href="<c:url value="/user/${model.id}" />">To User</a></div>
        <div><a href="<c:url value="/logout" />">Logout</a></div>
        <span>URL: ${pageContext.request.contextPath}</span>
        <h2>Usuario id: ${model.id}</h2>
        <h2>Usuario nombre: ${model.nombre}</h2>
        <c:out value="${pageContext.request.remoteUser}"/>
        
        <!-- PROGRESS BAR -->
        <ui:progress value="50" stripper="true" animated="true" />
        
        <!-- NAVBAR -->
        <ui:navbar classCss="navbar-default" brandtitle="Brand" brandurl="#">
            <ui:navitem title="LINK" dropdown="true">
                <ui:navitem title="LINK 2" url="#" />
                <ui:navitem title="LINK 3" url="#" />
            </ui:navitem>
        </ui:navbar>
        
        <!-- LIST -->
        <ui:list>
            <ui:list-item type="danger" href="#">PRUEBA 1</ui:list-item>
            <ui:list-item>PRUEBA 2</ui:list-item>
        </ui:list>

        <form:form commandName="model">
            <div class="row">
                <!-- INPUT -->
                <div class="col-sm-4 form-group">
                    <form:label path="id">BS - INPUT</form:label>
                    <ui:input path="id" />
                </div>
                <!-- SELECT 2 -->
                <div class="col-sm-4 form-group">
                    <form:label path="id">BS - SELECT2</form:label>
                    <ui:select2 path="id">
                        <ui:option value="10" label="10" />
                        <ui:option value="20" label="20" />
                        <ui:option value="1201" label="1201" />
                        <ui:option value="40" label="40" />
                    </ui:select2>
                </div>
                <!-- DATE -->
                <div class="col-sm-4 form-group">
                    <form:label path="id">BS - DATEPICKER</form:label>
                    <ui:date path="nombre" />
                </div>
            </div>
            
            <!-- ALERT -->
            <ui:alert type="danger">
                Hola Mundo
                <ui:date path="nombre" />
            </ui:alert>
            
            <!-- SUBMIT -->
            <ui:submit value="Submit" />
        </form:form>
    </div>
</ui:layout>
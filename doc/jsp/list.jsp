<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="ui" uri="http://www.obarcia.com/tags" %>
<ui:layout title="List">
    <div class="container">
        <ui:list>
            <c:forEach items="${users}" var="user">
                <ui:list-item>${user.nombre}</ui:list-item>
            </c:forEach>
        </ui:list>
    </div>
</ui:layout>
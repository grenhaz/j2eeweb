<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="ui" uri="http://www.obarcia.com/tags" %>
<ui:layout title="${model.title}" tag="games">
    <div class="section">
        <div class="header"><c:out value="${model.tags}" /></div>
        <div class="row">
            <div class="col-sm-8">
                <h2><c:out value="${model.title}" /></h2>
                <h3><c:out value="${model.description}" /></h3>
                <div><c:out value="${model.content}" /></div>
            </div>
            <div class="col-sm-4">
            </div>
        </div>
    </div>
</ui:layout>
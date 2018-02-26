<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="ui" uri="http://www.obarcia.com/tags" %>
<spring:message code="title.blog" var="title" />
<ui:layout title="${title}">
    <h1>${title}</h1>
    
    <div class="row">
        <div class="col-sm-8">
            <!-- BLOGS -->
        </div>
        <div class="col-sm-4">
            <!-- INFO -->
            <h4>CONTACT US</h4>
            <p>Come Have Coffee with Us.</p>
            <p>Sed ut perspiciatis unde omnis iste natus error sit voluptatem accusantium doloremque laudantium</p>
        </div>
    </div>
</ui:layout>
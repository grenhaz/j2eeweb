<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="ui" uri="http://www.obarcia.com/tags" %>
<ui:layout title="${model.title}" tag="games">
    <div class="section">
        <div class="header"><c:out value="${model.formattedTags}" /></div>
        <div class="row">
            <div class="col-sm-8">
                <h2><c:out value="${model.title}" /></h2>
                <h3><c:out value="${model.description}" /></h3>
                <div><c:out value="${model.content}" /></div>
            </div>
            <div class="col-sm-4">
            </div>
        </div>
        
        <!-- ADD COMMENTS -->
        <sec:authorize access="isAuthenticated()">
            <hr />
            <div class="header"><spring:message code="title.addcomment" /></div>
            <form:form commandName="comment">
                <div class="row">
                    <div class="col-xs-12">
                        <div class="form-group">
                            <form:textarea class="form-control" path="content" rows="8" />
                        </div>
                    </div>
                </div>
                <div class="row">
                    <div class="col-xs-12">
                        <div class="form-group">
                            <form:button class="btn btn-primary"><spring:message code="button.comment" /></form:button>
                        </div>
                    </div>
                </div>
            </form:form>
        </sec:authorize>
        <!-- COMMENTS -->
        <div class="comments"></div>
    </div>
    <script>
        $(function() {
            refreshBlock('.comments', __BASE + 'ajax/comments/${model.id}');
        });
    </script>
</ui:layout>
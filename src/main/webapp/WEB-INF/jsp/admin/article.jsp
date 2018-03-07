<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="ui" uri="http://www.obarcia.com/tags" %>
<spring:message code="title.admin" var="title" />
<ui:layout title="${title}" tag="admin" flash="${flash}">
    <div class="section">
        <div class="header"><spring:message code="label.admin.article" />: <c:out value="${form.id}" /></div>
        <form:form commandName="form" method="POST">
            <ul class="nav nav-tabs" role="tablist">
                <li role="presentation" class="active"><a href="#tab-data" aria-controls="tab-data" role="tab" data-toggle="tab"><spring:message code="label.admin.data" /></a></li>
                <li role="presentation"><a href="#tab-content" aria-controls="tab-content" role="tab" data-toggle="tab"><spring:message code="label.content" /></a></li>
                <c:if test="${id != 0}">
                    <li role="presentation"><a href="#tab-comments" aria-controls="tab-comments" role="tab" data-toggle="tab"><spring:message code="label.admin.coments" /></a></li>
                </c:if>
            </ul>
            <div class="tab-content">
                 <div role="tabpanel" class="tab-pane active" id="tab-data">
                    <div class="tab-container">
                        <div class="row">
                            <div class="col-xs-4 col-sm-4">
                                <div class="form-group">
                                    <form:label path="type"><spring:message code="label.type" /></form:label>
                                    <form:select class="form-control" path="type">
                                        <spring:eval expression="@configProperties.getProperty('sections.types')" var="types" />
                                        <c:forEach items="${types}" var="t">
                                            <form:option value="${t}"><spring:message code="article.type.${t}" /></form:option>
                                        </c:forEach>
                                    </form:select>
                                    <form:errors path="type" cssClass="help-block help-error" />
                                </div>
                            </div>
                            <div class="col-xs-4 col-sm-4">
                                <div class="form-group">
                                    <form:label path="publish"><spring:message code="label.publish" /></form:label>
                                    <form:input class="form-control" path="publish" />
                                    <form:errors path="publish" cssClass="help-block help-error" />
                                </div>
                            </div>
                            <div class="col-xs-4 col-sm-4">
                                <div class="form-group">
                                    <form:label path="active"><spring:message code="label.active" /></form:label>
                                    <form:checkbox class="form-control" path="active" />
                                    <form:errors path="active" cssClass="help-block help-error" />
                                </div>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-xs-4 col-sm-4">
                                <div class="form-group">
                                    <form:label path="important"><spring:message code="label.important" /></form:label>
                                    <form:checkbox class="form-control" path="important" />
                                    <form:errors path="important" cssClass="help-block help-error" />
                                </div>
                            </div>
                            <div class="col-xs-4 col-sm-4">
                                <div class="form-group">
                                    <form:label path="tags"><spring:message code="label.tags" /></form:label>
                                    <spring:eval expression="@configProperties.getProperty('sections.tags')" var="tags" />
                                    <c:forEach items="${tags}" var="t">
                                        <div><form:radiobutton path="tags" value="${t}" /><c:out value="${t}" /></div>
                                    </c:forEach>
                                    <form:errors path="tags" cssClass="help-block help-error" />
                                </div>
                            </div>
                            <div class="col-xs-4 col-sm-4">
                                <div class="form-group">
                                    <form:label path="score"><spring:message code="label.score" /></form:label>
                                    <form:input class="form-control" path="score" />
                                    <form:errors path="score" cssClass="help-block help-error" />
                                </div>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-xs-12">
                                <div class="form-group">
                                    <form:label path="title"><spring:message code="label.title" /></form:label>
                                    <form:input class="form-control" path="title" />
                                    <form:errors path="title" cssClass="help-block help-error" />
                                </div>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-xs-12">
                                <div class="form-group">
                                    <form:label path="description"><spring:message code="label.description" /></form:label>
                                    <form:textarea rows="4" class="form-control" path="description" />
                                    <form:errors path="description" cssClass="help-block help-error" />
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <div role="tabpanel" class="tab-pane" id="tab-content">
                    <div class="tab-container">
                        <div class="row">
                            <div class="col-xs-12">
                                <div class="form-group">
                                    <form:textarea class="form-control" path="content" style="height:500px;" />
                                    <form:errors path="content" cssClass="help-block help-error" />
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <c:if test="${id != 0}">
                    <div role="tabpanel" class="tab-pane" id="tab-comments">
                        <div class="tab-container">
                            <table id="records" class="table table-striped table-bordered" cellspacing="0">
                                <thead>
                                    <tr>
                                        <th><spring:message code="column.id" /></th>
                                        <th><spring:message code="column.user" /></th>
                                        <th><spring:message code="column.text" /></th>
                                        <th><spring:message code="column.publish" /></th>
                                        <th style="width:1px;"></th>
                                    </tr>
                                </thead>
                            </table>
                        </div>
                    </div>
                </c:if>
            </div>
            <div class="form-group">
                <form:button class="btn btn-primary"><spring:message code="button.save" /></form:button>
            </div>
        </form:form>  
        <script>
            $(function() {
                $('*[name=content]').tinymce({});
                
                $('#records').DataTable({
                    "language": {
                        "url": "<c:url value="/resources/datatables/i18n/es.json" />"
                    },
                    "order": [[ 3, "desc" ]],
                    "processing": true,
                    "serverSide": true,
                    "ajax": "<c:url value="/admin/ajax/comments/${id}" />",
                    "columns": [
                        {
                            "data": "id"
                        }, {
                            "data": "user",
                            "searchable": false,
                            "render": function ( data, type, row, meta ) {
                                return data.nickname;
                            }
                        }, {
                            "data": "content"
                        }, {
                            "data": "formattedPublish",
                            "name": "publish",
                            "className": "text-center"
                        }, {
                            "data": "erased",
                            "searchable": false,
                            "orderable": false,
                            "render": function ( data, type, row, meta ) {
                                return "<i data-id='" + row.id + "' data-value='" + data+ "' class='btn-active fa " + (data ? 'fa-ban text-danger' : 'fa-check-circle-o text-success') + "'></i>";
                            }
                        }
                    ]
                });
                
                $('#records').on('click', '.btn-active', function(e) {
                    $activate(this, '<c:url value="/admin/article/${form.id}" />/erase', {
                        '<c:out value="${_csrf.parameterName}" />': '<c:out value="${_csrf.token}" />',
                        'comment': $(this).data('id')
                    }, $(this).data('value'), 'fa-ban text-danger', 'fa-check-circle-o text-success');
                });
            });
        </script>
    </div>
</ui:layout>
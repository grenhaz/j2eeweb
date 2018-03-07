<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="ui" uri="http://www.obarcia.com/tags" %>
<spring:message code="title.admin" var="title" />
<ui:layout title="${title}" tag="admin" flash="${flash}">
    <div class="section">
        <div class="header"><spring:message code="label.admin.article" />: <c:out value="${form.id}" /></div>
        <form:form commandName="form" method="POST">
            <form:hidden path="id" />
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
            <div class="row">
                <div class="col-xs-12">
                    <div class="form-group">
                        <form:label path="content"><spring:message code="label.content" /></form:label>
                        <form:textarea rows="4" class="form-control" path="content" />
                        <form:errors path="content" cssClass="help-block help-error" />
                    </div>
                </div>
            </div>
            <div class="form-group">
                <form:button class="btn btn-primary"><spring:message code="button.save" /></form:button>
            </div>
        </form:form>
        <div>
            <div class="header"><spring:message code="label.admin.coments" /></div>
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
                    "ajax": "<c:url value="/admin/ajax/comments/${form.id}" />",
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
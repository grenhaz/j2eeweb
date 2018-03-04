<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="ui" uri="http://www.obarcia.com/tags" %>
<spring:message code="title.admin" var="title" />
<ui:layout title="${title}" tag="admin">
    <div class="section">
        <div class="header"><spring:message code="label.admin.articles" /></div>
        <table id="records" class="table table-striped table-bordered" cellspacing="0">
            <thead>
                <tr>
                    <th><spring:message code="column.id" /></th>
                    <th><spring:message code="column.title" /></th>
                    <th><spring:message code="column.description" /></th>
                    <th><spring:message code="column.type" /></th>
                    <th><spring:message code="column.tags" /></th>
                    <th><spring:message code="column.publish" /></th>
                    <th style="width:1px;"><spring:message code="column.active" /></th>
                    <th style="width:1px;"></th>
                </tr>
            </thead>
        </table>
    </div>
    <script>
        $(function() {
            $('#records').DataTable({
                "language": {
                    "url": "<c:url value="/resources/datatables/i18n/es.json" />"
                },
                "processing": true,
                "serverSide": true,
                "ajax": "<c:url value="/admin/ajax/articles" />",
                "columns": [
                    {
                        "data": "id",
                        "searchable": true
                    }, {
                        "data": "title",
                        "searchable": true
                    }, {
                        "data": "description",
                        "searchable": true
                    }, {
                        "data": "type",
                        "searchable": true
                    }, {
                        "data": "formattedTags",
                        "name": "tags",
                        "orderable": false,
                        "searchable": true
                    }, {
                        "data": "formattedPublish",
                        "name": "publish",
                        "className": "text-center",
                        "searchable": true
                    }, {
                        "data": "active",
                        "searchable": true,
                        "className": "text-center",
                        "render": function ( data, type, row, meta ) {
                            return "<i class='fa " + (data ? 'fa-check text-success' : 'fa-times text-danger') + "'></i>";
                        }
                    }, {
                        "orderable": false,
                        "searchable": false,
                        "render": function ( data, type, row, meta ) {
                            return "<a target='_blank' href='<c:url value="/admin/articles/" />" + row.id + "'><i class='fa fa-pencil'></i></a>";
                        }
                    }
                ]
            });
        });
    </script>
</ui:layout>
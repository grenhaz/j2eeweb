<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="ui" uri="http://www.obarcia.com/tags" %>
<spring:message code="title.admin" var="title" />
<ui:layout title="${title}" tag="admin" flash="${flash}">
    <div class="section">
        <div class="header"><spring:message code="label.admin.users" /></div>
        <table id="records" class="table table-striped table-bordered" cellspacing="0">
            <thead>
                <tr>
                    <th><spring:message code="column.id" /></th>
                    <th><spring:message code="column.email" /></th>
                    <th><spring:message code="column.nickname" /></th>
                    <th><spring:message code="column.user_role" /></th>
                    <th style="width:1px;"></th>
                    <th><spring:message code="column.created" /></th>
                    <th style="width:1px;"><spring:message code="column.active" /></th>
                    <th style="width:1px;"></th>
                </tr>
            </thead>
        </table>
    </div>
    <script>
        $(function() {
            var $table = $('#records').DataTable({
                "language": {
                    "url": "<c:url value="/resources/datatables/i18n/es.json" />"
                },
                "processing": true,
                "serverSide": true,
                "ajax": "<c:url value="/admin/ajax/users" />",
                "columns": [
                    {
                        "data": "id",
                        "searchable": true
                    }, {
                        "data": "email",
                        "searchable": true
                    }, {
                        "data": "nickname",
                        "searchable": true
                    }, {
                        "data": "userRole",
                        "name": "user_role",
                        "searchable": true
                    }, {
                        "data": "avatar",
                        "orderable": false,
                        "render": function ( data, type, row, meta ) {
                            return "<img src='<c:url value="/data/avatars/" />" + (data !== null ? data : "anonymous.png") + "' style='width:24px;' />";
                        }
                    }, {
                        "data": "formattedCreated",
                        "name": "created",
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
                            return "<a href='<c:url value="/admin/user/" />" + row.id + "'><i class='fa fa-pencil'></i></a>";
                        }
                    }
                ]
            });
            // Apply the search
            /*$table.columns().every(function () {
                var that = this;
                if (this.searchable) {
                    var thf = $('#records thead th:eq(' + $(this.header()).index() + ')').get(0);
                    console.log(thf);
                    $('<input />')
                        .on('keyup change', function () {
                            if (that.search() !== this.value) {
                                that
                                    .search(this.value)
                                    .draw();
                            }
                        })
                        .appendTo(thf);
                }
            });*/
        });
    </script>
</ui:layout>
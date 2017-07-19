

$(document).ready(function() {

    $('#ocs-categories-management-table').DataTable( {
        "processing": true,
        "serverSide": true,
        "ajax": "/spring-jp/admin/ocs/categories-management/categories",
        "columns": [
                    { "data": "id", "orderable": true, "searchable": false},
                    { "data": "name", "orderable": true },
                    { "targets": -1, "data": null, "defaultContent": "", "orderable": false}
                ],
		"createdRow": function ( row, data, index ) {
		    $('td', row).eq(2).append('<a title="Edit" href="/spring-jp/admin/ocs/categories-management/edit/' + data["id"] + '" class="action" ><i class="fa fa-pencil" aria-hidden="true"></i></a>');
		    $('td', row).eq(2).append(appendDeleteAction(data["id"]));
		}
    } );
} );



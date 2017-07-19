

$(document).ready(function() {
	
	var table = $('#ocs-customer-accounts-management-table');

    $('#ocs-customer-accounts-management-table').DataTable( {
        "processing": true,
        "serverSide": true,
        "ajax": "/spring-jp/admin/ocs/customer-accounts-management/customer-accounts",
        "columns": [
                    { "data": "username", "orderable": true },
                    { "data": "firstName", "orderable": true },
                    { "data": "lastName", "orderable": true },
                    { "data": "email", "orderable": true },
                    { "targets": -1, "data": null, "defaultContent": "", "orderable": false}
                ],
		"createdRow": function ( row, data, index ) {
		    $('td', row).eq(4).append('<a title="Edit" href="/spring-jp/admin/ocs/customer-accounts-management/edit/' + data["username"] + '" class="action" ><i class="fa fa-pencil" aria-hidden="true"></i></a>');
		    $('td', row).eq(4).append(appendDeleteAction(data["username"]));
		}
    } );
} );



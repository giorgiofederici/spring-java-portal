

$(document).ready(function() {

    $('#ocs-products-management-table').DataTable( {
        "processing": true,
        "serverSide": true,
        "ajax": "/spring-jp/admin/ocs/products-management/products",
        "columns": [
                    { "data": "id", "orderable": true, "searchable": false},
                    { "data": "name", "orderable": true },
                    { "data": "categoryName", "orderable": true },
                    { "data": "price", "orderable": true },
                    { "data": "stock", "orderable": true },
                    { "targets": -1, "data": null, "defaultContent": "", "orderable": false}
                ],
		"createdRow": function ( row, data, index ) {
		    $('td', row).eq(5).append('<a title="Edit" href="/spring-jp/admin/ocs/products-management/edit/' + data["id"] + '" class="action" ><i class="fa fa-pencil" aria-hidden="true"></i></a>');
		    $('td', row).eq(5).append(appendDeleteAction(data["id"]));
		}
    } );
} );



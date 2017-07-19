
function appendDeleteAction(recordId){
	
	var token = $("meta[name='_csrf']").attr("content");
	var csrfName = $("meta[name='_csrf_name']").attr("content");
	
	var deleteRecordAction = 
		'<form action="/spring-jp/admin/users-management/delete/' + recordId + '" method="POST" style="display:inline;">' +
			'<input type="hidden" name="' + csrfName + '" value="' + token + '"/>' +
			'<button type="submit" class="link-button action" title="Delete">' + 
				'<i class="fa fa-trash-o" aria-hidden="true"></i>' +
			'</button>' +
		'</form>';
	
	return deleteRecordAction;		
}


$(document).ready(function() {

    $('#admin-users-management-table').DataTable( {
        "processing": true,
        "serverSide": true,
        "ajax": "/spring-jp/admin/users-management/users",
        "columns": [
                    { "data": "username", "orderable": true },
                    { "data": "email", "orderable": true },
                    { "data": "enabled", "orderable": true},
                    { "targets": -1, "data": null, "defaultContent": ""}
                ],
		"createdRow": function ( row, data, index ) {
		    if ( data["enabled"]) {
		        $('td', row).eq(2).empty().append('<i class="fa fa-check fa-success" aria-hidden="true"></i>');
		    } else {
		    	$('td', row).eq(2).empty().append('<i class="fa fa-times fa-fail" aria-hidden="true"></i>');
		    }
		    $('td', row).eq(3).append('<a title="Edit" href="/spring-jp/admin/users-management/edit/' + data["username"] + '" class="action" ><i class="fa fa-pencil" aria-hidden="true"></i></a>');
//		    $('td', row).eq(3).append('<a title="Delete" href="/spring-jp/admin/users-management/delete/' + data["username"] + '" class="action" ><i class="fa fa-trash-o" aria-hidden="true"></i></a>');
		    $('td', row).eq(3).append(appendDeleteAction(data["username"]));
		}
    } );
} );



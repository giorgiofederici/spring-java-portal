function appendDeleteAction(recordId){
	
	var token = $("meta[name='_csrf']").attr("content");
	var csrfName = $("meta[name='_csrf_name']").attr("content");
	
	var deleteRecordAction = 
		'<form action="/spring-jp/admin/ocs/categories-management/delete/' + recordId + '" method="POST" style="display:inline;">' +
			'<input type="hidden" name="' + csrfName + '" value="' + token + '"/>' +
			'<button type="submit" class="link-button action" title="Delete">' + 
				'<i class="fa fa-trash-o" aria-hidden="true"></i>' +
			'</button>' +
		'</form>';
	
	return deleteRecordAction;		
}
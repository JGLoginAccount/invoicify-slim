$(function(){
	
	$('#create-comapnies-form').submit(function(e) {
		e.preventDefault();
		let company={
			name: $('#company-name').val()
		};
		
		let headers = {
			'X-CSRF-TOKEN': $('#company-csrf').val()
		};
		
		let settings = {
			url:'/api/admin/companies',
			data:JSON.stringify(company),
			contentType:'application/json',
			headers: headers
		};
		
		$.post(settings)
		.done(function (data) {
			
		console.log('data recevied:',data);
		
		$('#company-list')
			.append(`<li>${data.name}</li>`);
		$('#company-name').val('')
		})
		

		
	
		
	});
	
});


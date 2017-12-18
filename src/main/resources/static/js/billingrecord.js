$(function(){
	
	$('#flat-fee-billing-record').submit(function(e) {
		e.preventDefault();
		
		let billingRecord={
			description:$('#description').val(),
			amount:$('#amount').val(),
			client: {
			id: $('#company-id').val()
			}
		};
		
		
		let headers = {
			'X-CSRF-TOKEN': $('#billing-record-csrf').val()
		};
		
		let settings = {
			url:'/api/flatfees',
			data:JSON.stringify(billingRecord),
			contentType:'application/json',
			headers: headers
		};
		
		$.post(settings)
		.done(function (data) {
			
		console.log('data recevied:',data);
		
		$('#billingTable')
		.append(`<tr>
		<td>${data.createdBy.username}</td>
		<td>${data.description}</td>
		<td>${data.client.name}</td>
		<td>${data.amount}</td>
		<td>$</td>
		<td></td>
		<td>${data.total}</td>
		</tr>`);
		
		}) 
		
			$('#company-id').val('');
			$('#amount').val('');
			$('#description').val('');

		
		
	});
	
	
	$('#rate-based-billing-record').submit(function(e) {
		e.preventDefault();
		
		let billingRecord={
			description:$('#descriptionRate').val(),
			rate:$('#rate').val(),
			
			client: {
				id: $('#rate-company-id').val()
				},
			
			
			quantity:$('#quantity').val()
		
		};
		
		
		let headers = {
			'X-CSRF-TOKEN': $('#billing-record-csrf').val()
		};
		
		let settings = {
			url:'/api/ratefees',
			data:JSON.stringify(billingRecord),
			contentType:'application/json',
			headers: headers
		};
		
		$.post(settings)
		.done(function (data) {
			
		console.log('data recevied:',data);
		
		$('#billingTable')
		.append(`<tr>
		<td>${data.createdBy.username}</td>
		<td>${data.description}</td>
		<td>${data.client.name}</td>
		<td>$</td>
		<td>${data.quantity}</td>
		<td>${data.rate}</td>
		<td>${data.total}</td>
		</tr>`);
		})
		

		$('#quantity').val('');
		$('#rate').val('');
		$('#rate-company-id').val('');
		$('#descriptionRate').val('');
	
		
	});
	
	
	
	
});


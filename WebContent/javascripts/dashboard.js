EasyClinica.pages['dashboard'] = function(){
	
	$('.reply-button').live('click', function() {
		postReply(this.form);
	});
	
	$('.message-button').live('click', function() {
		if(isEmpty($('#messageText').val())) return;
		disable($('.message-button'));
		var url = EasyClinica.cfg.services.postMessage;
		$.post(url, {"message.text": $('#messageText').val()}, function(data) {
			$('#allMessages').html(data);
			enable($('.message-button'));
			$('#messageText').val('');
		});
	});
	
	postReply = function(form) {
		if(isEmpty(form.elements['reply'].value)) return;
		disable($('.reply-button'));
		var url = EasyClinica.cfg.services.postReply.format(form.elements['message'].value);
		$.post(url, {reply: form.elements['reply'].value}, function(data) {
			$('#allMessages').html(data);
		});
	};

	refreshMessages = function() {
		var url = EasyClinica.cfg.services.recentMessages;
		$.get(url, function(data) {
			$('#allMessages').html(data);
		});
		
		startRefreshingMessages();
	};
	
	startRefreshingMessages = function() {
		setTimeout(refreshMessages, 60000 * 5); // 5 minutos		
	};
	
	startRefreshingMessages();
	
	
};
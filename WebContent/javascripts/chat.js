
jQuery.fn.exists = function(){return jQuery(this).length>0;}

var CHAT_BOX = 225;
var SPACER = 20;
var REFRESH_TIME = 1000;

function createChat(user) {
	drawChat(user);
	rearrangeChats();
}

function drawChat(user) {
	
	if($(chatName(user)).exists()) {
		showChat(user);
	}
	else {
		$("body").append(
			"<div class='chat-box' id='chat-box-" + user + "'>" +
			"	<div class='chat-head'>" +
			"		<div class='chat-title'>" +
						user +
			"		</div>"+
			"		<div class='chat-options'>" +
			"		<a href='javascript:void(0);' onclick=\"upDownChat('" + user + "');\">-</a> " + 
			"		<a href='javascript:void(0);' onclick=\"closeChat('" + user + "');\">X</a> " + 
			"		</div>" +
			"		<br clear='all'/>" +
			"	</div>"+
			"	<div class='chat-msgs' id='chat-msgs-"+user+"'>"+
			"	</div>"+
			"	<div class='chat-keyboard'>" +
			"		<textarea class='chat-textarea' onkeydown='return sayInChat(this,\""+user+"\", event);'></textarea>" +
			"	</div>"+
			"</div>"
		);
	}
}

function sayInChat(msgField, user, event) {
	if(event.keyCode == 13 && event.shiftKey == 0)  {
		message = $(msgField).val();

		$.post(EasyClinica.cfg.services.newChatMessage, { destinationLogin: user, message: message });
		
		$(msgField).val('');
		$(msgField).focus();
		
		return false;
	}
	
	return true;
}

function refreshChatMsgs() {
	$.ajax({
	  url: EasyClinica.cfg.services.getChatMessages,
	  success: showChatMessages
	});
}

function showChatMessages(data) {
	for(var i in data.msgs){
		var msg = data.msgs[i];
		var window = msg.to == data.user ? msg.from : msg.to;
		putMsg(window, msg.from, msg.message);
	}
	
	refreshAutomatically();
}

function putMsg(window, author, message) {
	var chatDiv = '#chat-msgs-'+window;
	createChat(window);
	var formattedMsg = "<span class='chatAuthor'>" + author + ":</span><span class='chatText'>" + message + "</span><br/>";
	$(chatDiv).html($(chatDiv).html() + formattedMsg);	
	$(chatDiv).attr({ scrollTop: $(chatDiv).attr("scrollHeight") });
}

function rearrangeChats() {
	var position = SPACER;
	
	$('.chat-box:visible').each(function(i) {
		$(this).css('right', position);
		position += CHAT_BOX + SPACER;
	});
}

function showChat(user) {
	$(chatName(user)).show();
}

function chatName(user) {
	return '#chat-box-'+user;
}

function closeChat(user) {
	$(chatName(user)).hide();
	rearrangeChats();
}

function upDownChat(user) {
	var display = $(chatName(user) + ' .chat-msgs').css('display') == 'none' ? 'block' : 'none';
	
	$(chatName(user)+' .chat-msgs').css('display', display);
	$(chatName(user)+' .chat-keyboard').css('display', display);
}

function refreshAutomatically() {
	setTimeout('refreshChatMsgs()',REFRESH_TIME);	
}

$(function() {
	refreshAutomatically();
});

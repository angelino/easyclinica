
jQuery.fn.exists = function(){return jQuery(this).length>0;}

var CHAT_BOX = 225;
var SPACER = 20;
var REFRESH_TIME = 1000;
var LIST_REFRESH_TIME = 5000;

function createChat(user) {
	drawChat(user);
	rearrangeChats();
}

function drawChat(user) {
	
	if(!$(chatName(user)).exists()) {
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
			"		<textarea class='chat-textarea' id='chat-textarea-"+user+"' onfocus=\"restoreColor('"+user+"', true)\" onkeydown='return sayInChat(this,\""+user+"\", event);'></textarea>" +
			"	</div>"+
			"</div>"
		);
	}
}

function restoreColor(user, color) {
	var rgb = color ? '#4169E1' : '#00BFFF';
	$(chatName(user) + ' .chat-head').css('background-color', rgb);
	$(chatName(user) + ' .chat-head').css('border-right', '1px solid ' + rgb);
	$(chatName(user) + ' .chat-head').css('border-left', '1px solid ' + rgb);
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
	if(!isChatVisible(window)) {
		$(chatName(window)).show();
		makeMsgVisible(window, true);
	}
	else if(!isMsgVisible(window)) {
		restoreColor(window, false);
	}
	
	var formattedMsg = "<span class='chatAuthor'>" + author + ": </span><span class='chatText'>" + message + "</span><br/>";
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

	
function chatName(user) {
	return '#chat-box-'+user;
}

function closeChat(user) {
	$(chatName(user)).hide();
	rearrangeChats();
}

function isChatVisible(user) {
	return $(chatName(user)).is(':visible');
}

function isMsgVisible(user) {
	return $(chatName(user) + ' .chat-msgs').is(':visible');
}

function upDownChat(user) {
	makeMsgVisible(user, !isMsgVisible(user));
}

function makeMsgVisible(user, visibility) {
	var display = visibility == true ? 'block' : 'none';
	$(chatName(user)+' .chat-msgs').css('display', display);
	$(chatName(user)+' .chat-keyboard').css('display', display);
	
	if(visibility == true) $('#chat-textarea-' + user).focus();
}

function refreshAutomatically() {
	setTimeout('refreshChatMsgs()',REFRESH_TIME);	
}

function onlineUsers() {
	$.ajax({
		  url: EasyClinica.cfg.services.getOnlineChatUsers,
		  success: showOnlineUsers
		});
}

function openChat(user) {
	createChat(user);
	$(chatName(user)).show();
	makeMsgVisible(user, true);
	$('#chat-textarea-' + user).focus();
}

function showOnlineUsers(data) {
	var online = "<h4>Chat</h4>";
	
	for(var i in data.list) {
		var user = data.list[i];
		online += "<p>";
		online += "<img src='/images/green-ball.png' width='15' height='15' />";
		online += "<a href=\"javascript:void(0);\" onclick=\"openChat('" + user + "');\">" + user + "</a>";
		online += "</p>";
	}
	
	if($("#onlineUsers").exists()) {
		$("#onlineUsers").html(online);
	}
	else {
		$(".boxright").append("<div id='onlineUsers' class='schedule-widget'>" + online + "</div>");
	}
	
	refreshOnlineUsersAutomatically();
}

function refreshOnlineUsersAutomatically() {
	setTimeout('onlineUsers()',LIST_REFRESH_TIME);	
}

function backToThePast() {
	$.ajax({
		  url: EasyClinica.cfg.services.getChatsInLast3Minutes,
		  success: function(data) {
			for(var i in data.msgs) {
				msg = data.msgs[i];
				var window = msg.to == data.user ? msg.from : msg.to;
				createChat(window);
				makeMsgVisible(window, false);
			}
			showChatMessages(data);
			
		  }
	});
}

$(function() {
	backToThePast();
	onlineUsers();
});

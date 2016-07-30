function localizeTime(t){
    t = parseInt(t);
    return $.formatDateTime("yy-mm-dd hh:ii", new Date(t));
}

function localizeDate(t){
    t = parseInt(t);
    return $.formatDateTime("yy-mm-dd", new Date(t));
}

function showInfo(text, autoHide) {
	noty({
		text : text,
		type : 'information',
		layout : 'topCenter',
		template: '<div class="noty_message noty_message_info"><span class="noty_text"></span><div class="noty_close"></div></div>',
		timeout: 2000,
		killer : true,
		callback : {
			afterShow : function() {
				if (autoHide) {
					var self = this;
					setTimeout(function() {
						self.close();
					}, 3000);
				}
			}
		}
	});
};

function showWarning(text, autoHide) {
	noty({
		text : text,
		type : 'warning',
		layout : 'topCenter',
		template: '<div class="noty_message noty_message_warning"><span class="noty_text"></span><div class="noty_close"></div></div>',
		killer : true,
		callback : {
			afterShow : function() {
				if (autoHide) {
					var self = this;
					setTimeout(function() {
						self.close();
					}, 3000);
				}
			}
		}
	});
};

function showError(text, autoHide) {
	noty({
		text : text,
		type : 'error',
		layout : 'topCenter',
		killer : true,
		template: '<div class="noty_message noty_message_error"><span class="noty_text"></span><div class="noty_close"></div></div>',
		callback : {
			afterShow : function() {
				if (autoHide) {
					var self = this;
					setTimeout(function() {
						self.close();
					}, 3000);
				}
			}
		}
	});
};



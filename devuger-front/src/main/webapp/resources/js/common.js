var $response = null;

$.ajaxSetup({
	dataType : "json",
	type : "POST",
	async : true,
	cache : false,
	beforeSend: function(xhr) 
	{
	},
	complete : function() {
	},
	error: function(json, textStatus, errorThrown)
	{
//		alert('Ajax Error. :: ' + json +" , "+ textStatus +" , "+ errorThrown);
	}
});

function isSuccess(json)
{
	if(json.result != "success") {
		notyAlert("error", json.message);
	}
	if(json.redirect != null && json.redirect.length > 0) {
		document.location.href = json.redirect;
	}
	if(json.result != "success") {
		return false;
	}
	
	return true;
}

function notyAlert(type, message) {
    var n = noty({
        text        : message,
        type        : type,
        timeout     : 3000, // delay for closing event. Set false for sticky notifications
        dismissQueue: true,
        layout      : 'top',
        theme       : 'defaultTheme'
    });
    
    return n;
}

function bindAjax() {
	$('.ajax').unbind('click').bind('click', function (e) {
		e.stopPropagation();
		var href = $(this).attr('href');
		$.ajax({
			url : href,
			type : "get",
			success : function(json, textStatus) {
				if(isSuccess(json))
					notyAlert("success", json.message);
			}
		});
		
		return false;
	});
}

function bindReloadAjax() {
	$('.reload-ajax').unbind('click').bind('click', function (e) {
		e.stopPropagation();
		var href = $(this).attr('href');
		$.ajax({
			url : href,
			type : "get",
			success : function(json, textStatus) {
				if(isSuccess(json)) {
					document.location.reload();
				}
			}
		});
		
		return false;
	});
}

Number.prototype.format = function() {
    if(this==0) return 0;
    
    var reg = /(^[+-]?\d+)(\d{3})/;
    var n = (this + '');
 
    while (reg.test(n)) n = n.replace(reg, '$1' + ',' + '$2');
 
    return n;
};
String.prototype.startsWith = function (str) {
	return this.indexOf(str) == 0;
};

String.prototype.format = function() {
    var formatted = this;
    for( var arg in arguments ) {
        formatted = formatted.replace("{" + arg + "}", arguments[arg]);
    }
    return formatted;
};
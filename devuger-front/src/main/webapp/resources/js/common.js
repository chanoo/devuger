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
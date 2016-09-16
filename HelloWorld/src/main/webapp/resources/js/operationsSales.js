$( document ).ready(function() {
    $("#div_loadingGifSearch").hide();
    $("#div_loadingGifInsert").hide();
    $("#div_loadingGifInsertNew").hide();
});

function request(op,user, product, string) {
	$("#div_loadingGifSearch").show();
	switch(op) {
	case "search":
		search(user, product, string);
		break;
	case "save":
		save(Ext.encode(Ext.pluck(panel.getStore().data.items, 'data')));
		break;
	case "remove":
		remove(string);
		break;
	}
}

function search(user, product, string) {
	$.ajax({
	    url : searchURL,
	    async: false,
	    type : 'POST',
	    data : {
	        'user'   : user,
	        'product': product,
	    	'string' : string	        
	    },
	    dataType:'json',
	    success : function(data) {

	    	 panel.show();
	    	 panel.getStore().setData(data);
	         $("#div_loadingGifSearch").hide();
	    },
	    error : function(request,error)
	    {		
			$("#div_loadingGifSearch").hide();
	        alert("Request: "+JSON.stringify(request));
	    }
	});
}

function save(string) {
	$.ajax({
	    url : saveURL,
	    async: false,
	    type : 'POST',
	    data : {
	        'string' : string
	    },
	    dataType:'html',
	    success : function(data) {
			$("#div_loadingGifSearch").hide();
	    },
	    error : function(request,error)
	    {		
			$("#div_loadingGifSearch").hide();
	    }
	});
}

function remove(string) {
	var array_text = string[0];
	for (var i = 1; i < string.length; i++) {
		array_text += "," + string[i]; 
	}
	$.ajax({
	    url : removeURL,
	    async: false,
	    type : 'POST',
	    data : {
	        'string' : array_text
	    },
	    dataType:'html',
	    success : function(data) {
			$("#div_loadingGifSearch").hide();
	    },
	    error : function(request,error)
	    {		
			$("#div_loadingGifSearch").hide();
	    }
	});
}

$( document ).ready(function() {
    $("#div_loadingGifSearch").hide();
    $("#div_loadingGifInsert").hide();
    $("#div_loadingGifInsertNew").hide();
});

actionURL = searchURL;
actionURL = insertURL;
actionURL = deleteURL;


function request(op,string) {
	$("#div_loadingGifSearch").show();
	var actionURL=""; 
	switch(op) {
	case "search":
		search(string);
		break;
	case "insert":
		insert(string);
		break;
	case "delete":
		delete(string);
		break;
	}
}

function search(string) {
	$.ajax({
	    url : searchURL,
	    type : 'POST',
	    data : {
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

function insert(string) {
	$.ajax({
	    url : insertURL,
	    type : 'POST',
	    data : {
	        'string' : string
	    },
	    dataType:'html',
	    success : function(data) {
			$("#div_loadingGifSearch").hide();
			alert(data);
	    },
	    error : function(request,error)
	    {		
			$("#div_loadingGifSearch").hide();
	        alert("Request: "+JSON.stringify(request));
	    }
	});
}

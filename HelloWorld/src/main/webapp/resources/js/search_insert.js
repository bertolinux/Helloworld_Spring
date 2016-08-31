$( document ).ready(function() {
    $("#div_loadingGifSearch").hide();
    $("#div_loadingGifInsert").hide();
});

function searchItem(starts_with) {
	$("#div_loadingGifSearch").show();
	$.ajax({
	    url : searchURL,
	    type : 'POST',
	    data : {
	        'starts_with' : starts_with
	    },
	    dataType:'json',
	    success : function(data) {
	    	var sel = $("#items");
	    	sel.empty(); 	
	    	$.each(data.items, function() {
	    	    sel.append(new Option(this[selectAttribute], this[selectAttribute]));
	    	});
			$("#div_loadingGifSearch").hide();
	    },
	    error : function(request,error)
	    {		
			$("#div_loadingGifSearch").hide();
	        alert("Request: "+JSON.stringify(request));
	    }
	});
}

function insertItem(n) {
	$("#div_loadingGifInsert").show();
	$.ajax({
	
	    url : insertURL,
	    type : 'POST',
	    data : {
	        'n' : n
	    },
	    dataType:'html',
	    success : function(data) {		
			$("#div_loadingGifInsert").hide();
			alert(data)
	    },
	    error : function(request,error)
	    {	
			$("#div_loadingGifInsert").hide();
	        alert("Request: "+request);
	    }
	});		
}
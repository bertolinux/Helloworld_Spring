$( document ).ready(function() {
    $("#div_loadingGifSearch").hide();
    $("#div_loadingGifInsert").hide();
    $("#div_loadingGifInsertNew").hide();
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
	    	$.each(data, function() {
	    	    sel.append(new Option(this.value, this.id));
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

function searchItem(user,product,value) {
	$("#div_loadingGifSearch").show();
	$.ajax({
	    url : searchURL,
	    type : 'POST',
	    data : {
	        'user' : user,
	        'product' : product,
	        'value' : value
	    },
	    dataType:'json',
	    success : function(data) {
	    	var sel = $("#items");
	    	sel.empty(); 	
	    	sel.append(new Option("-",0));	    	
	    	$.each(data, function() {
	    	    sel.append(new Option(this.value, this.id));
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

function insertNewItem(user,product,value) {
	$("#div_loadingGifInsertNew").show();
	$.ajax({
	
	    url : insertNewURL,
	    type : 'POST',
	    data : {
	        'user' : user,
	        'product' : product,
	        'value' : value
	    },
	    dataType:'html',
	    success : function(data) {		
			$("#div_loadingGifInsertNew").hide();
			alert(data)
	    },
	    error : function(request,error)
	    {	
			$("#div_loadingGifInsertNew").hide();
	        alert("Request: "+request);
	    }
	});		
}
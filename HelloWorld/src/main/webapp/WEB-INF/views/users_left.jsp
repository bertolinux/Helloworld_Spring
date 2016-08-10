<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<spring:url value="/resources/img/loading.gif" var="loadingGif" />
<spring:url value="/resources/js/jquery-3.1.0.min.js" var="jqueryJS" />
<spring:url value="/${controller_url}/search" var="search" />
<spring:url value="/${controller_url}/insert" var="insert" />

<script>
	$( document ).ready(function() {
	    $("#div_loadingGifSearch").hide();
	    $("#div_loadingGifInsert").hide();
	});
	function searchItem(string) {
		$("#div_loadingGifSearch").show();
		$.ajax({
		    url : '${search}',
		    type : 'POST',
		    data : {
		        'search_string' : string
		    },
		    dataType:'json',
		    success : function(data) {
		    	var selectUsersFound = document.getElementById("selectUsersFound");  
		    	selectUsersFound.length = 0;            
		        for (i=0; i<data.usersFound.length;i++) {
		        	if (data.usersFound[i].name == null)
		        		continue;
			        var option = document.createElement("option");
					option.text = data.usersFound[i].name;
					selectUsersFound.add(option);
		        }		
				$("#div_loadingGifSearch").hide();
		    },
		    error : function(request,error)
		    {		
				$("#div_loadingGifSearch").hide();
		        alert("Request: "+JSON.stringify(request));
		    }
		});
	}
	
	function insertItem(n_users) {
		$("#div_loadingGifInsert").show();
		$.ajax({
		
		    url : '${insert}',
		    type : 'POST',
		    data : {
		        'n_users' : n_users
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
</script>

<form name="searchForm">
	Search... <input type=text name="search">
	<input type=button onclick="searchItem(this.form.search.value)" value=Search>
	<img src="${loadingGif}" width=20 id="div_loadingGifSearch" style="display: block">
</form> 
users_left
<br>
<div id="result">	
	<select id="selectUsersFound"></select>				
</div>
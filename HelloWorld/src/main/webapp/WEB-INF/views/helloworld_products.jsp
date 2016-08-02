<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head> 
<spring:url value="/resources/img/loading.gif" var="loadingGif" />
<spring:url value="/resources/js/jquery-3.1.0.min.js" var="jqueryJS" />
<spring:url value="/${controller_url}/search" var="search" />
<spring:url value="/${controller_url}/insert" var="insert" />
<script src="${jqueryJS}"></script>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Spring 4 MVC -HelloWorld</title>
</head>
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
		        for (i=0; i<data.productsFound.length;i++) {
		        	if (data.productsFound[i].name == null)
		        		continue;
			        var option = document.createElement("option");
					option.text = data.productsFound[i].name;
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
<body>
	<center>
		<h2>Hello World</h2>
	</center>
		<div style="position: absolute; left: 0px; width: 100%; background-color: #FFF8F0">
	
			<div id="left" style="position: absolute; left: 0px; width: 20%; height: 400px; background-color: #FFF0F0">
       				<form name="searchForm">
					Search... <input type=text name="search">
					<input type=button onclick="searchItem(this.form.search.value)" value=Search>
					<img src="${loadingGif}" width=20 id="div_loadingGifSearch" style="display: block">
					</form> 
					<br>
					<div id="result">	
						<select id="selectUsersFound"></select>				
					</div>
			</div>
			<div id="center" style="position: absolute; left: 20%; width: 60%; height: 400px; background-color: #F1F0F0">
				${message} <br>
				<br>
				<form>
				New Elements: <input type=text name=n_users> <input type=button onclick="insertItem(this.form.n_users.value)" value=Insert>
				<img src="${loadingGif}" width=20 id="div_loadingGifInsert" style="display: block">
					
				</form>
				
			</div>		
			<div id="right" style="position: absolute; left: 80%; width: 20%; height: 400px; background-color: #F1B0F0">
				<%@ include file="/WEB-INF/views/center.jsp" %>
			</div>
		</div>
</body>
</html>
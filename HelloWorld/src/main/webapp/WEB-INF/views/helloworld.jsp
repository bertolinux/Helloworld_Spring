<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head> 
<spring:url value="/resources/jquery-3.1.0.min.js" var="jqueryJS" />
<spring:url value="/search_users" var="search_users" />
<spring:url value="/insert_users" var="insert_users" />
<script src="${jqueryJS}"></script>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Spring 4 MVC -HelloWorld</title>
</head>
<script>
	function searchUser(string) {
		$.ajax({
		
		    url : '${search_users}',
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
		    },
		    error : function(request,error)
		    {
		        alert("Request: "+JSON.stringify(request));
		    }
		});			
	}
	
	function insert_users(n_users) {
		$.ajax({
		
		    url : '${insert_users}',
		    type : 'POST',
		    data : {
		        'n_users' : n_users
		    },
		    dataType:'html',
		    success : function(data) {
				alert(data)
		    },
		    error : function(request,error)
		    {
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
					<input type=button onclick="searchUser(this.form.search.value)" value=Search>
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
				New Elements: <input type=text name=n_users> <input type=button onclick="insert_users(this.form.n_users.value)" value=Insert>
				</form>
				
			</div>		
			<div id="right" style="position: absolute; left: 80%; width: 20%; height: 400px; background-color: #F1B0F0">
				<%@ include file="/WEB-INF/views/center.jsp" %>
			</div>
		</div>
</body>
</html>
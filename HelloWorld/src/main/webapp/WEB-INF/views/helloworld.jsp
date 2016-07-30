<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head> 
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Spring 4 MVC -HelloWorld</title>
</head>
<body>
	<center>
		<h2>Hello World</h2>
	</center>
		<div style="position: absolute; left: 0px; width: 100%; background-color: #FFF8F0">
	
			<div id="left" style="position: absolute; left: 0px; width: 20%; height: 400px; background-color: #FFF0F0">
				<select>
					<% for (int i = 0; i < 10; i++) { %>
						<option value=<%=i%>>Client <%=i%></option>
					<%} %> 
				</select><br><br>
				    Users
				    <select>
					    <c:forEach var="user" items="${users}">
           					<option value=${user.getId()}>
	           					${user.getName()}
	           				</option>
        				</c:forEach>
       				</select>
					
			</div>
			<div id="center" style="position: absolute; left: 20%; width: 60%; height: 400px; background-color: #F1F0F0">
				${message}
			</div>		
			<div id="right" style="position: absolute; left: 80%; width: 20%; height: 400px; background-color: #F1B0F0">
				<%@ include file="/WEB-INF/views/center.jsp" %>
			</div>
		</div>
</body>
</html>
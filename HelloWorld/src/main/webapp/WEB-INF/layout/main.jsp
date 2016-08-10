<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<spring:url value="/resources/js/jquery-3.1.0.min.js" var="jqueryJS" />

<script src="${jqueryJS}"></script>
<html>
	<head>
		<title>${title}</title>
	</head>

	<body>
		<div id="header" style="background-color: lightgray; height: 50px; text-align:center">
			<c:set var="string1" value="${title}"/>
			<c:set var="string2" value="${fn:toUpperCase(string1)}" />
			<h3 style="text-align: center"> ${string2} </h3>
		</div>
		
		<div id="center" style="background-color: #f0f0f0; height: 450px">
			<tiles:insertAttribute name="center"/>
		</div>
		
		<div id="footer" style="background-color: lightgray; height: 50px; text-align:center">
			&copy; Copyright Bertolinux 2016
		</div>				
	</body>
</html>
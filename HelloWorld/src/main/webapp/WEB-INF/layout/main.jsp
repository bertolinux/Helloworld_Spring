<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<spring:url value="/resources/img/left-arrow.jpg" var="leftArrow" />
<spring:url value="/resources/js/jquery-3.1.0.min.js" var="jqueryJS" />
<spring:url value="/resources/style/main.css" var="stylePATH" />

<link rel="stylesheet" type="text/css" href="${stylePATH}">
<script src="${jqueryJS}"></script>

<html>
	<head>
		<title>${title}</title>
	</head>

	<body>
		<div id="header">
			<c:if test="${title != 'Helloworld'}">
				<div style="position: absolute; left: 40%;">
					<a href=..>
						<img src="${leftArrow}" width=40px>
					</a>
				</div>
			</c:if>
			<h2> ${title} </h2>
		</div>
		
		<hr>
		
		<div id="center">
			<c:if test="${title != 'Helloworld'}">
				<br>
			</c:if>
			<tiles:insertAttribute name="center"/>
		</div>
		
		<hr>
		
		<div id="footer">
			<br>
			&copy; Copyright Bertolinux 2016
		</div>				
	</body>
</html>
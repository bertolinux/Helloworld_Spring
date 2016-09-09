<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<spring:url value="/resources/img/loading.gif" var="loadingGif"/>

<form name="searchForm">
	Search... <br>
	User 
	<select name=user>
	   <option value=0> - </option>
		<c:forEach var="user" items="${users}">
		   <option value=<c:out value="${user.id}"/>> <c:out value="${user.value}"/> </option>
		</c:forEach>
	</select>
	<br>
	
	Product 
	<select name=product>
	   <option value=0> - </option>
		<c:forEach var="product" items="${products}">
		   <option value=<c:out value="${product.id}"/>> <c:out value="${product.value}"/> </option>
		</c:forEach>	
	</select>
	<br>	
	<input type=text name="value">
	<input type=button onclick="searchItem(this.form.user.value,this.form.product.value,this.form.value.value)" value=Search>
	<img src="${loadingGif}" width=20 id="div_loadingGifSearch" style="display: block">
</form>
<br>
<div id="result">	
	<select id="items"></select>				
</div>
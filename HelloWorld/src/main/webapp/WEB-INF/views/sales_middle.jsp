<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<spring:url value="/resources/img/loading.gif" var="loadingGif"/>

<form name="insertNewForm">
	User 
	<select name=user>
		<c:forEach var="user" items="${users}">
		   <option value=<c:out value="${user.id}"/>> <c:out value="${user.value}"/> </option>
		</c:forEach>
	</select>
	<br>
	
	Product 
	<select name=product>
		<c:forEach var="product" items="${products}">
		   <option value=<c:out value="${product.id}"/>> <c:out value="${product.value}"/> </option>
		</c:forEach>	
	</select>
	<br>
	
	Insert New... <br>
	Name <input type=text name="value">
	<input type=button onclick="insertNewItem(this.form.user.value,this.form.product.value,this.form.value.value)" value=Add>
	<img src="${loadingGif}" width=20 id="div_loadingGifInsertNew" style="display: block">
</form>
<br>
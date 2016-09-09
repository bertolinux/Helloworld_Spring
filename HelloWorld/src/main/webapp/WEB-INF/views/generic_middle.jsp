<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<spring:url value="/resources/img/loading.gif" var="loadingGif"/>

<form name="insertNewForm">
	Insert New... <br>
	Name <input type=text name="name">
	<input type=button onclick="insertNewItem(this.form.name.value)" value=Add>
	<img src="${loadingGif}" width=20 id="div_loadingGifInsertNew" style="display: block">
</form>
<br>
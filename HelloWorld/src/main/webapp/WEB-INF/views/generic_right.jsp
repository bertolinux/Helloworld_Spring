<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<spring:url value="/resources/img/loading.gif" var="loadingGif" />

New Elements...
<br> 
<form>
	<input type=text name=n> <input type=button onclick="insertItem(this.form.n.value)" value=Insert>
	<img src="${loadingGif}" width=20 id="div_loadingGifInsert" style="display: block">
</form>			
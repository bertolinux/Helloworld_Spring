<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<spring:url value="/resources/img/loading.gif" var="loadingGif" />
<spring:url value="/resources/js/jquery-3.1.0.min.js" var="jqueryJS" />
<spring:url value="/${controller_url}/search" var="search" />
<spring:url value="/${controller_url}/insert" var="insert" />

${message} 
<br>
generic_right
<br>
<form>
	New Elements: <input type=text name=n> <input type=button onclick="insertItem(this.form.n.value)" value=Insert>
	<img src="${loadingGif}" width=20 id="div_loadingGifInsert" style="display: block">
</form>			
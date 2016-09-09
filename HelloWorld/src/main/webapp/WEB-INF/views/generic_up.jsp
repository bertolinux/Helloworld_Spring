<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<spring:url value="/resources/img/loading.gif" var="loadingGif"/>
<br>
 <form name="actions">
Name <input type=text name="string">
	<input type=button onclick="request('search',this.form.string.value)" value=Search>
	<input type=button onclick="request('insert',this.form.string.value)" value=New>
	<img src="${loadingGif}" width=20 id="div_loadingGifSearch" style="display: block">
</form>

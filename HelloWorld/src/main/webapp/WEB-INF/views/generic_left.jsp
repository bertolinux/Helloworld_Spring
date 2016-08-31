<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<spring:url value="/resources/img/loading.gif" var="loadingGif"/>

<form name="searchForm">
	Search... <br>
	<input type=text name="search">
	<input type=button onclick="searchItem(this.form.search.value)" value=Search>
	<img src="${loadingGif}" width=20 id="div_loadingGifSearch" style="display: block">
</form>
<br>
<div id="result">	
	<select id="items"></select>				
</div>
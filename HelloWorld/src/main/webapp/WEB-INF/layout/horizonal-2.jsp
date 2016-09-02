<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<spring:url value="/${controller_url}/search" var="searchURL" />
<spring:url value="/${controller_url}/insert" var="insertURL" />
<spring:url value="/resources/js/search_insert.js" var="searchInsert" />

<script>
	// Bind Spring variables with Javascript variables
	var insertURL="${insertURL}";
	var searchURL="${searchURL}";
</script>
<script src="${searchInsert}"></script>

<div class="left">
	<tiles:insertAttribute name="left"/>
</div>

<div class="right">
	<tiles:insertAttribute name="right"/>
</div>				


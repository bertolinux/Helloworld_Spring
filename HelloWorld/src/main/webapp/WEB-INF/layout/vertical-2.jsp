<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<spring:url value="/${controller_url}/search" var="searchURL" />
<spring:url value="/${controller_url}/insert" var="insertURL" />
<spring:url value="/${controller_url}/delete" var="deleteURL" />
<spring:url value="/resources/js/operations.js" var="operationJS" />

<!-- Activate ExtJS -->
<script type="text/javascript" src="/extjs/build/examples/classic/shared/include-ext.js"></script>
<script type="text/javascript" src="/extjs/build/examples/classic/shared/options-toolbar.js"></script>
    
<script>
	// Bind Spring variables with Javascript variables
	var searchURL="${searchURL}";
	var insertURL="${insertURL}";
	var deleteURL="${deleteURL}";
	Ext.require([
	             'Ext.grid.*',
	             'Ext.data.*',
	             'Ext.panel.*',
	             'Ext.layout.container.Border'
	         ]);	
</script>

<script src="${operationJS}"></script>

<div class="up">
	<tiles:insertAttribute name="up"/>
</div>

<div class="down">
	<tiles:insertAttribute name="down"/>
</div>				


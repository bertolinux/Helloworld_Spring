<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<spring:url value="/resources/img/loading.gif" var="loadingGif"/>
<spring:url value="/${controller_url}/search" var="searchURL" />
<spring:url value="/${controller_url}/save" var="saveURL" />
<spring:url value="/${controller_url}/remove" var="removeURL" />
<spring:url value="/resources/js/operations.js" var="operationJS" />
<spring:url value="/resources/js/createGenericExtjs.js" var="createGenericExtJS" />

<!-- Activate ExtJS -->
<script type="text/javascript" src="/extjs/build/examples/classic/shared/include-ext.js"></script>
<script type="text/javascript" src="/extjs/build/examples/classic/shared/options-toolbar.js"></script>  
<script type="text/javascript" src="${createGenericExtJS}"></script>  
    
<script>
	// Bind Spring variables with Javascript variables
	var searchURL="${searchURL}";
	var saveURL="${saveURL}";
	var removeURL="${removeURL}";
	Ext.require([
	             'Ext.grid.*',
	             'Ext.data.*',
	             'Ext.panel.*',
	             'Ext.layout.container.Border'
	         ]);	
</script>

<script src="${operationJS}"></script>

<div id="result">	
</div>	

<script>

var modelStoreFields = [ 'id', 'name'];
var columns = [ {text: 'Id', dataIndex: 'id'}, {text: 'Name',dataIndex: 'name', editor: {allowBlank: false}}];
var title = "${title}";
var panel;
var buttonSearch;
var idToDelete = new Array();
Ext.onReady(function() {
     createGenericExtjs(modelStoreFields,columns);	
});
</script>	


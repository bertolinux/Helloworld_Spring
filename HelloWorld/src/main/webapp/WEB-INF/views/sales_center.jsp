<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<spring:url value="/resources/img/loading.gif" var="loadingGif"/>
<spring:url value="/${controller_url}/search" var="searchURL" />
<spring:url value="/${controller_url}/save" var="saveURL" />
<spring:url value="/${controller_url}/remove" var="removeURL" />
<spring:url value="/resources/js/operationsSales.js" var="operationSalesJS" />
<spring:url value="/resources/js/createSalesExtjs.js" var="createSalesExtJS" />

<!-- Activate ExtJS -->
<script type="text/javascript" src="/extjs/build/examples/classic/shared/include-ext.js"></script>
<script type="text/javascript" src="/extjs/build/examples/classic/shared/options-toolbar.js"></script>  
<script type="text/javascript" src="${createSalesExtJS}"></script>  
    
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

<script src="${operationSalesJS}"></script>

<div id="result">	
</div>	

<script>

var salesStoreFields = ['id', 'iduser', 'user', 'idproduct', 'product', 'date', 'value'];
var columns = [ 
	{text: 'Id', dataIndex: 'id'}, 
	{text: 'Value',dataIndex: 'value', flex: 1, editor: {allowBlank: false}},
	{text: 'iduser', dataIndex: 'iduser', hidden: true}, 
	{text: 'user', dataIndex: 'user', flex: 1}, 
	{text: 'idproduct', dataIndex: 'idproduct', hidden: true}, 
	{text: 'product', dataIndex: 'product', flex: 1}, 
	{text: 'date', dataIndex: 'date', flex: 1} 
];
var title = "${title}";
var panel;
var buttonSearch;
var idToDelete = new Array();

var userStore = Ext.create('Ext.data.Store', {
    storeId: 'userStore',
    fields: ["name", "id"],
    data: ${userStoreJSON}
}); 

var productStore = Ext.create('Ext.data.Store', {
    storeId: 'productStore',
    fields: ["name", "id"],
    data: ${productStoreJSON}
}); 

Ext.onReady(function() {
	createSalesExtjs(userStore,productStore, salesStoreFields, columns);	
});

</script>	


<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<spring:url value="/resources/img/loading.gif" var="loadingGif"/>


<h3>Results</h3>
<div id="result">	
</div>	

<script>
var panel;
Ext.onReady(function(){
	 Ext.create('Ext.data.Store', {
		    storeId: 'usersStore',
		    fields:[ 'id', 'value'],
		    data: []
	 }); 
	  
	 panel = Ext.create('Ext.grid.Panel', {
		    title: 'Users',
		    store: Ext.data.StoreManager.lookup('usersStore'),
		    columns: [{
		        text: 'Id',
		        dataIndex: 'id'
		    }, {
		        text: 'Value',
		        dataIndex: 'value',
		        flex: 1
		    }],
		    height: 200,
		    width: 800,
		    renderTo: result
		});

	 panel.hide();
});
</script>	
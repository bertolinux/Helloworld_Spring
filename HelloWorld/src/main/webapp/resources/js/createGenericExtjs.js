function createGenericExtjs(modelStoreFields,columns) {
	 Ext.define('recordModel', {
			extend: 'Ext.data.Model',
			fields: modelStoreFields
	 });
		
	 Ext.create('Ext.data.Store', {
		    storeId: 'recordStore',
		    fields: modelStoreFields,
		    data: []
	 }); 

	 var rowEditing = Ext.create('Ext.grid.plugin.RowEditing', {
	        clicksToMoveEditor: 1,
	        autoCancel: false
  });

  var searchField = new Ext.form.TextField({});
  buttonSearch = Ext.create('Ext.Button', {
 	    text: 'Search',
 	    handler: function() {
 	    	idToDelete.length = 0;
  	    	request("search",searchField.getValue());
 	    }
	 });
 	  
  var buttonAdd = Ext.create('Ext.Button', {
	    text: 'Add',
	    handler: function() {
         rowEditing.cancelEdit();

         // Create a model instance
         var record = Ext.create('recordModel', {
             id: "",
             name: ''
         });

         panel.getStore().insert(0, record);
         rowEditing.startEdit(0, 0);  	    	
	    }
	 });

  var buttonDelete = Ext.create('Ext.Button', {
      text: 'Delete',
      handler: function() {
          var selection = panel.getView().getSelectionModel().getSelection()[0];
          idToDelete.push(selection.getId());
          if (selection) {
          	panel.getStore().remove(selection);
          }
      }
	 });
	 
  var buttonSave = Ext.create('Ext.Button', {
	    text: 'Save',
	    handler: function() {
	    	request("save",null);
	    	if (idToDelete.length != 0) {
	    		request("remove",idToDelete);
	 	    	idToDelete.length = 0;
	    	}
			buttonSearch.handler.call(buttonSearch.scope, buttonSearch, Ext.EventObject);
	    }
	 });
	   
	 panel = Ext.create('Ext.grid.Panel', {
		    title: title,
		    store: Ext.data.StoreManager.lookup('recordStore'),
		    columns: columns,
		    height: 400,
		    width: 800,
	        tbar: [
	   	        'Search String',
	        	searchField,
	        	buttonSearch,
	        	buttonAdd,
	        	buttonDelete,
	        	{ xtype: 'tbspacer' },
	        	{ xtype: 'tbseparator' },
	        	{ xtype: 'tbspacer' },
	        	buttonSave   	
         ],
		    renderTo: result,
	        plugins: [rowEditing]
		});
	
}
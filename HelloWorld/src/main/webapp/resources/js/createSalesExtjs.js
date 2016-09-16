function createSalesExtjs(userStore, productStore, modelStoreFields, columns) {   	
	var userCombo = Ext.create('Ext.form.ComboBox', {
	    forceSelection : false,
	    fieldLabel : 'User',
	    store : userStore,
	    queryMode : 'local',
	    displayField : 'name',
	    valueField : 'id'
	});
	userCombo.select("-");
	    
	var productCombo = Ext.create('Ext.form.ComboBox', {
	    forceSelection : false,
	    fieldLabel : 'Product',
	    store : productStore,
	    queryMode : 'local',
	    displayField : 'name',
	    valueField : 'id'
	});
	productCombo.select("-");
	
	Ext.define('saleModel', {
		extend: 'Ext.data.Model',
		fields: modelStoreFields
	});
		
	Ext.create('Ext.data.Store', {
		storeId: 'saleStore',
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
  	    	request("search",userCombo.getValue(), productCombo.getValue(),searchField.getValue());
 	    }
	});
 	  
	var buttonAdd = Ext.create('Ext.Button', {
	    text: 'Add',
	    handler: function() {
	    	var iduser = userCombo.getValue();
	    	var user = userCombo.getRawValue();
	    	var idproduct = productCombo.getValue();
	    	var product = productCombo.getRawValue();
	    	if (iduser == "-" || idproduct == "-") {
	    		alert("Select combos");
	    		return;
	    	}
	    	rowEditing.cancelEdit();

         // Create a model instance
         var sale = Ext.create('saleModel', {
             id: "",
             value: "",
             iduser: iduser,
             user: user,
             idproduct: idproduct,
             product: product,
             date: ""
         });
         
         panel.getStore().insert(0, sale);
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
	    		request("remove",null,null,idToDelete);
	 	    	idToDelete.length = 0;
	    	}
			buttonSearch.handler.call(buttonSearch.scope, buttonSearch, Ext.EventObject);
	    }
	 });
	   
	 panel = Ext.create('Ext.grid.Panel', {
		    title: title,
		    store: Ext.data.StoreManager.lookup('saleStore'),
		    columns: columns,
		    height: 500,
		    width: 1200,
	        tbar: [
	            userCombo,
	            productCombo,
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
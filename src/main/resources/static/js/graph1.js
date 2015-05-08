
    
    
    
    $(function () {
    	
    	$.get( "/getGraph1", function( data ) {
    		var results2 = JSON.parse(data);
    		var walmart = [];
    		var target = [];
    		var kohls = [];
    		var macys = [];
    		var staples = [];
    		
    		for (var idx in results2) {
    			if(results2[idx]["shopname"]=="Target")
    				target.push([parseInt(results2[idx]["value1"],10),parseInt(results2[idx]["value2"],10),parseInt(results2[idx]["value3"],10)]);
    			else if(results2[idx]["shopname"]=="Walmart")
    				walmart.push([parseInt(results2[idx]["value1"],10),parseInt(results2[idx]["value2"],10),parseInt(results2[idx]["value3"],10)]);
    			else if(results2[idx]["shopname"]=="Macys")
    				macys.push([parseInt(results2[idx]["value1"],10),parseInt(results2[idx]["value2"],10),parseInt(results2[idx]["value3"],10)]);
    			else if(results2[idx]["shopname"]=="Kohls")
    				kohls.push([parseInt(results2[idx]["value1"],10),parseInt(results2[idx]["value2"],10),parseInt(results2[idx]["value3"],10)]);
    			else if(results2[idx]["shopname"]=="Staples")
    				staples.push([parseInt(results2[idx]["value1"],10),parseInt(results2[idx]["value2"],10),parseInt(results2[idx]["value3"],10)]);
    								
    					
            }
    	    
    		
    		displayGraph(target,walmart,kohls,macys,staples);
    		});
    	
    });
    
    function displayGraph(target,walmart,kohls,macys,staples)
    {
    	$('#container').highcharts({

            chart: {
                type: 'bubble',
                zoomType: 'xy'
            },

            title: {
                text: ' Coupon Popularity Chart'
            },

            series: [{
            	 name : 'Target',
                data: target
            }, {
            	 name : 'Walmart',
                data: walmart
            }, {
            	 name : 'Kohls',
                data: kohls
            }, {
            	 name : 'Macys',
                data: macys
            },
            {
            	 name : 'Staples',
            	data:staples
            }]
        });
    }
 $(function () {
	 
	 
	 $.get( "/getGraph2", function( data ) {
 		var results2 = JSON.parse(data);
 		var data = [];
 		
 		
 		for (var idx in results2) {
 			
 				data.push([parseInt(results2[idx]["v1"],10),parseInt(results2[idx]["v2"],10),parseInt(results2[idx]["v3"],10)]);	
 					
         }
 	    
 		
 		displayGraph(data);
 		});
 	
 });
 
 function displayGraph(datachart)
 {

	    $('#container').highcharts({

	        chart: {
	            type: 'heatmap',
	            marginTop: 40,
	            marginBottom: 80
	        },


	        title: {
	            text: '<strong>Coupon downloads per shops per weekday</strong><br/>'
	        },

	        xAxis: {
	            categories: ['Target', 'Kohls', 'Macys', 'Walmart', 'Staples', 'CVS', 'Bath&Beyond']
	        },

	        yAxis: {
	            categories: ['Monday', 'Tuesday', 'Wednesday', 'Thursday', 'Friday'],
	            title: null
	        },

	        colorAxis: {
	            min: 0,
	            minColor: '#FFFFFF',
	            maxColor: Highcharts.getOptions().colors[0]
	        },

	        legend: {
	            align: 'right',
	            layout: 'vertical',
	            margin: 0,
	            verticalAlign: 'top',
	            y: 25,
	            symbolHeight: 280
	        },

	        tooltip: {
	            formatter: function () {
	                return '<b>'+ this.point.value + this.series.xAxis.categories[this.point.x] + '</b> coupons <br> were used on <br><b>' + this.series.yAxis.categories[this.point.y] + '</b>';
	            }
	        },

	        series: [{
	            name: 'Sales per Shop',
	            borderWidth: 1,
	            data: datachart,
	            dataLabels: {
	                enabled: true,
	                color: '#000000'
	            }
	        }]

	    });
	
	
	}
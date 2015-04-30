 $(function () {

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
	            data: [[0, 0, 10], [0, 1, 19], [0, 2, 8], [0, 3, 24], [0, 4, 67], [1, 0, 92], [1, 1, 58], [1, 2, 78], [1, 3, 117], [1, 4, 48], [2, 0, 35], [2, 1, 15], [2, 2, 123], [2, 3, 64], [2, 4, 52], [3, 0, 72], [3, 1, 132], [3, 2, 114], [3, 3, 19], [3, 4, 16], [4, 0, 38], [4, 1, 5], [4, 2, 8], [4, 3, 117], [4, 4, 115], [5, 0, 88], [5, 1, 32], [5, 2, 12], [5, 3, 6], [5, 4, 120], [6, 0, 13], [6, 1, 44], [6, 2, 88], [6, 3, 98], [6, 4, 96]],
	            dataLabels: {
	                enabled: true,
	                color: '#000000'
	            }
	        }]

	    });
	});
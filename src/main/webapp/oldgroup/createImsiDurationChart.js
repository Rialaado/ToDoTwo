var createImsiDurationChart = function (jsonObject) {
	
				$('#chartForData').highcharts({
					  chart: {
			                type: 'column'
			            },
			            title: {
			                text: 'Failures'
			            },
			            subtitle: {
			                text: 'Number of Occurances'
			            },
			            xAxis: {
			                categories: jsonObject.distinctImsi
			            },
			            yAxis: {
			                min: 0,
			                title: {
			                    text: 'Occurances'
			                }
			            },
			            tooltip: {
			                headerFormat: '<span style="font-size:10px">{point.key}</span><table>',
			                pointFormat: '<tr><td style="color:{series.color};padding:0">{series.name}: </td>' +
			                    '<td style="padding:0"><b>{point.y:.1f}</b></td></tr>',
			                footerFormat: '</table>',
			                shared: true,
			                useHTML: true
			            },
			            plotOptions: {
			                column: {
			                    pointPadding: 0.2,
			                    borderWidth: 0
			                }
			            },
			            series: [{
			                name: 'Occurances',
			                data: jsonObject.occurances,
			    
			            },{
			                name: '%Total Duration',
			                data: jsonObject.durations
			    
			            }]
		        });
		    };
	
			    


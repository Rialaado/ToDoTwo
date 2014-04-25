 var createCountChart = function (jsonObject) {
			var cataArray = new Array();
			var percentArray = new Array();
			totalFails = jsonObject.allEventsCount;
			
			for (var i = 0; i < jsonObject.events.length; i++)
			{
				
				var market = jsonObject.events[i].operatorBean.id.mnc;
				var operator = jsonObject.events[i].operatorBean.id.mcc;
				var cellID = jsonObject.events[i].cellHier.cellID;
				var percentFail = (jsonObject.occurances[i]/totalFails)*100;

				percentArray[i] = percentFail;
				cataArray[i] = "Node: "+market+operator+cellID ;

			}

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
		                categories: cataArray
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
		                name: '% Total',
		                data: percentArray
		    
		            }]
		        });
		    };
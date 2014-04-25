var createChart = function (jsonObject) {
				var cataArray = new Array();
				for (var i = 0; i < jsonObject.events.length; i++)
				{
					
					var causeCode = jsonObject.events[i].eventdetail.id.causeCode;
					var eventID = jsonObject.events[i].eventdetail.id.eventID;
					cataArray[i] = "CC:" +  causeCode + "  EID: " + eventID;
				}
				
				//var modelString = "Model Name : " + jsonObject.events[i].ue.model;
	
	
				$('#chartForData').highcharts({
		            chart: {
		                type: 'bar'
		            },
		            title: {
		                text: 'Failure Occurances'
		            },
		            subtitle: {
		                text: 'Search by Model'
		            },
		            xAxis: {
		                categories: cataArray,
		                title: {
		                    text: "Cause code / Event ID"
		                }
		            },
		            yAxis: {
		                min: 0,
		                title: {
		                    text: 'Number of Occurances',
		                    align: 'high'
		                },
		                labels: {
		                    overflow: 'justify'
		                }
		            },
		            plotOptions: {
		                bar: {
		                    dataLabels: {
		                        enabled: true
		                    }
		                }
		            },
		            legend: {
		                layout: 'vertical',
		                align: 'right',
		                verticalAlign: 'top',
		                x: -40,
		                y: 100,
		                floating: true,
		                borderWidth: 1,
		                backgroundColor: '#FFFFFF',
		                shadow: true
		            },
		            credits: {
		                enabled: false
		            },
		            series: [{
	                name: 'Occurances',
	                data: jsonObject.occurances
	    
	            }]
		        });
		    };

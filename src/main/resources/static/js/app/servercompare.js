

$(document).ready(function() {

	function getExpectedJsonFromServer(){
		var expectedurl="/compare/expected";
        var expectedData;
		$.ajax({
		    url : expectedurl,
		    type : "GET",
		    success : function(data) {
		        expectedData=data;
	            var options = {
				    collapsed: false,
					rootCollapsable: false,
					withQuotes: true,
					withLinks: true
				};
				$('#expectedRenderer').jsonViewer(expectedData, options);
			},
			error : function() {
			    // Handle upload error
				// ...
			}
		});
	}

	function getActualJsonFromServer(){
    	var expectedurl="/compare/actual";
    	var actualData;
    	$.ajax({
            url : expectedurl,
    		type : "GET",
    		success : function(data) {
    		    actualData=data;
    	        var options = {
    				    collapsed: false,
    					rootCollapsable: false,
    					withQuotes: true,
    					withLinks: true
    				};
    				$('#actualRenderer').jsonViewer(actualData, options);
    			},
    			error : function() {
    			    // Handle upload error
    				// ...
    			}
    		});
    }
    function getComparedDataJsonFromServer(){
        		var compareURL="/compare/";
        		var comparedData;
        		$.ajax({
        		    url : compareURL,
        		    type : "GET",
        		    success : function(data) {
        		        comparedData=data;
        	            var options = {
        				    collapsed: false,
        					rootCollapsable: false,
        					withQuotes: true,
        					withLinks: true
        				};
        				$('#serverResult').jsonViewer(comparedData, options);
        			},
        			error : function() {
        			    // Handle upload error
        				// ...
        			}
        		});
        	}


	function renderJson() {
		// Some logic to retrieve, or generate tree structure
		getExpectedJsonFromServer();
		getActualJsonFromServer();
		getComparedDataJsonFromServer();
	}

  // Display JSON sample on page load
  renderJson();




});




$(document).ready(function() {
        init();

        function init(){
            getExpectedJsonFromServer();
            getActualJsonFromServer();
        }

        function getActualJsonFromServer(){
            	var expectedurl="/compare/actual";
            	var actualData;
            	$.ajax({
                    url : expectedurl,
            		type : "GET",
            		success : function(data) {
            			    $('#json-input-right').val(data);
            			},
            			error : function() {
            			    // Handle upload error
            				// ...
            			}
            		});
            }

        function getExpectedJsonFromServer(){
        		var expectedurl="/compare/expected";
                var expectedData;
        		$.ajax({
        		    url : expectedurl,
        		    type : "GET",
        		    success : function(data) {
        				$('#json-input-left').val(data);
        			},
        			error : function() {
        			    // Handle upload error
        				// ...
        			}
        		});
        	}

        $('#compare').click(function(){
           var expected = $.trim($("#expectedJson").val());
           var actual = $.trim($("#actualJson").val());
           var delta = jsondiffpatch.diff(expected, actual);

           document.getElementById('results').innerHTML = jsondiffpatch.formatters.html.format(delta, expected);
           return false;
        });

});


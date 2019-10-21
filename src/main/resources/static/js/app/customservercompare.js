

$(document).ready(function() {

//	$("#checkFileUpload" ).click(function() {
//      alert( "Handler for .change() called." );
//    });

    onload();

    function onload(){
       $('#fileUploads').hide();
    }

    $('input[type="checkbox"][name="checkFileUpload"]').change(function() {
         if(this.checked) {
             $('#fileUploads').show();
             $('#textAreas').hide();
             $('#serverResult').hide();
         }else{
            $('#fileUploads').hide();
            $('#textAreas').show();
            $('#serverResult').hide();
         }
    });

    $('#btnCompare').click(function(){

        if($("#checkFileUpload").prop('checked') == true){
           uploadBothFile();
        }else{
            uploadTextAreas();
        }


    });


    function uploadTextAreas(){
        var form;
        form = new FormData();
        form.append("expectedJsonContent",$('#expectedJsonTxtArea').val());
        form.append("actualJsonContent",$('#actualJsonTxtArea').val());
        $.ajax({
            		url : "/compare/content",
            		type : "POST",
            		data : form,
            		enctype : 'multipart/form-data',
            		processData : false,
            		contentType : false,
            		cache : false,
            		success : function(data) {
            			$('#serverResult').show();
            			var options = {
                                				    collapsed: false,
                                					rootCollapsable: false,
                                					withQuotes: true,
                                					withLinks: true
                                				};
                        $('#serverResult').jsonViewer(data, options);
                    }
        });
    }

    function uploadBothFile() {
    	var form;
    	form = new FormData();
    	form.append("expectedJsonUpload", document
    			.getElementById('expectedJsonUpload').files[0]);
    	form.append("actualJsonUpload", document
    			.getElementById('actualJsonUpload').files[0]);
    	$.ajax({
    		url : "/compare/fileUploads",
    		type : "POST",
    		data : form,
    		enctype : 'multipart/form-data',
    		processData : false,
    		contentType : false,
    		cache : false,
    		success : function(data) {
    			$('#serverResult').show();
    			var options = {
                        				    collapsed: false,
                        					rootCollapsable: false,
                        					withQuotes: true,
                        					withLinks: true
                        				};
                $('#serverResult').jsonViewer(data, options);
            }

    	});
    }


});



modules = {
    application {
        resource url:'js/application.js'
    }
	
	datatables {
		dependsOn 'jquery'
		resource 'js/jquery.dataTables.min.js'
	}
	
}
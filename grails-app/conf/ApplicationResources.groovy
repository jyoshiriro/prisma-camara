modules = {
	
    application {
		dependsOn 'bootstrap'
		resource url:'css/prisma.css'
		resource url:'js/prisma.js'
        //resource url:'js/application.js'
		//resource url:'css/main.css'
    }
	
	bootstrap {
		dependsOn 'jquery'
		resource url:'vendor/bootstrap-3.0.0/css/bootstrap.min.css', exclude:'minify'
		resource url:'vendor/bootstrap-3.0.0/css/bootstrap-theme.min.css', exclude:'minify'
		resource url:'vendor/bootstrap-3.0.0/js/bootstrap.min.js', exclude:'minify'
	}
			
}
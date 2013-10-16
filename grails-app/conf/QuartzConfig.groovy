quartz {
	autoStartup = true
	jdbcStore = false
}

environments {
	development {
		quartz {
			autoStartup = true
		}
	}
	test {
		quartz {
			autoStartup = false
		}
	}
}

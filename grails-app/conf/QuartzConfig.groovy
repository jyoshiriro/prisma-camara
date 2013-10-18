quartz {
	autoStartup = true
	jdbcStore = false
}

environments {
	development {
		quartz {
			autoStartup = false
		}
	}
	test {
		quartz {
			autoStartup = false
		}
	}
}

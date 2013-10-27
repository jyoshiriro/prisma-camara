quartz {
	autoStartup = false
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

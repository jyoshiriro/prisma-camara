dataSource {
    pooled = true
    driverClassName = "com.mysql.jdbc.Driver"
}
hibernate {
    cache.use_second_level_cache = false
    cache.use_query_cache = false
    cache.region.factory_class = 'net.sf.ehcache.hibernate.EhCacheRegionFactory'
	hibernate.dialect='org.hibernate.dialect.MySQL5InnoDBDialect' 
}
// environment specific settings
environments {
    development {
        dataSource {
            dbCreate = "update" // one of 'create', 'create-drop', 'update', 'validate', ''
            url = "jdbc:mysql://localhost:3306/hackathon"
            username = "root"
            password = "admin"
//            password = "123456"
            pooled = true
			properties {
				defaultTransactionIsolation=2
				maxActive = 10
				minEvictableIdleTimeMillis=1800000
				timeBetweenEvictionRunsMillis=1800000
				numTestsPerEvictionRun=3
				testOnBorrow=true
				testWhileIdle=true
				testOnReturn=true
				validationQuery="SELECT 1"
			 }
        }
		hibernate{
			show_sql=false
			format_sql=true
		}
    }
    test {
        dataSource {
            url = "jdbc:mysql://localhost:3306/hackathon2"
    		username = "root"
    		password = "admin"
//    		password = "123456"
        }
    }
    production {
        dataSource {
            dbCreate = "update"
    		url = "jdbc:mysql://ec2-50-19-213-178.compute-1.amazonaws.com/hackathon2013"
    		username = "hackathon2013"
    		password = "123456"
            pooled = true
            properties {
               maxActive = 20
               minEvictableIdleTimeMillis=1800000
               timeBetweenEvictionRunsMillis=1800000
               numTestsPerEvictionRun=3
               testOnBorrow=true
               testWhileIdle=true
               testOnReturn=true
               validationQuery="SELECT 1"
            }
        }
		hibernate {
			cache.use_second_level_cache = true
			cache.use_query_cache = true
		}
    }
}

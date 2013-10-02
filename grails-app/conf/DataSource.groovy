dataSource {
    pooled = true
    driverClassName = "com.mysql.jdbc.Driver"
}
hibernate {
    cache.use_second_level_cache = true
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
            password = "123456"
        }
    }
    test {
        dataSource {
            url = "jdbc:mysql://localhost:3306/hackathon"
    		username = "root"
    		password = "123456"
        }
    }
    production {
        dataSource {
            dbCreate = "update"
    		url = "jdbc:mysql://localhost:3306/hackathon"
    		username = "root"
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
    }
}

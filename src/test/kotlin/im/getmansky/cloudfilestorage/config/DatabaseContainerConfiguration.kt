package im.getmansky.cloudfilestorage.config

import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.DynamicPropertyRegistry
import org.springframework.test.context.DynamicPropertySource
import org.testcontainers.containers.MariaDBContainer
import org.testcontainers.junit.jupiter.Container

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
abstract class DatabaseContainerConfiguration {

    companion object {
        @Container
        private val mysqlContainer = MariaDBContainer<Nothing>("mariadb").apply {
            withDatabaseName("database")
            withUsername("user")
            withPassword("password")
        }

        @JvmStatic
        @DynamicPropertySource
        fun properties(registry: DynamicPropertyRegistry) {
            registry.add("spring.datasource.url", mysqlContainer::getJdbcUrl)
            registry.add("spring.datasource.password", mysqlContainer::getPassword)
            registry.add("spring.datasource.username", mysqlContainer::getUsername)
            registry.add("spring.flyway.enabled") { "true" }
        }
    }
}
package im.getmansky.cloudfilestorage.service

import im.getmansky.cloudfilestorage.config.DatabaseContainerConfiguration
import im.getmansky.cloudfilestorage.model.dto.request.UserRegistrationDto
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.testcontainers.junit.jupiter.Testcontainers

@Testcontainers
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class UserServiceTest : DatabaseContainerConfiguration() {

    @Autowired
    private lateinit var userService: UserService

    private companion object {
        val USER1 = UserRegistrationDto(
            username = "adminUser",
            password = "adminUserPassword",
            passwordRepeat = "adminUserPassword",
        )
        val USER2 = UserRegistrationDto(
            username = "testUser",
            password = "testUserPassword",
            passwordRepeat = "testUserPassword",
        )
    }

    @Test
    fun `test success add user`() {
        val actual = userService.addUser(USER1)
        assertEquals(USER1.username, actual.username)
        assertNotNull(actual.password)
    }

    @Test
    fun `test user already exist`() {
        userService.addUser(USER2)
        assertThrows<RuntimeException>("User with username ${USER2.username} already exists") {
            userService.addUser(USER2)
        }
    }
}
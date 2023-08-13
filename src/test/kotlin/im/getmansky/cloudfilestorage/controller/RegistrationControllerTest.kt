package im.getmansky.cloudfilestorage.controller

import im.getmansky.cloudfilestorage.model.dto.request.UserRegistrationDto
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.get
import org.springframework.test.web.servlet.post

@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class RegistrationControllerTest {

    @Autowired
    private lateinit var mockMvc: MockMvc

    @Test
    fun `test registration page`() {
        mockMvc.get("/registration") {
            contentType = MediaType.TEXT_HTML
        }
            .andExpect {
                status { isOk() }
                content { string(org.hamcrest.Matchers.containsString("Registration page")) }
            }
    }

    @Test
    fun `test creating new user`() {
        val user = UserRegistrationDto(
            username = "admin",
            password = "password123",
            passwordRepeat = "password123",
        )
        val result = mockMvc.post("/registration", user) {
            contentType = MediaType.APPLICATION_FORM_URLENCODED
            content = "username=${user.username}&password=${user.password}&passwordRepeat=${user.passwordRepeat}"
            with(csrf())
        }
        result.andExpect {
            status {
                is3xxRedirection()
                redirectedUrl("/login")
            }
        }
    }
}
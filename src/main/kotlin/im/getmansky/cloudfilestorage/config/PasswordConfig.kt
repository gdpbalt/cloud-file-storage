package im.getmansky.cloudfilestorage.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder

@Configuration
class PasswordConfig {

    @Bean
    fun encoder(): PasswordEncoder =
        BCryptPasswordEncoder(PASSWORD_STRENGTH)

    private companion object {
        const val PASSWORD_STRENGTH = 10
    }
}
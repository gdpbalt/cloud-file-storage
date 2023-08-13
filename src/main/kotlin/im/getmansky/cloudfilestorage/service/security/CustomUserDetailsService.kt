package im.getmansky.cloudfilestorage.service.security

import im.getmansky.cloudfilestorage.service.UserService
import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Service

@Service
class CustomUserDetailsService(
    private val userService: UserService,
) : UserDetailsService {

    override fun loadUserByUsername(username: String): UserDetails {
        val user = userService.findByUsername(username)
            ?: throw RuntimeException("User with username $username not found")

        val builder = User
            .withUsername(username)
            .password(user.password)
            .roles(ROLE)
        return builder.build()
    }

    private companion object {
        const val ROLE = "USER"
    }
}
package im.getmansky.cloudfilestorage.service

import im.getmansky.cloudfilestorage.model.dto.request.UserRegistrationDto
import im.getmansky.cloudfilestorage.model.entity.UserEntity
import im.getmansky.cloudfilestorage.repository.UserRepository
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
class UserService(
    private val userRepository: UserRepository,
    private val passwordEncoder: PasswordEncoder,
) {

    fun findByUsername(username: String): UserEntity? = userRepository.findByUsername(username)

    fun addUser(userRegistrationDto: UserRegistrationDto): UserEntity {
        findByUsername(userRegistrationDto.username)?.let {
            throw RuntimeException("User with username ${userRegistrationDto.username} already exists")
        }
        val userEntity = UserEntity(
            username = userRegistrationDto.username,
            password = passwordEncoder.encode(userRegistrationDto.password)
        )
        return userRepository.save(userEntity)
    }
}
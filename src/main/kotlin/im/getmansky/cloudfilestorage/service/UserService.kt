package im.getmansky.cloudfilestorage.service

import im.getmansky.cloudfilestorage.model.dto.request.UserDto
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

    fun addUser(userDto: UserDto): UserEntity {
        findByUsername(userDto.username)?.let {
            throw RuntimeException("User with username ${userDto.username} already exists")
        }
        val userEntity = UserEntity(
            username = userDto.username,
            password = passwordEncoder.encode(userDto.password)
        )
        return userRepository.save(userEntity)
    }
}
package im.getmansky.cloudfilestorage.repository

import im.getmansky.cloudfilestorage.model.entity.UserEntity
import org.springframework.data.repository.CrudRepository

interface UserRepository : CrudRepository<UserEntity, Long> {
    fun findByUsername(username: String): UserEntity?
}
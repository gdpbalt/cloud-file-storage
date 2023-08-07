package im.getmansky.cloudfilestorage.model.dto.request

import javax.validation.constraints.NotBlank
import javax.validation.constraints.Size

data class UserDto(
    @field:NotBlank(message = "Name must not be blank")
    @field:Size(min = 3, max = 128, message = "Name length must be in range [3..128]")
    val username: String = "",

    @field:NotBlank(message = "Password must not be blank")
    @field:Size(min = 3, max = 128, message = "Password length must be in range [3..128]")
    val password: String = "",
)
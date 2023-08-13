package im.getmansky.cloudfilestorage.controller

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping

@Controller
class AuthenticationController {

    @GetMapping("/login")
    fun login(): String = "login"
}
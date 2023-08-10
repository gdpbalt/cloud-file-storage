package im.getmansky.cloudfilestorage.controller

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam


@Controller
class AuthenticationController {

    @GetMapping("/login")
    fun login(
        @RequestParam(value = "error", defaultValue = "false") loginError: Boolean,
        @RequestParam(value = "logout", defaultValue = "false") logout: Boolean
    ): String {
        return "login"
    }
}
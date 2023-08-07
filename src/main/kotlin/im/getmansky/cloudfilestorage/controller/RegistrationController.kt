package im.getmansky.cloudfilestorage.controller

import im.getmansky.cloudfilestorage.model.dto.request.UserDto
import im.getmansky.cloudfilestorage.service.UserService
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.validation.BindingResult
import org.springframework.validation.ObjectError
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.ModelAttribute
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import javax.validation.Valid


@Controller
@RequestMapping("/registration")
class RegistrationController(
    private val userService: UserService,
) {

    @GetMapping
    fun registration(model: Model): String {
        model.addAttribute("user", UserDto())
        return "registration"
    }

    @PostMapping
    fun addUser(@ModelAttribute("user") @Valid user: UserDto, result: BindingResult, model: Model): String {
        if (result.hasErrors()) {
            return "registration"
        }
        try {
            userService.addUser(user)
        } catch (ex: Exception) {
            val error = ObjectError("globalError", "Unable to register user")
            result.addError(error)
            return "registration"
        }
        return "redirect:/login"
    }
}
package im.getmansky.cloudfilestorage.controller

import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping

@Controller
class HelloController {

    @GetMapping("/")
    fun hello(model: Model): String {
        model.addAttribute("welcome", "Welcome message!!!")
        return "hello"
    }
}
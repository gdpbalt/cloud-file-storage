package im.getmansky.cloudfilestorage.config

import org.springframework.context.annotation.Configuration
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer


@Configuration
class WebConfig: WebMvcConfigurer {

    override fun addViewControllers(registry: ViewControllerRegistry) {
        registry.addViewController("/welcome").setViewName("welcome")
        registry.addViewController("/").setViewName("welcome")
        registry.addViewController("/inner").setViewName("inner")
    }
}
package im.getmansky.cloudfilestorage.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.web.SecurityFilterChain
import javax.sql.DataSource

@Configuration
@EnableWebSecurity
class SecurityConfig(
    private val dataSource: DataSource,
) {

    @Bean
    fun authenticationManagerBean(http: HttpSecurity): AuthenticationManager {
        val auth = http.getSharedObject(AuthenticationManagerBuilder::class.java)
        auth.jdbcAuthentication()
            .dataSource(dataSource)
            .passwordEncoder(encoder())
            .usersByUsernameQuery("select username, password, active from users where username = ?")
            .authoritiesByUsernameQuery("select username, '$ROLE' from users where username = ?")
        return auth.build()
    }

    @Bean
    fun filterChain(http: HttpSecurity): SecurityFilterChain =
        http
            .authorizeRequests()
            .antMatchers("/css/**", "/js/**").permitAll()
            .antMatchers("/", "/welcome", "/registration", "/error").permitAll()
            .anyRequest().authenticated()
            .and()
            .formLogin().loginPage("/login").defaultSuccessUrl("/inner", true).permitAll()
            .and()
            .logout().logoutSuccessUrl("/welcome")
            .and()
            .build()

    @Bean
    fun encoder(): PasswordEncoder = BCryptPasswordEncoder()

    private companion object {
        const val ROLE = "ROLE_USER"
    }
}
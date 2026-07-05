package ar.edu.unsam.pds.security.configs

import ar.edu.unsam.pds.security.services.AppUserDetailsService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.web.servlet.server.CookieSameSiteSupplier
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.web.authentication.rememberme.TokenBasedRememberMeServices

@Configuration
class RememberMeConfiguration {
    @Autowired private lateinit var userService: AppUserDetailsService

    @Bean
    fun rememberMeServices(): TokenBasedRememberMeServices {
        return TokenBasedRememberMeServices("springRocks", userService).apply {
            setAlwaysRemember(true)
            setUseSecureCookie(true)
        }
    }

    @Bean
    fun rememberMeCookieSameSiteSupplier(): CookieSameSiteSupplier {
        return CookieSameSiteSupplier.ofNone().whenHasName("remember-me")
    }
}
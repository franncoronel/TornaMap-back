package ar.edu.unsam.pds.security

import jakarta.servlet.DispatcherType.ASYNC
import jakarta.servlet.DispatcherType.ERROR
import org.springframework.boot.autoconfigure.security.servlet.PathRequest
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpMethod.*
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.web.SecurityFilterChain
import org.springframework.security.web.authentication.rememberme.TokenBasedRememberMeServices
import org.springframework.security.web.authentication.www.DigestAuthenticationFilter
import org.springframework.security.web.util.matcher.AntPathRequestMatcher.antMatcher

@Configuration
@EnableWebSecurity
class FilterChainConfiguration {

    @Bean
    fun filterChain(http: HttpSecurity, rememberMeServices: TokenBasedRememberMeServices): SecurityFilterChain {
        // #############################################################################################################
        // # filters for exceptions                                                                                    #
        // #############################################################################################################
        http.addFilterAfter(MyTempCorsFilter(), DigestAuthenticationFilter::class.java)

//        http.sessionManagement { sm -> sm
//            .sessionConcurrency { sc -> sc
//                .maximumSessions(10)
//                .sessionRegistry(sessionRegistry())
//                .expiredUrl("http://localhost:4200/ingresar")
//            }
//            .invalidSessionUrl("http://localhost:4200/ingresar")
//        }

        // #############################################################################################################
        // # all request matchers                                                                                      #
        // #############################################################################################################
        http.authorizeHttpRequests { authorize -> authorize
            // #########################################################################################################
            // # dispatcher for exceptions                                                                             #
            // #########################################################################################################
            .dispatcherTypeMatchers(ERROR, ASYNC).permitAll()

            // ADMIN @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
            .requestMatchers(
                antMatcher(GET, "/api/institutions/admin/**"),
                antMatcher(GET, "/api/courses/admin/**"),
                antMatcher(POST, "/api/courses"),
                antMatcher(PUT, "/api/courses"),
                antMatcher(PUT , "/api/courses/*"),
                antMatcher(PUT , "/api/courses/**"),
                antMatcher(POST, "/api/courses/**"),
                antMatcher(DELETE, "/api/courses/**"),
                antMatcher(DELETE, "/api/courses"),
                antMatcher(DELETE, "/api/courses/*"),
                antMatcher(POST, "/api/events"),
                antMatcher(PUT, "/api/events"),
                antMatcher(DELETE, "/api/events/**"),
                antMatcher(DELETE, "/api/institutions/**"),
                antMatcher(GET, "/api/events/*/admin"),
            ).permitAll()
            //TODO: volver a cambiar a has role admin despues de probar

            // USER @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
            .requestMatchers(
                antMatcher(DELETE, "/api/users"),
            ).hasRole("USER")

            // USER Y ADMIN @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
            .requestMatchers(
                antMatcher(POST, "/api/institutions"),
                antMatcher(POST, "/api/events/subscribe"),
                antMatcher(POST, "/api/events/unsubscribe"),
                antMatcher(PATCH, "/api/users/**"),

                antMatcher(GET, "/api/users/reviews"),
                antMatcher(POST, "/api/courses/*/review"),
            ).hasAnyRole("USER", "ADMIN")

            // #########################################################################################################
            // # all user                                                                                              #
            // #########################################################################################################
            .requestMatchers(
                antMatcher(OPTIONS, "/**"),
                antMatcher(GET, "/media/public/**"),

                // swagger @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
                antMatcher("/swagger-ui/**"),
                antMatcher("/v3/api-docs/**"),

                // registration @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
                antMatcher(POST, "/api/users/login"),
                antMatcher(POST, "/api/users/register"),

                // public @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
                antMatcher(GET, "/api/institutions"),
                antMatcher(GET, "/api/institutions/**"),
                antMatcher(GET, "/api/courses"),
                antMatcher(GET, "/api/courses/**"),
                antMatcher(GET, "/api/courses/*/stats"),
                antMatcher(GET, "/api/events"),
                antMatcher(GET, "/api/events/**"),
                antMatcher(GET, "/api/users"),
                antMatcher(GET, "/api/users/*"),
                antMatcher(GET, "/api/users/*/courses"),
                antMatcher(GET, "/api/users/*/subscriptions"),

                antMatcher(GET, "/api/courses/*/reviews"),

                // Schedules
                antMatcher(GET, "/api/schedules"),
                antMatcher(POST, "/api/schedules"),
                antMatcher(PUT, "/api/schedules"),
                antMatcher(DELETE, "/api/schedules/*"),

                //Programs
                antMatcher(GET, "/api/programs"),
                antMatcher(GET, "/api/programs/*"),
                antMatcher(POST, "/api/programs"),
                antMatcher(PUT, "/api/programs/*"),
                antMatcher(DELETE, "/api/programs/*"),

                //Periods
                antMatcher(GET, "/api/periods"),
                antMatcher(GET, "/api/periods/*"),
                antMatcher(POST, "/api/periods"),
                antMatcher(PUT, "/api/periods/*"),
                antMatcher(DELETE, "/api/periods/*"),

                // Buildings
                antMatcher(GET, "/api/buildings"),
                antMatcher(GET, "/api/buildings/*"),

                // Classroom
                antMatcher(GET, "/api/classroom/*"),
            ).permitAll()

            // H2 DataBase @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
            .requestMatchers(
                PathRequest.toH2Console()
            ).permitAll()

            // the rest of the endpoints @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
            .anyRequest().authenticated()
        }

        // #############################################################################################################
        // # remember me implementation                                                                                #
        // #############################################################################################################
        http.rememberMe { remember -> remember
            .rememberMeServices(rememberMeServices)
        }

        http.formLogin { it.disable() }
        http.cors { it.disable() }
        http.csrf { it.disable() }

        http.headers { h -> h.frameOptions { it.disable() } }

        return http.build()
    }
}
package by.itransition.mathtasksapp.configurations;

import by.itransition.mathtasksapp.services.Impl.UserServiceImpl;
import by.itransition.mathtasksapp.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {
    private final UserServiceImpl userService;

    @Autowired
    public WebSecurityConfiguration(UserServiceImpl userService) {
        this.userService = userService;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                    .antMatchers("/error","/","/resources/**").permitAll()
                    .antMatchers("/login").not().fullyAuthenticated()
                    .antMatchers("/css/**").permitAll()
                    .antMatchers("/img/**").permitAll()
                    .antMatchers("/js/**").permitAll()
                    .antMatchers("/admin").hasRole("ADMIN")
                    .antMatchers("/user").hasRole("USER")
                    .anyRequest().authenticated()
                    .and()
                .logout()
                .and()
                .oauth2Login()
                    .loginPage("/login")
                    .defaultSuccessUrl("/")
                    .failureUrl("/")
                        .userInfoEndpoint()
                            .userService(userService);
    }
}

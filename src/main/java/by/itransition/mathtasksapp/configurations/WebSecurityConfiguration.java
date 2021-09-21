package by.itransition.mathtasksapp.configurations;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                    .antMatchers("/error","/","/login","/resources/**").permitAll()
                    .antMatchers("/css/**").permitAll()
                    .antMatchers("/img/**").permitAll()
                    .antMatchers("/js/**").permitAll()
                    .anyRequest().authenticated()
                    .and()
                .oauth2Login()
                    .loginPage("/login")
                    .defaultSuccessUrl("/")
                    .failureUrl("/")
                    .and()
                .formLogin().disable()
                .logout();

    }
}

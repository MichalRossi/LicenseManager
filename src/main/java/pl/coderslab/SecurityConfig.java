package pl.coderslab;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation
        .web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
                .withUser("user").password("{noop}user123").roles("USER")
                .and()
                .withUser("admin").password("{noop}admin").roles("ADMIN");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/**/add").hasRole("ADMIN")
                .antMatchers("/**/update/**").hasRole("ADMIN")
                .antMatchers("/**/delete/**").hasRole("ADMIN")
                .and().formLogin()
                    .loginPage("/login")
                    .defaultSuccessUrl("/clubs/")
                    .failureUrl("/login/error")

                .and().logout().logoutSuccessUrl("/login")
                .permitAll();
    }



}

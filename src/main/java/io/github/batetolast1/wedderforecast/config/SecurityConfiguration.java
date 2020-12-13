package io.github.batetolast1.wedderforecast.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.sql.DataSource;

@Configuration

@RequiredArgsConstructor
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    private final DataSource dataSource;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.jdbcAuthentication()
                .dataSource(dataSource)
                .passwordEncoder(passwordEncoder())
                .usersByUsernameQuery("SELECT username, password, active FROM users WHERE username = ?")
                .authoritiesByUsernameQuery("SELECT u.username, r.user_role FROM users u JOIN user_role ur ON ur.user_id = u.id JOIN roles r ON r.id = ur.role_id WHERE u.username = ?");
    }

    @Override
    public void configure(WebSecurity web) {
        web.ignoring()
                .antMatchers("/css/**")
                .antMatchers("/js/**")
                .antMatchers("/images/**")
                .antMatchers("/dist/**")
                .antMatchers("/plugins/**")
                .antMatchers("/h2-console", "/h2-console/**");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/").permitAll()
                .antMatchers("/simple-result").permitAll()
                .antMatchers("/register").permitAll()
                .antMatchers("/login").permitAll()
                .antMatchers("/logout").authenticated()
                .antMatchers("/user", "/user/**").hasRole("USER")
                .anyRequest().authenticated();

        http.formLogin()
                .loginPage("/login")
                .usernameParameter("username")
                .passwordParameter("password")
                .defaultSuccessUrl("/user/dashboard")
                .failureUrl("/login?error=true")
                .and()
                .rememberMe()
                .key("rememberMeWeddingForecast")
                .tokenValiditySeconds(1209600);

        http.logout()
                .logoutUrl("/logout")
                .logoutSuccessUrl("/");
    }
}

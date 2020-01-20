package ua.nic.Practica.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableWebSecurity
@Configuration
public class AppSecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure (AuthenticationManagerBuilder auth) throws Exception {

        auth.inMemoryAuthentication()
                .withUser("user")
                .password("user")
                .roles("USER")
                .and()
                .withUser("admin")
                .password("admin")
                .roles("ADMIN");

    }

    // хешує пароль
    @Bean
    public PasswordEncoder getPasswordEncoder () {
        return NoOpPasswordEncoder.getInstance();
    }

    @Override
    protected void configure (HttpSecurity http) throws Exception {
        http.authorizeRequests()
                // Всі сторінки - "/**"
                .antMatchers("/addTradingFloor").hasRole("ADMIN")
                .antMatchers("/changeTradingFloor").hasRole("ADMIN")
//                .antMatchers("/**").hasRole("ADMIN") // надаю адміну доступ до всіх сторінок
//                .antMatchers("/user").hasAnyRole("USER", "ADMIN") // надаю юзеру та адміну доступ до сторінки user
                .antMatchers("/").permitAll() // надаю всім користувачам доступ до головної сторінки
                .antMatchers("/subscriberTradingFloor").permitAll() // надаю всім користувачам доступ до головної сторінки
                .and().formLogin(); // відкриває сторінку авторизації, якщо користувач не авторизувався

    }
    //    @Override
//    protected UserDetailsService userDetailsService () {
//        return super.userDetailsService();
//    }
}

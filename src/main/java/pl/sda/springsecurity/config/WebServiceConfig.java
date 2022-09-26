package pl.sda.springsecurity.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import javax.xml.validation.SchemaFactoryConfigurationError;

import java.lang.reflect.Array;
import java.util.Arrays;

import static org.springframework.security.config.Customizer.withDefaults;

// @EnableGlobalMethodSecurity(securedEnabled = true)
@Configuration
@EnableWebSecurity

public class WebServiceConfig {

    @Bean // bean szyfrujący hasła
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();

    }

    @Bean
    public InMemoryUserDetailsManager get(){
        UserDetails user = User.withUsername("user")
                .password(passwordEncoder().encode("test123"))
                .roles("USER").authorities("ADMIN","USER") //jaką rolę ma ta osoba user z hasłem test123
                .build();
        UserDetails admin = User.withUsername("admin")
                .password(passwordEncoder().encode("admin123"))
                .roles("ADMIN") //jaką rolę ma ta osoba user z hasłem test123
                .build();
        return new InMemoryUserDetailsManager(Arrays.asList(admin,user));
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
        http.authorizeHttpRequests ((auth) ->{
            try {
                auth
                        .antMatchers("/auth","/resources").permitAll() //komu dajemy dostęp do strony auch ustalilismy że wszystkim
                        .antMatchers("/**").hasAuthority("USER")
                        .and()
                        .csrf().disable() //na potrzeby nauki zablokujemy to bo crsf blokuje dostęp np na h2
                        .headers().frameOptions().disable()
                        .and()
                        .formLogin() //ustalanie formy logowania
                        .and() //rozpoczynanie kolejnego modułu
                        .logout()
                        .permitAll();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }).httpBasic(withDefaults());
        System.out.println(passwordEncoder().encode("lody123"));
        return http.build();
    }

}

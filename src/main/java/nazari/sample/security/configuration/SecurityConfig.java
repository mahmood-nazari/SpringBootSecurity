package nazari.sample.security.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

import static nazari.sample.security.enums.ApplicationUserRole.*;

@EnableWebSecurity
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests().antMatchers("/css/*", "index").permitAll()
                .antMatchers("/api/**").hasRole(ADMIN.name())
//                .antMatchers("/management/get-all").hasRole(ADMIN_TRAINEE.name())
//                .antMatchers("/management/**").hasRole(ADMIN.name())
                .anyRequest()
                .authenticated()
                .and()
                .httpBasic();
    }

    @Override
    @Bean
    protected UserDetailsService userDetailsService() {

        UserDetails aliUser = User
                .builder()
                .username("ali")
                .password(passwordEncoder.encode("1"))
                .roles(STUDENT.name())
                .build();

        UserDetails hasanUser = User
                .builder()
                .username("hasan")
                .password(passwordEncoder.encode("2"))
                .roles(ADMIN.name())
                .build();

        UserDetails hosseinUser = User
                .builder()
                .username("hossein")
                .password(passwordEncoder.encode("3"))
                .roles(ADMIN_TRAINEE.name())
                .build();
        return new InMemoryUserDetailsManager(
                aliUser,
                hasanUser,
                hosseinUser
        );
    }
}

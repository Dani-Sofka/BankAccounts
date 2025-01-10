package bank.accounts.config;


import bank.accounts.entities.Customer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig  {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
        http.authorizeHttpRequests(request ->
                request.requestMatchers("/api/customer/**")
                        .permitAll()
                        .requestMatchers("/api/account/**")
                        .authenticated()
        )
                .httpBasic(Customizer.withDefaults())
                .csrf(csrf -> csrf.disable());
        return http.build();
    }

    @Bean
    public UserDetailsService testCustomer(PasswordEncoder passwordEncoder){
            Customer customer = Customer.builder()
                    .name("Dani Rúa")
                    .password("abc123")
                    .build();
        return new InMemoryUserDetailsManager();
    }

    @Bean
    public PasswordEncoder passwordEncore (){
        return new BCryptPasswordEncoder();
    }


}

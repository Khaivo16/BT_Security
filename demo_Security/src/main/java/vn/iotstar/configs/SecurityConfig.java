package vn.iotstar.configs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
	@Bean
	// authentication
	UserDetailsService userDetailsService(PasswordEncoder encoder) {
		UserDetails admin = User.withUsername("trung")
								.password(encoder.encode("123"))
								.roles("ADMIN")
								.build();
		UserDetails user = User.withUsername("user")
								.password(encoder.encode("123"))
								.roles("USER")
								.build();
		return new InMemoryUserDetailsManager(admin,user);
	}
	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http.csrf(csrf -> csrf.disable()) // Vô hiệu hóa CSRF (nếu không cần)
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/hello").permitAll() // Trang chủ không yêu cầu xác thực
                .requestMatchers("/customer/**").authenticated() // Các URL bắt đầu với /customer/ yêu cầu xác thực
                //.anyRequest().authenticated() // Các đường dẫn khác cũng cần xác thực (bỏ comment nếu cần)
            )
            .formLogin(Customizer.withDefaults()) // Sử dụng form login mặc định
            .build();
}
}

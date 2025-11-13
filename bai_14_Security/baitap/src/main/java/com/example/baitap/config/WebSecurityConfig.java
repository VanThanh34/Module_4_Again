package com.example.baitap.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.thymeleaf.extras.springsecurity6.dialect.SpringSecurityDialect;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;



@Configuration
@EnableWebSecurity
public class WebSecurityConfig {

    @Autowired
    private UserDetailsService userDetailsService;

//    SpringSecurityDialect là một thành phần tích hợp giữa Spring Security và Thymeleaf,
//    giúp bạn dễ dàng kiểm soát và hiển thị nội dung trong các template Thymeleaf dựa trên quyền hạn và trạng thái xác thực của người dùng.
//    Dưới đây là giải thích chi tiết về mục đích và cách sử dụng SpringSecurityDialect trong ứng dụng Spring Boot của bạn.
    @Bean
    public SpringSecurityDialect springSecurityDialect() {
        return new SpringSecurityDialect();
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        return bCryptPasswordEncoder;
    }

    // xác thực
    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(userDetailsService);
        authenticationProvider.setPasswordEncoder(passwordEncoder());
        return authenticationProvider;
    }

//    @Bean
//    public AuthenticationProvider authenticationProvider() {
//        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
//        authenticationProvider.setUserDetailsService(userDetailsService(passwordEncoder()));
//        authenticationProvider.setPasswordEncoder(passwordEncoder());
//        return authenticationProvider;
//    }
//
//    @Bean
//    public UserDetailsService userDetailsService(PasswordEncoder encoder) {
//        UserDetails admin = User.withUsername("admin")
//                .password(encoder.encode("123"))
//                .roles("ADMIN")
//                .build();
//        UserDetails user = User.withUsername("user")
//                .password(encoder.encode("123"))
//                .roles("USER")
//                .build();
//        return new InMemoryUserDetailsManager(admin, user);
//    }

    // phân quyền
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf(AbstractHttpConfigurer::disable);

        http.authorizeHttpRequests(auth -> auth
                // Cho phép truy cập tự do
                .requestMatchers("/", "/welcome", "/login", "/logout", "/logoutSuccessful", "/403", "/css/**", "/js/**", "/images/**").permitAll()

                // Trang cầu thủ
                .requestMatchers("/players", "/players/{id}", "/players/team").permitAll()

                // Chỉ USER mới được xem đội hình yêu thích
                .requestMatchers("/players/favorites", "/players/favorite/**").hasRole("USER")

                // Chỉ ADMIN mới được thêm, sửa, xóa cầu thủ
                .requestMatchers("/players/add", "/players/edit/**", "/players/delete/**", "/players/toggle/**").hasRole("ADMIN")

                // Chỉ ADMIN mới được vào trang quản trị
                .requestMatchers("/admin/**").hasRole("ADMIN")

                // Trang user info dành cho cả USER & ADMIN
                .requestMatchers("/userInfo").hasAnyRole("USER", "ADMIN")

                // Tất cả request khác phải đăng nhập
                .anyRequest().authenticated()
        );

        http.formLogin(form -> form
                .loginPage("/login")
                .loginProcessingUrl("/process-login")
                .defaultSuccessUrl("/userInfo", true)
                .failureUrl("/login?error=true")
                .usernameParameter("username")
                .passwordParameter("password")
        );

        http.logout(form -> form
                .logoutUrl("/logout")
                .logoutSuccessUrl("/logoutSuccessful")
        );

        http.exceptionHandling(ex -> ex.accessDeniedPage("/403"));
        return http.build();
    }


    // Cấu hình vùng nhớ lưu trạng thái đăng nhập trên bộ nhớ của máy tính người dùng
//    @Bean
//    public PersistentTokenRepository persistentTokenRepository() {
//        InMemoryTokenRepositoryImpl memory = new InMemoryTokenRepositoryImpl();
//        return memory;
//    }

}
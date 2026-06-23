package kr.or.kosa.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {
    private final DataSource dataSource;

    // @Configuration 설정 파일 ... xml처럼 빈 생성 후 주입
    // @Bean: method에서 객체를 생성해서 return 하여 bean을 등록

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        /*
        <security:http auto-config="true">
            <security:csrf disabled="true" />
            <security:intercept-url pattern="/customer/noticeDetail.do"  access="hasRole('ROLE_USER')"/>
            <security:intercept-url pattern="/customer/noticeReg.do"     access="hasRole('ROLE_ADMIN')"/>
        </security:http>

        http.authorizeHttpRequests(auth -> auth
                    .requestMatchers("/admin/**").hasRole("ADMIN")  // ROLE_USER, ROLE_ADMIN에서 ROLE_은 생략 가능
                    .requestMatchers("/user/**").hasAnyRole("USER", "ADMIN")    // 의미: requestMatchers에 있는 url은 hasAnyRole에 있는 role들한테 허용한다.
                    .requestMatchers("/css/**", "/js/**", "/images/**").permitAll()
                    .requestMatchers("/", "/**").permitAll()    // security는 범위를 작은것 -> 큰것을 본다. 위에서 설정한 각 경로는 ROLE들만 접근 가능하지만, 여기서 나머지에 대해서는 접근을 다 허용해주는 것
                    .anyRequest().authenticated() // 설정한 것 말고 다른 경로는 인증된 사용자만 허락하겠다.
                )
                .formLogin(form -> form.permitAll())   // login 페이지는 권한에 걸리면 X
                .logout(logout -> logout.permitAll());
         */


        http.authorizeHttpRequests(auth -> auth
                        .requestMatchers("/admin/**").hasRole("ADMIN")  // ROLE_USER, ROLE_ADMIN에서 ROLE_은 생략 가능
                        .requestMatchers("/user/**").hasAnyRole("USER", "ADMIN")    // 의미: requestMatchers에 있는 url은 hasAnyRole에 있는 role들한테 허용한다.
                        .requestMatchers("/css/**", "/js/**", "/images/**").permitAll()
                        .requestMatchers("/", "/**").permitAll()    // security는 범위를 작은것 -> 큰것을 본다. 위에서 설정한 각 경로는 ROLE들만 접근 가능하지만, 여기서 나머지에 대해서는 접근을 다 허용해주는 것
                        .anyRequest().permitAll())   // 설정한 것 말고 다른 경로는 인증된 사용자만 허락하겠다.
                .formLogin(form -> {});

        http.logout(logout -> logout
                .logoutUrl("/logout")     //로그아웃 요청을 받을 URL
                .logoutSuccessUrl("/")    //로그아웃 성공 후 이동할 URL
                .deleteCookies("JSESSIONID")  //쿠키 삭제
                .invalidateHttpSession(true)); //세션 객체 삭제

        return http.build();
    }


    // 인증과 권한
    /*
		 http
    	.formLogin(form -> form
        .loginPage("/login")              // 내가 만든 로그인 페이지 URL
        .loginProcessingUrl("/loginProc") // 로그인 요청을 처리할 URL (Controller 필요 없음)
        .usernameParameter("email")      // 로그인 폼에서 name="email" 인 입력 필드 사용
        .passwordParameter("userPw")      // name="userPw"
        .defaultSuccessUrl("/home", true) // 로그인 성공 시 이동
        .failureUrl("/login?error=true")  // 실패 시 이동
        .permitAll()
         loginProcessingUrl()은 Spring Security가 직접 처리하기 때문에
         Controller에서 /loginProc 매핑을 만들 필요가 없습니다.
        );

		 */
    @Bean
    public UserDetailsService userDetailsService() {
        // DB 연결 (회원정보)
        // JDBC 연결
        JdbcUserDetailsManager userDetailsManager = new JdbcUserDetailsManager(dataSource);

        String userQuery = "SELECT user_id AS username, user_pw AS password, enabled FROM users WHERE user_id = ?";
        String roleQuery = "SELECT user_id AS username, auth FROM user_auth WHERE user_id = ?";

        userDetailsManager.setUsersByUsernameQuery(userQuery);
        userDetailsManager.setAuthoritiesByUsernameQuery(roleQuery);

        return userDetailsManager;
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}

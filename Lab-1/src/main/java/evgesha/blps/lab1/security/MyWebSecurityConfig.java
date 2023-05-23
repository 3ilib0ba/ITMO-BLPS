package evgesha.blps.lab1.security;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;


@EnableWebSecurity
public class MyWebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Value("${security.users.file}")
    private String usersFile;

//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//
//        auth.inMemoryAuthentication()
//                .withUser("adminka")
//                .password("1234")
//                .roles("ADMIN")
//                .and()
//                .withUser("test1")
//                .password("1111")
//                .roles("USER");
//
//    }

//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.authenticationProvider(jaasAuthenticationProvider());
//    }
//
//    @Bean
//    public JaasAuthenticationProvider jaasAuthenticationProvider() {
//        JaasAuthenticationProvider authenticationProvider = new JaasAuthenticationProvider();
//        authenticationProvider.setLoginConfig(loginConfig());
//        authenticationProvider.setUserDetailsService(userDetailsService());
//        return authenticationProvider;
//    }
//
//    @Bean
//    public JaasLoginConfig loginConfig() {
//        JaasLoginConfig loginConfig = new JaasLoginConfig();
//        loginConfig.setLoginModule("xmlFileLoginModule");
//        loginConfig.setOptions("file=\"" + usersFile + "\"");
//        return loginConfig;
//    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.authorizeRequests()
                .antMatchers("/ping").permitAll()
                .antMatchers("/swagger-ui/**").permitAll()
                .antMatchers("/**").permitAll()
//                .antMatchers("/recipes/getAll").hasRole("ADMIN")
//                .anyRequest().hasAnyRole()
                .and()
                .httpBasic()
                .and()
                .csrf().disable();

//        http.addFilterBefore(F);
    }

    @Bean
    public PasswordEncoder encoder() {
        return NoOpPasswordEncoder.getInstance();
    }
}
//
//    @Bean
//    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//        http
//                .authorizeHttpRequests((requests) -> requests
//                        .requestMatchers("/ping").permitAll()
//                        .anyRequest().authenticated()
//                );
//
//        return http.build();
//    }
//
//    @Bean
//    public UserDetailsService userDetailsService() {
//        UserDetails user =
//                User.withDefaultPasswordEncoder()
//                        .username("user1")
//                        .password("password")
//                        .roles("USER")
//                        .build();
//
//        return new InMemoryUserDetailsManager(user);
//    }
//}

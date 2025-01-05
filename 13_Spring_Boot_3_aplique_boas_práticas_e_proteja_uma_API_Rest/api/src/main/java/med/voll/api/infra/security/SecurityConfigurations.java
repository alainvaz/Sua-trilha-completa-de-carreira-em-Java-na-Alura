package med.voll.api.infra.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfigurations {

    @Autowired
    private SecurityFilter securityFilter;

    /**
     * Desabilitamos o processo de autenticação do Spring que é utilizado como exemplo no Front end e será configurado
     * requisição STATEFUL:
     * Configura a cadeia de filtros de segurança da aplicação.
     * <p>
     * Desativa a proteção CSRF, define a política de criação de sessão como
     * STATELESS (sem estado) para APIs REST e configura as regras de
     * autorização de requisições:
     * <ul>
     *   <li>Permite acesso irrestrito ao endpoint de login via POST.</li>
     *   <li>Exige autenticação para qualquer outra requisição.</li>
     * </ul>
     * Adiciona o filtro customizado de segurança antes do filtro de autenticação
     * padrão baseado em nome de usuário e senha.
     *
     * @param http Objeto {@link HttpSecurity} usado para configurar a segurança HTTP.
     */

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http.csrf(csrf -> csrf.disable())
                .sessionManagement(sm -> sm.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(req -> {
                    req.requestMatchers(HttpMethod.POST, "/login").permitAll();
                    req.anyRequest().authenticated();
                })
                .addFilterBefore(securityFilter, UsernamePasswordAuthenticationFilter.class) // importante esse trecho para executar no filtro criado na aplicação para depois executar o filtro do Spring
                .build();
    }

    @Bean
    public AuthenticationManager authenticationManager (AuthenticationConfiguration configuration) throws Exception{
        return configuration.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

}

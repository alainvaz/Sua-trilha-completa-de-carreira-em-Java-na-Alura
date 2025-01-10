package med.voll.api.infra.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import med.voll.api.domain.usuario.UsuarioRepositorory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class SecurityFilter extends OncePerRequestFilter {

    @Autowired
    private TokenService tokenService;

    @Autowired
    private UsuarioRepositorory repositorory;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        System.out.println("Chamando Filter!!!!");
        // recuperar o token
        var tokenJWT = recuperarToken(request);
        System.out.println("token recuperado: " + tokenJWT);

        if(tokenJWT != null){
            var subject = tokenService.getSubject(tokenJWT);
            System.out.println("Retorno subject: " + subject);

            // Autentica usuário para o Spring de acordo com o Token recebido:
            var usuario = repositorory.findByLogin(subject);
            var authentication = new UsernamePasswordAuthenticationToken(usuario, null, usuario.getAuthorities());

            SecurityContextHolder.getContext().setAuthentication(authentication);

            System.out.println("Logado na requisição!");

        }

        filterChain.doFilter(request, response); // chamando o próximo filtro, caso contrário será interrompido e retorna a requisição
    }

    private String recuperarToken(HttpServletRequest request) {
        System.out.println(request);
        var authorizationHeader = request.getHeader("Authorization");
        if(authorizationHeader != null){
            return authorizationHeader.replace("Bearer ", "");
        }

        return null;
    }
}

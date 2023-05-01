package com.example.demo.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.example.demo.exception.InvalidJwtAuthenticationException;
import com.example.demo.service.Dto.TokenDTO;
import com.example.demo.service.UserCustomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import java.util.Base64;
import java.util.Date;
import java.util.List;


@Service
public class JwtTokenProvider {

    @Value("${security.jwt.token.secret-key:secret}")
    private String secretKey = "secret";

    @Value("${security.jwt.token.expire-length:3600000}")
    private long  validMillSecounds = 360000;

    private final String BEARER = "Bearer ";

    @Autowired
    private UserCustomService userCustomService;

    Algorithm algorithm =  null;
    @PostConstruct
    protected void init(){
        secretKey = Base64.getEncoder().encodeToString(secretKey.getBytes());
        algorithm = Algorithm.HMAC256(secretKey.getBytes());
    }

    public TokenDTO createAcessToken( String email, List<String> roles){
        Date now  = new Date();
        Date validity  = new Date(now.getTime() + validMillSecounds);
        var acessToken = getAcessToken(email, roles,now,validity);
        var refreshToken = getRefreshToken(email, roles,now);
       return new TokenDTO(email,Boolean.TRUE,now,validity,acessToken,refreshToken);
    }

    private String getAcessToken(String email, List<String> roles, Date now, Date validity) {
        String issueUrl = ServletUriComponentsBuilder.fromCurrentContextPath().build().toUriString();

        return JWT.create()
                .withClaim("roles", roles)
                .withIssuedAt(now)
                .withExpiresAt(validity)
                .withSubject(email)
                .withIssuer(issueUrl)
                .sign(algorithm)
        .strip();
    }
    private String getRefreshToken(String email, List<String> roles, Date now) {
        Date validRefreshToken = new Date(now.getTime() + (validMillSecounds * 3));
        return JWT.create()
                .withClaim("roles", roles)
                .withIssuedAt(now)
                .withExpiresAt(validRefreshToken)
                .withSubject(email)
                .sign(algorithm)
                .strip();
    }

    public Authentication getAuthetication (String token){
        DecodedJWT decodedJWT = decodedToken(token);
        var userCustom = this.userCustomService.loadUserByEmail(decodedJWT.getSubject());
        return new UsernamePasswordAuthenticationToken(userCustom,"",userCustom.getAuthorities());
    }

    private DecodedJWT decodedToken(String token) {
      var alg = Algorithm.HMAC256(secretKey.getBytes());
      JWTVerifier verifier = JWT.require(alg).build();
      return  verifier.verify(token);
    }

    public String resolveToken(HttpServletRequest req){
        String bearerToken = req.getHeader("Authorization");
        if(!bearerToken.isEmpty() && bearerToken.startsWith(BEARER))
            return bearerToken.substring(BEARER.length());
        return null;
    }

    public Boolean validateToken(String token){
        var decodedJWT = decodedToken(token);
        try{
            if(decodedJWT.getExpiresAt().before(new Date())){
                return Boolean.FALSE;
            }
            return Boolean.TRUE;
        }catch (Exception e){
            throw new InvalidJwtAuthenticationException("Expired or Invalid JWT token !");
        }
    }

}

package Lim.practiceJwt;

import Lim.practiceJwt.jwt.TokenDto;
import Lim.practiceJwt.jwt.TokenManager;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

@Service
@RequiredArgsConstructor
public class LoginService {

    private final CustomerRepository customerRepository;
    private final AuthenticationManagerBuilder authenticationManager;
    private final TokenManager tokenManager;

    //서비스와 form의 독립성을 위해서 loginForm을 인자로 넘기지 않음
    //로그인 실패시 null을, 성공시 customer을 리턴함.
    public TokenDto login(String loginId, String password){
        UsernamePasswordAuthenticationToken upToken = new UsernamePasswordAuthenticationToken(loginId, password);
        Authentication authentication = authenticationManager.getObject().authenticate(upToken);

        if(!authentication.isAuthenticated()){
            return null;
        }else{
            return tokenManager.generateToken(authentication, loginId);
        }
    }

    //로그아웃
    public void logout(HttpServletRequest request) {
        String token = tokenManager.resolveRequest(request);

    }

}

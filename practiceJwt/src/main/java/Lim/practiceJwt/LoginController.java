package Lim.practiceJwt;

import Lim.practiceJwt.jwt.TokenDto;
import Lim.practiceJwt.jwt.TokenManager;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequiredArgsConstructor
public class LoginController {

    private final LoginService loginService;
    private final TokenManager tokenManager;

    @GetMapping("/")
    public String home(){
        return "home";
    }

    @PostMapping("/login")
    public TokenDto loginHome(@RequestBody LoginForm loginForm){
        TokenDto token = loginService.login(loginForm.getLoginId(), loginForm.getPassword());
        if(token == null) return null;
        else return token;
    }

    @GetMapping("/valid")
    public String checkValid(HttpServletRequest request){
        String accessToken = tokenManager.resolveRequest(request);
        if(tokenManager.validateToken(accessToken)) return "token is valid!!!!";
        else return "token is unvalid";
    }
}

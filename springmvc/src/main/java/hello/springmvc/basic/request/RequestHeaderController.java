package hello.springmvc.basic.request;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpMethod;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.net.http.HttpResponse;
import java.util.Locale;

@Slf4j
@RestController
public class RequestHeaderController {

    @RequestMapping("/headers")
    public String headers(HttpServletRequest req,
                          HttpServletResponse res,
                          HttpMethod httpMethod,
                          Locale locale, //언어정보
                          @RequestHeader MultiValueMap<String, String> headerMap,
                          @RequestHeader("host") String aa, // "host"는 header의 이름을 매핑함.
                          @CookieValue(value = "myCookie", required = false) String cookie) {
        log.info("request={}", req);
        log.info("response={}", res);
        log.info("httpMethod={}", httpMethod);
        log.info("locale={}", locale);
        log.info("headerMap={}", headerMap);
        log.info("header host={}", aa);
        log.info("myCookie={}", cookie);
        return "ok";
    }
}

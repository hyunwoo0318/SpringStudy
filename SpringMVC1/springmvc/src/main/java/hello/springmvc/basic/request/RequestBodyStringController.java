package hello.springmvc.basic.request;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Writer;
import java.nio.charset.StandardCharsets;

@Slf4j
@Controller
public class RequestBodyStringController {
    @PostMapping("/request-body-string-v1")
    public void requestBodyString(HttpServletRequest req, HttpServletResponse res) throws IOException {
        ServletInputStream inputStream = req.getInputStream();
        String messageBody = StreamUtils.copyToString(inputStream, StandardCharsets.UTF_8);// 스트림은 바이트 코드 이므로 어떤 문자로 바꿔서 인코딩해줄지 정해야함.

        log.info("messageBody = {}", messageBody);

        res.getWriter().write("ok");

    }

        @PostMapping("/request-body-string-v2")
        public void requestBodyString2(InputStream inputStream, Writer responseWriter) throws IOException {
            String messageBody = StreamUtils.copyToString(inputStream, StandardCharsets.UTF_8);// 스트림은 바이트 코드 이므로 어떤 문자로 바꿔서 인코딩해줄지 정해야함.

            log.info("messageBody = {}", messageBody);

            responseWriter.write("ok");

        }

    @PostMapping("/request-body-string-v3")
    public HttpEntity<String> requestBodyString3(HttpEntity<String> httpEntity) throws IOException {

        String messageBody = httpEntity.getBody();
        log.info("messageBody = {}", messageBody);

        return new HttpEntity<>("ok");
    }

    @ResponseBody
    @PostMapping("/request-body-string-v4")
    public String requestBodyString4(@RequestBody String messageBody) throws IOException {

        log.info("messageBody = {}", messageBody);

        return "ok";
    }
}

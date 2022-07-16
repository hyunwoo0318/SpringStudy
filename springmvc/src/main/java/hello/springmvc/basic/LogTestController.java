package hello.springmvc.basic;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

//문자를 반환하면 그 자체로 반환됨 -> controller는 view이름을 반환함. -->> rest-api를 만들때 많이 사용함.
@RestController
@Slf4j
public class LogTestController {
   // private final Logger log = LoggerFactory.getLogger(getClass());

    @RequestMapping("/log-test")
    public String logTest() {
        String name = "spring";

        //레벨순
        log.trace("trace log ={}", name);
        log.debug("debug log ={}", name);
        log.info("info log = {}", name);
        log.warn("warn log ={}", name);
        log.error("error log ={}", name);

        return "ok";
    }
}

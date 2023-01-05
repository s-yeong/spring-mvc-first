package hello.springmvc.basic;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

//@Slf4j    // 애노테이션으로도 가능
@RestController
public class LogTestController {

    // 로그 선언    (slf4j.Logger)
    private final Logger log = LoggerFactory.getLogger(getClass());

    @RequestMapping("/log-test")
    public String logTest() {
        String name = "Spring";

        // 로그 호출 - 레벨순
        log.trace("trace log={}", name);
        log.debug("debug log={}", name);
        log.info(" info log={}", name);
        log.warn(" warn log={}", name);
        log.error("error log={}", name);

        // 이 방식은 사용 X
        // info 레벨인 경우 debug 로그 사용하지 않음 -> 사용 안해도 a+b 계산 로직이 실행됨!
//        log.debug("String concat log=" + name);

        return "OK";
    }
}

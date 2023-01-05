package hello.springmvc.basic.request;

import hello.springmvc.basic.HelloData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

@Slf4j
@Controller
public class RequestParamController {

    // HTTP 요청 파라미터

    @RequestMapping("/request-param-v1")
    public void requestParamV1(HttpServletRequest request, HttpServletResponse response) throws IOException {

        String username = request.getParameter("username");
        int age = Integer.parseInt(request.getParameter("age"));
        log.info("username={}, age={}", username, age);

        response.getWriter().write("OK");
    }

    // request.getParameter("username") -> @RequestParam("username") String memberName
    @ResponseBody
    @RequestMapping("/request-param-v2")
    public String requestParamV2(@RequestParam("username") String memberName,
                                 @RequestParam("age") int memberAge) {
        log.info("username={}, age={}", memberName, memberAge);
        return "OK";
    }

    // HTTP 파라미터 이름이 변수 이름과 같으면 생략 가능
    @ResponseBody
    @RequestMapping("/request-param-v3")
    public String requestParamV3(@RequestParam String username,
                                 @RequestParam int age) {
        log.info("username={}, age={}", username, age);
        return "OK";
    }

    // String, int, Integer 등의 단순타입이면 @RequestParam도 생략 가능
    // (생략시 스프링 MVC 내부에서 required=false 적용)
    @ResponseBody
    @RequestMapping("/request-param-v4")
    public String requestParamV4(String username,
                                 int age) {
        log.info("username={}, age={}", username, age);
        return "OK";
    }

    @ResponseBody
    @RequestMapping("/request-param-required")
    public String requestParamRequired(@RequestParam(required = true) String username,  // 필수
                                       @RequestParam(required = false) Integer age) {    // 필수X
        log.info("username={}, age={}", username, age);
        return "OK";
    }

    // 기본값 적용 - defaultValue (required 의미X)
    // 빈 문자의 경우도 기본값 적용된다 (/request-param-default?username=)
    @ResponseBody
    @RequestMapping("/request-param-default")
    public String requestParamDefault(@RequestParam(required = true, defaultValue = "guest") String username,
                                       @RequestParam(required = false, defaultValue = "-1") int age) {
        log.info("username={}, age={}", username, age);
        return "OK";
    }

    // 파라미터 Map으로 조회
    @ResponseBody
    @RequestMapping("/request-param-map")
    public String requestParamMap(@RequestParam Map<String, Object> paramMap) {
        log.info("username={}, age={}", paramMap.get("username"), paramMap.get("age"));
        return "OK";
    }

    // @ModelAttribute 적용
    @ResponseBody
    @RequestMapping("/model-attribute-v1")
    public String modelAttributeV1(@ModelAttribute HelloData helloData) {

        // HelloData 객체 생성 -> 요청 파라미터 값 모두 들어감
        
        /*
        HelloData helloData = new HelloData();
        helloData.setAge(age);
        helloData.setUsername(username);
        */

        log.info("username={}, age={}", helloData.getUsername(), helloData.getAge());
        return "OK";
    }

    // @ModelAttribute 생략 가능 (argument resolver로 지정해둔 타입 외)
    @ResponseBody
    @RequestMapping("/model-attribute-v2")
    public String modelAttributeV2(HelloData helloData) {

        log.info("username={}, age={}", helloData.getUsername(), helloData.getAge());
        return "OK";
    }

}

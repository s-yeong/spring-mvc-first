package hello.springmvc.basic.response;


import hello.springmvc.basic.HelloData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
//@Controller
//@ResponseBody
@RestController
public class ResponseBodyController {

    // 서블릿을 직접 다룰 때 처럼 HttpServletResponse 객체를 통해서 HTTP 메시지 바디에 "직접" 응답
    @GetMapping("/response-body-string-v1")
    public void responseBodyV1(HttpServletResponse response) throws IOException {
        response.getWriter().write("OK");
    }

    /**
     * HttpEntity, ResponseEntity, @ResponseBody
     * 사용하면 view를 사용하지 않고, HTTP 메시지 컨버터를 통해서 HTTP 메시지 "직접" 입력
     */

    // HttpEntity, ResponseEntity(Http Status 추가)
    @GetMapping("/response-body-string-v2")
    public ResponseEntity<String> responseBodyV2() throws IOException {
        return new ResponseEntity<>("OK", HttpStatus.OK);
    }

//    @ResponseBody
    @GetMapping("/response-body-string-v3")
    public String responseBodyV3() throws IOException {
        return "OK";
    }

    // JSON 형태 ResponseEntity
    @GetMapping("/response-body-json-v1")
    public ResponseEntity<HelloData> responseBodyJsonV1() {
        HelloData helloData = new HelloData();
        helloData.setUsername("userA");
        helloData.setAge(20);
        return new ResponseEntity<>(helloData, HttpStatus.OK);
    }

    // JSON 형태 @ResponseBody + HttpStatus (이 방법 사용)
    @ResponseStatus(HttpStatus.OK)
//    @ResponseBody
    @GetMapping("/response-body-json-v2")
    public HelloData responseBodyJsonV2() {
        HelloData helloData = new HelloData();
        helloData.setUsername("userA");
        helloData.setAge(20);
        return helloData;
    }
}

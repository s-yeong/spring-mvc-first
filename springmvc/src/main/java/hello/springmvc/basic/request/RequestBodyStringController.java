package hello.springmvc.basic.request;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
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
import java.io.Writer;
import java.nio.charset.StandardCharsets;

@Slf4j
@Controller
public class RequestBodyStringController {

    @PostMapping("/request-body-string-v1")
    public void requestBodyStringV1(HttpServletRequest request, HttpServletResponse response) throws IOException {
        // HTTP 메시지 바디의 데이터를 InputStream을 사용해서 직접 읽을 수 있다.

        ServletInputStream inputStream = request.getInputStream();  // 스트림(바이트코드)
        String messageBody = StreamUtils.copyToString(inputStream, StandardCharsets.UTF_8); // 문자 변환

        log.info("messageBody={}", messageBody);

        response.getWriter().write("OK");
    }

    @PostMapping("/request-body-string-v2")
    public void requestBodyStringV2(InputStream inputStream, Writer responseWriter) throws IOException {

        // InputStream(Reader) : HTTP 요청 메시지 바디의 내용을 직접 조회
        // OutputStream(Writer) : HTTP 응답 메시지의 바디에 직접 결과 출력

        String messageBody = StreamUtils.copyToString(inputStream, StandardCharsets.UTF_8); // 문자 변환

        log.info("messageBody={}", messageBody);

        responseWriter.write("OK");
    }

    // HttpEntity
    @PostMapping("/request-body-string-v3")
    public HttpEntity<String> requestBodyStringV3(HttpEntity<String> httpEntity) throws IOException {

        String messageBody = httpEntity.getBody();

        log.info("messageBody={}", messageBody);

        return new HttpEntity<>("OK");
    }

    // @RequestBody, @ResponseBody
    @ResponseBody
    @PostMapping("/request-body-string-v4")
    public String requestBodyStringV4(@RequestBody String messageBody) throws IOException {

        log.info("messageBody={}", messageBody);

        return "OK";
    }






}

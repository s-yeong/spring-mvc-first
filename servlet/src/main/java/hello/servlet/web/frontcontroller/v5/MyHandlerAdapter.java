package hello.servlet.web.frontcontroller.v5;

import hello.servlet.web.frontcontroller.ModelView;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

// 어댑터용 인터페이스
public interface MyHandlerAdapter {

    // 핸들러(컨트롤러)를 내가 처리할 수 있는지 없는지 반환
    boolean supports(Object handler);

    // 핸들러 호출
    ModelView handle(HttpServletRequest request, HttpServletResponse response,
                     Object handler) throws ServletException, IOException;
    // 어댑터는 실제 컨트롤러를 호출하고, 그 결과로 ModelView 반환해야함
    // 실제 컨트롤러가 ModelView를 반환 못하면 어댑터가 ModelView를 직접 생성해서라도 반환해야함
    // 이 어댑터가 "실제 컨트롤러 호출"      <-> 프론트 컨트롤러가 실제 컨트롤러 호출
}
package hello.servlet.web.springmvc.old;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

// 스프링 빈의 이름을 urlPatterns로 맞춤 -> 빈의 이름으로 URL을 매핑하기 위해
@Component("/springmvc/old-controller")
public class OldController implements Controller {
    // FrontControllerV3와 유사

    @Override
    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
        System.out.println("OldController.handleRequest");
        return new ModelAndView("new-form");
        // "application.properties"에서 View 추가 안하면 에러 페이지 뜸
    }
}

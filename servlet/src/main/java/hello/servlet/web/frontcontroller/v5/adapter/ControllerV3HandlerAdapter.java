package hello.servlet.web.frontcontroller.v5.adapter;

import hello.servlet.web.frontcontroller.ModelView;
import hello.servlet.web.frontcontroller.MyView;
import hello.servlet.web.frontcontroller.v3.ControllerV3;
import hello.servlet.web.frontcontroller.v5.MyHandlerAdapter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class ControllerV3HandlerAdapter implements MyHandlerAdapter {
    // ControllerV3를 지원하는 어댑터

    @Override
    public boolean supports(Object handler) {
        // 이게 V3 인스턴스 인가요? (ControllerV3를 처리할 수 있는 어댑터)
        return (handler instanceof ControllerV3);
    }

    @Override
    public ModelView handle(HttpServletRequest request, HttpServletResponse response, Object handler) throws ServletException, IOException {
    // ModelView 반환!

        // 위에서 V3 인스턴스인지 검증했기 때문에 V3로 캐스팅
        ControllerV3 controller = (ControllerV3) handler;

        // V3 로직 ---->
        Map<String, String> paramMap = createParamMap(request, response);
        ModelView mv = controller.process(paramMap);

        return mv;
    }

    private Map<String, String> createParamMap(HttpServletRequest request, HttpServletResponse response) {

        HashMap<String, String> paramMap = new HashMap<>();
        request.getParameterNames().asIterator()
                .forEachRemaining(paramName -> paramMap.put(paramName, request.getParameter(paramName)));   // ex) age, "20"
        return paramMap;
    }
}

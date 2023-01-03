package hello.servlet.web.frontcontroller.v4;

import hello.servlet.web.frontcontroller.ModelView;
import hello.servlet.web.frontcontroller.MyView;
import hello.servlet.web.frontcontroller.v4.controller.MemberFormControllerV4;
import hello.servlet.web.frontcontroller.v4.controller.MemberListControllerV4;
import hello.servlet.web.frontcontroller.v4.controller.MemberSaveControllerV4;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet(name = "frontControllerServletV4", urlPatterns = "/front-controller/v4/*")
// "/front-controller/v4" 을 포함한 하위 모든 요청은 이 서블릿이 호출된다!
public class FrontControllerServletV4 extends HttpServlet {

    private Map<String, ControllerV4> controllerV4Map = new HashMap<>();    // 매핑 URL, 호출될 컨트롤러

    // 생성자 - URL 매핑 정보
    public FrontControllerServletV4() {
        controllerV4Map.put("/front-controller/v4/members/new-form", new MemberFormControllerV4());
        controllerV4Map.put("/front-controller/v4/members/save", new MemberSaveControllerV4());
        controllerV4Map.put("/front-controller/v4/members", new MemberListControllerV4());
    }


    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String requestURI = request.getRequestURI();

        // *** 인터페이스를 쓰면 이 코드를 일관되게 쓸 수 있다! *** (다형성에 의해 인터페이스로 받을 수 있음)
        ControllerV4 controller = controllerV4Map.get(requestURI);
        if(controller == null) {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return;
        }

        // *** 계층을 맞춰주기 위해 메서드 작성 ***

        Map<String, String> paramMap = createParamMap(request, response);

        // V4 추가
        Map<String, Object> model = new HashMap<>();
        String viewName = controller.process(paramMap, model);
        // model 넘겨서 컨트롤러에서 model에 값을 담아도 프론트 컨트롤러에 그대로 담겨있음

        MyView view = viewResolver(viewName);

        view.render(model, request, response); // 호출 -> forward 로직을 수행해서 JSP 실행! (모두 일관되게 처리 할 수 있음)
        // request, resonse를 model로 사용안했기 때문에 model까지 넘겨줌
    }

    // 컨트롤러가 서블릿에 종속적이지 않기 하기 위해 HttpServletRequest에서 파라미터 정보를 꺼내서 paramMap에 담음
    private Map<String, String> createParamMap(HttpServletRequest request, HttpServletResponse response) {

        HashMap<String, String> paramMap = new HashMap<>();
        request.getParameterNames().asIterator()
                .forEachRemaining(paramName -> paramMap.put(paramName, request.getParameter(paramName)));   // ex) age, "20"
        return paramMap;
    }

    // 뷰를 해결해준다 논리 이름 + 물리 이름
    private MyView viewResolver(String viewName) {
        return new MyView("/WEB-INF/views/" + viewName + ".jsp");
    }
}

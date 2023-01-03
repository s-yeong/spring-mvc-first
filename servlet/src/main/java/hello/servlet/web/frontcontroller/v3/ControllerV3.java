package hello.servlet.web.frontcontroller.v3;

import hello.servlet.web.frontcontroller.ModelView;

import java.util.Map;

public interface ControllerV3 {

    ModelView process(Map<String, String> paramMap);
    // 컨트롤러가 Servlet 기술을 사용X(종속X)
    // -> HttpServletRequest가 제공하는 파라미터는 프론트 컨트롤러가 paramMap에 담아서 호출해줌

    // request 객체를 Model로 사용안했기 때문에 별도의 Model 객체를 만들어서 반환
    // View -> ModelView (뷰 이름과 뷰에 전달할 Model 데이터를 포함하는 Modelview 객체를 반환)
}

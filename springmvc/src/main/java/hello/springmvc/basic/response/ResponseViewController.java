package hello.springmvc.basic.response;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ResponseViewController {

    @RequestMapping("/response-view-v1")
    public ModelAndView responseViewV1() {
        ModelAndView mav = new ModelAndView("response/hello")
                .addObject("data", "hello!");

        return mav;
    }

    // "@ResponseBody, HttpEntity"있으면 => 뷰 리졸버 실행안하고 HTTP 메시지 바디에 response/hello 입력
    @RequestMapping("/response-view-v2")
    public String responseViewV2(Model model) {

        model.addAttribute("data", "hello!!");
        return "response/hello";    // 뷰의 논리적인 이름
    }

    // 권장 X (HTTP 메시지 바디를 처리하는 파라미터 없으면 요청 URL을 참고해서 논리 뷰 이름으로 사용)
    @RequestMapping("/response/hello")
    public void responseViewV3(Model model) {

        model.addAttribute("data", "hello!!");
    }
}

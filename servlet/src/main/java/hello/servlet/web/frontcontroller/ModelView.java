package hello.servlet.web.frontcontroller;

import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;
import java.util.Map;

@Getter @Setter
public class ModelView {

    // 뷰의 이름과 뷰를 렌더링할 때 필요한 model 객체
    private String viewName;
    private Map<String, Object> model = new HashMap<>();
    // -> 컨트롤러에서 뷰에 필요한 데이터를 key, value로 넣어주면 된다

    public ModelView(String viewName) {
        this.viewName = viewName;
    }
}

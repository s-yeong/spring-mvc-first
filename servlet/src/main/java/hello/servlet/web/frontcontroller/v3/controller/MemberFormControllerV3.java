package hello.servlet.web.frontcontroller.v3.controller;

import hello.servlet.web.frontcontroller.ModelView;
import hello.servlet.web.frontcontroller.v3.ControllerV3;

import java.util.Map;

public class MemberFormControllerV3 implements ControllerV3 {

    @Override
    public ModelView process(Map<String, String> paramMap) {

        // 논리적 이름만!! <-> 실제 물리적인 이름은 프론트 컨트롤러에서 처리
        return new ModelView("new-form");
    }
}

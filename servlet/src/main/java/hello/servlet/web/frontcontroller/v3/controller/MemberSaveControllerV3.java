package hello.servlet.web.frontcontroller.v3.controller;

import hello.servlet.domain.member.Member;
import hello.servlet.domain.member.MemberRepository;
import hello.servlet.web.frontcontroller.ModelView;
import hello.servlet.web.frontcontroller.v3.ControllerV3;

import java.util.Map;

public class MemberSaveControllerV3 implements ControllerV3 {

    private MemberRepository memberRepository = MemberRepository.getInstance();

    @Override
    public ModelView process(Map<String, String> paramMap) {

        // 파라미터 정보는 map에 담겨있다. map에서 필요한 요청 파라미터를 조회하면 된다!
//        String username = request.getParameter("username");
        String username = paramMap.get("username");

//        int age = Integer.parseInt(request.getParameter("age"));
        int age = Integer.parseInt(paramMap.get("age"));

        Member member = new Member(username, age);
        memberRepository.save(member);

        // Model을 request.setAttribute()를 통해 데이터를 저장하고 뷰에 전달했음
//        request.setAttribute("member", member);

        // -> 별도의 Model 필요 -> ModelView.getModel();
        ModelView mv = new ModelView("save-result");
        mv.getModel().put("member", member);
        return mv;
    }
}

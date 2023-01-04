package hello.servlet.web.springmvc.v3;

import hello.servlet.domain.member.Member;
import hello.servlet.domain.member.MemberRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import java.util.List;

@Controller
@RequestMapping("/springmvc/v3/members")
public class SpringMemberControllerV3 {

    /**
     * Model 도입
     * ViewName 직접 반환
     * @RequestParam 사용
     * @RequestMapping -> @GetMapping, @PostMapping (HTTP 메서드 구분)
     */
    private MemberRepository memberRepository = MemberRepository.getInstance();

    @GetMapping("/new-form")
    public String newform() {
        return "new-form";
    }


    @GetMapping
    public String members(Model model) {

        List<Member> members = memberRepository.findAll();

        model.addAttribute("members", members);
        return "members";
    }

    @PostMapping("/save")
    public String save(@RequestParam("username") String username, @RequestParam("age") int age,
                       Model model) {

        // HTTP 요청 파라미터를 RequestParam으로 받을 수 있음(타입 캐스팅도 자동으로 해줌)
        // @RequestParam("username")은 request.getParameter("username")과 거의 같은 코드

        Member member = new Member(username, age);
        memberRepository.save(member);

        model.addAttribute("member", member);
        return "save-result";
    }

}

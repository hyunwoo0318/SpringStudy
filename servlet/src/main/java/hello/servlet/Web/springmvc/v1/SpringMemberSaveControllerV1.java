package hello.servlet.Web.springmvc.v1;

import hello.servlet.Web.frontcontroller.ModelView;
import hello.servlet.domain.member.Member;
import hello.servlet.domain.member.MemberRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

@Controller
public class SpringMemberSaveControllerV1 {

    private MemberRepository memberRepository = MemberRepository.getInstance();

    //Frontcontroller에서 모든 정보를 넣어서 process를 실행하므로 위 구현체에서는 꺼내서 쓰기만하면됨.
    @RequestMapping("/springmvc/v1/members/save")
    public ModelAndView process(HttpServletRequest req, HttpServletResponse res) {
        String username = req.getParameter("username");
        int age = Integer.parseInt(req.getParameter("age"));

        Member member = new Member(username,age);
        memberRepository.save(member);

        ModelAndView mv = new ModelAndView("save-result");
        mv.addObject("member", member);
        return mv;
    }
}

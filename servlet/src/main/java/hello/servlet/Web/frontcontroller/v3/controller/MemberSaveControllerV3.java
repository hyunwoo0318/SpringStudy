package hello.servlet.Web.frontcontroller.v3.controller;

import hello.servlet.Web.frontcontroller.ModelView;
import hello.servlet.Web.frontcontroller.v3.ControllerV3;
import hello.servlet.domain.member.Member;
import hello.servlet.domain.member.MemberRepository;
import org.springframework.ui.Model;

import java.util.Map;

public class MemberSaveControllerV3 implements ControllerV3 {

    private MemberRepository memberRepository = MemberRepository.getInstance();

    //Frontcontroller에서 모든 정보를 넣어서 process를 실행하므로 위 구현체에서는 꺼내서 쓰기만하면됨.
    @Override
    public ModelView process(Map<String, String> paramMap) {
        String username = paramMap.get("username");
        int age = Integer.parseInt(paramMap.get("age"));

        Member member = new Member(username, age);
        memberRepository.save(member);

        //viewname을 넣어줌 (= 논리주소)
        ModelView mv = new ModelView("save-result");
        //model객체를 넣어줌(member)
        mv.getModel().put("member", member);
        return mv;
    }
}

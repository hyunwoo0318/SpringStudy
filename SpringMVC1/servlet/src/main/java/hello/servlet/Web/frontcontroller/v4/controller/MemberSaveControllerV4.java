package hello.servlet.Web.frontcontroller.v4.controller;


import hello.servlet.Web.frontcontroller.v4.ControllerV4;
import hello.servlet.domain.member.Member;
import hello.servlet.domain.member.MemberRepository;

import java.util.Map;

public class MemberSaveControllerV4 implements ControllerV4 {

    private MemberRepository memberRepository = MemberRepository.getInstance();

    //Frontcontroller에서 모든 정보를 넣어서 process를 실행하므로 위 구현체에서는 꺼내서 쓰기만하면됨.
    @Override
    public String process(Map<String, String> paramMap, Map<String, Object> model) {

        String username = paramMap.get("username");
        int age = Integer.parseInt(paramMap.get("age"));

        Member member = new Member(username, age);
        memberRepository.save(member);

       model.put("member", member);
        return "save-result";
    }
}
